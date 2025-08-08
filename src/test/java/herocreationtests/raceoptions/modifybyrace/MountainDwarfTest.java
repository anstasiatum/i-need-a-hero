package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.dwarf.MountainDwarf;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.dndcharacter.dndcharacterenums.Race.MOUNTAIN_DWARF;

public class MountainDwarfTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        MountainDwarf mountainDwarf = new MountainDwarf();
        mountainDwarf.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Set race")
    void setRaceForMountainDwarf() {

        assertEquals(MOUNTAIN_DWARF, dndCharacter.getRace());
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForMountainDwarf() {

        assertEquals(12, dndCharacter.getStrength());
        assertEquals(11, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForMountainDwarf() {

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForMountainDwarf() {

        assertEquals(25, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForMountainDwarf() {
        Set<String> expectedResult = Set.of("Dwarfish");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForMountainDwarf() {

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForMountainDwarf() {
        String expectedResult = """
                Your speed is not reduced by wearing heavy armor.
                Dwarven Resilience. You have advantage on saving throws against poison, and you have resistance against poison damage.
                Stonecunning. Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered proficient in the History skill and add double your proficiency bonus to the check, instead of your normal proficiency bonus.
                You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can’t discern color in darkness, only shades of gray.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForMountainDwarf() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Light armour");
        expectedResult.add("Medium armour");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }
}
