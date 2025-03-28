package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.enums.Steps;

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
        String chooseSecondAbilityScore = "Enter another ability that will be increased by 1 (Strength, Wisdom etc)";
        String wrongCharacteristics = "Sorry, I don't understand. Maybe there is a typo?";
        String allSkills = """
                Survival
                Stealth
                Sleight of hand
                Religion
                Persuasion
                Performance
                Perception
                Nature
                Medicine
                Investigation
                Intimidation
                Insight
                History
                Deception
                Athletics
                Arcana
                Animal handling
                Acrobatics
                """;
        final String strength = "strength";
        final String dexterity = "dexterity";
        final String constitution = "constitution";
        final String intelligence = "intelligence";
        final String wisdom = "wisdom";
        final String charisma = "charisma";

        String chooseSkillProficiency = "Enter a skill that your character will be proficient in: " + allSkills;
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
                        response = new Response(newState, wrongCharacteristics);
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
                        response = new Response(newState, wrongCharacteristics);
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
                        response = new Response(newState, wrongCharacteristics);
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
                        response = new Response(newState, wrongCharacteristics);
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
