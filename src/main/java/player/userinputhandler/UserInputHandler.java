package player.userinputhandler;

import com.pengrad.telegrambot.model.Update;
import player.userinputhandler.commands.createnewhero.CreateNewHero;
import player.userinputhandler.commands.db.CharacterDaoImpl;
import player.userinputhandler.commands.deletehero.DeleteHero;
import player.userinputhandler.commands.printhero.PrintHero;

import java.util.HashMap;
import java.util.Map;

public class UserInputHandler {
    private static final Map<Long, State> statesByChatId = new HashMap<>();
    static DeleteHero deleteHero = new DeleteHero(new CharacterDaoImpl());
    static CreateNewHero createHero = new CreateNewHero(new CharacterDaoImpl());
    static PrintHero printHero = new PrintHero(new CharacterDaoImpl());

    public static BotAnswer handleUserInput(Update update) {
        boolean hasOptionMismatch = false;
        Long chatId = update.message().chat().id();
        State chatState = statesByChatId.get(chatId);
        Response response;
        if (chatState == null) {
            response = switch (update.message().text()) {
                case "/newhero" -> createHero.createNewHero();
                case "/dismisshero" -> deleteHero.deleteHero();
                case "/printhero" -> printHero.printHero(chatId);
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
                case CREATE_HERO -> createHero.heroCreationAnswer(chatState, chatId, update.message().text());
                case DELETE_HERO -> deleteHero.heroDeletionAnswer(chatState, chatId, update.message().text());
                case PRINT_HERO -> printHero.heroPrintingAnswer(chatState, update.message().text(), chatId);
            };
        }

        if (!hasOptionMismatch) {
            statesByChatId.put(chatId, response.getState());
        }

        return new BotAnswer(response.getTextAnswer(), response.getFile(), response.isHasFixedOptions(), response.getOptionTexts());
    }
}
