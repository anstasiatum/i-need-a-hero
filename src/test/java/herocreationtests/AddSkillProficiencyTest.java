package herocreationtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import static player.userinputhandler.commands.createnewhero.AddSkillProficiency.addSkillProficiency;

public class AddSkillProficiencyTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @Test
    @DisplayName("Add survival")
    void addSurvival() {
        Set<Skill> expectedResult = EnumSet.of(SURVIVAL);

        assertTrue(addSkillProficiency(dndCharacter, "survival"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add stealth")
    void addStealth() {
        Set<Skill> expectedResult = EnumSet.of(STEALTH);

        assertTrue(addSkillProficiency(dndCharacter, "stealth"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add sleight of hand")
    void addSleightOfHand() {
        Set<Skill> expectedResult = EnumSet.of(SLEIGHT_OF_HAND);

        assertTrue(addSkillProficiency(dndCharacter, "sleight of hand"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add religion")
    void addReligion() {
        Set<Skill> expectedResult = EnumSet.of(RELIGION);

        assertTrue(addSkillProficiency(dndCharacter, "religion"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add persuasion")
    void addPersuasion() {
        Set<Skill> expectedResult = EnumSet.of(PERSUASION);

        assertTrue(addSkillProficiency(dndCharacter, "persuasion"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add performance")
    void addPerformance() {
        Set<Skill> expectedResult = EnumSet.of(PERFORMANCE);

        assertTrue(addSkillProficiency(dndCharacter, "performance"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add perception")
    void addPerception() {
        Set<Skill> expectedResult = EnumSet.of(PERCEPTION);

        assertTrue(addSkillProficiency(dndCharacter, "perception"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add nature")
    void addNature() {
        Set<Skill> expectedResult = EnumSet.of(NATURE);

        assertTrue(addSkillProficiency(dndCharacter, "nature"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add medicine")
    void addMedicine() {
        Set<Skill> expectedResult = EnumSet.of(MEDICINE);

        assertTrue(addSkillProficiency(dndCharacter, "medicine"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add investigation")
    void addInvestigation() {
        Set<Skill> expectedResult = EnumSet.of(INVESTIGATION);

        assertTrue(addSkillProficiency(dndCharacter, "investigation"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add intimidation")
    void addIntimidation() {
        Set<Skill> expectedResult = EnumSet.of(INTIMIDATION);

        assertTrue(addSkillProficiency(dndCharacter, "intimidation"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add insight")
    void addInsight() {
        Set<Skill> expectedResult = EnumSet.of(INSIGHT);

        assertTrue(addSkillProficiency(dndCharacter, "insight"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add history")
    void addHistory() {
        Set<Skill> expectedResult = EnumSet.of(HISTORY);

        assertTrue(addSkillProficiency(dndCharacter, "history"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add deception")
    void addDeception() {
        Set<Skill> expectedResult = EnumSet.of(DECEPTION);

        assertTrue(addSkillProficiency(dndCharacter, "deception"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add athletics")
    void addAthletics() {
        Set<Skill> expectedResult = EnumSet.of(ATHLETICS);

        assertTrue(addSkillProficiency(dndCharacter, "athletics"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add arcana")
    void addArcana() {
        Set<Skill> expectedResult = EnumSet.of(ARCANA);

        assertTrue(addSkillProficiency(dndCharacter, "arcana"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add animal handling")
    void addAnimalHandling() {
        Set<Skill> expectedResult = EnumSet.of(ANIMAL_HANDLING);

        assertTrue(addSkillProficiency(dndCharacter, "animal handling"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add acrobatics")
    void addAcrobatics() {
        Set<Skill> expectedResult = EnumSet.of(ACROBATICS);

        assertTrue(addSkillProficiency(dndCharacter, "acrobatics"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Trim test")
    void addSkillTrimTest() {
        Set<Skill> expectedResult = EnumSet.of(PERFORMANCE);

        assertTrue(addSkillProficiency(dndCharacter, " Performance "));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Wrong input test")
    void wrongSkillInput() {
        Set<Skill> expectedResult = EnumSet.noneOf(Skill.class);
        String expectedMessage = "Wrong input";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> assertFalse(addSkillProficiency(dndCharacter, "test")));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
        assertEquals(expectedMessage, illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: acrobatics")
    void addSkillHeroAlreadyProficientInInput() {
        dndCharacter.getSkillsWithProficiency().add(ACROBATICS);
        Set<Skill> expectedResult = EnumSet.of(ACROBATICS);

        assertFalse(addSkillProficiency(dndCharacter, "Acrobatics"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: animal handling")
    void addSkillHeroAlreadyProficientInAnimalHandling() {
        dndCharacter.getSkillsWithProficiency().add(ANIMAL_HANDLING);
        Set<Skill> expectedResult = EnumSet.of(ANIMAL_HANDLING);

        assertFalse(addSkillProficiency(dndCharacter, "Animal handling"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: arcana")
    void addSkillHeroAlreadyProficientInArcana() {
        dndCharacter.getSkillsWithProficiency().add(ARCANA);
        Set<Skill> expectedResult = EnumSet.of(ARCANA);

        assertFalse(addSkillProficiency(dndCharacter, "Arcana"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: athletics")
    void addSkillHeroAlreadyProficientInAthletics() {
        dndCharacter.getSkillsWithProficiency().add(ATHLETICS);
        Set<Skill> expectedResult = EnumSet.of(ATHLETICS);

        assertFalse(addSkillProficiency(dndCharacter, "Athletics"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: deception")
    void addSkillHeroAlreadyProficientInDeception() {
        dndCharacter.getSkillsWithProficiency().add(DECEPTION);
        Set<Skill> expectedResult = EnumSet.of(DECEPTION);

        assertFalse(addSkillProficiency(dndCharacter, "Deception"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: history")
    void addSkillHeroAlreadyProficientInHistory() {
        dndCharacter.getSkillsWithProficiency().add(HISTORY);
        Set<Skill> expectedResult = EnumSet.of(HISTORY);

        assertFalse(addSkillProficiency(dndCharacter, "History"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: insight")
    void addSkillHeroAlreadyProficientInInsight() {
        dndCharacter.getSkillsWithProficiency().add(INSIGHT);
        Set<Skill> expectedResult = EnumSet.of(INSIGHT);

        assertFalse(addSkillProficiency(dndCharacter, "Insight"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: intimidation")
    void addSkillHeroAlreadyProficientInIntimidation() {
        dndCharacter.getSkillsWithProficiency().add(INTIMIDATION);
        Set<Skill> expectedResult = EnumSet.of(INTIMIDATION);

        assertFalse(addSkillProficiency(dndCharacter, "Intimidation"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: investigation")
    void addSkillHeroAlreadyProficientInInvestigation() {
        dndCharacter.getSkillsWithProficiency().add(INVESTIGATION);
        Set<Skill> expectedResult = EnumSet.of(INVESTIGATION);

        assertFalse(addSkillProficiency(dndCharacter, "Investigation"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: medicine")
    void addSkillHeroAlreadyProficientInMedicine() {
        dndCharacter.getSkillsWithProficiency().add(MEDICINE);
        Set<Skill> expectedResult = EnumSet.of(MEDICINE);

        assertFalse(addSkillProficiency(dndCharacter, "Medicine"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: nature")
    void addSkillHeroAlreadyProficientInNature() {
        dndCharacter.getSkillsWithProficiency().add(NATURE);
        Set<Skill> expectedResult = EnumSet.of(NATURE);

        assertFalse(addSkillProficiency(dndCharacter, "Nature"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: perception")
    void addSkillHeroAlreadyProficientInPerception() {
        dndCharacter.getSkillsWithProficiency().add(PERCEPTION);
        Set<Skill> expectedResult = EnumSet.of(PERCEPTION);

        assertFalse(addSkillProficiency(dndCharacter, "Perception"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: performance")
    void addSkillHeroAlreadyProficientInPerformance() {
        dndCharacter.getSkillsWithProficiency().add(PERFORMANCE);
        Set<Skill> expectedResult = EnumSet.of(PERFORMANCE);

        assertFalse(addSkillProficiency(dndCharacter, "Performance"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: persuasion")
    void addSkillHeroAlreadyProficientInPersuasion() {
        dndCharacter.getSkillsWithProficiency().add(PERSUASION);
        Set<Skill> expectedResult = EnumSet.of(PERSUASION);

        assertFalse(addSkillProficiency(dndCharacter, "Persuasion"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: religion")
    void addSkillHeroAlreadyProficientInReligion() {
        dndCharacter.getSkillsWithProficiency().add(RELIGION);
        Set<Skill> expectedResult = EnumSet.of(RELIGION);

        assertFalse(addSkillProficiency(dndCharacter, "Religion"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: sleight of hand")
    void addSkillHeroAlreadyProficientInSleightOfHand() {
        dndCharacter.getSkillsWithProficiency().add(SLEIGHT_OF_HAND);
        Set<Skill> expectedResult = EnumSet.of(SLEIGHT_OF_HAND);

        assertFalse(addSkillProficiency(dndCharacter, "Sleight of Hand"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add a skill the hero is already proficient in test: stealth")
    void addSkillHeroAlreadyProficientInStealth() {
        dndCharacter.getSkillsWithProficiency().add(STEALTH);
        Set<Skill> expectedResult = EnumSet.of(STEALTH);

        assertFalse(addSkillProficiency(dndCharacter, "Stealth"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }


    @Test
    @DisplayName("Add a skill the hero is already proficient in test: survival")
    void addSkillHeroAlreadyProficientInSurvival() {
        dndCharacter.getSkillsWithProficiency().add(SURVIVAL);
        Set<Skill> expectedResult = EnumSet.of(SURVIVAL);

        assertFalse(addSkillProficiency(dndCharacter, "Survival"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }
}
