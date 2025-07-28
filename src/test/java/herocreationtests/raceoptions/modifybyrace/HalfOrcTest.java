package herocreationtests.raceoptions.modifybyrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.dndcharacterenums.Skill;
import player.dndcharacter.race.halforc.HalfOrc;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HalfOrcTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(11);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);

        HalfOrc halfOrc = new HalfOrc();
        halfOrc.modifyByRace(dndCharacter);
    }

    @Test
    @DisplayName("Change base characteristics")
    void changeBaseCharacteristicsForHalfOrc() {

        assertEquals(12, dndCharacter.getStrength());
        assertEquals(11, dndCharacter.getDexterity());
        assertEquals(13, dndCharacter.getConstitution());
        assertEquals(13, dndCharacter.getIntelligence());
        assertEquals(14, dndCharacter.getWisdom());
        assertEquals(15, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Set size")
    void setSizeForHalfOrc() {

        assertEquals(Size.MEDIUM, dndCharacter.getSize());
    }

    @Test
    @DisplayName("Set speed")
    void setSpeedForHalfOrc() {

        assertEquals(30, dndCharacter.getSpeed());
    }

    @Test
    @DisplayName("Set language")
    void setLanguageForHalfOrc() {
        Set<String> expectedResult = Set.of("Orc");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set draconic ancestry damage")
    void setDraconicAncestryDamageForHalfOrc() {

        assertNull(dndCharacter.getDraconicAncestryDamage());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForHalfOrc() {
        String expectedResult =
                """
                        Relentless Endurance. When you are reduced to 0 hit points but not killed outright, you can drop to 1 hit point instead. You can’t use this feature again until you finish a long rest.
                        Savage Attacks. When you score a critical hit with a melee weapon attack, you can roll one of the weapon’s damage dice one additional time and add it to the extra damage of the critical hit.
                        You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can’t discern color in darkness, only shades of gray.""";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set proficiency in Intimidation")
    void setProficiencyInIntimidationForHalfOrc() {
        Set<Skill> expectedResult = new HashSet<>(1);
        expectedResult.add(Skill.INTIMIDATION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }
}
