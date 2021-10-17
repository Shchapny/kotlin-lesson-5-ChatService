fun main() {
    ChatService.createChat(1, 10)
    ChatService.createChat(1, 5)
    ChatService.createChat(2, 4)
    ChatService.createChat(2, 1)
    ChatService.createChat(3, 2)
    ChatService.sendMessage(1,10, "Привет")
    ChatService.sendMessage(1,5, "Я с трудом понимаю!")
    ChatService.sendMessage(2,4, "Какие планы на выходные?")
    ChatService.sendMessage(3,2, "Я знаю где ты был прошлой ночью!")
    ChatService.getChats(2)
    ChatService.deleteChat(3)
    ChatService.deleteMessage(1)
    ChatService.getCountUnreadChats(1)
    ChatService.getMessage(1, 10, 2)

}