import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.characteristicsgenerator.BaseCharacteristicsValuesGenerator;
import player.dndcharacter.race.RaceFactory;
import player.userinputhandler.BotAnswer;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.StateHolder;
import player.userinputhandler.UserInputHandler;
import player.userinputhandler.commands.createnewhero.AddSkillProficiency;
import player.userinputhandler.commands.createnewhero.ChooseCharacteristicsSettingMethod;
import player.userinputhandler.commands.createnewhero.CreateNewHero;
import player.userinputhandler.commands.createnewhero.SelectRace;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncreaseBaseCharacteristics;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncrementAbility;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;
import player.userinputhandler.commands.deletehero.DeleteHero;
import player.userinputhandler.commands.printhero.PrintHero;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Processes.DELETE_HERO;
import static player.userinputhandler.enums.Processes.PRINT_HERO;
import static player.userinputhandler.enums.Steps.CONFIRMATION;
import static player.userinputhandler.enums.Steps.ENTER_NAME;
import static player.userinputhandler.enums.Steps.ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD;
import static player.userinputhandler.enums.Steps.ENTER_THIRD_MUSICAL_INSTRUMENT_FOR_BARD;
import static player.userinputhandler.enums.Steps.PRINT_PDF;
import static player.userinputhandler.enums.Steps.SELECT_A_HERO_TO_BE_DELETED;
import static player.userinputhandler.enums.Steps.SET_AGE;

public class UserInputHandlerTest {
    private String userAnswer;
    private final Long chatId = 123L;
    private final CharacterDao characterDao = new CharacterDaoImpl();
    private final StateHolder mockStateHolder = mock(StateHolder.class);
    private final PrintHero mockPrintHero = mock(PrintHero.class);
    private final DeleteHero mockDeleteHero = mock(DeleteHero.class);
    private final BaseCharacteristicsValuesGenerator baseCharacteristicsValuesGenerator = new BaseCharacteristicsValuesGenerator();
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethodSpy = spy(new ChooseCharacteristicsSettingMethod(baseCharacteristicsValuesGenerator));
    private final IncrementAbility incrementAbility = new IncrementAbility();
    private final IncreaseBaseCharacteristics increaseBaseCharacteristicsSpy = spy(new IncreaseBaseCharacteristics(incrementAbility));
    private final RaceFactory raceFactory = new RaceFactory();
    private final SelectRace selectRace = new SelectRace(raceFactory);
    private final AddSkillProficiency skillProficiency = new AddSkillProficiency();
    private final UserInputHandler handleUserInput = new UserInputHandler(mockStateHolder, new DeleteHero(characterDao), new CreateNewHero(characterDao, characteristicsSettingMethodSpy, increaseBaseCharacteristicsSpy, selectRace, skillProficiency), mockPrintHero);
    private final UserInputHandler handleUserInputWithMockedDeletion = new UserInputHandler(mockStateHolder, mockDeleteHero, new CreateNewHero(characterDao, characteristicsSettingMethodSpy, increaseBaseCharacteristicsSpy, selectRace, skillProficiency), mockPrintHero);
    private final DndCharacter dndCharacter = new DndCharacter();

