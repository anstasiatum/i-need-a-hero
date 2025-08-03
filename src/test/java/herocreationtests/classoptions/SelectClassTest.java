package herocreationtests.classoptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.characterclass.CharacterClassFactory;
import player.dndcharacter.characterclass.characterclasses.Barbarian;
import player.dndcharacter.characterclass.characterclasses.Bard;
import player.dndcharacter.characterclass.characterclasses.Cleric;
import player.dndcharacter.characterclass.characterclasses.Druid;
import player.dndcharacter.characterclass.characterclasses.Fighter;
import player.dndcharacter.characterclass.characterclasses.Monk;
import player.dndcharacter.characterclass.characterclasses.Paladin;
import player.dndcharacter.characterclass.characterclasses.Ranger;
import player.dndcharacter.characterclass.characterclasses.Rogue;
import player.dndcharacter.characterclass.characterclasses.Sorcerer;
import player.dndcharacter.characterclass.characterclasses.Warlock;
import player.dndcharacter.characterclass.characterclasses.Wizard;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.SelectClass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static player.dndcharacter.dndcharacterenums.CharacterClass.BARBARIAN;
import static player.dndcharacter.dndcharacterenums.CharacterClass.BARD;
import static player.dndcharacter.dndcharacterenums.CharacterClass.CLERIC;
import static player.dndcharacter.dndcharacterenums.CharacterClass.DRUID;
import static player.dndcharacter.dndcharacterenums.CharacterClass.FIGHTER;
import static player.dndcharacter.dndcharacterenums.CharacterClass.MONK;
import static player.dndcharacter.dndcharacterenums.CharacterClass.PALADIN;
import static player.dndcharacter.dndcharacterenums.CharacterClass.RANGER;
import static player.dndcharacter.dndcharacterenums.CharacterClass.ROGUE;
import static player.dndcharacter.dndcharacterenums.CharacterClass.SORCERER;
import static player.dndcharacter.dndcharacterenums.CharacterClass.WARLOCK;
import static player.dndcharacter.dndcharacterenums.CharacterClass.WIZARD;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_BARBARIAN;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_BARD;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_CLERIC;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_DRUID;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_FIGHTER;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_MONK;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_PALADIN;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_RANGER;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_ROGUE;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_SORCERER;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_WARLOCK;
import static player.userinputhandler.enums.Steps.ENTER_FIRST_SKILL_FOR_WIZARD;

public class SelectClassTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private State expectedState;
    private Response actualResponse;
    private final CharacterClassFactory classFactoryMock = mock(CharacterClassFactory.class);
    private final SelectClass selectClass = new SelectClass(classFactoryMock);
    private final String expectedTextAnswer = "Enter a skill your hero will be proficient in. Here is the list of available ones:\n";
    private final Barbarian barbarianMock = mock(Barbarian.class);
    private final Bard bardMock = mock(Bard.class);
    private final Cleric clericMock = mock(Cleric.class);
    private final Druid druidMock = mock(Druid.class);
    private final Fighter fighterMock = mock(Fighter.class);
    private final Monk monkMock = mock(Monk.class);
    private final Paladin paladinMock = mock(Paladin.class);
    private final Ranger rangerMock = mock(Ranger.class);
    private final Rogue rogueMock = mock(Rogue.class);
    private final Sorcerer sorcererMock = mock(Sorcerer.class);
    private final Warlock warlockMock = mock(Warlock.class);
    private final Wizard wizardMock = mock(Wizard.class);

    @Test
    @DisplayName("selectClass(): Barbarian")
    void selectClassBarbarian() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARBARIAN, dndCharacter);
        when(classFactoryMock.createClassFactory(BARBARIAN)).thenReturn(barbarianMock);

        actualResponse = selectClass.selectClass("barbarian", dndCharacter);

        verify(barbarianMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Bard")
    void selectClassBard() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_BARD, dndCharacter);
        when(classFactoryMock.createClassFactory(BARD)).thenReturn(bardMock);

        actualResponse = selectClass.selectClass("bard", dndCharacter);

        verify(bardMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Cleric")
    void selectClassCleric() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_CLERIC, dndCharacter);
        when(classFactoryMock.createClassFactory(CLERIC)).thenReturn(clericMock);

        actualResponse = selectClass.selectClass("cleric", dndCharacter);

        verify(clericMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Druid")
    void selectClassDruid() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_DRUID, dndCharacter);
        when(classFactoryMock.createClassFactory(DRUID)).thenReturn(druidMock);

        actualResponse = selectClass.selectClass("druid", dndCharacter);

        verify(druidMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Fighter")
    void selectClassFighter() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_FIGHTER, dndCharacter);
        when(classFactoryMock.createClassFactory(FIGHTER)).thenReturn(fighterMock);

        actualResponse = selectClass.selectClass("fighter", dndCharacter);

        verify(fighterMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Monk")
    void selectClassMonk() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_MONK, dndCharacter);
        when(classFactoryMock.createClassFactory(MONK)).thenReturn(monkMock);

        actualResponse = selectClass.selectClass("monk", dndCharacter);

        verify(monkMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Paladin")
    void selectClassPaladin() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_PALADIN, dndCharacter);
        when(classFactoryMock.createClassFactory(PALADIN)).thenReturn(paladinMock);

        actualResponse = selectClass.selectClass("paladin", dndCharacter);

        verify(paladinMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Ranger")
    void selectClassRanger() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_RANGER, dndCharacter);
        when(classFactoryMock.createClassFactory(RANGER)).thenReturn(rangerMock);

        actualResponse = selectClass.selectClass("ranger", dndCharacter);

        verify(rangerMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Rogue")
    void selectClassRogue() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_ROGUE, dndCharacter);
        when(classFactoryMock.createClassFactory(ROGUE)).thenReturn(rogueMock);

        actualResponse = selectClass.selectClass("rogue", dndCharacter);

        verify(rogueMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Sorcerer")
    void selectClassSorcerer() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_SORCERER, dndCharacter);
        when(classFactoryMock.createClassFactory(SORCERER)).thenReturn(sorcererMock);

        actualResponse = selectClass.selectClass("sorcerer", dndCharacter);

        verify(sorcererMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Warlock")
    void selectClassWarlock() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WARLOCK, dndCharacter);
        when(classFactoryMock.createClassFactory(WARLOCK)).thenReturn(warlockMock);

        actualResponse = selectClass.selectClass("warlock", dndCharacter);

        verify(warlockMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Wizard")
    void selectClassWizard() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
        when(classFactoryMock.createClassFactory(WIZARD)).thenReturn(wizardMock);

        actualResponse = selectClass.selectClass("wizard", dndCharacter);

        verify(wizardMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }

    @Test
    @DisplayName("selectClass(): Trim Test")
    void selectClassTrimTest() {
        expectedState = new State(CREATE_HERO, ENTER_FIRST_SKILL_FOR_WIZARD, dndCharacter);
        when(classFactoryMock.createClassFactory(WIZARD)).thenReturn(wizardMock);

        actualResponse = selectClass.selectClass(" wizArd ", dndCharacter);

        verify(wizardMock, times(1)).modifyByClass(dndCharacter);
        assertEquals(expectedState, actualResponse.getState());
        assertTrue(actualResponse.getTextAnswer().contains(expectedTextAnswer));
        assertNotNull(actualResponse.getOptionTexts());
    }
}
