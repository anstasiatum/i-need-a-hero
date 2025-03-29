package modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.dwarf.HillDwarf;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HillDwarfTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(11, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(15, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        assertEquals(25, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        Set<String> expectedResult = Set.of("Dwarfish");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        String expectedResult = """
                Your speed is not reduced by wearing heavy armor.
                Dwarven Resilience. You have advantage on saving throws against poison, and you have resistance against poison damage.
                Stonecunning. Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered proficient in the History skill and add double your proficiency bonus to the check, instead of your normal proficiency bonus.
                You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can’t discern color in darkness, only shades of gray.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        Set<String> expectedResult = new HashSet<>(4);
        expectedResult.add("Battleaxe");
        expectedResult.add("Handaxe");
        expectedResult.add("Throwing hammer");
        expectedResult.add("Warhammer");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Tool Proficiency")
    void setToolProficiencyHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        Set<String> expectedResult = new HashSet<>(4);
        expectedResult.add("Artisan’s tools of your choice: smith’s tools, brewer’s supplies, or mason’s tools.");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForHillDwarf() {
        HillDwarf hillDwarf = new HillDwarf();
        hillDwarf.modifyByRace(dndCharacter);

        assertEquals(2, dndCharacter.getHitPoints());
    }
}
