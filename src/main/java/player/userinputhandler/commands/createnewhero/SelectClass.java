package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skill;
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

import java.util.Set;

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
        BuildAvailableProficiencySkillsWithoutApplied buildSkills = new BuildAvailableProficiencySkillsWithoutApplied();
        Set<Skill> finalAvailableSkills;
        switch (userAnswer.toLowerCase().trim()) {
            case "barbarian" -> {
                Barbarian barbarian = new Barbarian();
                barbarian.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Barbarian.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "bard" -> {
                Bard bard = new Bard();
                bard.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "cleric" -> {
                Cleric cleric = new Cleric();
                cleric.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Cleric.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "druid" -> {
                Druid druid = new Druid();
                druid.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Druid.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "fighter" -> {
                Fighter fighter = new Fighter();
                fighter.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Fighter.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "monk" -> {
                Monk monk = new Monk();
                monk.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Monk.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "paladin" -> {
                Paladin paladin = new Paladin();
                paladin.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Paladin.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "ranger" -> {
                Ranger ranger = new Ranger();
                ranger.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "rogue" -> {
                Rogue rogue = new Rogue();
                rogue.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "sorcerer" -> {
                Sorcerer sorcerer = new Sorcerer();
                sorcerer.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Sorcerer.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "warlock" -> {
                Warlock warlock = new Warlock();
                warlock.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Warlock.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            case "wizard" -> {
                Wizard wizard = new Wizard();
                wizard.modifyByClass(dndCharacter);
                finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(dndCharacter.getSkillsWithProficiency(), Wizard.buildAvailableProficiencySkills());
                newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
                response = new Response(newState, chooseSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
            }
            default -> {
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, "Cannot understand your input. Here is the list of available classes: \n" + allClasses, getClassOptions());
            }
        }
        return response;
    }
}
