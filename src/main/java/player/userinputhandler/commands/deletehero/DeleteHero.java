package player.userinputhandler.commands.deletehero;

import lombok.AllArgsConstructor;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;

import java.util.List;

import static java.lang.String.format;
import static player.userinputhandler.commands.CommandTexts.newHeroCommand;
import static player.userinputhandler.commands.deletehero.Options.getYesOrNoOptions;
import static player.userinputhandler.enums.Processes.DELETE_HERO;
import static player.userinputhandler.enums.Steps.CONFIRMATION;
import static player.userinputhandler.enums.Steps.SELECT_A_HERO_TO_BE_DELETED;
@AllArgsConstructor
public class DeleteHero {

    final CharacterDao characterJpaDao;

    public Response deleteHero() {
        State newState = new State(DELETE_HERO, CONFIRMATION, null);
        return new Response(newState, "Are you sure you want to delete your character? Note, that once they are dismissed, they will be gone forever, no coming back", getYesOrNoOptions());
    }

    public Response heroDeletionAnswer(State state, Long chatId, String userAnswer) {
        Response response;
        State newState;
        switch (state.getStepId()) {
            case CONFIRMATION:
                switch (userAnswer.toLowerCase().trim()) {
                    case "yes":
                        List<Character> userCharacters = characterJpaDao.findByChatId(chatId);
                        if (userCharacters.isEmpty()) {
                            response = new Response(null, format("You don't have any characters to delete. Create one by using %s command", newHeroCommand));
                            break;
                        }
                        List<String> outputList = userCharacters.stream()
                                .map(character -> format("Character ID: %d, Name: %s \n", character.getId(), character.getDndCharacter().getCharacterName()))
                                .toList();

                        newState = new State(DELETE_HERO, SELECT_A_HERO_TO_BE_DELETED, state.getDndCharacter());
                        response = new Response(newState, "Enter the ID of the character you want to delete: \n" + outputList);
                        break;
                    case "no":
                        response = new Response(null, "Alright, dismissal has been cancelled");
                        break;
                    default:
                        newState = new State(DELETE_HERO, CONFIRMATION, state.getDndCharacter());
                        response = new Response(newState, "Sorry, I cannot understand your input. Please, enter Yes or No", getYesOrNoOptions());
                        break;
                }
                break;
            case SELECT_A_HERO_TO_BE_DELETED:
                try {
                    characterJpaDao.deleteByCharacterId(Integer.valueOf(userAnswer), chatId);
                    response = new Response(null, "Alright, your hero has been dismissed");
                } catch (Exception ex) {
                    newState = new State(DELETE_HERO, SELECT_A_HERO_TO_BE_DELETED, state.getDndCharacter());
                    response = new Response(newState, "Couldn't find a hero with this ID. Please enter an available one.");
                }
                break;
            default:
                newState = new State(DELETE_HERO, CONFIRMATION, state.getDndCharacter());
                response = new Response(newState, "Wrong deletion step ID. Redirecting back to the beginning of the character deletion");
                break;
        }
        return response;
    }
}
