package player.userinputhandler.commands.printhero;

import lombok.AllArgsConstructor;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;

import java.io.File;
import java.util.List;

import static java.lang.String.format;
import static player.userinputhandler.commands.CommandTexts.newHeroCommand;
import static player.userinputhandler.enums.Processes.PRINT_HERO;
import static player.userinputhandler.enums.Steps.PRINT_PDF;

@AllArgsConstructor
public class PrintHero {
    private final CharacterDao characterJpaDao;
    private final PDFCreator createPDF;

    public Response printHero(Long chatId) {
        try {
            List<Character> userCharacters = characterJpaDao.findByChatId(chatId);
            if (userCharacters.isEmpty()) {
                return new Response(null, format("You don't have any characters to print. Create one by using %s command", newHeroCommand));
            }
            List<String> outputList = userCharacters.stream()
                    .map(character -> String.format("Character ID: %d, Name: %s \n", character.getId(), character.getDndCharacter().getCharacterName()))
                    .toList();

            State newState = new State(PRINT_HERO, PRINT_PDF, null);
            return new Response(newState, "Ok, let's print your hero. First, enter the ID of the character you want to get a PDF version of\n" + outputList);
        } catch (Exception e) {
            State newState = new State(PRINT_HERO, null, null);
            return new Response(newState, "Something went wrong when I was getting the list of your characters");
        }
    }

    public Response heroPrintingAnswer(State state, String userAnswer, Long chatId) {
        Response response;
        State newState;

        if (state.getStepId() == PRINT_PDF) {
            try {
                File file = createPDF.createPDF(Integer.valueOf(userAnswer), chatId);
                response = new Response(null, null, file);
            } catch (Exception ex) {
                newState = new State(PRINT_HERO, PRINT_PDF, state.getDndCharacter());
                response = new Response(newState, "Couldn't find a hero with this ID. Please enter an available one.");
            }
        } else {
            newState = new State(PRINT_HERO, PRINT_PDF, state.getDndCharacter());
            response = new Response(newState, "Wrong printing step ID. Redirecting back to the character printing");
        }
        return response;
    }
}

