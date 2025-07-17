package player.userinputhandler;

import com.pengrad.telegrambot.model.Update;
import player.userinputhandler.commands.db.CharacterDaoImpl;
import player.userinputhandler.commands.deletehero.DeleteHero;

import java.util.HashMap;
import java.util.Map;

import static player.userinputhandler.commands.createnewhero.CreateNewHero.createNewHero;
import static player.userinputhandler.commands.createnewhero.CreateNewHero.heroCreationAnswer;
import static player.userinputhandler.commands.printhero.PrintHero.heroPrintingAnswer;
import static player.userinputhandler.commands.printhero.PrintHero.printHero;

public class UserInputHandler {
    private static final Map<Long, State> statesByChatId = new HashMap<>();
    static DeleteHero deleteHero = new DeleteHero(new CharacterDaoImpl());

    public static BotAnswer handleUserInput(Update update) {
        boolean hasOptionMismatch = false;
        Long chatId = update.message().chat().id();
        State chatState = statesByChatId.get(chatId);
        Response response;
        if (chatState == null) {
            response = switch (update.message().text()) {
                case "/newhero" -> createNewHero();
                case "/dismisshero" -> deleteHero.deleteHero();
                case "/printhero" -> printHero(chatId);
                default -> {
                    String textAnswer = """
                            Sorry, I don't understand your request. Here is my list of commands:
                             /newhero
                             /printhero
                             /dismisshero
                            """;
                    hasOptionMismatch = true;
                    yield new Response(textAnswer);
                }
            };
        } else {
            response = switch (chatState.getProcess()) {
                case CREATE_HERO -> heroCreationAnswer(chatState, chatId, update.message().text());
                case DELETE_HERO -> deleteHero.heroDeletionAnswer(chatState, chatId, update.message().text());
                case PRINT_HERO -> heroPrintingAnswer(chatState, update.message().text(), chatId);
            };
        }

        if (!hasOptionMismatch) {
            statesByChatId.put(chatId, response.getState());
        }

        return new BotAnswer(response.getTextAnswer(), response.getFile(), response.isHasFixedOptions(), response.getOptionTexts());
    }
}
