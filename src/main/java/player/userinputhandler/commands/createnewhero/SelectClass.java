package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.CharacterClass;
import player.dndcharacter.dndclass.Barbarian;
import player.dndcharacter.dndclass.Bard;
import player.dndcharacter.dndclass.Cleric;
import player.dndcharacter.dndclass.Druid;
import player.dndcharacter.dndclass.Fighter;
import player.dndcharacter.dndclass.Monk;
import player.dndcharacter.dndclass.Paladin;
import player.dndcharacter.dndclass.Ranger;
import player.dndcharacter.dndclass.Rogue;
import player.dndcharacter.dndclass.Sorcerer;
import player.dndcharacter.dndclass.Warlock;
import player.dndcharacter.dndclass.Wizard;
import player.userinputhandler.Response;
import player.userinputhandler.State;

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

public class SelectClass {
    public static Response selectClass(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        String chooseSkill = "Enter a skill your hero will be proficient in (Arcana, Intimidation etc.). Here is the list of available ones:\n";
        String allClasses = """
                Barbarian
                Bard
                Cleric
                Druid
                Fighter
                Monk
                Paladin
                Ranger
                Rogue
                Sorcerer
                Warlock
                Wizard""";
        switch (userAnswer.toLowerCase().trim()) {
            case "barbarian" -> {
                dndCharacter.setCharacterClass(BARBARIAN);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);
                response = new Response(newState, chooseSkill + Barbarian.buildAvailableProficiencySkills(), getSkillOptions(Barbarian.buildAvailableProficiencySkills()));
            }
            case "bard" -> {
                dndCharacter.setCharacterClass(BARD);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);
                response = new Response(newState, chooseSkill + Bard.buildAvailableProficiencySkills(), getSkillOptions(Bard.buildAvailableProficiencySkills()));
            }
            case "cleric" -> {
                dndCharacter.setCharacterClass(CLERIC);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);
                response = new Response(newState, chooseSkill + Cleric.buildAvailableProficiencySkills(), getSkillOptions(Cleric.buildAvailableProficiencySkills()));
            }
            case "druid" -> {
                dndCharacter.setCharacterClass(DRUID);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);
                response = new Response(newState, chooseSkill + Druid.buildAvailableProficiencySkills(), getSkillOptions(Druid.buildAvailableProficiencySkills()));
            }
            case "fighter" -> {
                dndCharacter.setCharacterClass(FIGHTER);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);
                response = new Response(newState, chooseSkill + Fighter.buildAvailableProficiencySkills(), getSkillOptions(Fighter.buildAvailableProficiencySkills()));
            }
            case "monk" -> {
                dndCharacter.setCharacterClass(MONK);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);
                response = new Response(newState, chooseSkill + Monk.buildAvailableProficiencySkills(), getSkillOptions(Monk.buildAvailableProficiencySkills()));
            }
            case "paladin" -> {
                dndCharacter.setCharacterClass(PALADIN);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);
                response = new Response(newState, chooseSkill + Paladin.buildAvailableProficiencySkills(), getSkillOptions(Paladin.buildAvailableProficiencySkills()));
            }
            case "ranger" -> {
                dndCharacter.setCharacterClass(RANGER);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);
                response = new Response(newState, chooseSkill + Ranger.buildAvailableProficiencySkills(), getSkillOptions(Ranger.buildAvailableProficiencySkills()));
            }
            case "rogue" -> {
                dndCharacter.setCharacterClass(ROGUE);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);
                response = new Response(newState, chooseSkill + Rogue.buildAvailableProficiencySkills(), getSkillOptions(Rogue.buildAvailableProficiencySkills()));
            }
            case "sorcerer" -> {
                dndCharacter.setCharacterClass(SORCERER);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);
                response = new Response(newState, chooseSkill + Sorcerer.buildAvailableProficiencySkills(), getSkillOptions(Sorcerer.buildAvailableProficiencySkills()));
            }
            case "warlock" -> {
                dndCharacter.setCharacterClass(WARLOCK);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);
                response = new Response(newState, chooseSkill + Warlock.buildAvailableProficiencySkills(), getSkillOptions(Warlock.buildAvailableProficiencySkills()));
            }
            case "wizard" -> {
                dndCharacter.setCharacterClass(WIZARD);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
                response = new Response(newState, chooseSkill + Wizard.buildAvailableProficiencySkills(), getSkillOptions(Wizard.buildAvailableProficiencySkills()));
            }
            default -> {
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, "Cannot understand your input. Here is the list of available classes: \n" + allClasses, getClassOptions());
            }
        }
        return response;
    }
}
