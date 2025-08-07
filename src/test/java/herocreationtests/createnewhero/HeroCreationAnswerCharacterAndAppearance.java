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
import player.userinputhandler.commands.createnewhero.AddSkillProficiency;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.DESCRIBE_EYES;
import static player.userinputhandler.enums.Steps.DESCRIBE_HAIR;
import static player.userinputhandler.enums.Steps.DESCRIBE_SKIN;
import static player.userinputhandler.enums.Steps.ENTER_ALLIES_AND_ORGANIZATIONS;
import static player.userinputhandler.enums.Steps.ENTER_CHARACTER_BACKSTORY;
import static player.userinputhandler.enums.Steps.ENTER_TREASURE_AND_FINISH;
import static player.userinputhandler.enums.Steps.SET_AGE;
import static player.userinputhandler.enums.Steps.SET_BONDS;
import static player.userinputhandler.enums.Steps.SET_FLAWS;
import static player.userinputhandler.enums.Steps.SET_HEIGHT;
import static player.userinputhandler.enums.Steps.SET_IDEALS;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;
import static player.userinputhandler.enums.Steps.SET_WEIGHT;

public class HeroCreationAnswerCharacterAndAppearance {
    private final CharacterDao characterJpaDaoMock = mock(CharacterDao.class);
    private final BaseCharacteristicsValuesGenerator baseCharacteristicsValuesGenerator = new BaseCharacteristicsValuesGenerator();
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod = new ChooseCharacteristicsSettingMethod(baseCharacteristicsValuesGenerator);
    private final IncrementAbility incrementAbility = new IncrementAbility();
    private final IncreaseBaseCharacteristics increaseBaseCharacteristics = new IncreaseBaseCharacteristics(incrementAbility);
    private final RaceFactory raceFactory = new RaceFactory();
    private final SelectRace selectRace = new SelectRace(raceFactory);
    private final AddSkillProficiency addSkillProficiency = new AddSkillProficiency();
    private final CharacterClassFactory characterClassFactory = new CharacterClassFactory();
    private final SelectClass selectClass = new SelectClass(characterClassFactory);
    private final BackgroundFactory backgroundFactory = new BackgroundFactory();
    private final SetBackground setBackground = new SetBackground(backgroundFactory);
    private final SetPirateFeature setPirateFeature = new SetPirateFeature();
    private final ChoosePossessionsForGuildMerchant choosePossessionsForGuildMerchant = new ChoosePossessionsForGuildMerchant();
    private final ChooseProficiencyForGuildMerchant chooseProficiencyForGuildMerchant = new ChooseProficiencyForGuildMerchant();
    private final CreateNewHero createNewHero = new CreateNewHero(characterJpaDaoMock, characteristicsSettingMethod, increaseBaseCharacteristics, selectRace, addSkillProficiency, selectClass, setBackground, setPirateFeature, choosePossessionsForGuildMerchant, chooseProficiencyForGuildMerchant);
    private State incomingState;
    private Response expectedResponse;
    private Response actualResponse;
    private DndCharacter dndCharacter = new DndCharacter();
    private final Long chatID = 123L;
    private String userAnswer;
    private final Faker faker = new Faker();

