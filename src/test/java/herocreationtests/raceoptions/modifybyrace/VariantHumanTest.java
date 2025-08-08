package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.human.VariantHuman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Race.VARIANT_HUMAN;

public class VariantHumanTest {
    private final  DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        VariantHuman variantHuman = new VariantHuman();
        variantHuman.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Set race")
    void setRaceForVariantHuman() {

        assertEquals(VARIANT_HUMAN, dndCharacter.getRace());
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForVariantHuman() {

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(11, dndCharacter.getDexterity());
        assertEquals(12, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForVariantHuman() {

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForVariantHuman() {

        assertEquals(30, dndCharacter.getSpeed());
    }
}
