package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
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
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;

import static player.userinputhandler.commands.createnewhero.AddSkillProficiency.addSkillProficiency;
import static player.userinputhandler.commands.createnewhero.ChooseCharacteristicsSettingMethod.chooseCharacteristicsSettingMethod;
import static player.userinputhandler.commands.createnewhero.IncreaseBaseCharacteristics.increaseBaseCharacteristics;
import static player.userinputhandler.commands.createnewhero.SelectClass.selectClass;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;
import static player.userinputhandler.enums.Steps.CHOOSE_ROLLING_CHARACTERISTICS_METHOD;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.ENTER_ALIGNMENT;
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
import static player.userinputhandler.enums.Steps.ENTER_FOURTH_SKILL_FOR_ROGUE;
import static player.userinputhandler.enums.Steps.ENTER_NAME;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_BARBARIAN;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_BARD;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_CLERIC;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_DRUID;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_FIGHTER;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_MONK;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_PALADIN;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_RANGER;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_ROGUE;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_SORCERER;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_WARLOCK;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_SKILL_FOR_WIZARD;
import static player.userinputhandler.enums.Steps.ENTER_THIRD_SKILL_FOR_BARD;
import static player.userinputhandler.enums.Steps.ENTER_THIRD_SKILL_FOR_RANGER;
import static player.userinputhandler.enums.Steps.ENTER_THIRD_SKILL_FOR_ROGUE;
import static player.userinputhandler.enums.Steps.SET_BONDS_AND_FINISH;
import static player.userinputhandler.enums.Steps.SET_CHARISMA;
import static player.userinputhandler.enums.Steps.SET_CONSTITUTION;
import static player.userinputhandler.enums.Steps.SET_DEXTERITY;
import static player.userinputhandler.enums.Steps.SET_FLAWS;
import static player.userinputhandler.enums.Steps.SET_IDEALS;
import static player.userinputhandler.enums.Steps.SET_INTELLIGENCE;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;
import static player.userinputhandler.enums.Steps.SET_STRENGTH;
import static player.userinputhandler.enums.Steps.SET_WISDOM;

public class CreateNewHero {
    final static CharacterDao characterJpaDao = new CharacterDaoImpl();

    public static Response createNewHero() {
        State newState = new State(CREATE_HERO, ENTER_NAME, new DndCharacter());
        return new Response(newState, "Alright, let's name your future hero!");
    }

