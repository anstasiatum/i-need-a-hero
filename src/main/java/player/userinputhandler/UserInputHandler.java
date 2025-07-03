package player.userinputhandler;


import com.pengrad.telegrambot.model.Update;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static player.userinputhandler.commands.createnewhero.CreateNewHero.createNewHero;
import static player.userinputhandler.commands.createnewhero.CreateNewHero.heroCreationAnswer;
import static player.userinputhandler.commands.deletehero.DeleteHero.deleteHero;
import static player.userinputhandler.commands.deletehero.DeleteHero.heroDeletionAnswer;
import static player.userinputhandler.commands.printhero.PrintHero.heroPrintingAnswer;
import static player.userinputhandler.commands.printhero.PrintHero.printHero;

public class UserInputHandler {
    private static final Map<Long, State> statesByChatId = new HashMap<>();

    public static BotAnswer handleUserInput(Update update) {
        String textAnswer;
        File file = null;
        Long chatId = update.message().chat().id();
        State chatState = statesByChatId.get(chatId);
        Response response;
        if (chatState == null) {
            switch (update.message().text()) {
                case "/newhero":
                    response = createNewHero();
                    statesByChatId.put(chatId, response.getState());
                    textAnswer = response.getTextAnswer();
                    break;
                case "/dismisshero":
                    response = deleteHero();
                    statesByChatId.put(chatId, response.getState());
                    textAnswer = response.getTextAnswer();
                    break;
                case "/printhero":
                    response = printHero(chatId);
                    statesByChatId.put(chatId, response.getState());
                    textAnswer = response.getTextAnswer();
                    break;
                default:
                    textAnswer = """
                            Sorry, I don't understand your request. Here is my list of commands:
                             /newhero
                             /printhero
                             /dismisshero
                            """;
                    break;
            }
        } else {
            switch (chatState.getProcess()) {
                case CREATE_HERO:
                    response = heroCreationAnswer(chatState, chatId, update.message().text());
                    textAnswer = response.getTextAnswer();
                    statesByChatId.put(chatId, response.getState());
                    break;
                case DELETE_HERO:
                    response = heroDeletionAnswer(chatState, chatId, update.message().text());
                    textAnswer = response.getTextAnswer();
                    statesByChatId.put(chatId, response.getState());
                    break;
                case PRINT_HERO:
                    response = heroPrintingAnswer(chatState, update.message().text(), chatId);
                    file = response.getFile();
                    textAnswer = response.getTextAnswer();
                    statesByChatId.put(chatId, response.getState());
                    break;
                default:
                    textAnswer = "Wrong process type";
                    break;
            }
        }
        return new BotAnswer(textAnswer, file);
    }
}
