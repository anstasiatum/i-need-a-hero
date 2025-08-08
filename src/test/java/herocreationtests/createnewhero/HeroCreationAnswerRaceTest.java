package herocreationtests.createnewhero;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static player.dndcharacter.dndcharacterenums.CharacterClass.getAllClasses;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.ARCANA;
import static player.userinputhandler.commands.createnewhero.Options.getAllSkillOptions;
import static player.userinputhandler.commands.createnewhero.Options.getClassOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDraconicAncestryOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseClass;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.CHOOSE_DRACONIC_ANCESTRY;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_BASE_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_HIGH_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.ENTER_FEAT_FOR_VARIANT_HUMAN;

public class HeroCreationAnswerRaceTest {
    private final CharacterDao characterJpaDao = new CharacterDaoImpl();
    private final BaseCharacteristicsValuesGenerator baseCharacteristicsValuesGenerator = new BaseCharacteristicsValuesGenerator();
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod = new ChooseCharacteristicsSettingMethod(baseCharacteristicsValuesGenerator);
    private final IncrementAbility incrementAbility = new IncrementAbility();
    private final IncreaseBaseCharacteristics increaseBaseCharacteristicsSpy = spy(new IncreaseBaseCharacteristics(incrementAbility));
    private final RaceFactory raceFactory = new RaceFactory();
    private final SelectRace selectRace = new SelectRace(raceFactory);
    private final SelectRace selectRaceSpy = spy(selectRace);
    private final AddSkill addSkillSpy = spy(new AddSkill());
    private final CharacterClassFactory characterClassFactory = new CharacterClassFactory();
    private final SelectClass selectClass = new SelectClass(characterClassFactory);
    private final BackgroundFactory backgroundFactory = new BackgroundFactory();
    private final SetBackground setBackground = new SetBackground(backgroundFactory);
    private final SetPirateFeature setPirateFeature = new SetPirateFeature();
    private final ChoosePossessionsForGuildMerchant choosePossessionsForGuildMerchant = new ChoosePossessionsForGuildMerchant();
    private final ChooseProficiencyForGuildMerchant chooseProficiencyForGuildMerchant = new ChooseProficiencyForGuildMerchant();
    private final CreateNewHero createNewHero = new CreateNewHero(characterJpaDao, characteristicsSettingMethod, increaseBaseCharacteristicsSpy, selectRaceSpy, addSkillSpy, selectClass, setBackground, setPirateFeature, choosePossessionsForGuildMerchant, chooseProficiencyForGuildMerchant);
    private final DndCharacter dndCharacter = new DndCharacter();
    private Response actualResponse;
    private State incomingState;
    private Response expectedResponse;
    private State expectedState;
    private final Long chatID = 123L;
    private final Faker faker = new Faker();
    private String userAnswer;
    private final String userAnswerArcana = "arcana";
    private final String userAnswerStrength = "strength";
    private final String userAnswerClimbing = "climbing";

