package herocreationtests.raceoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.commands.createnewhero.IncreaseBaseCharacteristics.increaseBaseCharacteristics;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN;

public class IncreaseBaseCharacteristicsTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(12);
        dndCharacter.setConstitution(14);
        dndCharacter.setIntelligence(16);
        dndCharacter.setWisdom(18);
        dndCharacter.setCharisma(20);
    }

    @Test
    @DisplayName("Increase Strength first for Half Elf")
    void increaseStrengthFirstForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "Strength");
        assertEquals(11, dndCharacter.getStrength());
    }

    @Test
    @DisplayName("Increase Dexterity first for Half Elf")
    void increaseDexterityFirstForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "Dexterity");
        assertEquals(13, dndCharacter.getDexterity());
    }

    @Test
    @DisplayName("Increase Constitution first for Half Elf")
    void increaseConstitutionFirstForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "Constitution");
        assertEquals(15, dndCharacter.getConstitution());
    }

    @Test
    @DisplayName("Increase Intelligence first for Half Elf")
    void increaseIntelligenceFirstForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "Intelligence");
        assertEquals(17, dndCharacter.getIntelligence());
    }

    @Test
    @DisplayName("Increase Wisdom first for Half Elf")
    void increaseWisdomFirstForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "Wisdom");
        assertEquals(19, dndCharacter.getWisdom());
    }

    @Test
    @DisplayName("Increase Charisma first for Half Elf")
    void increaseCharismaFirstForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "Charisma");
        assertEquals(21, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase Charisma first for Half Elf: trim test")
    void increaseCharismaFirstForHalfElfWithTrim() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "charisma ");
        assertEquals(21, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase characteristics first for Half Elf: invalid user answer")
    void increaseCharacteristicsFirstForHalfElfWrongUserAnswer() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "test");
        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase Strength first for Human")
    void increaseStrengthFirstForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Strength");
        assertEquals(11, dndCharacter.getStrength());
    }

    @Test
    @DisplayName("Increase Dexterity first for Human")
    void increaseDexterityFirstForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Dexterity");
        assertEquals(13, dndCharacter.getDexterity());
    }

    @Test
    @DisplayName("Increase Constitution first for Human")
    void increaseConstitutionFirstForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Constitution");
        assertEquals(15, dndCharacter.getConstitution());
    }

    @Test
    @DisplayName("Increase Intelligence first for Human")
    void increaseIntelligenceFirstForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Intelligence");
        assertEquals(17, dndCharacter.getIntelligence());
    }

    @Test
    @DisplayName("Increase Wisdom first for Human")
    void increaseWisdomFirstForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Wisdom");
        assertEquals(19, dndCharacter.getWisdom());
    }

    @Test
    @DisplayName("Increase Charisma first for Human")
    void increaseCharismaFirstForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Charisma");
        assertEquals(21, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase Charisma first for Human: trim test")
    void increaseCharismaFirstForHumanWithTrim() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, "charisma ");
        assertEquals(21, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase characteristics first for Human: invalid user answer")
    void increaseCharacteristicsFirstForHumanWrongUserAnswer() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, "test");
        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase Strength second for Half Elf")
    void increaseStrengthSecondForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "Strength");
        assertEquals(11, dndCharacter.getStrength());
    }

    @Test
    @DisplayName("Increase Dexterity second for Half Elf")
    void increaseDexteritySecondForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "Dexterity");
        assertEquals(13, dndCharacter.getDexterity());
    }

    @Test
    @DisplayName("Increase Constitution second for Half Elf")
    void increaseConstitutionSecondForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "Constitution");
        assertEquals(15, dndCharacter.getConstitution());
    }

    @Test
    @DisplayName("Increase Intelligence second for Half Elf")
    void increaseIntelligenceSecondForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "Intelligence");
        assertEquals(17, dndCharacter.getIntelligence());
    }

    @Test
    @DisplayName("Increase Wisdom second for Half Elf")
    void increaseWisdomFSecondForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "Wisdom");
        assertEquals(19, dndCharacter.getWisdom());
    }

    @Test
    @DisplayName("Increase Charisma second for Half Elf")
    void increaseCharismaSecondForHalfElf() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "Charisma");
        assertEquals(21, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase Charisma second for Half Elf: trim test")
    void increaseCharismaSecondForHalfElfWithTrim() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "charisma ");
        assertEquals(21, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase characteristics second for Half Elf: invalid user answer")
    void increaseCharacteristicsSecondForHalfElfWrongUserAnswer() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_HALF_ELF, "test");
        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());
    }
    @Test
    @DisplayName("Increase Strength second for Human")
    void increaseStrengthSecondForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Strength");
        assertEquals(11, dndCharacter.getStrength());
    }

    @Test
    @DisplayName("Increase Dexterity second for Human")
    void increaseDexteritySecondForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Dexterity");
        assertEquals(13, dndCharacter.getDexterity());
    }

    @Test
    @DisplayName("Increase Constitution second for Human")
    void increaseConstitutionSecondForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Constitution");
        assertEquals(15, dndCharacter.getConstitution());
    }

    @Test
    @DisplayName("Increase Intelligence second for Human")
    void increaseIntelligenceSecondForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Intelligence");
        assertEquals(17, dndCharacter.getIntelligence());
    }

    @Test
    @DisplayName("Increase Wisdom second for Human")
    void increaseWisdomFSecondForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Wisdom");
        assertEquals(19, dndCharacter.getWisdom());
    }

    @Test
    @DisplayName("Increase Charisma second for Human")
    void increaseCharismaSecondForHuman() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "Charisma");
        assertEquals(21, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase Charisma second for Human: trim test")
    void increaseCharismaSecondForHumanWithTrim() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "charisma ");
        assertEquals(21, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase characteristics second for Human: invalid user answer")
    void increaseCharacteristicsSecondForHumanWrongUserAnswer() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_SECOND_ABILITY_SCORE_FOR_VARIANT_HUMAN, "test");
        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());
    }

    @Test
    @DisplayName("Increase characteristics second for Human: invalid step")
    void increaseCharacteristicsSecondForHumanWrongStep() {
        increaseBaseCharacteristics(dndCharacter, CHOOSE_CLASS, "Charisma");
        assertEquals(10, dndCharacter.getStrength());
        assertEquals(12, dndCharacter.getDexterity());
        assertEquals(14, dndCharacter.getConstitution());
        assertEquals(16, dndCharacter.getIntelligence());
        assertEquals(18, dndCharacter.getWisdom());
        assertEquals(20, dndCharacter.getCharisma());
    }
}
