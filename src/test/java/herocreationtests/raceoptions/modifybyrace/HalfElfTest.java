package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.halfelf.HalfElf;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.dndcharacter.dndcharacterenums.Race.HALF_ELF;
import static player.dndcharacter.dndcharacterenums.Race.WOOD_ELF;

public class HalfElfTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        HalfElf halfElf = new HalfElf();
        halfElf.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Set race")
    void setRaceForHalfElf() {

        assertEquals(HALF_ELF, dndCharacter.getRace());
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForHalfElf() {

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(11, dndCharacter.getDexterity());
        assertEquals(12, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(17, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForHalfElf() {

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForHalfElf() {

        assertEquals(30, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForHalfElf() {
        Set<String> expectedResult = Set.of("Elvish");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForHalfElf() {

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForHalfElf() {
        String expectedResult =
                """
                        Fey Ancestry. You have advantage on saving throws against being charmed, and magic can’t put you to sleep
                        You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can’t discern color in darkness, only shades of gray.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
