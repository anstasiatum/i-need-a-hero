package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.dragonborn.Dragonborn;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DragonbornTest {
    private final DndCharacter dndCharacter = new DndCharacter();

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
}