    @Test
    @DisplayName("Return the list of commands when chatState is null and no command was entered")
    void checkResponseForChatStateNullAndUnknownCommand() {
        userAnswer = "/test";
        String expectedTexAnswer = """
                Sorry, I don't understand your request. Here is my list of commands:
                /newhero
                /dismisshero
                /printhero
                """;
        State incomingState = null;
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        BotAnswer expectedBotAnswer = new BotAnswer(expectedTexAnswer, null, false, List.of());
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, never()).updateStateByChatId(any(), any());
    }

    @Test
    @DisplayName("Launch hero creation, when /newhero is entered. ChatState is null")
    void launchHeroCreationWhenCommandIsEnteredChatStateIsNull() {
        userAnswer = "/newhero";
        String expectedTextAnswer = "Alright, let's name your future hero!";
        State incomingState = null;
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        BotAnswer expectedBotAnswer = new BotAnswer(expectedTextAnswer, null, false, List.of());
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(eq(chatId), argThat(n -> n.getProcess() == CREATE_HERO && n.getStepId() == ENTER_NAME && n.getDndCharacter() != null));
    }

    @Test
    @DisplayName("Launch hero creation, when /newhero is entered. ChatState is not null")
    void launchHeroCreationWhenCommandIsEnteredChatStateIsNotNull() {
        userAnswer = "/newhero";
        String expectedTexAnswer = "Alright, let's name your future hero!";
        State incomingState = new State(DELETE_HERO, CONFIRMATION, dndCharacter);
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        BotAnswer expectedBotAnswer = new BotAnswer(expectedTexAnswer, null, false, List.of());
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(eq(chatId), argThat(n -> n.getProcess() == CREATE_HERO && n.getStepId() == ENTER_NAME && n.getDndCharacter() != null));
    }

    @Test
    @DisplayName("Launch hero dismissal, when /dismisshero is entered. ChatState is null")
    void launchHeroDeletionWhenCommandIsEnteredChatStateIsNull() {
        userAnswer = "/dismisshero";
        String expectedTexAnswer = "Are you sure you want to delete your character? Note, that once they are dismissed, they will be gone forever, no coming back";
        State incomingState = null;
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        BotAnswer expectedBotAnswer = new BotAnswer(expectedTexAnswer, null, true, List.of("Yes", "No"));
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(chatId, new State(DELETE_HERO, CONFIRMATION, null));
    }

    @Test
    @DisplayName("Launch hero dismissal, when /dismisshero is entered. ChatState is not null")
    void launchHeroDeletionWhenCommandIsEnteredChatStateIsNotNull() {
        userAnswer = "/dismisshero";
        String expectedTexAnswer = "Are you sure you want to delete your character? Note, that once they are dismissed, they will be gone forever, no coming back";
        State incomingState = new State(CREATE_HERO, SET_AGE, dndCharacter);
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        BotAnswer expectedBotAnswer = new BotAnswer(expectedTexAnswer, null, true, List.of("Yes", "No"));
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(chatId, new State(DELETE_HERO, CONFIRMATION, null));
    }

    @Test
    @DisplayName("Launch hero printing, when /printhero is entered. ChatState is null")
    void launchHeroPrintingWhenCommandIsEnteredChatStateIsNull() {
        userAnswer = "/printhero";
        State incomingState = null;
        State newState = new State(PRINT_HERO, PRINT_PDF, null);
        String textAnswer = "Ok, let's print your hero. First, enter the ID of the character you want to get a PDF version of\n";
        when(mockPrintHero.printHero(any())).thenReturn(new Response(newState, textAnswer));
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        BotAnswer expectedBotAnswer = new BotAnswer(textAnswer, null, false, List.of());
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(chatId, new State(PRINT_HERO, PRINT_PDF, null));
    }

    @Test
    @DisplayName("Launch hero printing, when /printhero is entered. ChatState is not null")
    void launchHeroPrintingWhenCommandIsEnteredChatStateIsNotNull() {
        userAnswer = "/printhero";
        State incomingState = new State(DELETE_HERO, CONFIRMATION, dndCharacter);
        State newState = new State(PRINT_HERO, PRINT_PDF, null);
        String textAnswer = "Ok, let's print your hero. First, enter the ID of the character you want to get a PDF version of\n";
        when(mockPrintHero.printHero(any())).thenReturn(new Response(newState, textAnswer));
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        BotAnswer expectedBotAnswer = new BotAnswer(textAnswer, null, false, List.of());
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(chatId, new State(PRINT_HERO, PRINT_PDF, null));
    }

    @Test
    @DisplayName("Check that the state is updated when continuing to create a hero")
    void checkStatueIsUpdatedWhenContinuingToCreateHero() {
        userAnswer = "test";
        State incomingState = new State(CREATE_HERO, ENTER_SECOND_MUSICAL_INSTRUMENT_FOR_BARD, dndCharacter);
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        String textAnswer = "Enter the third musical instrument your bard will be proficient with";
        BotAnswer expectedBotAnswer = new BotAnswer(textAnswer, null, false, List.of());
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(chatId, new State(CREATE_HERO, ENTER_THIRD_MUSICAL_INSTRUMENT_FOR_BARD, dndCharacter));
    }

    @Test
    @DisplayName("Check that the state is updated when continuing to print a hero")
    void checkStatueIsUpdatedWhenContinuingToPrintHero() {
        userAnswer = "99";
        State incomingState = new State(PRINT_HERO, PRINT_PDF, dndCharacter);
        File mockFile = new File("src/test/java/testdata/DndCharacter-test10134581991918731959.pdf");
        when(mockStateHolder.getStateByChatId(any())).thenReturn(incomingState);
        when(mockPrintHero.heroPrintingAnswer(any(), any(), any())).thenReturn(new Response(null, null, mockFile));
        BotAnswer expectedBotAnswer = new BotAnswer(null, mockFile, false, List.of());
        assertEquals(expectedBotAnswer, handleUserInput.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(chatId, null);
    }

    @Test
    @DisplayName("Check that the state is updated when continuing to delete a hero")
    void checkStatueIsUpdatedWhenContinuingToDeleteHero() {
        userAnswer = "Yes";
        State incomingState = new State(DELETE_HERO, CONFIRMATION, null);
        when(mockStateHolder.getStateByChatId(chatId)).thenReturn(incomingState);
        State newState = new State(DELETE_HERO, SELECT_A_HERO_TO_BE_DELETED, dndCharacter);
        when(mockDeleteHero.heroDeletionAnswer(any(), any(), any())).thenReturn(new Response(newState, "Enter the ID of the character you want to delete: \n"));
        BotAnswer expectedBotAnswer = new BotAnswer("Enter the ID of the character you want to delete: \n", null, false, List.of());
        assertEquals(expectedBotAnswer, handleUserInputWithMockedDeletion.handleUserInput(chatId, userAnswer));
        verify(mockStateHolder, times(1)).updateStateByChatId(chatId, new State(DELETE_HERO, SELECT_A_HERO_TO_BE_DELETED, dndCharacter));
    }
}