    @Test
    @DisplayName("SET_PERSONALITY_TRAITS -> SET_IDEALS")
    void heroCreationAnswer_SetPersonalityTraits() {
        incomingState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_IDEALS, dndCharacter), "Now enter your character's ideals");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getPersonalityTraits());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_IDEALS -> SET_FLAWS")
    void heroCreationAnswer_SetIdeals() {
        incomingState = new State(CREATE_HERO, SET_IDEALS, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_FLAWS, dndCharacter), "What about flaws?");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getIdeals());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_FLAWS -> SET_BONDS")
    void heroCreationAnswer_SetFlaws() {
        incomingState = new State(CREATE_HERO, SET_FLAWS, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_BONDS, dndCharacter), "Set your character's bonds");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getFlaws());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_BONDS -> SET_AGE")
    void heroCreationAnswer_SetBonds() {
        incomingState = new State(CREATE_HERO, SET_BONDS, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_AGE, dndCharacter), "What is their age?");
        userAnswer = String.valueOf(faker.number().numberBetween(1, 500));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getBonds());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_AGE -> SET_HEIGHT")
    void heroCreationAnswer_SetAge() {
        incomingState = new State(CREATE_HERO, SET_AGE, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_HEIGHT, dndCharacter), "Time to describe your character's appearance. What is their height?");
        userAnswer = String.valueOf(faker.number().numberBetween(1, 200));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getAge());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_HEIGHT -> SET_WEIGHT")
    void heroCreationAnswer_SetHeight() {
        incomingState = new State(CREATE_HERO, SET_HEIGHT, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, SET_WEIGHT, dndCharacter), "What is their weight?");
        userAnswer = String.valueOf(faker.number().numberBetween(1, 10));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getHeight());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("SET_WEIGHT -> DESCRIBE_EYES")
    void heroCreationAnswer_SetWeight() {
        incomingState = new State(CREATE_HERO, SET_WEIGHT, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, DESCRIBE_EYES, dndCharacter), "What about eyes?");
        userAnswer = String.valueOf(faker.number().numberBetween(1, 200));

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getWeight());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("DESCRIBE_EYES -> DESCRIBE_SKIN")
    void heroCreationAnswer_DescribeEyes() {
        incomingState = new State(CREATE_HERO, DESCRIBE_EYES, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, DESCRIBE_SKIN, dndCharacter), "Skin?");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getEyes());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("DESCRIBE_SKIN -> DESCRIBE_HAIR")
    void heroCreationAnswer_DescribeSkin() {
        incomingState = new State(CREATE_HERO, DESCRIBE_SKIN, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, DESCRIBE_HAIR, dndCharacter), "Hair?");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getSkin());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("DESCRIBE_HAIR -> ENTER_ALLIES_AND_ORGANIZATIONS")
    void heroCreationAnswer_DescribeHair() {
        incomingState = new State(CREATE_HERO, DESCRIBE_HAIR, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_ALLIES_AND_ORGANIZATIONS, dndCharacter), "Mention any allies and organizations your hero is familiar with");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getHair());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_ALLIES_AND_ORGANIZATIONS -> ENTER_CHARACTER_BACKSTORY")
    void heroCreationAnswer_EnterAlliesANdOrganizations() {
        incomingState = new State(CREATE_HERO, ENTER_ALLIES_AND_ORGANIZATIONS, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_CHARACTER_BACKSTORY, dndCharacter), "What is your character's backstory?");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getAlliesAndOrganizations());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_CHARACTER_BACKSTORY -> ENTER_TREASURE_AND_FINISH")
    void heroCreationAnswer_EnterBackstory() {
        incomingState = new State(CREATE_HERO, ENTER_CHARACTER_BACKSTORY, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, ENTER_TREASURE_AND_FINISH, dndCharacter), "Mention any treasure your hero might possess");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getBackstory());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("ENTER_TREASURE_AND_FINISH saves the character")
    void heroCreationAnswer_EnterTreasure() {
        incomingState = new State(CREATE_HERO, ENTER_TREASURE_AND_FINISH, dndCharacter);
        expectedResponse = new Response(null, "Hooray! We have finished setting your character and they have been successfully saved. Now you can print their character sheet using /printhero");
        userAnswer = faker.harryPotter().quote();

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        verify(characterJpaDaoMock, times(1)).save(argThat(n -> n.getId() == null && n.getChatId().equals(chatID) && n.getDndCharacter() == dndCharacter));
        assertEquals(userAnswer, dndCharacter.getTreasure());
        assertEquals(expectedResponse, actualResponse);
    }
}