    public static Response heroCreationAnswer(State state, Long chatId, String userAnswer) {
        Response response;
        State newState;
        String notANumberInput = "Please enter a number";
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
        String chooseClass = "Choose your class:\n" + allClasses;
        String allRaces = """
                Dragonborn
                Hill Dwarf
                Mountain Dwarf
                Dark Elf
                High Elf
                Wood Elf
                Forest Gnome
                Rock Gnome
                Half Elf
                Lightfoot Halfling
                Stout Halfling
                Half Orc
                Base Human
                Variant Human
                Tiefling
                """;
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

        String chooseSecondSkill = "Enter the second skill your hero will be proficient in. Available ones:\n";
        String chooseThirdSkill = "Enter the third skill your hero will be proficient in. Available ones:\n";
        String wrongSkill = "Cannot understand your input. Please enter a skill\n" + allSkills;
        String chooseAlignment = """
                Set your hero's alignment:
                Lawful good
                Neutral good
                Chaotic good
                Lawful neutral
                True neutral
                Chaotic neutral
                Lawful evil
                Neutral evil
                Chaotic evil
                """;

        switch (state.getStepId()) {
            case ENTER_NAME:
                state.getDndCharacter().setCharacterName(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_ROLLING_CHARACTERISTICS_METHOD, state.getDndCharacter());
                response = new Response(newState, "Now, let's get your base characteristics. A.You can roll them yourself or B. I will roll them for you. Enter A or B");
                break;
            case CHOOSE_ROLLING_CHARACTERISTICS_METHOD:
                response = chooseCharacteristicsSettingMethod(userAnswer, state.getDndCharacter());
                break;
            case SET_STRENGTH:
                try {
                    state.getDndCharacter().setStrength(Integer.parseInt(userAnswer));
                } catch (NumberFormatException e) {
                    newState = new State(CREATE_HERO, SET_STRENGTH, state.getDndCharacter());
                    response = new Response(newState, notANumberInput);
                    break;
                }
                newState = new State(CREATE_HERO, SET_DEXTERITY, state.getDndCharacter());
                response = new Response(newState, "Set dexterity:");
                break;
            case SET_DEXTERITY:
                try {
                    state.getDndCharacter().setDexterity(Integer.parseInt(userAnswer));
                } catch (NumberFormatException e) {
                    newState = new State(CREATE_HERO, SET_DEXTERITY, state.getDndCharacter());
                    response = new Response(newState, notANumberInput);
                    break;
                }
                newState = new State(CREATE_HERO, SET_CONSTITUTION, state.getDndCharacter());
                response = new Response(newState, "Set constitution:");
                break;
            case SET_CONSTITUTION:
                try {
                    state.getDndCharacter().setConstitution(Integer.parseInt(userAnswer));
                } catch (NumberFormatException e) {
                    newState = new State(CREATE_HERO, SET_CONSTITUTION, state.getDndCharacter());
                    response = new Response(newState, notANumberInput);
                    break;
                }
                newState = new State(CREATE_HERO, SET_INTELLIGENCE, state.getDndCharacter());
                response = new Response(newState, "Set intelligence:");
                break;
            case SET_INTELLIGENCE:
                try {
                    state.getDndCharacter().setIntelligence(Integer.parseInt(userAnswer));
                } catch (NumberFormatException e) {
                    newState = new State(CREATE_HERO, SET_INTELLIGENCE, state.getDndCharacter());
                    response = new Response(newState, notANumberInput);
                    break;
                }
                newState = new State(CREATE_HERO, SET_WISDOM, state.getDndCharacter());
                response = new Response(newState, "Set wisdom:");
                break;
            case SET_WISDOM:
                try {
                    state.getDndCharacter().setWisdom(Integer.parseInt(userAnswer));
                } catch (NumberFormatException e) {
                    newState = new State(CREATE_HERO, SET_WISDOM, state.getDndCharacter());
                    response = new Response(newState, notANumberInput);
                    break;
                }
                newState = new State(CREATE_HERO, SET_CHARISMA, state.getDndCharacter());
                response = new Response(newState, "Set charisma:");
                break;
            case SET_CHARISMA:
                try {
                    state.getDndCharacter().setCharisma(Integer.parseInt(userAnswer));
                } catch (NumberFormatException e) {
                    newState = new State(CREATE_HERO, SET_CHARISMA, state.getDndCharacter());
                    response = new Response(newState, notANumberInput);
                    break;
                }
                newState = new State(CREATE_HERO, CHOOSE_RACE, state.getDndCharacter());
                response = new Response(newState, """
                        We have finished setting the base characteristics.
                        
                        Now, pick the race:
                        """ + allRaces);
                break;
            case CHOOSE_RACE:
                response = SelectRace.selectRace(userAnswer, state.getDndCharacter());
                break;
            case CHOOSE_DRACONIC_ANCESTRY:
                state.getDndCharacter().setFeaturesAndTraits(state.getDndCharacter().getFeaturesAndTraits() + "Your Draconic Ancestry is" + userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                response = new Response(newState, chooseClass);
                break;
            case CHOOSE_LANGUAGE_FOR_HIGH_ELF, CHOOSE_LANGUAGE_FOR_BASE_HUMAN:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                response = new Response(newState, chooseClass);
                break;
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF:
                response = increaseBaseCharacteristics(state.getDndCharacter(), CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, userAnswer);
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF:
                response = increaseBaseCharacteristics(state.getDndCharacter(), CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, userAnswer);
                break;
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                response = increaseBaseCharacteristics(state.getDndCharacter(), CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, userAnswer);
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                response = increaseBaseCharacteristics(state.getDndCharacter(), CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, userAnswer);
                break;
            case CHOOSE_FIRST_SKILL_FOR_HALF_ELF:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + allSkills);
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case CHOOSE_SECOND_SKILL_FOR_HALF_ELF:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                    response = new Response(newState, chooseClass);
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                    response = new Response(newState, chooseClass);
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FEAT_FOR_VARIANT_HUMAN:
                state.getDndCharacter().setFeaturesAndTraits(state.getDndCharacter().getFeaturesAndTraits() + userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                response = new Response(newState, chooseClass);
                break;
            case CHOOSE_CLASS:
                response = selectClass(userAnswer, state.getDndCharacter());
                break;
            case ENTER_FIRST_SKILL_FOR_BARBARIAN:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Barbarian.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_BARBARIAN:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Barbarian barbarian = new Barbarian();
                    barbarian.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_BARD:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Bard.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_BARD:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, state.getDndCharacter());
                    response = new Response(newState, chooseThirdSkill + Bard.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_THIRD_SKILL_FOR_BARD:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Bard bard = new Bard();
                    bard.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_CLERIC:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Cleric.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_CLERIC:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Cleric cleric = new Cleric();
                    cleric.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_DRUID:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Druid.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_DRUID:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Druid druid = new Druid();
                    druid.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_FIGHTER:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Fighter.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_FIGHTER:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Fighter fighter = new Fighter();
                    fighter.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_MONK:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Monk.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_MONK:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Monk monk = new Monk();
                    monk.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_PALADIN:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Paladin.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_PALADIN:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Paladin paladin = new Paladin();
                    paladin.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_RANGER:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Ranger.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_RANGER:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Ranger.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_THIRD_SKILL_FOR_RANGER:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Ranger ranger = new Ranger();
                    ranger.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_ROGUE:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Rogue.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_ROGUE:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, chooseThirdSkill + Rogue.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_THIRD_SKILL_FOR_ROGUE:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, "Choose the fourth skill your rogue will be proficient in \n" + Rogue.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FOURTH_SKILL_FOR_ROGUE:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Rogue rogue = new Rogue();
                    rogue.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_SORCERER:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Sorcerer.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_SORCERER:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Sorcerer sorcerer = new Sorcerer();
                    sorcerer.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_WARLOCK:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Warlock.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_WARLOCK:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Warlock warlock = new Warlock();
                    warlock.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_FIRST_SKILL_FOR_WIZARD:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, state.getDndCharacter());
                    response = new Response(newState, chooseSecondSkill + Wizard.buildAvailableProficiencySkills());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_SECOND_SKILL_FOR_WIZARD:
                try {
                    addSkillProficiency(state.getDndCharacter(), userAnswer);
                    newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                    response = new Response(newState, chooseAlignment);
                    Wizard wizard = new Wizard();
                    wizard.modifyByClass(state.getDndCharacter());
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill);
                }
                break;
            case ENTER_ALIGNMENT:
                state.getDndCharacter().setAlignment(userAnswer);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                response = new Response(newState, "Type any personality traits you'd like to mention");
                break;
            case SET_PERSONALITY_TRAITS:
                state.getDndCharacter().setPersonalityTraits(userAnswer);
                newState = new State(CREATE_HERO, SET_IDEALS, state.getDndCharacter());
                response = new Response(newState, "Now, enter your character's ideals");
                break;
            case SET_IDEALS:
                state.getDndCharacter().setIdeals(userAnswer);
                newState = new State(CREATE_HERO, SET_FLAWS, state.getDndCharacter());
                response = new Response(newState, "What about flaws?");
                break;
            case SET_FLAWS:
                state.getDndCharacter().setFlaws(userAnswer);
                newState = new State(CREATE_HERO, SET_BONDS_AND_FINISH, state.getDndCharacter());
                response = new Response(newState, "Set your character's bonds");
                break;
            case SET_BONDS_AND_FINISH:
                state.getDndCharacter().setBonds(userAnswer);
                try {
                    Character character = new Character(null, chatId, state.getDndCharacter());
                    characterJpaDao.save(character);
                    response = new Response(null, "Hooray! We have finished setting your character and they have been successfully saved. Now you can print their character sheet using /printhero");
                } catch (Exception e) {
                    response = new Response(null, "Oh no, something went wrong. I couldn't save your character");
                }
                break;
            default:
                newState = new State(CREATE_HERO, ENTER_NAME, state.getDndCharacter());
                response = new Response(newState, "Wrong Step ID. Redirecting to the beginning of the character creation.");
                break;
        }
        return response;
    }
}
