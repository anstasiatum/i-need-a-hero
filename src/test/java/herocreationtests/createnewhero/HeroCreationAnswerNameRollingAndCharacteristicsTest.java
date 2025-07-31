package herocreationtests.createnewhero;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.characteristicsgenerator.BaseCharacteristicsValuesGenerator;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.AddSkillProficiency;
import player.userinputhandler.commands.createnewhero.ChooseCharacteristicsSettingMethod;
import player.userinputhandler.commands.createnewhero.CreateNewHero;
import player.userinputhandler.commands.createnewhero.SelectRace;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncreaseBaseCharacteristics;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncrementAbility;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static player.dndcharacter.dndcharacterenums.Race.getAllRaces;
import static player.userinputhandler.commands.createnewhero.Options.getCharacteristicsRollingMethodOptions;
import static player.userinputhandler.commands.createnewhero.Options.getRaceOptions;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;
import static player.userinputhandler.enums.Steps.CHOOSE_ROLLING_CHARACTERISTICS_METHOD;
import static player.userinputhandler.enums.Steps.ENTER_NAME;
import static player.userinputhandler.enums.Steps.SET_CHARISMA;
import static player.userinputhandler.enums.Steps.SET_CONSTITUTION;
import static player.userinputhandler.enums.Steps.SET_DEXTERITY;
import static player.userinputhandler.enums.Steps.SET_INTELLIGENCE;
import static player.userinputhandler.enums.Steps.SET_STRENGTH;
import static player.userinputhandler.enums.Steps.SET_WISDOM;

public class HeroCreationAnswerNameRollingAndCharacteristicsTest {
    private final CharacterDao characterJpaDao = new CharacterDaoImpl();
    private final BaseCharacteristicsValuesGenerator baseCharacteristicsValuesGenerator = new BaseCharacteristicsValuesGenerator();
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod = new ChooseCharacteristicsSettingMethod(baseCharacteristicsValuesGenerator);
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethodSpy = spy(characteristicsSettingMethod);
    private final IncrementAbility incrementAbility = new IncrementAbility();
    private final IncreaseBaseCharacteristics increaseBaseCharacteristics = new IncreaseBaseCharacteristics(incrementAbility);
    private final IncreaseBaseCharacteristics increaseBaseCharacteristicsSpy = spy(increaseBaseCharacteristics);
    private final SelectRace selectRace = new SelectRace();
    private final AddSkillProficiency addSkillProficiency = new AddSkillProficiency();
    private final CreateNewHero createNewHero = new CreateNewHero(characterJpaDao, characteristicsSettingMethodSpy, increaseBaseCharacteristicsSpy, selectRace, addSkillProficiency);
    private final DndCharacter dndCharacter = new DndCharacter();
    private Response actualResponse;
    private State incomingState;
    private Response expectedResponse;
    private final Long chatID = 123L;
    private final Faker faker = new Faker();
    private final String userAnswerWithSpecialSymbols = "! @ # $ % ^ & * ( ) - _ = + [ ] { } ; : ' \" , . / ? \\ | £ $ ¥ ¢ € + - * / = < > ≤ ≥ ± ∓ © ® ™ § ° é à ç ö ♥ 1";
    private final String userAnswerNumeric = String.valueOf(faker.number().numberBetween(3, 18));
    private final String userAnswerText = faker.harryPotter().quote();

