package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.dndcharacterenums.Skill;
import player.dndcharacter.race.elf.DarkElf;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.dndcharacter.dndcharacterenums.Race.DARK_ELF;
import static player.dndcharacter.dndcharacterenums.Race.HALF_ELF;
import static player.dndcharacter.dndcharacterenums.Skill.PERCEPTION;

public class DarkElfTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        DarkElf darkElf = new DarkElf();
        darkElf.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Set race")
    void setRaceForDarkElf() {

        assertEquals(DARK_ELF, dndCharacter.getRace());
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForDarkElf() {

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(13, dndCharacter.getDexterity());
        assertEquals(12, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(16, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForDarkElf() {

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForDarkElf() {

        assertEquals(30, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForDarkElf() {
        Set<String> expectedResult = Set.of("Elvish");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForDarkElf() {

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForDarkElf() {
        String expectedResult = """
                Trance: Elves don’t need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. (The Common word for such meditation is “trance.”) While meditating, you can dream after a fashion; such dreams are actually mental exercises that have become reflexive through years of practice. After resting in this way, you gain the same benefit that a human does from 8 hours of sleep.
                Fey Ancestry: You have advantage on saving throws against being charmed, and magic can’t put you to sleep.
                Cantrip. You know the dancing lights cantrip.
                You can see in dim light within 120 feet of you as if it were bright light, and in darkness as if it were dim light. You can’t discern color in darkness, only shades of gray.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set Perception Proficiency")
    void setPerceptionProficiencyForDarkElf() {
        Set<Skill> expectedResult = new HashSet<>(1);
        expectedResult.add(PERCEPTION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForMountainDwarf() {
        Set<String> expectedResult = new HashSet<>(3);
        expectedResult.add("Rapiers");
        expectedResult.add("Shortsword");
        expectedResult.add("Hand crossbows");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }
}
