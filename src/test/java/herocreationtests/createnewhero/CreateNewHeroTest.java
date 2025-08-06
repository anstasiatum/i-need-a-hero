package herocreationtests.createnewhero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.background.BackgroundFactory;
import player.dndcharacter.characterclass.CharacterClassFactory;
import player.dndcharacter.characteristicsgenerator.BaseCharacteristicsValuesGenerator;
import player.dndcharacter.race.RaceFactory;
import player.userinputhandler.Response;
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
import player.userinputhandler.commands.db.CharacterDaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.spy;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.ENTER_NAME;

public class CreateNewHeroTest {
    private final CharacterDao characterJpaDao = new CharacterDaoImpl();
    private final BaseCharacteristicsValuesGenerator baseCharacteristicsValuesGenerator = new BaseCharacteristicsValuesGenerator();
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethod = new ChooseCharacteristicsSettingMethod(baseCharacteristicsValuesGenerator);
    private final ChooseCharacteristicsSettingMethod characteristicsSettingMethodSpy = spy(characteristicsSettingMethod);
    private final IncrementAbility incrementAbility = new IncrementAbility();
    private final IncreaseBaseCharacteristics increaseBaseCharacteristics = new IncreaseBaseCharacteristics(incrementAbility);
    private final IncreaseBaseCharacteristics increaseBaseCharacteristicsSpy = spy(increaseBaseCharacteristics);
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
    private final CreateNewHero createNewHero = new CreateNewHero(characterJpaDao, characteristicsSettingMethodSpy, increaseBaseCharacteristicsSpy, selectRace, addSkillProficiency, selectClass, setBackground, setPirateFeature, choosePossessionsForGuildMerchant, chooseProficiencyForGuildMerchant);

    @Test
    @DisplayName("createNewHero() should transition to the first hero creation step")
    void createNewHeroReturnResponse() {
        Response actualResponse = createNewHero.createNewHero();

        assertEquals(CREATE_HERO, actualResponse.getState().getProcess());
        assertEquals(ENTER_NAME, actualResponse.getState().getStepId());
        assertNotNull(actualResponse.getState().getDndCharacter());
        assertEquals("Alright, let's name your future hero!", actualResponse.getTextAnswer());
    }
}
