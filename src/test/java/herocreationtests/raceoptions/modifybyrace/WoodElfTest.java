package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.ProficiencyLevel;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.dndcharacterenums.Skill;
import player.dndcharacter.race.elf.WoodElf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Race.WOOD_ELF;
import static player.dndcharacter.dndcharacterenums.Skill.PERCEPTION;

public class WoodElfTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        WoodElf woodElf = new WoodElf();
        woodElf.modifyByRace(dndCharacter);

    }


    @Test
    @DisplayName("Set race")
    void setRaceForWoodElf() {

        assertEquals(WOOD_ELF, dndCharacter.getRace());
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForWoodElf() {

        assertEquals(10, dndCharacter.getStrength());
        assertEquals(13, dndCharacter.getDexterity());
        assertEquals(12, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(15, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForWoodElf() {

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForWoodElf() {

        assertEquals(35, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForWoodElf() {
        Set<String> expectedResult = Set.of("Elvish");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForWoodElf() {

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForWoodElf() {

        String expectedResult = """
                Trance: Elves don’t need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. (The Common word for such meditation is “trance.”) While meditating, you can dream after a fashion; such dreams are actually mental exercises that have become reflexive through years of practice. After resting in this way, you gain the same benefit that a human does from 8 hours of sleep.
                Fey Ancestry: You have advantage on saving throws against being charmed, and magic can’t put you to sleep.
                Mask of the Wild. You can attempt to hide even when you are only lightly obscured by foliage, heavy rain, falling snow, mist, and other natural phenomena.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set Perception Proficiency")
    void setArmourProficiencyForWoodElf() {
        Map<Skill, ProficiencyLevel> expectedResult = new HashMap<>(1);
        expectedResult.put(PERCEPTION, PROFICIENT);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForWoodElf() {
        Set<String> expectedResult = new HashSet<>(4);
        expectedResult.add("Longsword");
        expectedResult.add("Shortsword");
        expectedResult.add("Shortbow");
        expectedResult.add("Longbow");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }
}
