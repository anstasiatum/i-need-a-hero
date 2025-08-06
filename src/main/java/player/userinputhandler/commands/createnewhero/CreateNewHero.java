package player.userinputhandler.commands.createnewhero;

import lombok.AllArgsConstructor;
import player.dndcharacter.DndCharacter;
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
import player.dndcharacter.dndcharacterenums.Skill;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.backgroundoptions.ChoosePossessionsForGuildMerchant;
import player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseProficiencyForGuildMerchant;
import player.userinputhandler.commands.createnewhero.backgroundoptions.SetBackground;
import player.userinputhandler.commands.createnewhero.backgroundoptions.SetPirateFeature;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncreaseBaseCharacteristics;
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;

import java.util.EnumSet;
import java.util.Set;

import static java.lang.String.format;
import static player.userinputhandler.commands.createnewhero.Options.getAlignmentOptions;
import static player.userinputhandler.commands.createnewhero.Options.getAllSkillOptions;
import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBackgroundOptions;
import static player.userinputhandler.commands.createnewhero.Options.getCharacteristicsRollingMethodOptions;
import static player.userinputhandler.commands.createnewhero.Options.getClassOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDraconicAncestryOptions;
import static player.userinputhandler.commands.createnewhero.Options.getGamingSetOptions;
import static player.userinputhandler.commands.createnewhero.Options.getPirateFeatureOptions;
import static player.userinputhandler.commands.createnewhero.Options.getPossessionsForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.Options.getProficienciesForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.Options.getRaceOptions;
import static player.userinputhandler.commands.createnewhero.Options.getSkillOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.allBackgrounds;
import static player.userinputhandler.commands.createnewhero.OutputTexts.allRaces;
import static player.userinputhandler.commands.createnewhero.OutputTexts.alreadyHaveProficiencyInThisSkill;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseAlignment;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseArtisanToolPossessionWithPreviousStep;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseArtisanTools;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseClass;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseFeatureForPirate;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseMusicalInstrumentProficiency;
import static player.userinputhandler.commands.createnewhero.OutputTexts.choosePossessionsForGuildMerchantText;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseSecondLanguage;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseSecondSkill;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseThirdSkill;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseTraits;
import static player.userinputhandler.commands.createnewhero.OutputTexts.notANumberInput;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongSkill;
import static player.userinputhandler.commands.createnewhero.SetDraconicAncestry.setDraconicAncestry;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_FOLK_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_BACKGROUND;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.CHOOSE_DRACONIC_ANCESTRY;
import static player.userinputhandler.enums.Steps.CHOOSE_FEATURE_FOR_PIRATE;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_FOR_NOBLE_AND_KNIGHT;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_OUTLANDER;
import static player.userinputhandler.enums.Steps.CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_PRAYER_ITEM_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;
import static player.userinputhandler.enums.Steps.CHOOSE_ROLLING_CHARACTERISTICS_METHOD;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_LANGUAGE_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_TROPHY_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.DESCRIBE_EYES;
import static player.userinputhandler.enums.Steps.DESCRIBE_HAIR;
import static player.userinputhandler.enums.Steps.DESCRIBE_SKIN;
import static player.userinputhandler.enums.Steps.ENTER_ALIGNMENT;
import static player.userinputhandler.enums.Steps.ENTER_ALLIES_AND_ORGANIZATIONS;
import static player.userinputhandler.enums.Steps.ENTER_CHARACTER_BACKSTORY;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_MUSICAL_INSTRUMENT_FOR_BARD;
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
import static player.userinputhandler.enums.Steps.ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD;
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
import static player.userinputhandler.enums.Steps.ENTER_THIRD_MUSICAL_INSTRUMENT_FOR_BARD;
import static player.userinputhandler.enums.Steps.ENTER_THIRD_SKILL_FOR_BARD;
import static player.userinputhandler.enums.Steps.ENTER_THIRD_SKILL_FOR_RANGER;
import static player.userinputhandler.enums.Steps.ENTER_THIRD_SKILL_FOR_ROGUE;
import static player.userinputhandler.enums.Steps.ENTER_TREASURE_AND_FINISH;
import static player.userinputhandler.enums.Steps.SET_AGE;
import static player.userinputhandler.enums.Steps.SET_BONDS;
import static player.userinputhandler.enums.Steps.SET_CHARISMA;
import static player.userinputhandler.enums.Steps.SET_CONSTITUTION;
import static player.userinputhandler.enums.Steps.SET_DEXTERITY;
import static player.userinputhandler.enums.Steps.SET_FLAWS;
import static player.userinputhandler.enums.Steps.SET_HEIGHT;
import static player.userinputhandler.enums.Steps.SET_IDEALS;
import static player.userinputhandler.enums.Steps.SET_INTELLIGENCE;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;
import static player.userinputhandler.enums.Steps.SET_STRENGTH;
import static player.userinputhandler.enums.Steps.SET_WEIGHT;
import static player.userinputhandler.enums.Steps.SET_WISDOM;

