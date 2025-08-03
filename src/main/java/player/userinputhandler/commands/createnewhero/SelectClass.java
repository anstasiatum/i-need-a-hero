package player.userinputhandler.commands.createnewhero;

import lombok.AllArgsConstructor;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.characterclass.CharacterClassFactory;
import player.dndcharacter.characterclass.characterclasses.Barbarian;
import player.dndcharacter.characterclass.characterclasses.Bard;
import player.dndcharacter.characterclass.characterclasses.Cleric;
import player.dndcharacter.characterclass.characterclasses.Druid;
import player.dndcharacter.characterclass.characterclasses.Fighter;
import player.dndcharacter.characterclass.characterclasses.Monk;
import player.dndcharacter.characterclass.characterclasses.Paladin;
import player.dndcharacter.characterclass.characterclasses.Ranger;
import player.dndcharacter.characterclass.characterclasses.Rogue;
import player.dndcharacter.characterclass.characterclasses.Sorcerer;
import player.dndcharacter.characterclass.characterclasses.Warlock;
import player.dndcharacter.characterclass.characterclasses.Wizard;
import player.dndcharacter.dndcharacterenums.CharacterClass;
import player.dndcharacter.dndcharacterenums.Skill;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.BARBARIAN;
import static player.dndcharacter.dndcharacterenums.CharacterClass.BARD;
import static player.dndcharacter.dndcharacterenums.CharacterClass.CLERIC;
import static player.dndcharacter.dndcharacterenums.CharacterClass.DRUID;
import static player.dndcharacter.dndcharacterenums.CharacterClass.FIGHTER;
import static player.dndcharacter.dndcharacterenums.CharacterClass.MONK;
import static player.dndcharacter.dndcharacterenums.CharacterClass.PALADIN;
import static player.dndcharacter.dndcharacterenums.CharacterClass.RANGER;
import static player.dndcharacter.dndcharacterenums.CharacterClass.ROGUE;
import static player.dndcharacter.dndcharacterenums.CharacterClass.SORCERER;
import static player.dndcharacter.dndcharacterenums.CharacterClass.WARLOCK;
import static player.dndcharacter.dndcharacterenums.CharacterClass.WIZARD;
import static player.dndcharacter.dndcharacterenums.CharacterClass.getAllClasses;
import static player.userinputhandler.commands.createnewhero.Options.getClassOptions;
import static player.userinputhandler.commands.createnewhero.Options.getSkillOptions;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_BARBARIAN;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_BARD;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_CLERIC;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_DRUID;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_FIGHTER;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_MONK;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_PALADIN;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_RANGER;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_ROGUE;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_SORCERER;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_WARLOCK;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_WIZARD;

@AllArgsConstructor
public class SelectClass {
    private final CharacterClassFactory characterClassFactory;

    public Response selectClass(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        String chooseSkill = "Enter a skill your hero will be proficient in. Here is the list of available ones:\n";
        BuildAvailableProficiencySkillsWithoutApplied buildSkills = new BuildAvailableProficiencySkillsWithoutApplied();
        Set<Skill> finalAvailableSkills;
        CharacterClass characterClass;
        Response defaultResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Cannot understand your input. Here is the list of available classes: \n" + getAllClasses(), getClassOptions());

        try {
            characterClass = parseClass(userAnswer);
        } catch (IllegalArgumentException exception) {
            return defaultResponse;
        }
        switch (characterClass) {
            case BARBARIAN -> {
                Barbarian barbarian = (Barbarian) characterClassFactory.createClassFactory(BARBARIAN);
                barbarian.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Barbarian.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case BARD -> {
                Bard bard = (Bard) characterClassFactory.createClassFactory(BARD);
                bard.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case CLERIC -> {
                Cleric cleric = (Cleric) characterClassFactory.createClassFactory(CLERIC);
                cleric.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Cleric.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case DRUID -> {
                Druid druid = (Druid) characterClassFactory.createClassFactory(DRUID);
                druid.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Druid.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case FIGHTER -> {
                Fighter fighter = (Fighter) characterClassFactory.createClassFactory(FIGHTER);
                fighter.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Fighter.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case MONK -> {
                Monk monk = (Monk) characterClassFactory.createClassFactory(MONK);
                monk.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Monk.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case PALADIN -> {
                Paladin paladin = (Paladin) characterClassFactory.createClassFactory(PALADIN);
                paladin.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Paladin.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case RANGER -> {
                Ranger ranger = (Ranger) characterClassFactory.createClassFactory(RANGER);
                ranger.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case ROGUE -> {
                Rogue rogue = (Rogue) characterClassFactory.createClassFactory(ROGUE);
                rogue.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case SORCERER -> {
                Sorcerer sorcerer = (Sorcerer) characterClassFactory.createClassFactory(SORCERER);
                sorcerer.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Sorcerer.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case WARLOCK -> {
                Warlock warlock = (Warlock) characterClassFactory.createClassFactory(WARLOCK);
                warlock.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Warlock.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case WIZARD -> {
                Wizard wizard = (Wizard) characterClassFactory.createClassFactory(WIZARD);
                wizard.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Wizard.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            default -> response = defaultResponse;
        }
        return response;
    }

    public static CharacterClass parseClass(String input) {
        String normalized = input.trim().toLowerCase();
        for (CharacterClass characterClass : CharacterClass.values()) {
            if (characterClass.getDisplayName().toLowerCase().equals(normalized)) {
                return characterClass;
            }
        }
        throw new IllegalArgumentException("Cannot parse user input into an existent character class");
    }
}
