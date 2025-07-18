package herodeletiontests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.deletehero.DeleteHero;

import java.util.List;

import static testdata.TestData.chatID;
import static testdata.TestData.getMockCharacterAna;
import static testdata.TestData.getMockCharacterHanzo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static player.userinputhandler.commands.deletehero.Options.getYesOrNoOptions;
import static player.userinputhandler.enums.Processes.DELETE_HERO;
import static player.userinputhandler.enums.Steps.CONFIRMATION;
import static player.userinputhandler.enums.Steps.SELECT_A_HERO_TO_BE_DELETED;
import static player.userinputhandler.enums.Steps.SET_WEIGHT;

public class HeroDeletionTest {
    private static final CharacterDao characterDaoMock = mock(CharacterDao.class);
    private static final DeleteHero deleteHero = new DeleteHero(characterDaoMock);
    private static final DndCharacter dndCharacter = new DndCharacter();
    private static State incomingState;
    private static State expectedState;
    private static Response expectedResponse;
    private static String userAnswer;

    @Test
    @DisplayName("Deletion confirmation response")
    void confirmDeletionResponse() {

        expectedState = new State(DELETE_HERO, CONFIRMATION, null);
        expectedResponse = new Response(expectedState, "Are you sure you want to delete your character? Note, that once they are dismissed, they will be gone forever, no coming back", getYesOrNoOptions());

        assertEquals(expectedResponse, deleteHero.deleteHero());
    }

    @Test
    @DisplayName("Accept deletion response")
    void acceptDeletionResponse() {

        Character mockCharacterAna = getMockCharacterAna();
        Character mockCharacterHanzo = getMockCharacterHanzo();
        List<Character> userCharacters = List.of(mockCharacterAna, mockCharacterHanzo);
        when(characterDaoMock.findByChatId(chatID)).thenReturn(userCharacters);

        userAnswer = "Yes";
        incomingState = new State(DELETE_HERO, CONFIRMATION, dndCharacter);
        expectedState = new State(DELETE_HERO, SELECT_A_HERO_TO_BE_DELETED, incomingState.getDndCharacter());
        List<String> userCharacterIDAndName = List.of("Character ID: 31, Name: Ana Amari \n", "Character ID: 32, Name: Hanzo Shimada \n");
        expectedResponse = new Response(expectedState, "Enter the ID of the character you want to delete: \n" + userCharacterIDAndName, null, false, List.of());

        assertEquals(expectedResponse, deleteHero.heroDeletionAnswer(incomingState, chatID, userAnswer));
    }

    @Test
    @DisplayName("Decline deletion response")
    void declineDeletionResponse() {

        userAnswer = "No";
        incomingState = new State(DELETE_HERO, CONFIRMATION, dndCharacter);
        expectedState = null;
        expectedResponse = new Response(expectedState, "Alright, dismissal has been cancelled");

        assertEquals(expectedResponse, deleteHero.heroDeletionAnswer(incomingState, chatID, userAnswer));
    }

    @Test
    @DisplayName("Decline deletion response: Trim test")
    void declineDeletionResponseWithUserAnswerTrim() {

        userAnswer = "No ";
        incomingState = new State(DELETE_HERO, CONFIRMATION, dndCharacter);
        expectedState = null;
        expectedResponse = new Response(expectedState, "Alright, dismissal has been cancelled");

        assertEquals(expectedResponse, deleteHero.heroDeletionAnswer(incomingState, chatID, userAnswer));
    }

    @Test
    @DisplayName("Invalid deletion confirmation user answer response")
    void invalidDeletionUserAnswerResponse() {

        userAnswer = "Test";
        incomingState = new State(DELETE_HERO, CONFIRMATION, dndCharacter);
        expectedState = new State(DELETE_HERO, CONFIRMATION, incomingState.getDndCharacter());
        expectedResponse = new Response(expectedState, "Sorry, I cannot understand your input. Please, enter Yes or No", getYesOrNoOptions());

        assertEquals(expectedResponse, deleteHero.heroDeletionAnswer(incomingState, chatID, userAnswer));
    }

    @Test
    @DisplayName("Invalid character ID response")
    void invalidCharacterIDResponse() {

        userAnswer = "Test";
        incomingState = new State(DELETE_HERO, SELECT_A_HERO_TO_BE_DELETED, dndCharacter);
        expectedState = new State(DELETE_HERO, SELECT_A_HERO_TO_BE_DELETED, incomingState.getDndCharacter());
        expectedResponse = new Response(expectedState, "Couldn't find a hero with this ID. Please enter an available one.");

        assertEquals(expectedResponse, deleteHero.heroDeletionAnswer(incomingState, chatID, userAnswer));
    }

    @Test
    @DisplayName("Invalid deletion step")
    void invalidDeletionStepResponse() {

        incomingState = new State(DELETE_HERO, SET_WEIGHT, dndCharacter);
        expectedState = new State(DELETE_HERO, CONFIRMATION, incomingState.getDndCharacter());
        expectedResponse = new Response(expectedState, "Wrong deletion step ID. Redirecting back to the beginning of the character deletion");

        assertEquals(expectedResponse, deleteHero.heroDeletionAnswer(incomingState, chatID, userAnswer));
    }
}
