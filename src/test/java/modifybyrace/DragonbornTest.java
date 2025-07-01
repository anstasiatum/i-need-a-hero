package modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.dragonborn.Dragonborn;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DragonbornTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        Dragonborn dragonborn = new Dragonborn();
        dragonborn.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForDragonborn() {
        assertEquals(12, dndCharacter.getStrength());
        assertEquals(11, dndCharacter.getDexterity());
        assertEquals(12, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(16, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForDragonborn() {

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForDragonborn() {

        assertEquals(30, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForDragonborn() {
        Set<String> expectedResult = Set.of("Draconic");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForDragonborn() {

        assertEquals(2, dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForDragonborn() {

        String expectedResult = """
                        Draconic Ancestry
                        You have draconic ancestry. Choose one type of dragon from the Draconic Ancestry table. Your breath weapon and damage resistance are determined by the dragon type, as shown in the table.
                        Breath Weapon
                        You can use your action to exhale destructive energy. Your draconic ancestry determines the size, shape, and damage type of the exhalation. When you use your breath weapon, each creature in the area of the exhalation must make a saving throw, the type of which is determined by your draconic ancestry. The DC for this saving throw equals 8 + your Constitution modifier + your proficiency bonus. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. The damage increases to 3d6 at 6th level, 4d6 at 11th level, and 5d6 at 16th level. After you use your breath weapon, you can’t use it again until you complete a short or long rest.
                        Damage Resistance
                        You have resistance to the damage type associated with your draconic ancestry.
                """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}