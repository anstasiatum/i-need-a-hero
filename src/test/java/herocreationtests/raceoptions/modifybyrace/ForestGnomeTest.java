package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.gnome.ForestGnome;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ForestGnomeTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        ForestGnome forestGnome = new ForestGnome();
        forestGnome.modifyByRace(dndCharacter);

    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForForestGnome() {

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(12, dndCharacter.getConstitution());
        assertEquals(15, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForForestGnome() {

        assertEquals(Size.SMALL, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForForestGnome() {

        assertEquals(25, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForForestGnome() {
        Set<String> expectedResult = Set.of("Gnomish");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForForestGnome() {

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForForestGnome() {
        String expectedResult =
                """
                        You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can’t discern color in darkness, only shades of gray.You have advantage on all Intelligence, Wisdom, and Charisma saving throws against magic.
                        Natural Illusionist. You know the minor illusion cantrip. Intelligence is your spellcasting ability for it.
                        Speak with Small Beasts. Through sounds and gestures, you can communicate simple ideas with Small or smaller beasts. Forest gnomes love animals and often keep squirrels, badgers, rabbits, moles, woodpeckers, and other creatures as beloved pets.
                        """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
