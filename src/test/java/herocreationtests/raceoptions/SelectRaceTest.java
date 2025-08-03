package herocreationtests.raceoptions;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.race.RaceFactory;
import player.dndcharacter.race.dragonborn.Dragonborn;
import player.dndcharacter.race.dwarf.HillDwarf;
import player.dndcharacter.race.dwarf.MountainDwarf;
import player.dndcharacter.race.elf.DarkElf;
import player.dndcharacter.race.elf.HighElf;
import player.dndcharacter.race.elf.WoodElf;
import player.dndcharacter.race.gnome.ForestGnome;
import player.dndcharacter.race.gnome.RockGnome;
import player.dndcharacter.race.halfelf.HalfElf;
import player.dndcharacter.race.halfling.Lightfoot;
import player.dndcharacter.race.halfling.Stout;
import player.dndcharacter.race.halforc.HalfOrc;
import player.dndcharacter.race.human.BaseHuman;
import player.dndcharacter.race.human.VariantHuman;
import player.dndcharacter.race.teifling.Tiefling;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.SelectRace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static player.dndcharacter.dndcharacterenums.CharacterClass.getAllClasses;
import static player.dndcharacter.dndcharacterenums.Race.BASE_HUMAN;
import static player.dndcharacter.dndcharacterenums.Race.DARK_ELF;
import static player.dndcharacter.dndcharacterenums.Race.DRAGONBORN;
import static player.dndcharacter.dndcharacterenums.Race.FOREST_GNOME;
import static player.dndcharacter.dndcharacterenums.Race.HALF_ELF;
import static player.dndcharacter.dndcharacterenums.Race.HALF_ORC;
import static player.dndcharacter.dndcharacterenums.Race.HIGH_ELF;
import static player.dndcharacter.dndcharacterenums.Race.HILL_DWARF;
import static player.dndcharacter.dndcharacterenums.Race.LIGHTFOOT_HALFLING;
import static player.dndcharacter.dndcharacterenums.Race.MOUNTAIN_DWARF;
import static player.dndcharacter.dndcharacterenums.Race.ROCK_GNOME;
import static player.dndcharacter.dndcharacterenums.Race.STOUT_HALFLING;
import static player.dndcharacter.dndcharacterenums.Race.TIEFLING;
import static player.dndcharacter.dndcharacterenums.Race.VARIANT_HUMAN;
import static player.dndcharacter.dndcharacterenums.Race.WOOD_ELF;
import static player.dndcharacter.dndcharacterenums.Race.getAllRaces;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptions;
import static player.userinputhandler.commands.createnewhero.Options.getClassOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDraconicAncestryOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDwarfArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getRaceOptions;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.CHOOSE_DRACONIC_ANCESTRY;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_BASE_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_HIGH_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;

