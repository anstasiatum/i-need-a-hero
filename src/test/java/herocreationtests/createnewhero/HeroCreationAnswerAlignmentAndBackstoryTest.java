package herocreationtests.createnewhero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
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
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncreaseBaseCharacteristics;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncrementAbility;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.db.CharacterDaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.commands.createnewhero.Options.getBackgroundOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.allBackgrounds;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_BACKGROUND;
import static player.userinputhandler.enums.Steps.ENTER_ALIGNMENT;

public class HeroCreationAnswerAlignmentAndBackstoryTest {
    private final CharacterDao characterJpaDao = new CharacterDaoImpl();
    private final BaseCharacteristicsValuesGenerator baseCharacteristicsValuesGenerator = new BaseCharacteristicsValuesGenerator();
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod = new ChooseCharacteristicsSettingMethod(baseCharacteristicsValuesGenerator);
    private final IncrementAbility incrementAbility = new IncrementAbility();
    private final IncreaseBaseCharacteristics increaseBaseCharacteristics = new IncreaseBaseCharacteristics(incrementAbility);
    private final RaceFactory raceFactory = new RaceFactory();
    private final SelectRace selectRace = new SelectRace(raceFactory);
    private final AddSkillProficiency addSkillProficiency = new AddSkillProficiency();
    private final CharacterClassFactory characterClassFactory = new CharacterClassFactory();
    private final SelectClass selectClass = new SelectClass(characterClassFactory);
    private final CreateNewHero createNewHero = new CreateNewHero(characterJpaDao, characteristicsSettingMethod, increaseBaseCharacteristics, selectRace, addSkillProficiency, selectClass);
    private State incomingState;
    private State expectedState;
    private Response expectedResponse;
    private Response actualResponse;
    private DndCharacter dndCharacter = new DndCharacter();
    private final Long chatID = 123L;
    private String userAnswer;

    @Test
    @DisplayName("ENTER_ALIGNMENT -> CHOOSE_BACKGROUND")
    void heroCreationAnswer_chooseFirstSkillForBarbarian() {
        incomingState = new State(CREATE_HERO, ENTER_ALIGNMENT, dndCharacter);
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_BACKGROUND, dndCharacter), "Choose the background of your hero\n" + allBackgrounds, getBackgroundOptions());
        userAnswer = "chaotic-good";

        actualResponse = createNewHero.heroCreationAnswer(incomingState, chatID, userAnswer);

        assertEquals(userAnswer, dndCharacter.getAlignment());
        assertEquals(expectedResponse, actualResponse);
    }
}
