package player.userinputhandler.commands.deletehero;

import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;
import player.userinputhandler.commands.db.Character;

import java.util.List;

import static player.userinputhandler.enums.Processes.DELETE_HERO;
import static player.userinputhandler.enums.Steps.CONFIRMATION;
import static player.userinputhandler.enums.Steps.SELECT_A_HERO_TO_BE_DELETED;

public class DeleteHero {
    final static CharacterDao characterJpaDao = new CharacterDaoImpl();

    public static Response deleteHero() {
        State newState = new State(DELETE_HERO, CONFIRMATION, null);
        return new Response(newState, "Are you sure you want to delete your character? Note, that once they are dismissed, they will be gone forever, no coming back\nAnswer Yes or No");
    }

    public static Response heroDeletionAnswer(State state, Long chatId, String userAnswer) {
        Response response;
        State newState;
        switch (state.getStepId()) {
            case CONFIRMATION:
                switch (userAnswer) {
                    case "Yes":
                        List<Character> userCharacters = characterJpaDao.findByChatId(chatId);
                        List<String> outputList = userCharacters.stream()
                                .map(character -> String.format("Character ID: %d, Name: %s \n", character.getId(), character.getDndCharacter().getCharacterName()))
                                .toList();

                        newState = new State(DELETE_HERO, SELECT_A_HERO_TO_BE_DELETED, state.getDndCharacter());
                        response = new Response(newState, "Enter the ID of the character you want to delete: \n" + outputList);
                        break;
                    case "No":
                        response = new Response(null, "Alright, dismissal has been cancelled");
                        break;
                    default:
                        newState = new State(DELETE_HERO, CONFIRMATION, state.getDndCharacter());
                        response = new Response(newState, "Sorry, I cannot understand your input. Please, enter Yes or No");
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
