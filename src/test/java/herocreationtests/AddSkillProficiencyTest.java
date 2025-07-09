package herocreationtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skills.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skills.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skills.INTIMIDATION;
import static player.dndcharacter.dndcharacterenums.Skills.INVESTIGATION;
import static player.dndcharacter.dndcharacterenums.Skills.MEDICINE;
import static player.dndcharacter.dndcharacterenums.Skills.NATURE;
import static player.dndcharacter.dndcharacterenums.Skills.PERCEPTION;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;
import static player.dndcharacter.dndcharacterenums.Skills.RELIGION;
import static player.dndcharacter.dndcharacterenums.Skills.SLEIGHT_OF_HAND;
import static player.dndcharacter.dndcharacterenums.Skills.STEALTH;
import static player.dndcharacter.dndcharacterenums.Skills.SURVIVAL;
import static player.userinputhandler.commands.createnewhero.AddSkillProficiency.addSkillProficiency;

public class AddSkillProficiencyTest {
    DndCharacter dndCharacter = new DndCharacter();

    @Test
    @DisplayName("Add survival")
    void addSurvival() {
        addSkillProficiency(dndCharacter, "survival");
        Set<Skills> expectedResult = EnumSet.of(SURVIVAL);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add stealth")
    void addStealth() {
        addSkillProficiency(dndCharacter, "stealth");
        Set<Skills> expectedResult = EnumSet.of(STEALTH);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add sleight of hand")
    void addSleightOfHand() {
        addSkillProficiency(dndCharacter, "sleight of hand");
        Set<Skills> expectedResult = EnumSet.of(SLEIGHT_OF_HAND);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add religion")
    void addReligion() {
        addSkillProficiency(dndCharacter, "religion");
        Set<Skills> expectedResult = EnumSet.of(RELIGION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add persuasion")
    void addPersuasion() {
        addSkillProficiency(dndCharacter, "persuasion");
        Set<Skills> expectedResult = EnumSet.of(PERSUASION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add performance")
    void addPerformance() {
        addSkillProficiency(dndCharacter, "performance");
        Set<Skills> expectedResult = EnumSet.of(PERFORMANCE);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add perception")
    void addPerception() {
        addSkillProficiency(dndCharacter, "perception");
        Set<Skills> expectedResult = EnumSet.of(PERCEPTION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add nature")
    void addNature() {
        addSkillProficiency(dndCharacter, "nature");
        Set<Skills> expectedResult = EnumSet.of(NATURE);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add medicine")
    void addMedicine() {
        addSkillProficiency(dndCharacter, "medicine");
        Set<Skills> expectedResult = EnumSet.of(MEDICINE);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add investigation")
    void addInvestigation() {
        addSkillProficiency(dndCharacter, "investigation");
        Set<Skills> expectedResult = EnumSet.of(INVESTIGATION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add intimidation")
    void addIntimidation() {
        addSkillProficiency(dndCharacter, "intimidation");
        Set<Skills> expectedResult = EnumSet.of(INTIMIDATION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add insight")
    void addInsight() {
        addSkillProficiency(dndCharacter, "insight");
        Set<Skills> expectedResult = EnumSet.of(INSIGHT);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add history")
    void addHistory() {
        addSkillProficiency(dndCharacter, "history");
        Set<Skills> expectedResult = EnumSet.of(HISTORY);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add deception")
    void addDeception() {
        addSkillProficiency(dndCharacter, "deception");
        Set<Skills> expectedResult = EnumSet.of(DECEPTION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add athletics")
    void addAthletics() {
        addSkillProficiency(dndCharacter, "athletics");
        Set<Skills> expectedResult = EnumSet.of(ATHLETICS);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add arcana")
    void addArcana() {
        addSkillProficiency(dndCharacter, "arcana");
        Set<Skills> expectedResult = EnumSet.of(ARCANA);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add animal handling")
    void addAnimalHandling() {
        addSkillProficiency(dndCharacter, "animal handling");
        Set<Skills> expectedResult = EnumSet.of(ANIMAL_HANDLING);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add acrobatics")
    void addAcrobatics() {
        addSkillProficiency(dndCharacter, "acrobatics");
        Set<Skills> expectedResult = EnumSet.of(ACROBATICS);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Trim test")
    void addSkillTrimTest() {
        addSkillProficiency(dndCharacter, " Performance ");
        Set<Skills> expectedResult = EnumSet.of(PERFORMANCE);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Wrong input test")
    void wrongSkillInput() {
        Set<Skills> expectedResult = EnumSet.noneOf(Skills.class);
        String expectedMessage = "Wrong input";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> addSkillProficiency(dndCharacter, "test"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
        assertEquals(expectedMessage, illegalArgumentException.getMessage());
    }
}
