package modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.dragonborn.Dragonborn;
import player.dndcharacter.race.teifling.Tiefling;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TieflingTest {
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
    void changeBaseCharacteristicsForTiefling() {
        Tiefling tiefling = new Tiefling();
        tiefling.modifyByRace(dndCharacter);

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(11, dndCharacter.getDexterity());
        assertEquals(12, dndCharacter.getConstitution());
        assertEquals(14, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(17, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForTiefling() {
        Tiefling tiefling = new Tiefling();
        tiefling.modifyByRace(dndCharacter);

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForTiefling() {
        Tiefling tiefling = new Tiefling();
        tiefling.modifyByRace(dndCharacter);

        assertEquals(30, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForTiefling() {
        Tiefling tiefling = new Tiefling();
        tiefling.modifyByRace(dndCharacter);

        Set<String> expectedResult = Set.of("Infernal");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForTiefling() {
        Tiefling tiefling = new Tiefling();
        tiefling.modifyByRace(dndCharacter);

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForTiefling() {
        Tiefling tiefling = new Tiefling();
        tiefling.modifyByRace(dndCharacter);

        String expectedResult = """
                        Hellish Resistance. You have resistance to fire damage.
                        Infernal Legacy. You know the thaumaturgy cantrip.
                        You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can’t discern color in darkness, only shades of gray.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
