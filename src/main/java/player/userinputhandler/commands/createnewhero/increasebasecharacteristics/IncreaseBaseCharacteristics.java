package player.userinputhandler.commands.createnewhero.increasebasecharacteristics;

import lombok.AllArgsConstructor;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.enums.Steps;

import static player.userinputhandler.commands.createnewhero.Options.getAllSkillOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptionsWithoutSpecified;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseSecondAbilityScore;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseSkillProficiency;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN;

@AllArgsConstructor
public class IncreaseBaseCharacteristics {
    private IncrementAbility incrementAbility;

    public  Response increaseBaseCharacteristics(DndCharacter dndCharacter, Steps currentStep, String userAnswer) {
        Response response;
        State newState;
        String normalizedInput = userAnswer.toLowerCase().trim();
        Characteristics chosenCharacteristics = Characteristics.fromString(normalizedInput);

        switch (currentStep) {
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF:
                if (chosenCharacteristics != null) {
                    incrementAbility.incrementAbility(chosenCharacteristics, dndCharacter);
                    newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                    response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(chosenCharacteristics));
                } else {
                    newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                    response = new Response(newState, wrongInput, getBasicAbilityOptions());
                }
                break;
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                if (chosenCharacteristics != null) {
                    incrementAbility.incrementAbility(chosenCharacteristics, dndCharacter);
                    newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                    response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(chosenCharacteristics));
                } else {
                    newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                    response = new Response(newState, wrongInput, getBasicAbilityOptions());
                }
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF:
                if (chosenCharacteristics != null) {
                    incrementAbility.incrementAbility(chosenCharacteristics, dndCharacter);
                    newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                    response = new Response(newState, chooseSkillProficiency, getAllSkillOptions());
                } else {
                    newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                    response = new Response(newState, wrongInput, getBasicAbilityOptions());
                }
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                if (chosenCharacteristics != null) {
                    incrementAbility.incrementAbility(chosenCharacteristics, dndCharacter);
                    newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                    response = new Response(newState, chooseSkillProficiency, getAllSkillOptions());
                } else {
                    newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                    response = new Response(newState, wrongInput, getBasicAbilityOptions());
                    break;
                }
                break;
            default:
                newState = new State(CREATE_HERO, CHOOSE_RACE, dndCharacter);
                response = new Response(newState, "Wrong step");
                break;
        }
        return response;
    }
}