    @Test
    @DisplayName("ENTER_NAME -> CHOOSE_ROLLING_CHARACTERISTICS_METHOD: Valid name")
    void heroCreationAnswer_enterName() {
        incomingState = new State(CREATE_HERO, ENTER_NAME, dndCharacter);
        String userAnswer = faker.name().fullName();
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ROLLING_CHARACTERISTICS_METHOD, dndCharacter), "Now let's get your base characteristics. You can roll them yourself or I will roll them for you", getCharacteristicsRollingMethodOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getCharacterName());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_NAME -> CHOOSE_ROLLING_CHARACTERISTICS_METHOD: Name with special characters")
    void heroCreationAnswer_enterNameWithSpecialCharacters() {
        incomingState = new State(CREATE_HERO, ENTER_NAME, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ROLLING_CHARACTERISTICS_METHOD, dndCharacter), "Now let's get your base characteristics. You can roll them yourself or I will roll them for you", getCharacteristicsRollingMethodOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerWithSpecialSymbols);

        assertEquals(userAnswerWithSpecialSymbols, dndCharacter.getCharacterName());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_ROLLING_CHARACTERISTICS_METHOD -> SET_STRENGTH")
    void heroCreationAnswer_chooseRollingCharacteristicsMethod() {
        incomingState = new State(CREATE_HERO, CHOOSE_ROLLING_CHARACTERISTICS_METHOD, dndCharacter);
        String userAnswer = "i'll roll myself";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        verify(characteristicsSettingMethodSpy, times(1)).chooseCharacteristicsSettingMethod(userAnswer, dndCharacter);
        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertNotNull(actualResponse.getState().getStepId());
        assertEquals(dndCharacter, actualResponse.getState().getDndCharacter());
        assertNotNull(actualResponse.getTextAnswer());
    }

    @Test
    @DisplayName("SET_STRENGTH -> SET_DEXTERITY when userAnswer is a number")
    void heroCreationAnswer_setNumericStrength() {
        incomingState = new State(CREATE_HERO, SET_STRENGTH, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_DEXTERITY, dndCharacter), "Set dexterity:");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerNumeric);

        assertEquals(Integer.parseInt(userAnswerNumeric), dndCharacter.getStrength());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_STRENGTH -> SET_STRENGTH when userAnswer is letters")
    void heroCreationAnswer_setInvalidStrength() {
        incomingState = new State(CREATE_HERO, SET_STRENGTH, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_STRENGTH, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerText);

        assertEquals(0, dndCharacter.getStrength());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_STRENGTH -> SET_STRENGTH when userAnswer is special symbols")
    void heroCreationAnswer_setInvalidStrengthWithSpecialSymbols() {
        incomingState = new State(CREATE_HERO, SET_STRENGTH, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_STRENGTH, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerWithSpecialSymbols);

        assertEquals(0, dndCharacter.getStrength());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_DEXTERITY -> SET_CONSTITUTION when userAnswer is a number")
    void heroCreationAnswer_setNumericDexterity() {
        incomingState = new State(CREATE_HERO, SET_DEXTERITY, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_CONSTITUTION, dndCharacter), "Set constitution:");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerNumeric);

        assertEquals(Integer.parseInt(userAnswerNumeric), dndCharacter.getDexterity());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_DEXTERITY -> SET_DEXTERITY when userAnswer is letters")
    void heroCreationAnswer_setInvalidDexterity() {
        incomingState = new State(CREATE_HERO, SET_DEXTERITY, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_DEXTERITY, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerText);

        assertEquals(0, dndCharacter.getDexterity());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_DEXTERITY -> SET_DEXTERITY when userAnswer is special symbols")
    void heroCreationAnswer_setInvalidDexterityWithSpecialSymbols() {
        incomingState = new State(CREATE_HERO, SET_DEXTERITY, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_DEXTERITY, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerWithSpecialSymbols);

        assertEquals(0, dndCharacter.getDexterity());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_CONSTITUTION -> SET_INTELLIGENCE when userAnswer is a number")
    void heroCreationAnswer_setNumericConstitution() {
        incomingState = new State(CREATE_HERO, SET_CONSTITUTION, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_INTELLIGENCE, dndCharacter), "Set intelligence:");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerNumeric);

        assertEquals(Integer.parseInt(userAnswerNumeric), dndCharacter.getConstitution());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_CONSTITUTION -> SET_CONSTITUTION when userAnswer is letters")
    void heroCreationAnswer_setInvalidConstitution() {
        incomingState = new State(CREATE_HERO, SET_CONSTITUTION, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_CONSTITUTION, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerText);

        assertEquals(0, dndCharacter.getConstitution());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_CONSTITUTION -> SET_CONSTITUTION when userAnswer is special symbols")
    void heroCreationAnswer_setInvalidConstitutionWithSpecialSymbols() {
        incomingState = new State(CREATE_HERO, SET_CONSTITUTION, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_CONSTITUTION, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerWithSpecialSymbols);

        assertEquals(0, dndCharacter.getConstitution());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_INTELLIGENCE -> SET_WISDOM when userAnswer is a number")
    void heroCreationAnswer_setNumericIntelligence() {
        incomingState = new State(CREATE_HERO, SET_INTELLIGENCE, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_WISDOM, dndCharacter), "Set wisdom:");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerNumeric);

        assertEquals(Integer.parseInt(userAnswerNumeric), dndCharacter.getIntelligence());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_INTELLIGENCE -> SET_INTELLIGENCE when userAnswer is letters")
    void heroCreationAnswer_setInvalidIntelligence() {
        incomingState = new State(CREATE_HERO, SET_INTELLIGENCE, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_INTELLIGENCE, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerText);

        assertEquals(0, dndCharacter.getIntelligence());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_INTELLIGENCE -> SET_INTELLIGENCE when userAnswer is special symbols")
    void heroCreationAnswer_setInvalidIntelligenceWithSpecialSymbols() {
        incomingState = new State(CREATE_HERO, SET_INTELLIGENCE, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_INTELLIGENCE, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerWithSpecialSymbols);

        assertEquals(0, dndCharacter.getIntelligence());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_WISDOM -> SET_CHARISMA when userAnswer is a number")
    void heroCreationAnswer_setNumericWisdom() {
        incomingState = new State(CREATE_HERO, SET_WISDOM, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_CHARISMA, dndCharacter), "Set charisma:");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerNumeric);

        assertEquals(Integer.parseInt(userAnswerNumeric), dndCharacter.getWisdom());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_WISDOM -> SET_WISDOM when userAnswer is letters")
    void heroCreationAnswer_setInvalidWisdom() {
        incomingState = new State(CREATE_HERO, SET_WISDOM, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_WISDOM, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerText);

        assertEquals(0, dndCharacter.getWisdom());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_WISDOM -> SET_WISDOM when userAnswer is special symbols")
    void heroCreationAnswer_setInvalidWisdomWithSpecialSymbols() {
        incomingState = new State(CREATE_HERO, SET_WISDOM, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_WISDOM, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerWithSpecialSymbols);

        assertEquals(0, dndCharacter.getWisdom());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_CHARISMA -> CHOOSE_RACE when userAnswer is a number")
    void heroCreationAnswer_setNumericCharisma() {
        incomingState = new State(CREATE_HERO, SET_CHARISMA, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_RACE, dndCharacter), """
                We have finished setting the base characteristics.
                
                Now pick the race:
                """ + getAllRaces(), getRaceOptions());

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerNumeric);

        assertEquals(Integer.parseInt(userAnswerNumeric), dndCharacter.getCharisma());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_CHARISMA -> SET_CHARISMA when userAnswer is letters")
    void heroCreationAnswer_setInvalidCharisma() {
        incomingState = new State(CREATE_HERO, SET_CHARISMA, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_CHARISMA, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerText);

        assertEquals(0, dndCharacter.getCharisma());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_CHARISMA -> SET_CHARISMA when userAnswer is special symbols")
    void heroCreationAnswer_setInvalidCharismaWithSpecialSymbols() {
        incomingState = new State(CREATE_HERO, SET_CHARISMA, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_CHARISMA, dndCharacter), "Please enter a number");

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswerWithSpecialSymbols);

        assertEquals(0, dndCharacter.getCharisma());
        assertEquals(expectedResponse, actualResponse);
    }
}