    @Test
    @DisplayName("CHOOSE_RACE starts selectRace()")
    void heroCreationAnswer_chooseRace() {
        incomingState = new State(CREATE_HERO, CHOOSE_RACE, dndCharacter);
        String userAnswer = faker.name().fullName();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        verify(selectRaceSpy, times(1)).selectRace(userAnswer, dndCharacter);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertNotNull(actualResponse.getState().getStepId());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_DRACONIC_ANCESTRY -> CHOOSE_CLASS when the userAnswer is valid")
    void heroCreationAnswer_chooseValidDraconicAncestry() {
        incomingState = new State(CREATE_HERO, CHOOSE_DRACONIC_ANCESTRY, dndCharacter);
        String userAnswer = "black";
        dndCharacter.setFeaturesAndTraits("test ");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), chooseClass, getClassOptions());
        String expectedFeaturesAndTraits = """
                test Breath Weapon\s
                You can use your action to exhale destructive energy: Acid, 5 by 30 ft. line (Dex save). The DC for this saving throw is 5. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.
                 Damage Resistance\s
                 You have resistance to acid.""";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFeaturesAndTraits, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("CHOOSE_DRACONIC_ANCESTRY -> CHOOSE_DRACONIC_ANCESTRY when the userAnswer is invalid")
    void heroCreationAnswer_chooseInvalidDraconicAncestry() {
        incomingState = new State(CREATE_HERO, CHOOSE_DRACONIC_ANCESTRY, dndCharacter);
        String userAnswer = "pink";
        dndCharacter.setFeaturesAndTraits("test ");
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_DRACONIC_ANCESTRY, dndCharacter), "Enter your draconic ancestry", getDraconicAncestryOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedResponse, actualResponse);
        assertEquals("test ", dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF -> CHOOSE_CLASS")
    void heroCreationAnswer_chooseArtisansToolProficiencyForDwarf() {
        incomingState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF, dndCharacter);
        userAnswer = "cook utensils";
        dndCharacter.getToolProficiency().add("test");

        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n" + getAllClasses(), getClassOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedResponse, actualResponse);
        assertEquals(Set.of("test", "cook utensils"), dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("CHOOSE_LANGUAGE_FOR_HIGH_ELF -> CHOOSE_CLASS")
    void heroCreationAnswer_chooseLanguageForHighElf() {
        incomingState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HIGH_ELF, dndCharacter);
        userAnswer = "Infernal";
        dndCharacter.getLanguages().add("Common");

        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n" + getAllClasses(), getClassOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedResponse, actualResponse);
        assertEquals(Set.of("Infernal", "Common"), dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("CHOOSE_LANGUAGE_FOR_BASE_HUMAN -> CHOOSE_CLASS")
    void heroCreationAnswer_chooseLanguageForBaseHuman() {
        incomingState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_BASE_HUMAN, dndCharacter);
        userAnswer = "Infernal";
        dndCharacter.getLanguages().add("Common");

        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n" + getAllClasses(), getClassOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedResponse, actualResponse);
        assertEquals(Set.of("Infernal", "Common"), dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF starts increaseBaseCharacteristics()")
    void heroCreationAnswer_chooseFirstAbilityScoreForHalfElf() {
        incomingState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStrength);

        verify(increaseBaseCharacteristicsSpy, times(1)).increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, userAnswerStrength);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertNotNull(actualResponse.getState().getStepId());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF starts increaseBaseCharacteristics()")
    void heroCreationAnswer_chooseSecondAbilityScoreForHalfElf() {
        incomingState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStrength);

        verify(increaseBaseCharacteristicsSpy, times(1)).increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, userAnswerStrength);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertNotNull(actualResponse.getState().getStepId());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN starts increaseBaseCharacteristics()")
    void heroCreationAnswer_chooseFirstAbilityScoreForVariantHuman() {
        incomingState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStrength);

        verify(increaseBaseCharacteristicsSpy, times(1)).increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, userAnswerStrength);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertNotNull(actualResponse.getState().getStepId());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN starts increaseBaseCharacteristics()")
    void heroCreationAnswer_chooseSecondAbilityScoreForVariantHuman() {
        incomingState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerStrength);

        verify(increaseBaseCharacteristicsSpy, times(1)).increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, userAnswerStrength);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertNotNull(actualResponse.getState().getStepId());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("CHOOSE_FIRST_SKILL_FOR_HALF_ELF -> CHOOSE_SECOND_SKILL_FOR_HALF_ELF when the skill is valid")
    void heroCreationAnswer_chooseFirstSkillForHalfElf() {
        incomingState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
        expectedState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerArcana);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerArcana);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains("Enter the second skill your hero will be proficient in. Available ones:\n"));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("CHOOSE_FIRST_SKILL_FOR_HALF_ELF -> CHOOSE_FIRST_SKILL_FOR_HALF_ELF when the skill is already applied")
    void heroCreationAnswer_chooseFirstSkillForHalfElfSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(ARCANA, PROFICIENT);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter), "You already have proficiency in this skill. Choose another one", getAllSkillOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerArcana);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerArcana);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_FIRST_SKILL_FOR_HALF_ELF -> CHOOSE_FIRST_SKILL_FOR_HALF_ELF when the skill is invalid")
    void heroCreationAnswer_chooseFirstSkillForHalfElfInvalidSkill() {
        incomingState = new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, dndCharacter), "Cannot understand your input. Please enter a skill", getAllSkillOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerClimbing);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerClimbing);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_SECOND_SKILL_FOR_HALF_ELF -> CHOOSE_CLASS when the skill is valid")
    void heroCreationAnswer_chooseSecondSkillForHalfElf() {
        incomingState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), chooseClass, getClassOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerArcana);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerArcana);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_SECOND_SKILL_FOR_HALF_ELF -> CHOOSE_SECOND_SKILL_FOR_HALF_ELF when the skill is already applied")
    void heroCreationAnswer_chooseSecondSkillForHalfElfSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(ARCANA, PROFICIENT);
        expectedState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerArcana);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerArcana);
        assertEquals(expectedState, actualResponse.getState());
        assertEquals("You already have proficiency in this skill. Choose another one", actualResponse.getTextAnswer());
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("CHOOSE_SECOND_SKILL_FOR_HALF_ELF -> CHOOSE_SECOND_SKILL_FOR_HALF_ELF when the skill is invalid")
    void heroCreationAnswer_chooseSecondSkillForHalfElfInvalidSkill() {
        incomingState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, dndCharacter);
        expectedState = new State(CREATE_HERO, CHOOSE_SECOND_SKILL_FOR_HALF_ELF, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerClimbing);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerClimbing);
        assertEquals("Cannot understand your input. Please enter a skill", actualResponse.getTextAnswer());
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN -> CHOOSE_CLASS when the skill is valid")
    void heroCreationAnswer_chooseOneSkillForVariantHuman() {
        incomingState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), chooseClass, getClassOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerArcana);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerArcana);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN -> CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN when the skill is already applied")
    void heroCreationAnswer_chooseOneSkillForVariantHumanSkillAlreadyApplied() {
        incomingState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
        dndCharacter.getSkillsWithProficiency().put(ARCANA, PROFICIENT);
        expectedState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerArcana);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerArcana);
        assertEquals(expectedState, actualResponse.getState());
        assertEquals("You already have proficiency in this skill. Choose another one", actualResponse.getTextAnswer());
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN -> CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN when the skill is invalid")
    void heroCreationAnswer_chooseOneSkillForVariantHumanInvalidSkill() {
        incomingState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
        expectedState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerClimbing);

        verify(addSkillSpy, times(1)).addSkillProficiency(dndCharacter, userAnswerClimbing);
        assertEquals("Cannot understand your input. Please enter a skill", actualResponse.getTextAnswer());
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("ENTER_FEAT_FOR_VARIANT_HUMAN -> CHOOSE_CLASS")
    void heroCreationAnswer_chooseFeatForVariantHuman() {
        incomingState = new State(CREATE_HERO, ENTER_FEAT_FOR_VARIANT_HUMAN, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), chooseClass, getClassOptions());
        dndCharacter.setFeaturesAndTraits("Lucky. ");
        userAnswer = "Mobile.";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(expectedResponse, actualResponse);
        assertEquals("Lucky. Mobile.", dndCharacter.getFeaturesAndTraits());
    }
}
