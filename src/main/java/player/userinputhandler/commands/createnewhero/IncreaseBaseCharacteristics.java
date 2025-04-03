package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.enums.Steps;

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

public class IncreaseBaseCharacteristics {
    public static Response increaseBaseCharacteristics(DndCharacter dndCharacter, Steps currentStep, String userAnswer) {
        Response response = null;
        State newState;
        final String strength = "strength";
        final String dexterity = "dexterity";
        final String constitution = "constitution";
        final String intelligence = "intelligence";
        final String wisdom = "wisdom";
        final String charisma = "charisma";
        switch (currentStep) {
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF:
                switch (userAnswer.toLowerCase().trim()) {
                    case strength:
                        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case dexterity:
                        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case constitution:
                        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case intelligence:
                        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case wisdom:
                        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case charisma:
                        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, wrongInput);
                        break;
                }
                break;
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                switch (userAnswer.toLowerCase().trim()) {
                    case strength:
                        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case dexterity:
                        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case constitution:
                        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case intelligence:
                        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case wisdom:
                        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    case charisma:
                        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore);
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, wrongInput);
                        break;
                }
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF:
                switch (userAnswer.toLowerCase().trim()) {
                    case strength:
                        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case dexterity:
                        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case constitution:
                        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case intelligence:
                        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case wisdom:
                        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case charisma:
                        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, wrongInput);
                        break;
                }
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                switch (userAnswer.toLowerCase().trim()) {
                    case strength:
                        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case dexterity:
                        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case constitution:
                        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case intelligence:
                        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case wisdom:
                        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    case charisma:
                        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency);
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, wrongInput);
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