public class SelectRaceTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private Response actualResponse;
    private Response expectedResponse;
    private final RaceFactory raceFactoryMock = mock(RaceFactory.class);
    private final SelectRace selectRace = new SelectRace(raceFactoryMock);
    private final Dragonborn dragonbornMock = mock(Dragonborn.class);
    private final HillDwarf hillDwarfMock = mock(HillDwarf.class);
    private final MountainDwarf mountainDwarfMock = mock(MountainDwarf.class);
    private final DarkElf darkElfMock = mock(DarkElf.class);
    private final HighElf highElfMock = mock(HighElf.class);
    private final WoodElf woodElfMock = mock(WoodElf.class);
    private final ForestGnome forestGnomeMock = mock(ForestGnome.class);
    private final RockGnome rockGnomeMock = mock(RockGnome.class);
    private final HalfElf halfElfMock = mock(HalfElf.class);
    private final Lightfoot lightfootMock = mock(Lightfoot.class);
    private final Stout stoutMock = mock(Stout.class);
    private final HalfOrc halfOrcMock = mock(HalfOrc.class);
    private final BaseHuman baseHumanMock = mock(BaseHuman.class);
    private final VariantHuman variantHumanMock = mock(VariantHuman.class);
    private final Tiefling tieflingMock = mock(Tiefling.class);

    @Test
    @DisplayName("selectRace(): Dragonborn")
    void selectRaceDragonborn() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_DRACONIC_ANCESTRY, dndCharacter), "Enter your draconic ancestry", getDraconicAncestryOptions());
        when(raceFactoryMock.createRaceFactory(DRAGONBORN)).thenReturn(dragonbornMock);

        actualResponse = selectRace.selectRace("dragonborn", dndCharacter);

        verify(dragonbornMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Hill Dwarf")
    void selectRaceHillDwarf() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF, dndCharacter), "Which artisan tool would you like to be proficient with? Smith’s tools, brewer’s supplies, or mason’s tools.", getDwarfArtisanToolOptions());
        when(raceFactoryMock.createRaceFactory(HILL_DWARF)).thenReturn(hillDwarfMock);

        actualResponse = selectRace.selectRace("hill dwarf", dndCharacter);

        verify(hillDwarfMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Mountain Dwarf")
    void selectRaceMountainDwarf() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF, dndCharacter), "Which artisan tool would you like to be proficient with? Smith’s tools, brewer’s supplies, or mason’s tools.", getDwarfArtisanToolOptions());
        when(raceFactoryMock.createRaceFactory(MOUNTAIN_DWARF)).thenReturn(mountainDwarfMock);

        actualResponse = selectRace.selectRace("mountain dwarf", dndCharacter);

        verify(mountainDwarfMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Dark Elf")
    void selectRaceDarkElf() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(DARK_ELF)).thenReturn(darkElfMock);

        actualResponse = selectRace.selectRace("dark elf", dndCharacter);

        verify(darkElfMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): High Elf")
    void selectRaceHighElf() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HIGH_ELF, dndCharacter), "Enter an extra language your character will know");
        when(raceFactoryMock.createRaceFactory(HIGH_ELF)).thenReturn(highElfMock);

        actualResponse = selectRace.selectRace("high elf", dndCharacter);

        verify(highElfMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Wood Elf")
    void selectRaceWoodElf() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(WOOD_ELF)).thenReturn(woodElfMock);

        actualResponse = selectRace.selectRace("wood elf", dndCharacter);

        verify(woodElfMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Forest Gnome")
    void selectRaceForestGnome() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(FOREST_GNOME)).thenReturn(forestGnomeMock);

        actualResponse = selectRace.selectRace("forest gnome", dndCharacter);

        verify(forestGnomeMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Rock Gnome")
    void selectRaceRockGnome() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(ROCK_GNOME)).thenReturn(rockGnomeMock);

        actualResponse = selectRace.selectRace("rock gnome", dndCharacter);

        verify(rockGnomeMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Half Elf")
    void selectRaceHalfElf() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter), "Enter an ability that will be increased by 1 (Dexterity, Intelligence etc)", getBasicAbilityOptions());
        when(raceFactoryMock.createRaceFactory(HALF_ELF)).thenReturn(halfElfMock);

        actualResponse = selectRace.selectRace("half elf", dndCharacter);

        verify(halfElfMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Lightfoot Halfling")
    void selectRaceLightfootHalfling() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(LIGHTFOOT_HALFLING)).thenReturn(lightfootMock);

        actualResponse = selectRace.selectRace("lightfoot halfling", dndCharacter);

        verify(lightfootMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Stout Halfling")
    void selectRaceStoutHalfling() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(STOUT_HALFLING)).thenReturn(stoutMock);

        actualResponse = selectRace.selectRace("stout halfling", dndCharacter);

        verify(stoutMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Half Orc")
    void selectRaceHalfOrc() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(HALF_ORC)).thenReturn(halfOrcMock);

        actualResponse = selectRace.selectRace("half orc", dndCharacter);

        verify(halfOrcMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Base Human")
    void selectRaceBaseHuman() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_BASE_HUMAN, dndCharacter), "Enter an additional language your character will know");
        when(raceFactoryMock.createRaceFactory(BASE_HUMAN)).thenReturn(baseHumanMock);

        actualResponse = selectRace.selectRace("base human", dndCharacter);

        verify(baseHumanMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Variant Human")
    void selectRaceVariantHuman() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter), "Enter an ability that will be increased by 1 (Dexterity, Intelligence etc)", getBasicAbilityOptions());
        when(raceFactoryMock.createRaceFactory(VARIANT_HUMAN)).thenReturn(variantHumanMock);

        actualResponse = selectRace.selectRace("variant human", dndCharacter);

        verify(variantHumanMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Tiefling")
    void selectRaceTiefling() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(TIEFLING)).thenReturn(tieflingMock);

        actualResponse = selectRace.selectRace("tiefling", dndCharacter);

        verify(tieflingMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("selectRace(): Trim test")
    void selectRaceTrimTest() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter), "Choose your class:\n " + getAllClasses(), getClassOptions());
        when(raceFactoryMock.createRaceFactory(TIEFLING)).thenReturn(tieflingMock);

        actualResponse = selectRace.selectRace(" TieFling ", dndCharacter);

        verify(tieflingMock, times(1)).modifyByRace(dndCharacter);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("CHOOSE_RACE -> CHOOSE_RACE when the race is invalid")
    void selectRaceInvalidRace() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_RACE, dndCharacter), """
                Sorry, I don't understand your input. Here is the list of available races:
                """ + getAllRaces(), getRaceOptions());

        actualResponse = selectRace.selectRace("race", dndCharacter);

        assertEquals(expectedResponse, actualResponse);
    }
}
