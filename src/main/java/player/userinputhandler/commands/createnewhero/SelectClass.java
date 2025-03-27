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
        Response response = null;
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
        switch (userAnswer.toLowerCase()) {
            case "barbarian" -> {
                dndCharacter.setCharacterClass(CharacterClass.BARBARIAN);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);
                response = new Response(newState, chooseSkill + Barbarian.buildAvailableProficiencySkills());
            }
            case "bard" -> {
                dndCharacter.setCharacterClass(CharacterClass.BARD);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);
                response = new Response(newState, chooseSkill + Bard.buildAvailableProficiencySkills());
            }
            case "cleric" -> {
                dndCharacter.setCharacterClass(CharacterClass.CLERIC);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);
                response = new Response(newState, chooseSkill + Cleric.buildAvailableProficiencySkills());
            }
            case "druid" -> {
                dndCharacter.setCharacterClass(CharacterClass.DRUID);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);
                response = new Response(newState, chooseSkill + Druid.buildAvailableProficiencySkills());
            }
            case "fighter" -> {
                dndCharacter.setCharacterClass(CharacterClass.FIGHTER);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);
                response = new Response(newState, chooseSkill + Fighter.buildAvailableProficiencySkills());
            }
            case "monk" -> {
                dndCharacter.setCharacterClass(CharacterClass.MONK);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);
                response = new Response(newState, chooseSkill + Monk.buildAvailableProficiencySkills());
            }
            case "paladin" -> {
                dndCharacter.setCharacterClass(CharacterClass.PALADIN);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);
                response = new Response(newState, chooseSkill + Paladin.buildAvailableProficiencySkills());
            }
            case "ranger" -> {
                dndCharacter.setCharacterClass(CharacterClass.RANGER);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);
                response = new Response(newState, chooseSkill + Ranger.buildAvailableProficiencySkills());
            }
            case "rogue" -> {
                dndCharacter.setCharacterClass(CharacterClass.ROGUE);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);
                response = new Response(newState, chooseSkill + Rogue.buildAvailableProficiencySkills());
            }
            case "sorcerer" -> {
                dndCharacter.setCharacterClass(CharacterClass.SORCERER);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);
                response = new Response(newState, chooseSkill + Sorcerer.buildAvailableProficiencySkills());
            }
            case "warlock" -> {
                dndCharacter.setCharacterClass(CharacterClass.WARLOCK);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);
                response = new Response(newState, chooseSkill + Warlock.buildAvailableProficiencySkills());
            }
            case "wizard" -> {
                dndCharacter.setCharacterClass(CharacterClass.WIZARD);
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
                response = new Response(newState, chooseSkill + Wizard.buildAvailableProficiencySkills());
            }
            default -> {
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, "Cannot understand your input. Here is the list of available classes: \n" + allClasses);
            }
        }
        return response;
    }
}
