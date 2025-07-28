package herocreationtests;

import org.junit.jupiter.api.Test;
import player.dndcharacter.dndcharacterenums.Skill;
import player.userinputhandler.commands.createnewhero.BuildAvailableProficiencySkillsWithoutApplied;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildAvailableProficiencySkillsWithoutAppliedTest {
    private final BuildAvailableProficiencySkillsWithoutApplied buildSkills = new BuildAvailableProficiencySkillsWithoutApplied();

    @Test
    void testNoSkillsAppliedReturnsAllAvailable() {
        Set<Skill> allAvailable = EnumSet.of(Skill.ARCANA, Skill.MEDICINE, Skill.HISTORY);
        Set<Skill> applied = Set.of();

        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(allAvailable, actualResult);
    }

    @Test
    void testSomeSkillsAppliedReturnsRemaining() {
        Set<Skill> allAvailable = EnumSet.of(Skill.PERCEPTION, Skill.INTIMIDATION, Skill.DECEPTION);
        Set<Skill> applied = Set.of(Skill.DECEPTION);

        Set<Skill> expected = Set.of(Skill.PERCEPTION, Skill.INTIMIDATION);
        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(expected, actualResult);
    }

    @Test
    void testAllSkillsAppliedReturnsEmptySet() {
        Set<Skill> allAvailable = EnumSet.of(Skill.NATURE, Skill.SURVIVAL);
        Set<Skill> applied = Set.of(Skill.NATURE, Skill.SURVIVAL);

        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testAvailableSkillsIsEmptyReturnsEmptySet() {
        Set<Skill> allAvailable = Set.of();
        Set<Skill> applied = Set.of(Skill.HISTORY);

        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testAppliedSkillsNotInAvailableSkillsIgnoresThem() {
        Set<Skill> allAvailable = Set.of(Skill.ACROBATICS);
        Set<Skill> applied = Set.of(Skill.DECEPTION, Skill.STEALTH);

        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(allAvailable, actualResult);
    }

    @Test
    void testBothInputsEmptyReturnsEmpty() {
        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(Set.of(), Set.of());
        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testNullAvailableReturnsEmptySet() {
        Set<Skill> applied = Set.of(Skill.PERFORMANCE);
        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, null);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testBothNullReturnsEmptySet() {
        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(null, null);
        assertTrue(actualResult.isEmpty());
    }
}
