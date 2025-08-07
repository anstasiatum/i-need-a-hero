package herocreationtests;

import org.junit.jupiter.api.Test;
import player.dndcharacter.dndcharacterenums.ProficiencyLevel;
import player.dndcharacter.dndcharacterenums.Skill;
import player.userinputhandler.commands.createnewhero.BuildAvailableProficiencySkillsWithoutApplied;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skill.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skill.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skill.INTIMIDATION;
import static player.dndcharacter.dndcharacterenums.Skill.MEDICINE;
import static player.dndcharacter.dndcharacterenums.Skill.NATURE;
import static player.dndcharacter.dndcharacterenums.Skill.PERCEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.PERFORMANCE;
import static player.dndcharacter.dndcharacterenums.Skill.STEALTH;
import static player.dndcharacter.dndcharacterenums.Skill.SURVIVAL;

public class BuildAvailableProficiencySkillsWithoutAppliedTest {
    private final BuildAvailableProficiencySkillsWithoutApplied buildSkills = new BuildAvailableProficiencySkillsWithoutApplied();

    @Test
    void testNoSkillsAppliedReturnsAllAvailable() {
        Set<Skill> allAvailable = EnumSet.of(ARCANA, MEDICINE, HISTORY);
        Map<Skill, ProficiencyLevel> applied = Collections.emptyMap();

        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(allAvailable, actualResult);
    }

    @Test
    void testSomeSkillsAppliedReturnsRemaining() {
        Set<Skill> allAvailable = EnumSet.of(PERCEPTION, INTIMIDATION, DECEPTION);
        Map<Skill, ProficiencyLevel> applied = new HashMap<>(1);
        applied.put(DECEPTION, PROFICIENT);

        Set<Skill> expected = Set.of(PERCEPTION, INTIMIDATION);
        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(expected, actualResult);
    }

    @Test
    void testAllSkillsAppliedReturnsEmptySet() {
        Set<Skill> allAvailable = EnumSet.of(NATURE, SURVIVAL);
        Map<Skill, ProficiencyLevel> applied = new HashMap<>(2);
        applied.put(NATURE, PROFICIENT);
        applied.put(SURVIVAL, PROFICIENT);

        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testAvailableSkillsIsEmptyReturnsEmptySet() {
        Set<Skill> allAvailable = Set.of();
        Map<Skill, ProficiencyLevel> applied = new HashMap<>(1);
        applied.put(HISTORY, PROFICIENT);

        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testAppliedSkillsNotInAvailableSkillsIgnoresThem() {
        Set<Skill> allAvailable = Set.of(ACROBATICS);
        Map<Skill, ProficiencyLevel> applied = new HashMap<>(2);
        applied.put(DECEPTION, PROFICIENT);
        applied.put(STEALTH, PROFICIENT);

        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, allAvailable);

        assertEquals(allAvailable, actualResult);
    }

    @Test
    void testBothInputsEmptyReturnsEmpty() {
        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(Collections.emptyMap(), Set.of());
        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testNullAvailableReturnsEmptySet() {
        Map<Skill, ProficiencyLevel> applied = new HashMap<>(1);
        applied.put(PERFORMANCE, PROFICIENT);
        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(applied, null);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    void testBothNullReturnsEmptySet() {
        Set<Skill> actualResult = buildSkills.buildAvailableProficiencySkillsWithoutApplied(null, null);
        assertTrue(actualResult.isEmpty());
    }
}
