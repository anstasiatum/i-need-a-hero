package heroprintingtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.printhero.PDFCreator;
import player.userinputhandler.commands.printhero.PrintHero;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Processes.PRINT_HERO;
import static player.userinputhandler.enums.Steps.PRINT_PDF;
import static player.userinputhandler.enums.Steps.SET_AGE;
import static testdata.TestData.getMockCharacterAna;
import static testdata.TestData.getMockCharacterHanzo;

public class HeroPrintingTest {

    private final Long chatID = 123L;
    private final CharacterDao characterDaoMock = mock(CharacterDao.class);
    private State incomingState;
    private State expectedState;
    private Response expectedResponse;
    private String userAnswer;
    private Integer characterID;
    private final PDFCreator createPDFMock = mock(PDFCreator.class);
    private final PrintHero printHero = new PrintHero(characterDaoMock, createPDFMock);
    private final DndCharacter dndCharacter = getMockCharacterAna().getDndCharacter();

    @Test
    @DisplayName("Get the list of available characters response")
    void getTheListOfAvailableCharactersResponse() {
        Character mockCharacterAna = getMockCharacterAna();
        Character mockCharacterHanzo = getMockCharacterHanzo();
        List<Character> userCharacters = List.of(mockCharacterAna, mockCharacterHanzo);
        when(characterDaoMock.findByChatId(chatID)).thenReturn(userCharacters);

        expectedState = new State(PRINT_HERO, PRINT_PDF, null);
        List<String> userCharacterIDAndName = List.of("Character ID: 31, Name: Ana Amari \n", "Character ID: 32, Name: Hanzo Shimada \n");
        expectedResponse = new Response(expectedState, "Ok, let's print your hero. First, enter the ID of the character you want to get a PDF version of\n" + userCharacterIDAndName);

        assertEquals(expectedResponse, printHero.printHero(chatID));
    }

    @Test
    @DisplayName("Get the list of available characters with exception response")
    void getTheListOfAvailableCharactersExceptionResponse() {
        when(characterDaoMock.findByChatId(chatID)).thenThrow(RuntimeException.class);

        incomingState = new State(PRINT_HERO, PRINT_PDF, dndCharacter);
        expectedState = new State(PRINT_HERO, null, null);
        expectedResponse = new Response(expectedState, "Something went wrong when I was getting the list of your characters");

        assertEquals(expectedResponse, printHero.printHero(chatID));
    }

    @Test
    @DisplayName("Get PDF successfully")
    void getPDFSuccessResponse() {
        userAnswer = "1";
        characterID = 1;
        File mockFile = new File("src/test/java/testdata/DndCharacter-test10134581991918731959.pdf");
        when(createPDFMock.createPDF(characterID, chatID)).thenReturn(mockFile);

        incomingState = new State(PRINT_HERO, PRINT_PDF, dndCharacter);
        expectedResponse = new Response(null, null, mockFile);

        assertEquals(expectedResponse, printHero.heroPrintingAnswer(incomingState, userAnswer, chatID));
    }

    @Test
    @DisplayName("Get PDF: Invalid Character ID")
    void getPDFWithInvalidCharacterIDResponse() {
        userAnswer = "test";

        incomingState = new State(PRINT_HERO, PRINT_PDF, dndCharacter);
        expectedState = new State(PRINT_HERO, PRINT_PDF, incomingState.getDndCharacter());
        expectedResponse = new Response(expectedState, "Couldn't find a hero with this ID. Please enter an available one.");

        assertEquals(expectedResponse, printHero.heroPrintingAnswer(incomingState, userAnswer, chatID));
    }

    @Test
    @DisplayName("Get PDF: Exception from the file generator")
    void getPDFWithExceptionFromSupportMethodResponse() {
        userAnswer = "1";
        characterID = 1;
        when(createPDFMock.createPDF(characterID, chatID)).thenThrow(RuntimeException.class);

        incomingState = new State(PRINT_HERO, PRINT_PDF, dndCharacter);
        expectedState = new State(PRINT_HERO, PRINT_PDF, incomingState.getDndCharacter());
        expectedResponse = new Response(expectedState, "Couldn't find a hero with this ID. Please enter an available one.");

        assertEquals(expectedResponse, printHero.heroPrintingAnswer(incomingState, userAnswer, chatID));
    }

    @Test
    @DisplayName("Get PDF: wrong Step ID")
    void getPDFWithWrongStepID() {
        userAnswer = "1";
        incomingState = new State(CREATE_HERO, SET_AGE, dndCharacter);
        expectedState = new State(PRINT_HERO, PRINT_PDF, incomingState.getDndCharacter());
        expectedResponse = new Response(expectedState, "Wrong printing step ID. Redirecting back to the character printing");

        assertEquals(expectedResponse, printHero.heroPrintingAnswer(incomingState, userAnswer, chatID));
    }
}
