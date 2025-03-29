package modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.dndcharacterenums.Skills;
import player.dndcharacter.race.elf.HighElf;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.dndcharacter.dndcharacterenums.Skills.PERCEPTION;

public class HighElfTest {
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
    void changeBaseCharacteristicsForHighElf() {
        HighElf highElf = new HighElf();
        highElf.modifyByRace(dndCharacter);

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(13, dndCharacter.getDexterity());
        assertEquals(12, dndCharacter.getConstitution());
        assertEquals(14, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForHighElf() {
        HighElf highElf = new HighElf();
        highElf.modifyByRace(dndCharacter);

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForHighElf() {
        HighElf highElf = new HighElf();
        highElf.modifyByRace(dndCharacter);

        assertEquals(30, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForHighElf() {
        HighElf highElf = new HighElf();
        highElf.modifyByRace(dndCharacter);

        Set<String> expectedResult = Set.of("Elvish");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForHighElf() {
        HighElf highElf = new HighElf();
        highElf.modifyByRace(dndCharacter);

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForHighElf() {
        HighElf highElf = new HighElf();
        highElf.modifyByRace(dndCharacter);

        String expectedResult = """
                Trance: Elves don’t need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. (The Common word for such meditation is “trance.”) While meditating, you can dream after a fashion; such dreams are actually mental exercises that have become reflexive through years of practice. After resting in this way, you gain the same benefit that a human does from 8 hours of sleep.
                Fey Ancestry: You have advantage on saving throws against being charmed, and magic can’t put you to sleep.
                Cantrip. You know one cantrip of your choice from the wizard spell list. Intelligence is your spellcasting ability for it.
                You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can’t discern color in darkness, only shades of gray.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set Perception Proficiency")
    void setArmourProficiencyForHighElf() {
        HighElf highElf = new HighElf();
        highElf.modifyByRace(dndCharacter);

        Set<Skills> expectedResult = new HashSet<>(1);
        expectedResult.add(PERCEPTION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForHighElf() {
        HighElf highElf = new HighElf();
        highElf.modifyByRace(dndCharacter);

        Set<String> expectedResult = new HashSet<>(4);
        expectedResult.add("Longsword");
        expectedResult.add("Shortsword");
        expectedResult.add("Shortbow");
        expectedResult.add("Longbow");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }
}
