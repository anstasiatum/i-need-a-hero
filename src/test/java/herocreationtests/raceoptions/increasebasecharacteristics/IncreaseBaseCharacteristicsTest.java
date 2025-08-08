package herocreationtests.raceoptions.increasebasecharacteristics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncreaseBaseCharacteristics;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncrementAbility;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static player.userinputhandler.commands.createnewhero.Options.getAllSkillOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptionsWithoutSpecified;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseSecondAbilityScore;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseSkillProficiency;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_SKILL_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

public class IncreaseBaseCharacteristicsTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private final IncrementAbility incrementAbilitySpy = spy(IncrementAbility.class);
    private final IncreaseBaseCharacteristics increaseBaseCharacteristics = new IncreaseBaseCharacteristics(incrementAbilitySpy);
    private State expectedState;
    private Response expectedResponse;
    private Response actualResponse;

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(12);
        dndCharacter.setConstitution(14);
        dndCharacter.setIntelligence(16);
        dndCharacter.setWisdom(18);
        dndCharacter.setCharisma(20);
    }


    static Stream<AbilityTestData> provideAbilities() {
        return Stream.of(
                new AbilityTestData("Strength", 10, 11, Characteristics.STRENGTH, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(Characteristics.STRENGTH)),
                new AbilityTestData("Dexterity", 12, 13, Characteristics.DEXTERITY, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, chooseSecondAbilityScore, getBasicAbilityOptionsWithoutSpecified(Characteristics.DEXTERITY)),
                new AbilityTestData("Constitution", 14, 15, Characteristics.CONSTITUTION, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, CHOOSE_FIRST_SKILL_FOR_HALF_ELF, chooseSkillProficiency, getAllSkillOptions()),
                new AbilityTestData("Intelligence", 16, 17, Characteristics.INTELLIGENCE, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, chooseSkillProficiency, getAllSkillOptions()),
                new AbilityTestData(" charisma ", 20, 21, Characteristics.CHARISMA, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, chooseSkillProficiency, getAllSkillOptions())
        );
    }

    @ParameterizedTest(name = "Increase {0} from step {3}")
    @MethodSource("provideAbilities")
    @DisplayName("Valid input tests for all increaseBaseCharacteristics steps")
    void increaseBaseAbilityScoreAllSteps(AbilityTestData data) {
        // Arrange
        expectedState = new State(CREATE_HERO, data.getExpectedNextStep(), dndCharacter);
        expectedResponse = new Response(expectedState, data.getExpectedMessage(), data.getExpectedOptions());

        // Act
        actualResponse = increaseBaseCharacteristics.increaseBaseCharacteristics(dndCharacter, data.getStep(), data.getInput());

        // Assert
        int actualValue = getStatValue(dndCharacter, data.getCharacteristic());
        assertEquals(data.getExpectedValue(), actualValue);
        assertEquals(expectedResponse, actualResponse);
        verify(incrementAbilitySpy, times(1)).incrementAbility(any(), any());
    }

    @Test
    @DisplayName("Increase characteristics first for Half Elf: invalid user answer")
    void increaseCharacteristicsFirstForHalfElfWrongUserAnswer() {
        expectedState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
        expectedResponse = new Response(expectedState, wrongInput, getBasicAbilityOptions());

        actualResponse = increaseBaseCharacteristics.increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "test");

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());

        assertEquals(expectedResponse, actualResponse);
        verify(incrementAbilitySpy, never()).incrementAbility(any(), any());
    }

    @Test
    @DisplayName("Increase characteristics first for Human: invalid user answer")
    void increaseCharacteristicsFirstForHumanWrongUserAnswer() {
        expectedState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
        expectedResponse = new Response(expectedState, wrongInput, getBasicAbilityOptions());

        actualResponse = increaseBaseCharacteristics.increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, "test");

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());

        assertEquals(expectedResponse, actualResponse);
        verify(incrementAbilitySpy, never()).incrementAbility(any(), any());
    }

    @Test
    @DisplayName("Increase characteristics second for Half Elf: invalid user answer")
    void increaseCharacteristicsSecondForHalfElfWrongUserAnswer() {
        expectedState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
        expectedResponse = new Response(expectedState, wrongInput, getBasicAbilityOptions());

        actualResponse = increaseBaseCharacteristics.increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "test");

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());

        assertEquals(expectedResponse, actualResponse);
        verify(incrementAbilitySpy, never()).incrementAbility(any(), any());
    }

    @ParameterizedTest(name = "Increase {0} second for Human")
    @MethodSource("provideAbilities")
    @DisplayName("Increase base ability score for Human - parameterized")
    void increaseBaseAbilitySecondForHuman(AbilityTestData data) {
        expectedState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
        expectedResponse = new Response(expectedState,  chooseSkillProficiency, getAllSkillOptions());

        actualResponse = increaseBaseCharacteristics.increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, data.getInput());

        int actualCharacteristicsValue = getStatValue(dndCharacter, data.getCharacteristic());
        assertEquals(data.getExpectedValue(), actualCharacteristicsValue);
        assertEquals(expectedResponse, actualResponse);
        verify(incrementAbilitySpy, times(1)).incrementAbility(any(), any());
    }


    @Test
    @DisplayName("Increase Dexterity second for Half Elf: trim test")
    void increaseDexteritySecondForHumanTrimTest() {
        expectedState = new State(CREATE_HERO, CHOOSE_ONE_SKILL_FOR_VARIANT_HUMAN, dndCharacter);
        expectedResponse = new Response(expectedState,  chooseSkillProficiency, getAllSkillOptions());

        actualResponse = increaseBaseCharacteristics.increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, " Dexterity ");

        assertEquals(13, dndCharacter.getDexterity());
        assertEquals(expectedResponse, actualResponse);
        verify(incrementAbilitySpy, times(1)).incrementAbility(any(), any());
    }

    @Test
    @DisplayName("Increase characteristics second for Human: invalid user answer")
    void increaseCharacteristicsSecondForHumanWrongUserAnswer() {
        expectedState = new State(CREATE_HERO, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
        expectedResponse = new Response(expectedState, wrongInput, getBasicAbilityOptions());

        actualResponse = increaseBaseCharacteristics.increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "test");

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());

        assertEquals(expectedResponse, actualResponse);
        verify(incrementAbilitySpy, never()).incrementAbility(any(), any());
    }

    @Test
    @DisplayName("Increase characteristics: invalid step")
    void increaseCharacteristicsInvalidStep() {
        expectedState = new State(CREATE_HERO, CHOOSE_RACE, dndCharacter);
        expectedResponse = new Response(expectedState, "Wrong step");

        actualResponse = increaseBaseCharacteristics.increaseBaseCharacteristics(dndCharacter, SET_PERSONALITY_TRAITS, "test");

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());

        assertEquals(expectedResponse, actualResponse);
        verify(incrementAbilitySpy, never()).incrementAbility(any(), any());
    }

    private int getStatValue(DndCharacter character, Characteristics stat) {
        return switch (stat) {
            case STRENGTH -> character.getStrength();
            case DEXTERITY -> character.getDexterity();
            case CONSTITUTION -> character.getConstitution();
            case INTELLIGENCE -> character.getIntelligence();
            case WISDOM -> character.getWisdom();
            case CHARISMA -> character.getCharisma();
        };
    }
}

