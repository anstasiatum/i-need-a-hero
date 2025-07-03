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
        Long chatId = update.message().chat().id();
        State chatState = statesByChatId.get(chatId);
        Response response = null;
        if (chatState == null) {
            response = switch (update.message().text()) {
                case "/newhero" -> createNewHero();
                case "/dismisshero" -> deleteHero();
                case "/printhero" -> printHero(chatId);
                default -> {
                    String textAnswer = """
                            Sorry, I don't understand your request. Here is my list of commands:
                             /newhero
                             /printhero
                             /dismisshero
                            """;
                    yield new Response(textAnswer);
                }
            };
        } else {
            response = switch (chatState.getProcess()) {
                case CREATE_HERO -> heroCreationAnswer(chatState, chatId, update.message().text());
                case DELETE_HERO -> heroDeletionAnswer(chatState, chatId, update.message().text());
                case PRINT_HERO -> heroPrintingAnswer(chatState, update.message().text(), chatId);
            };
        }

        if (response.getState() != null) {
            statesByChatId.put(chatId, response.getState());
        }

        return new BotAnswer(response.getTextAnswer(), response.getFile(), response.isHasFixedOptions(), response.getOptionTexts());
    }
}