@AllArgsConstructor
public class CreateNewHero {
    private final CharacterDao characterJpaDao;
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod;
    private final IncreaseBaseCharacteristics increaseBaseCharacteristics;
    private final SelectRace selectRace;
    private final AddSkillProficiency addSkillProficiency;
    private final SelectClass selectClass;
    private final SetBackground setBackground;
    private final SetPirateFeature setPirateFeature;
    private final ChoosePossessionsForGuildMerchant choosePossessionsForGuildMerchant;
    private final ChooseProficiencyForGuildMerchant chooseProficiencyForGuildMerchant;

    public Response createNewHero() {
        State newState = new State(CREATE_HERO, ENTER_NAME, new DndCharacter());
        return new Response(newState, "Alright, let's name your future hero!");
    }

    public Response heroCreationAnswer(State state, Long chatId, String userAnswer) {
        Response response;
        State newState;
        BuildAvailableProficiencySkillsWithoutApplied buildSkills = new BuildAvailableProficiencySkillsWithoutApplied();
        Set<Skill> finalAvailableSkills;


        switch (state.getStepId()) {
            case ENTER_NAME:
                state.getDndCharacter().setCharacterName(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_ROLLING_CHARACTERISTICS_METHOD, state.getDndCharacter());
                response = new Response(newState, "Now let's get your base characteristics. You can roll them yourself or I will roll them for you", getCharacteristicsRollingMethodOptions());
                break;
            case CHOOSE_ROLLING_CHARACTERISTICS_METHOD:
                response = characteristicsSettingMethod.chooseCharacteristicsSettingMethod(userAnswer, state.getDndCharacter());
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
                        
                        Now pick the race:
                        """ + allRaces, getRaceOptions());
                break;
            case CHOOSE_RACE:
                response = selectRace.selectRace(userAnswer, state.getDndCharacter());
                break;
            case CHOOSE_DRACONIC_ANCESTRY:
                try {
                    state.getDndCharacter().setFeaturesAndTraits(state.getDndCharacter().getFeaturesAndTraits() + setDraconicAncestry(userAnswer, state.getDndCharacter()));
                    newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                    response = new Response(newState, chooseClass, getClassOptions());
                } catch (IllegalArgumentException exception) {
                    newState = new State(CREATE_HERO, CHOOSE_DRACONIC_ANCESTRY, state.getDndCharacter());
                    response = new Response(newState, "Enter your draconic ancestry", getDraconicAncestryOptions());
                }
                break;
            case CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case CHOOSE_LANGUAGE_FOR_HIGH_ELF, CHOOSE_LANGUAGE_FOR_BASE_HUMAN:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF:
                response = increaseBaseCharacteristics.increaseBaseCharacteristics(state.getDndCharacter(), CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, userAnswer);
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF:
                response = increaseBaseCharacteristics.increaseBaseCharacteristics(state.getDndCharacter(), CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, userAnswer);
                break;
            case CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                response = increaseBaseCharacteristics.increaseBaseCharacteristics(state.getDndCharacter(), CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, userAnswer);
                break;
            case CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN:
                response = increaseBaseCharacteristics.increaseBaseCharacteristics(state.getDndCharacter(), CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, userAnswer);
                break;
            case CHOOSE_FIRST_SKILL_FOR_HALF_ELF:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), EnumSet.allOf(Skill.class));
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getAllSkillOptions());
                    }
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getAllSkillOptions());
                }
                break;
            case CHOOSE_SECOND_SKILL_FOR_HALF_ELF:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                        response = new Response(newState, chooseClass, getClassOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), EnumSet.allOf(Skill.class));
                        newState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), EnumSet.allOf(Skill.class));
                    newState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                        response = new Response(newState, chooseClass, getClassOptions());
                    } else {
                        newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getAllSkillOptions());
                    }
                } catch (IllegalArgumentException ex) {
                    newState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getAllSkillOptions());
                }
                break;
            case ENTER_FEAT_FOR_VARIANT_HUMAN:
                state.getDndCharacter().setFeaturesAndTraits(state.getDndCharacter().getFeaturesAndTraits() + userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, state.getDndCharacter());
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case CHOOSE_CLASS:
                response = selectClass.selectClass(userAnswer, state.getDndCharacter());
                break;
            case ENTER_FIRST_SKILL_FOR_BARBARIAN:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Barbarian.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Barbarian.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Barbarian.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_BARBARIAN:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Barbarian.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Barbarian.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_BARD:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_BARD:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, state.getDndCharacter());
                        response = new Response(newState, chooseThirdSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_THIRD_SKILL_FOR_BARD:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_FIRST_MUSICAL_INSTRUMENT_FOR_BARD, state.getDndCharacter());
                        response = new Response(newState, "Enter the first musical instrument your bard will be proficient with");
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Bard.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_MUSICAL_INSTRUMENT_FOR_BARD:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD, state.getDndCharacter());
                response = new Response(newState, "Enter the second musical instrument your bard will be proficient with");
                break;
            case ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, ENTER_THIRD_MUSICAL_INSTRUMENT_FOR_BARD, state.getDndCharacter());
                response = new Response(newState, "Enter the third musical instrument your bard will be proficient with");
                break;
            case ENTER_THIRD_MUSICAL_INSTRUMENT_FOR_BARD:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                response = new Response(newState, chooseAlignment, getAlignmentOptions());
                break;
            case ENTER_FIRST_SKILL_FOR_CLERIC:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Cleric.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Cleric.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Cleric.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_CLERIC:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Cleric.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Cleric.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_DRUID:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Druid.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Druid.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Druid.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_DRUID:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Druid.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Druid.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_FIGHTER:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Fighter.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Fighter.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Fighter.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_FIGHTER:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Fighter.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Fighter.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_MONK:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Monk.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Monk.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Monk.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_MONK:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Monk.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Monk.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_PALADIN:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Paladin.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Paladin.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Paladin.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_PALADIN:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Paladin.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Paladin.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_RANGER:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_RANGER:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, state.getDndCharacter());
                        response = new Response(newState, chooseThirdSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_THIRD_SKILL_FOR_RANGER:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Ranger.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_ROGUE:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_ROGUE:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, state.getDndCharacter());
                        response = new Response(newState, chooseThirdSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_THIRD_SKILL_FOR_ROGUE:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, state.getDndCharacter());
                        response = new Response(newState, "Choose the fourth skill your rogue will be proficient in \n" + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FOURTH_SKILL_FOR_ROGUE:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Rogue.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_SORCERER:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Sorcerer.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Sorcerer.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Sorcerer.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_SORCERER:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Sorcerer.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Sorcerer.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_WARLOCK:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Warlock.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Warlock.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Warlock.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_WARLOCK:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Warlock.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Warlock.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_FIRST_SKILL_FOR_WIZARD:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Wizard.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, state.getDndCharacter());
                        response = new Response(newState, chooseSecondSkill + finalAvailableSkills, getSkillOptions(finalAvailableSkills));
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Wizard.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Wizard.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_SECOND_SKILL_FOR_WIZARD:
                try {
                    if (addSkillProficiency.addSkillProficiency(state.getDndCharacter(), userAnswer)) {
                        newState = new State(CREATE_HERO, ENTER_ALIGNMENT, state.getDndCharacter());
                        response = new Response(newState, chooseAlignment, getAlignmentOptions());
                    } else {
                        finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Wizard.buildAvailableProficiencySkills());
                        newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, state.getDndCharacter());
                        response = new Response(newState, alreadyHaveProficiencyInThisSkill, getSkillOptions(finalAvailableSkills));
                    }
                } catch (IllegalArgumentException ex) {
                    finalAvailableSkills = buildSkills.buildAvailableProficiencySkillsWithoutApplied(state.getDndCharacter().getSkillsWithProficiency(), Wizard.buildAvailableProficiencySkills());
                    newState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, state.getDndCharacter());
                    response = new Response(newState, wrongSkill, getSkillOptions(finalAvailableSkills));
                }
                break;
            case ENTER_ALIGNMENT:
                state.getDndCharacter().setAlignment(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_BACKGROUND, state.getDndCharacter());
                response = new Response(newState, "Choose the background of your hero\n" + allBackgrounds, getBackgroundOptions());
                break;
            case CHOOSE_BACKGROUND:
                response = setBackground.setBackground(state.getDndCharacter(), userAnswer);
                break;
            case CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE, state.getDndCharacter());
                response = new Response(newState, format(chooseSecondLanguage, state.getDndCharacter().getBackground().getDisplayName().toLowerCase()));
                break;
            case CHOOSE_SECOND_LANGUAGE_FOR_ACOLYTE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_PRAYER_ITEM_FOR_ACOLYTE, state.getDndCharacter());
                response = new Response(newState, "Enter your prayer item (a book, a wheel, etc.)");
                break;
            case CHOOSE_PRAYER_ITEM_FOR_ACOLYTE, CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_FOLK_HERO,
                 CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER, CHOOSE_LUCKY_CHARM_FOR_SAILOR,
                 CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_GUILD_ARTISAN, CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT,
                 CHOOSE_CON_FOR_CHARLATAN, CHOOSE_WEAPON_FOR_GLADIATOR,
                 CHOOSE_MUSICAL_INSTRUMENT_YOU_POSSESS_FOR_ENTERTAINER:
                state.getDndCharacter().setEquipment(state.getDndCharacter().getEquipment() + " " + userAnswer);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                response = new Response(newState, chooseTraits);
                break;
            case CHOOSE_GAMING_SET_FOR_CRIMINAL, CHOOSE_GAMING_SET_FOR_NOBLE_AND_KNIGHT,
                 CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER,
                 CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_OUTLANDER,
                 CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_SAGE:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                response = new Response(newState, chooseTraits);
                break;
            case CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_FOLK_HERO, state.getDndCharacter());
                response = new Response(newState, chooseArtisanToolPossessionWithPreviousStep, getArtisanToolOptions());
                break;
            case CHOOSE_LANGUAGE_FOR_OUTLANDER:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_OUTLANDER, state.getDndCharacter());
                response = new Response(newState, chooseMusicalInstrumentProficiency);
                break;
            case CHOOSE_LANGUAGE_FOR_HERMIT:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, state.getDndCharacter());
                response = new Response(newState, chooseTraits);
                break;
            case CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_FOR_NOBLE_AND_KNIGHT, state.getDndCharacter());
                response = new Response(newState, "Enter a gaming set your hero will be proficient with");
                break;
            case CHOOSE_FIRST_LANGUAGE_FOR_SAGE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_SECOND_LANGUAGE_FOR_SAGE, state.getDndCharacter());
                response = new Response(newState, format(chooseSecondLanguage, state.getDndCharacter().getBackground().getDisplayName().toLowerCase()));
                break;
            case CHOOSE_SECOND_LANGUAGE_FOR_SAGE:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_SAGE, state.getDndCharacter());
                response = new Response(newState, chooseMusicalInstrumentProficiency);
                break;
            case CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_TROPHY_FOR_SOLDIER, state.getDndCharacter());
                response = new Response(newState, "Describe a trophy taken from a fallen enemy (e.g. a dagger, broken blade, or piece of a banner)");
                break;
            case CHOOSE_LUCKY_CHARM_FOR_PIRATE:
                state.getDndCharacter().setEquipment(state.getDndCharacter().getEquipment() + " " + userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_FEATURE_FOR_PIRATE, state.getDndCharacter());
                response = new Response(newState, chooseFeatureForPirate, getPirateFeatureOptions());
                break;
            case CHOOSE_FEATURE_FOR_PIRATE:
                response = setPirateFeature.setPirateFeature(userAnswer, state.getDndCharacter());
                break;
            case CHOOSE_TROPHY_FOR_SOLDIER:
                state.getDndCharacter().setEquipment(state.getDndCharacter().getEquipment() + " " + userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_POSSESSION_FOR_SOLDIER, state.getDndCharacter());
                response = new Response(newState, "Will your character possess a set of bone dice or a deck of cards?", getGamingSetOptions());
                break;
            case CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT, state.getDndCharacter());
                response = new Response(newState, "Would you like to learn an additional language, be proficient in navigator's tools or in one type of artisan's tools?", getProficienciesForGuildMerchantOptions());
                break;
            case CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_GUILD_ARTISAN, state.getDndCharacter());
                response = new Response(newState, chooseArtisanTools, getArtisanToolOptions());
                break;
            case CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_GUILD_ARTISAN:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_POSSESSION_FOR_GUILD_ARTISAN, state.getDndCharacter());
                response = new Response(newState, chooseArtisanToolPossessionWithPreviousStep, getArtisanToolOptions());
                break;
            case CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT:
                response = chooseProficiencyForGuildMerchant.chooseProficiencyForGuildMerchant(userAnswer, state.getDndCharacter());
                break;
            case CHOOSE_ARTISAN_TOOL_PROFICIENCY_FOR_GUILD_MERCHANT:
                state.getDndCharacter().getToolProficiency().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT, state.getDndCharacter());
                response = new Response(newState, chooseArtisanToolPossessionWithPreviousStep, getArtisanToolOptions());
                break;
            case CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT:
                response = choosePossessionsForGuildMerchant.choosePossessionsForGuildMerchant(userAnswer, state.getDndCharacter());
                break;
            case CHOOSE_ADDITIONAL_LANGUAGE_FOR_GUILD_MERCHANT:
                state.getDndCharacter().getLanguages().add(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT, state.getDndCharacter());
                response = new Response(newState, choosePossessionsForGuildMerchantText, getPossessionsForGuildMerchantOptions());
                break;
            case SET_PERSONALITY_TRAITS:
                state.getDndCharacter().setPersonalityTraits(userAnswer);
                newState = new State(CREATE_HERO, SET_IDEALS, state.getDndCharacter());
                response = new Response(newState, "Now enter your character's ideals");
                break;
            case SET_IDEALS:
                state.getDndCharacter().setIdeals(userAnswer);
                newState = new State(CREATE_HERO, SET_FLAWS, state.getDndCharacter());
                response = new Response(newState, "What about flaws?");
                break;
            case SET_FLAWS:
                state.getDndCharacter().setFlaws(userAnswer);
                newState = new State(CREATE_HERO, SET_BONDS, state.getDndCharacter());
                response = new Response(newState, "Set your character's bonds");
                break;
            case SET_BONDS:
                state.getDndCharacter().setBonds(userAnswer);
                newState = new State(CREATE_HERO, SET_AGE, state.getDndCharacter());
                response = new Response(newState, "What is their age?");
                break;
            case SET_AGE:
                state.getDndCharacter().setAge(userAnswer);
                newState = new State(CREATE_HERO, SET_HEIGHT, state.getDndCharacter());
                response = new Response(newState, "Time to describe your character's appearance. What is their height?");
                break;
            case SET_HEIGHT:
                state.getDndCharacter().setHeight(userAnswer);
                newState = new State(CREATE_HERO, SET_WEIGHT, state.getDndCharacter());
                response = new Response(newState, "What is their weight?");
                break;
            case SET_WEIGHT:
                state.getDndCharacter().setWeight(userAnswer);
                newState = new State(CREATE_HERO, DESCRIBE_EYES, state.getDndCharacter());
                response = new Response(newState, "What about eyes?");
                break;
            case DESCRIBE_EYES:
                state.getDndCharacter().setEyes(userAnswer);
                newState = new State(CREATE_HERO, DESCRIBE_SKIN, state.getDndCharacter());
                response = new Response(newState, "Skin?");
                break;
            case DESCRIBE_SKIN:
                state.getDndCharacter().setSkin(userAnswer);
                newState = new State(CREATE_HERO, DESCRIBE_HAIR, state.getDndCharacter());
                response = new Response(newState, "Hair?");
                break;
            case DESCRIBE_HAIR:
                state.getDndCharacter().setHair(userAnswer);
                newState = new State(CREATE_HERO, ENTER_ALLIES_AND_ORGANIZATIONS, state.getDndCharacter());
                response = new Response(newState, "Mention any allies and organizations your hero is familiar with");
                break;
            case ENTER_ALLIES_AND_ORGANIZATIONS:
                state.getDndCharacter().setAlliesAndOrganizations(userAnswer);
                newState = new State(CREATE_HERO, ENTER_CHARACTER_BACKSTORY, state.getDndCharacter());
                response = new Response(newState, "What is your character's backstory?");
                break;
            case ENTER_CHARACTER_BACKSTORY:
                state.getDndCharacter().setBackstory(userAnswer);
                newState = new State(CREATE_HERO, ENTER_TREASURE_AND_FINISH, state.getDndCharacter());
                response = new Response(newState, "Mention any treasure your hero might possess");
                break;
            case ENTER_TREASURE_AND_FINISH:
                state.getDndCharacter().setTreasure(userAnswer);
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
