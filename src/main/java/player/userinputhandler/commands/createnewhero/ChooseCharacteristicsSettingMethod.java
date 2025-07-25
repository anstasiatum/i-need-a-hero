package player.userinputhandler.commands.createnewhero;

import lombok.AllArgsConstructor;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.characteristicsgenerator.BaseCharacteristicsValuesGenerator;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.userinputhandler.commands.createnewhero.Options.getCharacteristicsRollingMethodOptions;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ROLLING_CHARACTERISTICS_METHOD;
import static player.userinputhandler.enums.Steps.SET_STRENGTH;

@AllArgsConstructor
public class ChooseCharacteristicsSettingMethod {

    private final BaseCharacteristicsValuesGenerator generateCharacteristics;

    public Response chooseCharacteristicsSettingMethod(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        response = switch (userAnswer.toLowerCase().trim()) {
            case "i'll roll myself" -> {
                newState = new State(CREATE_HERO, SET_STRENGTH, dndCharacter);
                yield new Response(newState, "Set strength:");
            }
            case "roll for me, bot" -> {
                newState = new State(CREATE_HERO, SET_STRENGTH, dndCharacter);
                yield new Response(newState, "Here is your result: \n" + generateCharacteristics.generateCharacteristics() + "\n Set strength:");
            }
            default -> {
                newState = new State(CREATE_HERO, CHOOSE_ROLLING_CHARACTERISTICS_METHOD, dndCharacter);
                yield new Response(newState, "Choose \"Roll for me, bot\" and the bot will roll the dice or \"I'll roll myself\" to roll the dice yourself", getCharacteristicsRollingMethodOptions());
            }
        };
        return response;
    }
}
