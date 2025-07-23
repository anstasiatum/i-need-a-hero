package herocreationtests;

import org.junit.jupiter.api.Test;
import player.dndcharacter.dndcharacterenums.Skills;
import player.userinputhandler.commands.createnewhero.BuildAvailableProficiencySkillsWithoutApplied;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildAvailableProficiencySkillsWithoutAppliedTest {
    private final BuildAvailableProficiencySkillsWithoutApplied buildSkills = new BuildAvailableProficiencySkillsWithoutApplied();

    @Test
    void testNoSkillsAppliedReturnsAllAvailable() {
        Set<Skills> allAvailable = EnumSet.of(Skills.ARCANA, Skills.MEDICINE, Skills.HISTORY);
        Set<Skills> applied = Set.of();

        Set<Skills> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(allAvailable, actualResult);
    }

    @Test
    void testSomeSkillsAppliedReturnsRemaining() {
        Set<Skills> allAvailable = EnumSet.of(Skills.PERCEPTION, Skills.INTIMIDATION, Skills.DECEPTION);
        Set<Skills> applied = Set.of(Skills.DECEPTION);

        Set<Skills> expected = Set.of(Skills.PERCEPTION, Skills.INTIMIDATION);
        Set<Skills> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(expected, actualResult);
    }

    @Test
    void testAllSkillsAppliedReturnsEmptySet() {
        Set<Skills> allAvailable = EnumSet.of(Skills.NATURE, Skills.SURVIVAL);
        Set<Skills> applied = Set.of(Skills.NATURE, Skills.SURVIVAL);

        Set<Skills> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testAvailableSkillsIsEmptyReturnsEmptySet() {
        Set<Skills> allAvailable = Set.of();
        Set<Skills> applied = Set.of(Skills.HISTORY);

        Set<Skills> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testAppliedSkillsNotInAvailableSkillsIgnoresThem() {
        Set<Skills> allAvailable = Set.of(Skills.ACROBATICS);
        Set<Skills> applied = Set.of(Skills.DECEPTION, Skills.STEALTH);

        Set<Skills> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(allAvailable, actualResult);
    }

    @Test
    void testBothInputsEmptyReturnsEmpty() {
        Set<Skills> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(Set.of(), Set.of());
        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testNullAvailableReturnsEmptySet() {
        Set<Skills> applied = Set.of(Skills.PERFORMANCE);
        Set<Skills> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, null);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testBothNullReturnsEmptySet() {
        Set<Skills> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(null, null);
        assertTrue(actualResult.isEmpty());
    }
}
