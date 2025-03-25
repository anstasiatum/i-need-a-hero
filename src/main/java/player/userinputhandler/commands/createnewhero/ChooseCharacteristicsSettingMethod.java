package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.dndcharacter.characteristicsgenerator.BaseCharacteristicsValuesGenerator.generateCharacteristics;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ROLLING_CHARACTERISTICS_METHOD;
import static player.userinputhandler.enums.Steps.SET_STRENGTH;

public class ChooseCharacteristicsSettingMethod {
    public static Response chooseCharacteristicsSettingMethod(String userAnswer, DndCharacter dndCharacter) {
        Response response = null;
        State newState;
        response = switch (userAnswer) {
            case "A" -> {
                newState = new State(CREATE_HERO, SET_STRENGTH, dndCharacter);
                yield new Response(newState, "Set strength:");
            }
            case "B" -> {
                newState = new State(CREATE_HERO, SET_STRENGTH, dndCharacter);
                yield new Response(newState, "Here is your result: \n" + generateCharacteristics() + "\n Set strength:");
            }
            default -> {
                newState = new State(CREATE_HERO, CHOOSE_ROLLING_CHARACTERISTICS_METHOD, dndCharacter);
                yield new Response(newState, "Choose A or B");
            }
        };
        return response;
    }
}
