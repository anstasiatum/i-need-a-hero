package herocreationtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.characteristicsgenerator.BaseCharacteristicsValuesGenerator;
import player.dndcharacter.characteristicsgenerator.CharacteristicsValues;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.ChooseCharacteristicsSettingMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ROLLING_CHARACTERISTICS_METHOD;
import static player.userinputhandler.enums.Steps.SET_STRENGTH;

public class ChooseCharacteristicsSettingMethodTest {
    private final BaseCharacteristicsValuesGenerator characteristicsValuesGeneratorMock = mock(BaseCharacteristicsValuesGenerator.class);
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod = new ChooseCharacteristicsSettingMethod(characteristicsValuesGeneratorMock);
    private final DndCharacter dndCharacter = new DndCharacter();

    @Test
    @DisplayName("Should transition to SET_STRENGTH with prompt when user chooses to roll themselves")
    void testUserRollsThemselves() {
        Response expectedResponse = new Response(new State(CREATE_HERO, SET_STRENGTH, dndCharacter), "Set strength:");

        Response actualResponse = characteristicsSettingMethod.chooseCharacteristicsSettingMethod("i'll roll myself", dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should generate characteristics and transition to SET_STRENGTH with prompt when bot rolls")
    void testBotRollsForUser() {
        CharacteristicsValues mockValueOne = new CharacteristicsValues(14, List.of(3, 5, 4, 5));
        CharacteristicsValues mockValueTwo = new CharacteristicsValues(15, List.of(6, 3, 3, 6));
        CharacteristicsValues mockValueThree = new CharacteristicsValues(10, List.of(5, 3, 1, 2));
        CharacteristicsValues mockValueFour = new CharacteristicsValues(14, List.of(1, 5, 3, 6));
        CharacteristicsValues mockValueFive = new CharacteristicsValues(17, List.of(6, 5, 3, 6));
        CharacteristicsValues mockValueSix = new CharacteristicsValues(16, List.of(6, 5, 3, 5));
        List<CharacteristicsValues> mockAnswerFromGenerator = List.of(mockValueOne, mockValueTwo, mockValueThree, mockValueFour, mockValueFive, mockValueSix);

        when(characteristicsValuesGeneratorMock.generateCharacteristics()).thenReturn(mockAnswerFromGenerator);

        String expectedTextAnswer = """
                Here is your result:\s
                [Characteristics Value = 14, Dice Values = [3, 5, 4, 5]\s
                , Characteristics Value = 15, Dice Values = [6, 3, 3, 6]\s
                , Characteristics Value = 10, Dice Values = [5, 3, 1, 2]\s
                , Characteristics Value = 14, Dice Values = [1, 5, 3, 6]\s
                , Characteristics Value = 17, Dice Values = [6, 5, 3, 6]\s
                , Characteristics Value = 16, Dice Values = [6, 5, 3, 5]\s
                ]
                 Set strength:""";
        Response expectedResponse = new Response(new State(CREATE_HERO, SET_STRENGTH, dndCharacter), expectedTextAnswer);

        Response actualResponse = characteristicsSettingMethod.chooseCharacteristicsSettingMethod("roll for me, bot", dndCharacter);
        assertEquals(expectedResponse, actualResponse);
        verify(characteristicsValuesGeneratorMock, times(1)).generateCharacteristics();
    }

    @Test
    @DisplayName("Should accept whitespace and case-insensitive input")
    void testWhitespaceAndCaseInsensitivity() {
        Response expectedResponse = new Response(new State(CREATE_HERO, SET_STRENGTH, dndCharacter), "Set strength:");
        Response actualResponse = characteristicsSettingMethod.chooseCharacteristicsSettingMethod("   I'll RoLL Myself  ", dndCharacter);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should handle invalid input and return to CHOOSE_ROLLING_CHARACTERISTICS_METHOD")
    void testInvalidInput() {
        Response expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ROLLING_CHARACTERISTICS_METHOD, dndCharacter), "Choose \"Roll for me, bot\" and the bot will roll the dice or \"I'll roll myself\" to roll the dice yourself", null, true, List.of("Roll for me, bot", "I'll roll myself"));
        Response actualResponse = characteristicsSettingMethod.chooseCharacteristicsSettingMethod("test", dndCharacter);

        assertEquals(expectedResponse, actualResponse);
    }

}
