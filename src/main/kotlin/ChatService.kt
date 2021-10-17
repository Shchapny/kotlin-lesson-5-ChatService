object ChatService {
    private val chats = mutableListOf<Chat>()
    private var chatId = 0
    private var messageId = 0

    fun createChat(senderId: Int, recipientId: Int): Chat {
        chatId++
        val chat = Chat(chatId, senderId, recipientId)
        chats.add(chat)
        return chats.last()
    }

    fun getChats(id: Int): List<Chat> {
        return chats
            .filter { (it.senderId == id || it.recipientId == id) && !it.empty }
    }

    fun getCountUnreadChats(userId: Int): Int {
        return chats
            .filter { (it.senderId == userId || it.recipientId == userId) && it.readOrUnread }.size
    }

    fun deleteChat(id: Int) {
        val chat = chats
            .find { it.chatId == id } ?: throw ChatNotFoundException("Чат не найден")
        chat.messages.clear()
        chat.empty = true
    }

    fun sendMessage(senderId: Int, recipientId: Int, text: String) {
        messageId++
        val message = Message(messageId, text, senderId, recipientId)
        val chat = chats
            .find {
                it.senderId == senderId ||
                        it.senderId == recipientId && it.recipientId == recipientId ||
                        it.recipientId == senderId
            } ?: createChat(senderId, recipientId)
        chat.messages.add(message)
    }

    fun getMessage(chatId: Int, messageId: Int, count: Int): List<Message> {
        val chat = chats
            .find { it.chatId == chatId } ?: throw ChatNotFoundException("Чат не найден")
        val message = chat.messages
            .takeLastWhile { messageId != it.messageId }
            .take(count)
        message.forEach { it.read = true }
        return message
    }

    fun deleteMessage(chatId: Int) {
        val chat = chats
            .find { it.chatId == chatId } ?: throw ChatNotFoundException("Чат не найден")
        if (chat.messages.size == 0) deleteChat(chatId) else chat.messages.removeLast()
    }
}