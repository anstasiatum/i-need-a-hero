package modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.halfling.Stout;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StoutHalflingTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        Stout stout = new Stout();
        stout.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForLightfootHalfling() {

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(13, dndCharacter.getDexterity());
        assertEquals(13, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForStoutHalfling() {

        assertEquals(Size.SMALL, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForStoutHalfling() {

        assertEquals(25, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageStoutHalfling() {

        Set<String> expectedResult = Set.of("Halfling");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForStoutHalfling() {

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForStoutHalfling() {
        String expectedResult = """
                Lucky. When you roll a 1 on an attack roll, ability check, or saving throw, you can reroll the die and must use the new roll.
                Brave. You have advantage on saving throws against being frightened.
                Halfling Nimbleness. You can move through the space of any creature that is of a size larger than yours.
                Stout Resilience. You have advantage on saving throws against poison, and you have resistance against poison damage.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
