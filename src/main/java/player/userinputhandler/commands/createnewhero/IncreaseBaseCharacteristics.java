package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.enums.Steps;

import static player.dndcharacter.dndcharacterenums.Characteristics.CHARISMA;
import static player.dndcharacter.dndcharacterenums.Characteristics.CONSTITUTION;
import static player.dndcharacter.dndcharacterenums.Characteristics.DEXTERITY;
import static player.dndcharacter.dndcharacterenums.Characteristics.INTELLIGENCE;
import static player.dndcharacter.dndcharacterenums.Characteristics.STRENGTH;
import static player.dndcharacter.dndcharacterenums.Characteristics.WISDOM;
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

public class IncreaseBaseCharacteristics {
    public static Response increaseBaseCharacteristics(DndCharacter dndCharacter, Steps currentStep, String userAnswer) {
        Response response;
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
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(STRENGTH));
                        break;
                    case dexterity:
                        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(DEXTERITY));
                        break;
                    case constitution:
                        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(CONSTITUTION));
                        break;
                    case intelligence:
                        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(INTELLIGENCE));
                        break;
                    case wisdom:
                        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(WISDOM));
                        break;
                    case charisma:
                        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(CHARISMA));
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, wrongInput, getBasicAbilityOptions());
                        break;
                }
                break;
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                switch (userAnswer.toLowerCase().trim()) {
                    case strength:
                        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(STRENGTH));
                        break;
                    case dexterity:
                        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(DEXTERITY));
                        break;
                    case constitution:
                        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(CONSTITUTION));
                        break;
                    case intelligence:
                        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(INTELLIGENCE));
                        break;
                    case wisdom:
                        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(WISDOM));
                        break;
                    case charisma:
                        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(CHARISMA));
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, wrongInput, getBasicAbilityOptions());
                        break;
                }
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF:
                switch (userAnswer.toLowerCase().trim()) {
                    case strength:
                        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(STRENGTH));
                        break;
                    case dexterity:
                        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(DEXTERITY));
                        break;
                    case constitution:
                        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(CONSTITUTION));
                        break;
                    case intelligence:
                        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(INTELLIGENCE));
                        break;
                    case wisdom:
                        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(WISDOM));
                        break;
                    case charisma:
                        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(CHARISMA));
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                        response = new Response(newState, wrongInput, getBasicAbilityOptions());
                        break;
                }
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                switch (userAnswer.toLowerCase().trim()) {
                    case strength:
                        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(STRENGTH));
                        break;
                    case dexterity:
                        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(DEXTERITY));
                        break;
                    case constitution:
                        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(CONSTITUTION));
                        break;
                    case intelligence:
                        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(INTELLIGENCE));
                        break;
                    case wisdom:
                        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(WISDOM));
                        break;
                    case charisma:
                        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
                        response = new Response(newState, chooseSkillProficiency, getBasicAbilityOptionsWithoutSpecified(CHARISMA));
                        break;
                    default:
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
