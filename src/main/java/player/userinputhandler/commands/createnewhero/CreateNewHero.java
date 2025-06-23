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
import static player.userinputhandler.commands.createnewhero.OutputTexts.allBackgrounds;
import static player.userinputhandler.commands.createnewhero.OutputTexts.allRaces;
import static player.userinputhandler.commands.createnewhero.OutputTexts.allSkills;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseAlignment;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseClass;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseSecondSkill;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseThirdSkill;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseTraits;
import static player.userinputhandler.commands.createnewhero.OutputTexts.notANumberInput;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongSkill;
import static player.userinputhandler.commands.createnewhero.SelectClass.selectClass;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_POSESSION_FOR_FOLK_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_BACKGROUND;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.CHOOSE_ENTERTAINER_OR_GLADIATOR;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_FOR_NOBLE;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER;
import static player.userinputhandler.enums.Steps.CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_PRAYER_ITEM_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;
import static player.userinputhandler.enums.Steps.CHOOSE_ROLLING_CHARACTERISTICS_METHOD;
import static player.userinputhandler.enums.Steps.CHOOSE_SAILOR_OR_PIRATE_FOR_SAILOR;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_LANGUAGE_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_TROPHY_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.CHOOSE_WEAPON_FOR_GLADIATOR;
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
                newState = new State(CREATE_HERO, CHOOSE_BACKGROUND, state.getDndCharacter());
                response = new Response(newState, "Choose the background of your hero\n" + allBackgrounds);
                break;
            case CHOOSE_BACKGROUND:
                state.getDndCharacter().setBackground(userAnswer);
                response = SetBackground.setBackground(state.getDndCharacter(), userAnswer);
                break;
            case CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE, state.getDndCharacter());
                response = new Response(newState, "Choose the second language for your acolyte");
                break;
            case CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_PRAYER_ITEM_FOR_ACOLYTE, state.getDndCharacter());
                response = new Response(newState, "Enter your prayer item (a book, a wheel, etc.)");
                break;
            case CHOOSE_PRAYER_ITEM_FOR_ACOLYTE, CHOOSE_ARTISANS_TOOL_POSESSION_FOR_FOLK_HERO,
                 CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER:
                state.getDndCharacter().setEquipment(state.getDndCharacter().getEquipment() + ", " + userAnswer);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                response = new Response(newState, chooseTraits);
                break;
            case CHOOSE_GAMING_SET_FOR_CRIMINAL, CHOOSE_GAMING_SET_FOR_NOBLE,
                 CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                response = new Response(newState, chooseTraits);
                break;
            case CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_POSESSION_FOR_FOLK_HERO, state.getDndCharacter());
                response = new Response(newState, "Choose a set of artisan's tools your hero will have. Might be the same as in the previous step");
                break;
            case CHOOSE_LANGUAGE_FOR_OUTLANDER, CHOOSE_SECOND_LANGUAGE_FOR_SAGE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                response = new Response(newState, chooseTraits);
                break;
            case CHOOSE_CON_FOR_CHARLATAN, CHOOSE_WEAPON_FOR_GLADIATOR,
                 CHOOSE_MUSICAL_INSTRUMENT_YOU_POSSESS_FOR_ENTERTAINER:
                state.getDndCharacter().setEquipment(state.getDndCharacter() + userAnswer);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                response = new Response(newState, chooseTraits);
                break;
            case CHOOSE_ENTERTAINER_OR_GLADIATOR:
                switch (userAnswer.toLowerCase().trim()) {
                    case "entertainer":
                        state.getDndCharacter().setFeaturesAndTraits(state.getDndCharacter().getFeaturesAndTraits() + "You can perform at inns, theaters, circuses, or any place with a stage. While you’re performing there each night, you receive free modest or comfortable lodging and food. This can allow you to take long rests for free as you travel with your party across the land. In addition, your performance makes you famous wherever you perform. When strangers recognize you in the town, they usually like you more. This may make it easier to persuade them to do things for you.\n");
                        newState = new State(CREATE_HERO, CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER, state.getDndCharacter());
                        response = new Response(newState, "Choose one musical instrument your character is proficient with");
                        break;
                    case "gladiator":
                        state.getDndCharacter().setFeaturesAndTraits(state.getDndCharacter().getFeaturesAndTraits() + "You can find a place to perform in any place that features combat for entertainment-perhaps a gladiatorial arena or secret pit fighting club.\n");
                        newState = new State(CREATE_HERO, CHOOSE_WEAPON_FOR_GLADIATOR, state.getDndCharacter());
                        response = new Response(newState, "Enter an inexpensive, but unusual weapon (such as a trident or net) your hero will possess");
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_ENTERTAINER_OR_GLADIATOR, state.getDndCharacter());
                        response = new Response(newState, wrongInput);
                        break;
                }
                break;
            case CHOOSE_LANGUAGE_FOR_NOBLE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_FOR_NOBLE, state.getDndCharacter());
                response = new Response(newState, "Enter a gaming set your hero will be proficient with");
                break;
            case CHOOSE_FIRST_LANGUAGE_FOR_SAGE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_SECOND_LANGUAGE_FOR_SAGE, state.getDndCharacter());
                response = new Response(newState, chooseTraits);
                break;
            case CHOOSE_SAILOR_OR_PIRATE_FOR_SAILOR:
                switch (userAnswer.toLowerCase().trim()) {
                    case "sailor", "common sailor":
                        state.getDndCharacter().setFeaturesAndTraits(state.getDndCharacter().getFeaturesAndTraits() + "Ship's Passage When you need to, you can secure free passage on a sailing ship for yourself and your adventuring companions. You might sail on the ship you served on, or another ship you have good relations with (perhaps one captained by a former crewmate). Because you're calling in a favor, you can't be certain of a schedule or route that will meet your every need. Your DM will determine how long it takes to get where you need to go. In return for your free passage, you and your companions are expected to assist the crew during the voyage.\n");
                        state.getDndCharacter().setBackground(userAnswer);
                        newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                        response = new Response(newState, chooseTraits);
                        break;
                    case "pirate":
                        state.getDndCharacter().setFeaturesAndTraits(state.getDndCharacter().getFeaturesAndTraits() + "No matter where you go, people are afraid of you due to your reputation. When you are in a civilized settlement, you can get away with minor criminal offenses, such as refusing to pay for food at a tavern or breaking down doors at a local shop, since most people will not report your activity to the authorities.\n");
                        state.getDndCharacter().setBackground(userAnswer);
                        newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                        response = new Response(newState, chooseTraits);
                        break;
                    default:
                        newState = new State(CREATE_HERO, CHOOSE_SAILOR_OR_PIRATE_FOR_SAILOR, state.getDndCharacter());
                        response = new Response(newState, wrongInput);
                        break;
                }
                break;
            case CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_TROPHY_FOR_SOLDIER, state.getDndCharacter());
                response = new Response(newState, "Describe a trophy taken from a fallen enemy (e.g. a dagger, broken blade, or piece of a banner)");
                break;
            case CHOOSE_TROPHY_FOR_SOLDIER:
                state.getDndCharacter().setEquipment(state.getDndCharacter().getEquipment() + ", " + userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER , state.getDndCharacter());
                response = new Response(newState, "Will your character possess a set of bone dice or a deck of cards?");
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
