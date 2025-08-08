package herocreationtests.createnewhero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundFactory;
import player.dndcharacter.characterclass.CharacterClassFactory;
import player.dndcharacter.characteristicsgenerator.BaseCharacteristicsValuesGenerator;
import player.dndcharacter.race.RaceFactory;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.AddSkill;
import player.userinputhandler.commands.createnewhero.ChooseCharacteristicsSettingMethod;
import player.userinputhandler.commands.createnewhero.CreateNewHero;
import player.userinputhandler.commands.createnewhero.SelectClass;
import player.userinputhandler.commands.createnewhero.SelectRace;
import player.userinputhandler.commands.createnewhero.backgroundoptions.ChoosePossessionsForGuildMerchant;
import player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseProficiencyForGuildMerchant;
import player.userinputhandler.commands.createnewhero.backgroundoptions.SetBackground;
import player.userinputhandler.commands.createnewhero.backgroundoptions.SetPirateFeature;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncreaseBaseCharacteristics;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncrementAbility;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;
import player.userinputhandler.enums.Steps;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.EXPERTISE;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skill.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skill.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.STEALTH;
import static player.userinputhandler.commands.createnewhero.Options.getAlignmentOptions;
import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getFavouredEnemyOptions;
import static player.userinputhandler.commands.createnewhero.Options.getFavouredTerrainOptions;
import static player.userinputhandler.commands.createnewhero.Options.getLeaveEmptyOption;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseAlignment;
import static player.userinputhandler.commands.createnewhero.OutputTexts.couldNotUpgradeProficiency;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongSkill;
import static player.userinputhandler.enums.Alignment.getAllAlignments;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.ENTER_ALIGNMENT;
import static player.userinputhandler.enums.Steps.ENTER_FAVOURED_ENEMY_FOR_RANGER;
import static player.userinputhandler.enums.Steps.ENTER_FAVOURED_ENEMY_LANGUAGE_FOR_RANGER;
import static player.userinputhandler.enums.Steps.ENTER_FAVOURED_TERRAIN_FOR_RANGER;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_EXPERTISE_FOR_ROGUE;
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
import static player.userinputhandler.enums.Steps.ENTER_PROFICIENCY_FOR_MONK;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_EXPERTISE_FOR_ROGUE;
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

