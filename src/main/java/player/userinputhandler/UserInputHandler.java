package player.userinputhandler;

import lombok.AllArgsConstructor;
import player.userinputhandler.commands.createnewhero.CreateNewHero;
import player.userinputhandler.commands.deletehero.DeleteHero;
import player.userinputhandler.commands.printhero.PrintHero;

import static java.lang.String.format;
import static player.userinputhandler.commands.CommandTexts.dismissHeroCommand;
import static player.userinputhandler.commands.CommandTexts.newHeroCommand;
import static player.userinputhandler.commands.CommandTexts.printHeroCommand;

@AllArgsConstructor
public class UserInputHandler {
    private final StateHolder stateHolder;
    private final DeleteHero deleteHero;
    private final CreateNewHero createHero;
    private final PrintHero printHero;


    public BotAnswer handleUserInput(Long chatId, String userAnswer) {

        boolean isInvalidCommand = false;
        State chatState = stateHolder.getStateByChatId(chatId);
        Response response;
        if (chatState == null && !userAnswer.equals(newHeroCommand) && !userAnswer.equals(dismissHeroCommand) && !userAnswer.equals(printHeroCommand)) {
            String textAnswer = format("Sorry, I don't understand your request. Here is my list of commands:\n%s\n%s\n%s\n", newHeroCommand, dismissHeroCommand, printHeroCommand);
            isInvalidCommand = true;
            response = new Response(textAnswer);
        } else if (userAnswer.equals(newHeroCommand)) {
            response = createHero.createNewHero();
        } else if (userAnswer.equals(dismissHeroCommand)) {
            response = deleteHero.deleteHero();
        } else if (userAnswer.equals(printHeroCommand)) {
            response = printHero.printHero(chatId);
        } else {
            response = switch (chatState.getProcess()) {
                case CREATE_HERO -> createHero.heroCreationAnswer(chatState, chatId, userAnswer);
                case DELETE_HERO -> deleteHero.heroDeletionAnswer(chatState, chatId, userAnswer);
                case PRINT_HERO -> printHero.heroPrintingAnswer(chatState, userAnswer, chatId);
            };
        }

        if (!isInvalidCommand) {
            stateHolder.updateStateByChatId(chatId, response.getState());
        }

        return new BotAnswer(response.getTextAnswer(), response.getFile(), response.isHasFixedOptions(), response.getOptionTexts());
    }
}
