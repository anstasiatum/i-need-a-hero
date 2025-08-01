package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.gnome.RockGnome;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.dndcharacter.dndcharacterenums.Race.ROCK_GNOME;
import static player.dndcharacter.dndcharacterenums.Race.WOOD_ELF;

class RockGnomeTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        RockGnome rockGnome = new RockGnome();
        rockGnome.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Set race")
    void setRaceForRockGnome() {

        assertEquals(ROCK_GNOME, dndCharacter.getRace());
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForRockGnome() {

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(11, dndCharacter.getDexterity());
        assertEquals(13, dndCharacter.getConstitution());
        assertEquals(15, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForRockGnome() {

        assertEquals(Size.SMALL, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForRockGnome() {

        assertEquals(25, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForRockGnome() {
        Set<String> expectedResult = Set.of("Gnomish");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForRockGnome() {

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForRockGnome() {
        String expectedResult =
                """
                        Artificer’s Lore
                        Whenever you make an Intelligence (History) check related to magic items, alchemical objects, or technological devices, you can add twice your proficiency bonus, instead of any proficiency bonus you normally apply.
                        Tinker
                        You have proficiency with artisan’s tools (tinker’s tools). Using those tools, you can spend 1 hour and 10 gp worth of materials to construct a Tiny clockwork device (AC 5, 1 hp). The device ceases to function after 24 hours (unless you spend 1 hour repairing it to keep the device functioning), or when you use your action to dismantle it; at that time, you can reclaim the materials used to create it. You can have up to three such devices active at a time.
                        When you create a device, choose one of the following options:
                        Clockwork Toy. This toy is a clockwork animal, monster, or person, such as a frog, mouse, bird, dragon, or soldier. When placed on the ground, the toy moves 5 feet across the ground on each of your turns in a random direction. It makes noises as appropriate to the creature it represents.
                        Fire Starter. The device produces a miniature flame, which you can use to light a candle, torch, or campfire. Using the device requires your action.
                        Music Box. When opened, this music box plays a single song at a moderate volume. The box stops playing when it reaches the song’s end or when it is closed.
                        """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}