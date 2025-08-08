package herocreationtests.addskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.ProficiencyLevel;
import player.dndcharacter.dndcharacterenums.Skill;
import player.userinputhandler.commands.createnewhero.AddSkill;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skill.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skill.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skill.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skill.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skill.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skill.INTIMIDATION;
import static player.dndcharacter.dndcharacterenums.Skill.INVESTIGATION;
import static player.dndcharacter.dndcharacterenums.Skill.MEDICINE;
import static player.dndcharacter.dndcharacterenums.Skill.NATURE;
import static player.dndcharacter.dndcharacterenums.Skill.PERCEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.PERFORMANCE;
import static player.dndcharacter.dndcharacterenums.Skill.PERSUASION;
import static player.dndcharacter.dndcharacterenums.Skill.RELIGION;
import static player.dndcharacter.dndcharacterenums.Skill.SLEIGHT_OF_HAND;
import static player.dndcharacter.dndcharacterenums.Skill.STEALTH;
import static player.dndcharacter.dndcharacterenums.Skill.SURVIVAL;

public class AddSkillProficiencyTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private final AddSkill skillProficiency = new AddSkill();
    private Map<Skill, ProficiencyLevel> expectedResult = new HashMap<>();

    @Test
    @DisplayName("Add survival")
    void addSurvival() {
        expectedResult.put(SURVIVAL, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "survival"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add stealth")
    void addStealth() {
        expectedResult.put(STEALTH, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "stealth"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add sleight of hand")
    void addSleightOfHand() {
        expectedResult.put(SLEIGHT_OF_HAND, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "sleight of hand"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add religion")
    void addReligion() {
        expectedResult.put(RELIGION, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "religion"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add persuasion")
    void addPersuasion() {
        expectedResult.put(PERSUASION, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "persuasion"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add performance")
    void addPerformance() {
        expectedResult.put(PERFORMANCE, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "performance"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add perception")
    void addPerception() {
        expectedResult.put(PERCEPTION, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "perception"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add nature")
    void addNature() {
        expectedResult.put(NATURE, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "nature"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add medicine")
    void addMedicine() {
        expectedResult.put(MEDICINE, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "medicine"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add investigation")
    void addInvestigation() {
        expectedResult.put(INVESTIGATION, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "investigation"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add intimidation")
    void addIntimidation() {
        expectedResult.put(INTIMIDATION, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "intimidation"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add insight")
    void addInsight() {
        expectedResult.put(INSIGHT, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "insight"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add history")
    void addHistory() {
        expectedResult.put(HISTORY, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "history"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add deception")
    void addDeception() {
        expectedResult.put(DECEPTION, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "deception"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add athletics")
    void addAthletics() {
        expectedResult.put(ATHLETICS, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "athletics"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add arcana")
    void addArcana() {
        expectedResult.put(ARCANA, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "arcana"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add animal handling")
    void addAnimalHandling() {
        expectedResult.put(ANIMAL_HANDLING, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "animal handling"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add acrobatics")
    void addAcrobatics() {
        expectedResult.put(ACROBATICS, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, "acrobatics"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Trim test")
    void addSkillTrimTest() {
        expectedResult.put(PERFORMANCE, PROFICIENT);

        assertTrue(skillProficiency.addSkillProficiency(dndCharacter, " Performance "));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Wrong input test")
    void wrongSkillInput() {
        expectedResult = Collections.emptyMap();
        String expectedMessage = "Wrong input";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "test")));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
        assertEquals(expectedMessage, illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: acrobatics")
    void addSkillHeroAlreadyProficientInInput() {
        dndCharacter.getSkillsWithProficiency().put(ACROBATICS, PROFICIENT);
        expectedResult.put(ACROBATICS, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Acrobatics"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: animal handling")
    void addSkillHeroAlreadyProficientInAnimalHandling() {
        dndCharacter.getSkillsWithProficiency().put(ANIMAL_HANDLING, PROFICIENT);
        expectedResult.put(ANIMAL_HANDLING, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Animal handling"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: arcana")
    void addSkillHeroAlreadyProficientInArcana() {
        dndCharacter.getSkillsWithProficiency().put(ARCANA, PROFICIENT);
        expectedResult.put(ARCANA, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Arcana"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: athletics")
    void addSkillHeroAlreadyProficientInAthletics() {
        dndCharacter.getSkillsWithProficiency().put(ATHLETICS, PROFICIENT);
        expectedResult.put(ATHLETICS, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Athletics"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: deception")
    void addSkillHeroAlreadyProficientInDeception() {
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        expectedResult.put(DECEPTION, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Deception"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: history")
    void addSkillHeroAlreadyProficientInHistory() {
        dndCharacter.getSkillsWithProficiency().put(HISTORY, PROFICIENT);
        expectedResult.put(HISTORY, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "History"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: insight")
    void addSkillHeroAlreadyProficientInInsight() {
        dndCharacter.getSkillsWithProficiency().put(INSIGHT, PROFICIENT);
        expectedResult.put(INSIGHT, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Insight"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: intimidation")
    void addSkillHeroAlreadyProficientInIntimidation() {
        dndCharacter.getSkillsWithProficiency().put(INTIMIDATION, PROFICIENT);
        expectedResult.put(INTIMIDATION, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Intimidation"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: investigation")
    void addSkillHeroAlreadyProficientInInvestigation() {
        dndCharacter.getSkillsWithProficiency().put(INVESTIGATION, PROFICIENT);
        expectedResult.put(INVESTIGATION, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Investigation"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: medicine")
    void addSkillHeroAlreadyProficientInMedicine() {
        dndCharacter.getSkillsWithProficiency().put(MEDICINE, PROFICIENT);
        expectedResult.put(MEDICINE, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Medicine"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: nature")
    void addSkillHeroAlreadyProficientInNature() {
        dndCharacter.getSkillsWithProficiency().put(NATURE, PROFICIENT);
        expectedResult.put(NATURE, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Nature"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: perception")
    void addSkillHeroAlreadyProficientInPerception() {
        dndCharacter.getSkillsWithProficiency().put(PERCEPTION, PROFICIENT);
        expectedResult.put(PERCEPTION, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Perception"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: performance")
    void addSkillHeroAlreadyProficientInPerformance() {
        dndCharacter.getSkillsWithProficiency().put(PERFORMANCE, PROFICIENT);
        expectedResult.put(PERFORMANCE, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Performance"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: persuasion")
    void addSkillHeroAlreadyProficientInPersuasion() {
        dndCharacter.getSkillsWithProficiency().put(PERSUASION, PROFICIENT);
        expectedResult.put(PERSUASION, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Persuasion"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: religion")
    void addSkillHeroAlreadyProficientInReligion() {
        dndCharacter.getSkillsWithProficiency().put(RELIGION, PROFICIENT);
        expectedResult.put(RELIGION, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Religion"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: sleight of hand")
    void addSkillHeroAlreadyProficientInSleightOfHand() {
        dndCharacter.getSkillsWithProficiency().put(SLEIGHT_OF_HAND, PROFICIENT);
        expectedResult.put(SLEIGHT_OF_HAND, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Sleight of Hand"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: stealth")
    void addSkillHeroAlreadyProficientInStealth() {
        dndCharacter.getSkillsWithProficiency().put(STEALTH, PROFICIENT);
        expectedResult.put(STEALTH, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Stealth"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }


    @Test
    @DisplayName("Add a skill the hero is already proficient in test: survival")
    void addSkillHeroAlreadyProficientInSurvival() {
        dndCharacter.getSkillsWithProficiency().put(SURVIVAL, PROFICIENT);
        expectedResult.put(SURVIVAL, PROFICIENT);

        assertFalse(skillProficiency.addSkillProficiency(dndCharacter, "Survival"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }
}
