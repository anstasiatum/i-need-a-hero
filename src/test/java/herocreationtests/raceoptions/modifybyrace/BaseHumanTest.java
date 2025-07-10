package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.human.BaseHuman;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseHumanTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        BaseHuman baseHuman = new BaseHuman();
        baseHuman.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForBaseHuman() {

        assertEquals(11, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(13, dndCharacter.getConstitution());
        assertEquals(14, dndCharacter.getIntelligence());
        assertEquals(15, dndCharacter.getWisdom());
        assertEquals(16, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForBaseHuman() {

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForBaseHuman() {

        assertEquals(30, dndCharacter.getSpeed());
    }
}
