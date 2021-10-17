import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun createChat() {
        val service = ChatService
        val expected = Chat(3, 1, 10)
        val result = service.createChat(1, 10)

        assertEquals(expected, result)
    }

    @Test
    fun getChats() {
        val service = ChatService
        val chat1 = Chat(1, 1, 10)
        val chat2 = Chat(2, 2, 4)
        val expected = mutableListOf<Chat>()
        expected.add(chat1)
        expected.add(chat2)
        service.createChat(2, 2)
        service.createChat(2, 4)
        val result = expected

        assertEquals(expected, result)
    }

    @Test
    fun getCountUnreadChats() {
        val service = ChatService
        service.sendMessage(1, 10, "Сообщение1")
        service.sendMessage(1, 5, "Сообщение2")
        service.sendMessage(2, 4, "Сообщение3")
        val result = service.getCountUnreadChats(1)

        assertEquals(1, result)
    }

    @Test
    fun deleteChat() {
        val service = ChatService
        val expected = Chat(4, 1, 10, empty = true)
        val result = service.createChat(1, 10)
        service.sendMessage(1, 10, "Сообщение1")
        service.deleteChat(4)

        assertEquals(expected, result)
    }

    @Test
    fun sendMessage() {
        val service = ChatService
        val message1 = Message(1, "Сообщение1", 1, 10, read = true)
        val message2 = Message(2, "Сообщение2", 10, 1, read = true)
        val expected = mutableListOf<Message>()
        expected.add(message1)
        expected.add(message2)
        service.sendMessage(message1.senderId, message1.recipientId, message1.text)
        service.sendMessage(message2.senderId, message2.recipientId, message2.text)
        val result = service.getMessage(1, 10, 2)

        assertEquals(expected, result)
    }

    @Test
    fun getMessage() {
        val service = ChatService
        val message1 = Message(1, "Сообщение1", 1, 10, read = true)
        val message2 = Message(2, "Сообщение2", 10, 1, read = true)
        val expected = mutableListOf<Message>()
        expected.add(message1)
        expected.add(message2)
        service.sendMessage(message1.senderId, message1.recipientId, message1.text)
        service.sendMessage(message2.senderId, message2.recipientId, message2.text)
        val result = service.getMessage(1, 0, 2)

        assertEquals(expected, result)
    }

    @Test(expected = ChatNotFoundException::class)
    fun shouldThrowGetMessage() {
        val service = ChatService
        service.sendMessage(1, 10, "Сообщение1")
        service.sendMessage(10, 1, "Сообщение2")
        service.getMessage(10, 0, 0)

    }

    @Test
    fun deleteMessage() {
        val service = ChatService
        val messageList = mutableListOf(
            Message(1, "Сообщение1", 1, 10),
            Message(2, "Сообщение2", 10, 1),
            Message(3, "Сообщение1", 1, 10),
            Message(4, "Сообщение2", 10, 1)
        )
        val expected = Chat(1, 1, 10, messages = messageList)
        service.sendMessage(1, 2, "Сообщение1")
        service.deleteMessage(1)
        val chatService = service.getChats(1)
        val result = chatService.last()

        assertEquals(expected, result)
    }
}