public class HeroCreationAnswerClassTest {
    private final CharacterDao characterJpaDao = new CharacterDaoImpl();
    private final BaseCharacteristicsValuesGenerator baseCharacteristicsValuesGenerator = new BaseCharacteristicsValuesGenerator();
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod = new ChooseCharacteristicsSettingMethod(baseCharacteristicsValuesGenerator);
    private final IncrementAbility incrementAbility = new IncrementAbility();
    private final IncreaseBaseCharacteristics increaseBaseCharacteristics = new IncreaseBaseCharacteristics(incrementAbility);
    private final RaceFactory raceFactory = new RaceFactory();
    private final SelectRace selectRace = new SelectRace(raceFactory);
    private final AddSkill addSkillSpy = spy(new AddSkill());
    private final CharacterClassFactory characterClassFactory = new CharacterClassFactory();
    private final SelectClass selectClass = new SelectClass(characterClassFactory);
    private final BackgroundFactory backgroundFactory = new BackgroundFactory();
    private final SetBackground setBackground = new SetBackground(backgroundFactory);
    private final SetPirateFeature setPirateFeature = new SetPirateFeature();
    private final ChoosePossessionsForGuildMerchant choosePossessionsForGuildMerchant = new ChoosePossessionsForGuildMerchant();
    private final ChooseProficiencyForGuildMerchant chooseProficiencyForGuildMerchant = new ChooseProficiencyForGuildMerchant();
    private final CreateNewHero createNewHero = new CreateNewHero(characterJpaDao, characteristicsSettingMethod, increaseBaseCharacteristics, selectRace, addSkillSpy, selectClass, setBackground, setPirateFeature, choosePossessionsForGuildMerchant, chooseProficiencyForGuildMerchant);
    private State incomingState;
    private State expectedState;
    private Response expectedResponse;
    private Response actualResponse;
    private DndCharacter dndCharacter = new DndCharacter();
    private final Long chatID = 123L;
    private final String userAnswerStealth = "stealth";
    private final String userAnswerSkill = "skill";
    private final String wrongInputTextAnswer = "Cannot understand your input. Please enter a skill";
    private final String chooseSecondSkillTextAnswer = "Enter the second skill your hero will be proficient in. Available ones:\n";
    private final String alreadyHaveProficiencyInThisSkillTextAnswer = "You already have proficiency in this skill. Choose another one";
    private final String chooseAlignmentTextAnswer = "Set your hero's alignment\n" + getAllAlignments();
    private final String chooseThirdSkillTextAnswer = "Enter the third skill your hero will be proficient in. Available ones:\n";

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_BARBARIAN -> ENTER_SECOND_SKILL_FOR_BARBARIAN when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForBarbarian() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_BARBARIAN -> ENTER_FIRST_SKILL_FOR_BARBARIAN when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForBarbarianSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_BARBARIAN -> ENTER_FIRST_SKILL_FOR_BARBARIAN when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForBarbarianInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_BARBARIAN -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForBarbarian() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_BARBARIAN -> ENTER_SECOND_SKILL_FOR_BARBARIAN when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForBarbarianSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_BARBARIAN -> ENTER_SECOND_SKILL_FOR_BARBARIAN when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForBarbarianInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARBARIAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_BARD -> ENTER_SECOND_SKILL_FOR_BARD when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForBard() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_BARD -> ENTER_FIRST_SKILL_FOR_BARD when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForBardSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_BARD -> ENTER_FIRST_SKILL_FOR_BARD when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForBardInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_BARD -> ENTER_THIRD_SKILL_FOR_BARD when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForBard() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseThirdSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_BARD -> ENTER_SECOND_SKILL_FOR_BARD when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForBardSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_BARD -> ENTER_SECOND_SKILL_FOR_BARD when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForBardInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_BARD -> ENTER_FIRST_MUSICAL_INSTRUMENT_FOR_BARD when the skill is valid")
    void heroCreationAnswer_chooseThirdSkillForBard() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_MUSICAL_INSTRUMENT_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains("Enter the first musical instrument your bard will be proficient with"));
        assertEquals(List.of(), actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_BARD -> ENTER_THIRD_SKILL_FOR_BARD when the skill is already applied")
    void heroCreationAnswer_chooseThirdSkillForBardSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_BARD -> ENTER_THIRD_SKILL_FOR_BARD when the skill is invalid")
    void heroCreationAnswer_chooseThirdSkillForBardInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_BARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_MUSICAL_INSTRUMENT_FOR_BARD -> ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD")
    void heroCreationAnswer_chooseFirstMusicalInstrumentForBard() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_MUSICAL_INSTRUMENT_FOR_BARD, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD, dndCharacter), "Enter the second musical instrument your bard will be proficient with");
        dndCharacter.getToolProficiency().add("Cello");
        Set<String> expectedToolProficiency = Set.of("Cello", "Violin");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, "Violin");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedToolProficiency, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD -> ENTER_THIRD_MUSICAL_INSTRUMENT_FOR_BARD")
    void heroCreationAnswer_chooseSecondMusicalInstrumentForBard() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_THIRD_MUSICAL_INSTRUMENT_FOR_BARD, dndCharacter), "Enter the third musical instrument your bard will be proficient with");
        dndCharacter.getToolProficiency().add("Cello");
        Set<String> expectedToolProficiency = Set.of("Cello", "Violin");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, "Violin");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedToolProficiency, dndCharacter.getToolProficiency());
    }


    @ParameterizedTest(name = "{0} -> ENTER_ALIGNMENT")
    @EnumSource(value = Steps.class, names = {
            "ENTER_THIRD_MUSICAL_INSTRUMENT_FOR_BARD",
            "ENTER_PROFICIENCY_FOR_MONK"
    })
    void heroCreationAnswer_chooseThirdMusicalInstrumentForBard(Steps step) {
        incomingState = new State(CREATE_HERO, step, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());
        dndCharacter.getToolProficiency().add("Cello");
        Set<String> expectedToolProficiency = Set.of("Cello", "Violin");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, "Violin");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedToolProficiency, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_CLERIC -> ENTER_SECOND_SKILL_FOR_CLERIC when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForCleric() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_CLERIC -> ENTER_FIRST_SKILL_FOR_CLERIC when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForClericSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_CLERIC -> ENTER_FIRST_SKILL_FOR_CLERIC when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForClericInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_CLERIC -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForCleric() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_CLERIC -> ENTER_SECOND_SKILL_FOR_CLERIC when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForClericSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_CLERIC -> ENTER_SECOND_SKILL_FOR_CLERIC when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForClericInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_CLERIC, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_DRUID -> ENTER_SECOND_SKILL_FOR_DRUID when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForDruid() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_DRUID -> ENTER_FIRST_SKILL_FOR_DRUID when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForDruidSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_DRUID -> ENTER_FIRST_SKILL_FOR_DRUID when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForDruidInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_DRUID -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForDruid() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_DRUID -> ENTER_SECOND_SKILL_FOR_DRUID when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForDruidSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_DRUID -> ENTER_SECOND_SKILL_FOR_DRUID when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForDruidInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_DRUID, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_FIGHTER -> ENTER_SECOND_SKILL_FOR_FIGHTER when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForFighter() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_FIGHTER -> ENTER_FIRST_SKILL_FOR_FIGHTER when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForFighterSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_FIGHTER -> ENTER_FIRST_SKILL_FOR_FIGHTER when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForFighterInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_FIGHTER -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForFighter() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_FIGHTER -> ENTER_SECOND_SKILL_FOR_FIGHTER when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForFighterSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_FIGHTER -> ENTER_SECOND_SKILL_FOR_FIGHTER when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForFighterInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_FIGHTER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_MONK -> ENTER_SECOND_SKILL_FOR_MONK when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForMonk() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_MONK -> ENTER_FIRST_SKILL_FOR_MONK when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForMonkSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_MONK -> ENTER_FIRST_SKILL_FOR_MONK when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForMonkInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_MONK -> ENTER_PROFICIENCY_FOR_MONK when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForMonk() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_PROFICIENCY_FOR_MONK, dndCharacter), "Your monk can be proficient with any one type of artisan's tools or any one musical instrument of your choice. Enter the musical instrument or select the artisan's tool option", getArtisanToolOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_MONK -> ENTER_SECOND_SKILL_FOR_MONK when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForMonkSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_MONK -> ENTER_SECOND_SKILL_FOR_MONK when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForMonkInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_MONK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_PALADIN -> ENTER_SECOND_SKILL_FOR_PALADIN when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForPaladin() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_PALADIN -> ENTER_FIRST_SKILL_FOR_MONK when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForPaladinSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_PALADIN -> ENTER_FIRST_SKILL_FOR_PALADIN when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForPaladinInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_PALADIN -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForPaladin() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_PALADIN -> ENTER_SECOND_SKILL_FOR_PALADIN when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForPaladinSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_PALADIN -> ENTER_SECOND_SKILL_FOR_PALADIN when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForPaladinInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_PALADIN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_RANGER -> ENTER_SECOND_SKILL_FOR_RANGER when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForRanger() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_RANGER -> ENTER_FIRST_SKILL_FOR_RANGER when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForRangerSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_RANGER -> ENTER_FIRST_SKILL_FOR_RANGER when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForRangerInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_RANGER -> ENTER_THIRD_SKILL_FOR_RANGER when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForRanger() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseThirdSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_RANGER -> ENTER_SECOND_SKILL_FOR_RANGER when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForRangerSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_RANGER -> ENTER_SECOND_SKILL_FOR_RANGER when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForRangerInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_RANGER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_RANGER -> ENTER_FAVOURED_ENEMY_FOR_RANGER when the skill is valid")
    void heroCreationAnswer_chooseThirdSkillForRanger() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_FAVOURED_ENEMY_FOR_RANGER, dndCharacter), "Let's set the favoured enemy of your ranger. You can enter two races of humanoid (such as gnolls and orcs) or choose one option from the list below", getFavouredEnemyOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_RANGER -> ENTER_THIRD_SKILL_FOR_RANGER when the skill is already applied")
    void heroCreationAnswer_chooseThirdSkillForRangerSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_RANGER -> ENTER_THIRD_SKILL_FOR_RANGER when the skill is invalid")
    void heroCreationAnswer_chooseThirdSkillForRangerInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_RANGER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FAVOURED_ENEMY_FOR_RANGER -> ENTER_FAVOURED_ENEMY_LANGUAGE_FOR_RANGER when the skill is valid")
    void heroCreationAnswer_chooseFavouredEnemyForRanger() {
        incomingState = new State(CREATE_HERO, ENTER_FAVOURED_ENEMY_FOR_RANGER, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_FAVOURED_ENEMY_LANGUAGE_FOR_RANGER, dndCharacter), "You also learn a language of your choice that is spoken by your favored enemies, enter this language. Or choose \"Leave empty\" if they speak none.", getLeaveEmptyOption());
        dndCharacter.setFeaturesAndTraits("test\n");
        String expectedFeaturesAndTraits = """
                        test
                        Favoured Enemy
                        Your favoured enemies are: oozes. You have advantage on Wisdom (Survival) checks to track them, as well as on Intelligence checks to recall information about them.
                        """;

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, "oozes");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFeaturesAndTraits, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("ENTER_FAVOURED_ENEMY_LANGUAGE_FOR_RANGER -> ENTER_FAVOURED_TERRAIN_FOR_RANGER when a language is entered")
    void heroCreationAnswer_chooseFavouredEnemyLanguageForRanger() {
        incomingState = new State(CREATE_HERO, ENTER_FAVOURED_ENEMY_LANGUAGE_FOR_RANGER, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_FAVOURED_TERRAIN_FOR_RANGER, dndCharacter), "Choose the favoured terrain of your ranger", getFavouredTerrainOptions());
        dndCharacter.getLanguages().add("Elvish");
        Set<String> expectedlanguages = Set.of("Elvish", "Celestial");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, "Celestial");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedlanguages, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("ENTER_FAVOURED_ENEMY_LANGUAGE_FOR_RANGER -> ENTER_FAVOURED_TERRAIN_FOR_RANGER when no language is entered")
    void heroCreationAnswer_chooseFavouredEnemyLanguageForRangerSkip() {
        incomingState = new State(CREATE_HERO, ENTER_FAVOURED_ENEMY_LANGUAGE_FOR_RANGER, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_FAVOURED_TERRAIN_FOR_RANGER, dndCharacter), "Choose the favoured terrain of your ranger", getFavouredTerrainOptions());
        dndCharacter.getLanguages().add("Elvish");
        Set<String> expectedLanguages = Set.of("Elvish");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, "Leave empty ");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedLanguages, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("ENTER_FAVOURED_TERRAIN_FOR_RANGER -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseFavouredTerrainForRanger() {
        incomingState = new State(CREATE_HERO, ENTER_FAVOURED_TERRAIN_FOR_RANGER, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignment, getAlignmentOptions());
        dndCharacter.setFeaturesAndTraits("test\n");
        String expectedFeaturesAndTraits = """
                        test
                        Natural Explorer
                        You are particularly familiar with swamp and are adept at traveling and surviving in such regions. When you make an Intelligence or Wisdom check related to swamp, your proficiency bonus is doubled if you are using a skill that you're proficient in.
                        While traveling for an hour or more in swamp, you gain the following benefits:
                        Difficult terrain doesn't slow your group's travel.
                        Your group can't become lost except by magical means.
                        Even when you are engaged in another activity while traveling (such as foraging, navigating, or tracking), you remain alert to danger.
                        If you are traveling alone, you can move stealthily at a normal pace.
                        When you forage, you find twice as much food as you normally would.
                        While tracking other creatures, you also learn their exact number, their sizes, and how long ago they passed through the area.
                        """;

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, "Swamp");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFeaturesAndTraits, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_ROGUE -> ENTER_SECOND_SKILL_FOR_ROGUE when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForRogue() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_ROGUE -> ENTER_FIRST_SKILL_FOR_ROGUE when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForRogueSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_ROGUE -> ENTER_FIRST_SKILL_FOR_ROGUE when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForRogueInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_ROGUE -> ENTER_THIRD_SKILL_FOR_ROGUE when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForRogue() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseThirdSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_ROGUE -> ENTER_SECOND_SKILL_FOR_ROGUE when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForRogueSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_ROGUE -> ENTER_SECOND_SKILL_FOR_ROGUE when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForRogueInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_ROGUE -> ENTER_FOURTH_SKILL_FOR_ROGUE when the skill is valid")
    void heroCreationAnswer_chooseThirdSkillForRogue() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains("Choose the fourth skill your rogue will be proficient in \n"));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_ROGUE -> ENTER_THIRD_SKILL_FOR_ROGUE when the skill is already applied")
    void heroCreationAnswer_chooseThirdSkillForRogueSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_THIRD_SKILL_FOR_ROGUE -> ENTER_THIRD_SKILL_FOR_ROGUE when the skill is invalid")
    void heroCreationAnswer_chooseThirdSkillForRogueInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_THIRD_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FOURTH_SKILL_FOR_ROGUE -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseFourthSkillForRogue() {
        incomingState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_FIRST_EXPERTISE_FOR_ROGUE, dndCharacter), "Let's set rogue expertise. Choose a skill, your hero's proficiency bonus will be doubled for any ability check that uses it", List.of("Stealth"));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_FOURTH_SKILL_FOR_ROGUE -> ENTER_FOURTH_SKILL_FOR_ROGUE when the skill is already applied")
    void heroCreationAnswer_chooseFourthSkillForRogueSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FOURTH_SKILL_FOR_ROGUE -> ENTER_FOURTH_SKILL_FOR_ROGUE when the skill is invalid")
    void heroCreationAnswer_chooseFourthSkillForRogueInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FOURTH_SKILL_FOR_ROGUE, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_EXPERTISE_FOR_ROGUE -> ENTER_SECOND_EXPERTISE_FOR_ROGUE when the skill is valid")
    void heroCreationAnswer_chooseFirstExpertiseForRogue() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_EXPERTISE_FOR_ROGUE, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_SECOND_EXPERTISE_FOR_ROGUE, dndCharacter), "Your hero can also be expert with another skill or with thieves' tools. Choose your option", List.of("Arcana", "Thieves' tools"));
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        dndCharacter.getSkillsWithProficiency().put(ARCANA, PROFICIENT);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillExpertise(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_FIRST_EXPERTISE_FOR_ROGUE -> ENTER_FIRST_EXPERTISE_FOR_ROGUE when hero is not proficient with the skill")
    void heroCreationAnswer_chooseFirstExpertiseForRogueNotProficient() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_EXPERTISE_FOR_ROGUE, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_FIRST_EXPERTISE_FOR_ROGUE, dndCharacter), couldNotUpgradeProficiency, List.of("Deception"));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillExpertise(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_FIRST_EXPERTISE_FOR_ROGUE -> ENTER_FIRST_EXPERTISE_FOR_ROGUE when the skill is invalid")
    void heroCreationAnswer_chooseFirstExpertiseForRogueInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_EXPERTISE_FOR_ROGUE, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_FIRST_EXPERTISE_FOR_ROGUE, dndCharacter), wrongSkill, List.of("Deception"));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillExpertise(dndCharacter, userAnswerSkill);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_EXPERTISE_FOR_ROGUE -> ENTER_ALIGNMENT when Thief`s tools are chosen")
    void heroCreationAnswer_chooseSecondExpertiseForRogueThievesTools() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_EXPERTISE_FOR_ROGUE, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignment, getAlignmentOptions());
        dndCharacter.setFeaturesAndTraits("test\n");
        String expectedFeaturesAndTraits = """
                test
                Expertise
                Your proficiency bonus is doubled for any ability check you make that uses thieves' tools.
                """;

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, "Thieves' tools ");

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFeaturesAndTraits, dndCharacter.getFeaturesAndTraits());

    }


    @Test
    @DisplayName("ENTER_SECOND_EXPERTISE_FOR_ROGUE -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondExpertiseForRogueSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_EXPERTISE_FOR_ROGUE, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignment, getAlignmentOptions());
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillExpertise(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_EXPERTISE_FOR_ROGUE -> ENTER_SECOND_EXPERTISE_FOR_ROGUE when hero is not proficient with the skill")
    void heroCreationAnswer_chooseSecondExpertiseForRogueNotProficient() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_EXPERTISE_FOR_ROGUE, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        dndCharacter.getSkillsWithProficiency().put(ANIMAL_HANDLING, EXPERTISE);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_SECOND_EXPERTISE_FOR_ROGUE, dndCharacter), couldNotUpgradeProficiency, List.of("Deception", "Thieves' tools"));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillExpertise(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_EXPERTISE_FOR_ROGUE -> ENTER_SECOND_EXPERTISE_FOR_ROGUE when the skill is invalid")
    void heroCreationAnswer_chooseSecondExpertiseForRogueInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_EXPERTISE_FOR_ROGUE, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        dndCharacter.getSkillsWithProficiency().put(ANIMAL_HANDLING, EXPERTISE);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_SECOND_EXPERTISE_FOR_ROGUE, dndCharacter), wrongSkill, List.of("Deception", "Thieves' tools"));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillExpertise(dndCharacter, userAnswerSkill);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_SORCERER -> ENTER_SECOND_SKILL_FOR_SORCERER when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForSorcerer() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_SORCERER -> ENTER_FIRST_SKILL_FOR_SORCERER when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForSorcererSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_SORCERER -> ENTER_FIRST_SKILL_FOR_SORCERER when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForSorcererInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_SORCERER -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForSorcerer() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_SORCERER -> ENTER_SECOND_SKILL_FOR_SORCERER when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForSorcererSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_SORCERER -> ENTER_SECOND_SKILL_FOR_SORCERER when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForSorcererInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_SORCERER, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_WARLOCK -> ENTER_SECOND_SKILL_FOR_WARLOCK when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForWarlock() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_WARLOCK -> ENTER_FIRST_SKILL_FOR_WARLOCK when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForWarlockSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_WARLOCK -> ENTER_FIRST_SKILL_FOR_WARLOCK when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForWarlockInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_WARLOCK -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForWarlock() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_WARLOCK -> ENTER_SECOND_SKILL_FOR_WARLOCK when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForWarlockSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_WARLOCK -> ENTER_SECOND_SKILL_FOR_WARLOCK when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForWarlockInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_WIZARD -> ENTER_SECOND_SKILL_FOR_WIZARD when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForWizard() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(chooseSecondSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_WIZARD -> ENTER_FIRST_SKILL_FOR_WIZARD when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForWizardSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FIRST_SKILL_FOR_WIZARD -> ENTER_FIRST_SKILL_FOR_WIZARD when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForWizardInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_WARLOCK -> ENTER_ALIGNMENT when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForWizard() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WARLOCK, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter), chooseAlignmentTextAnswer, getAlignmentOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_WIZARD -> ENTER_SECOND_SKILL_FOR_WIZARD when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForWizardSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStealth);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerStealth);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(alreadyHaveProficiencyInThisSkillTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_SECOND_SKILL_FOR_WIZARD -> ENTER_SECOND_SKILL_FOR_WIZARD when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForWizardInvalidSkill() {
        incomingState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, dndCharacter);
        expectedState = new State(CREATE_HERO, ENTER_SECOND_SKILL_FOR_WIZARD, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerSkill);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerSkill);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(wrongInputTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }
}
