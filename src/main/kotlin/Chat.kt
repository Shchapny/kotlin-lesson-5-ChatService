data class Chat(
    val chatId: Int = 0,
    val senderId: Int = 0,
    val recipientId: Int = 0,
    val messages: MutableList<Message> = mutableListOf(),
    val readOrUnread: Boolean = true,
    var empty: Boolean = false
)
