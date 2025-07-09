package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Entertainer;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;

public class EntertainerTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skills> skills = new HashSet<>();
        skills.add(ATHLETICS);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.getToolProficiency().add("Test tool");

        Entertainer entertainer = new Entertainer();
        entertainer.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForEntertainer() {
        Set<Skills> expectedResult = new HashSet<>(3);
        expectedResult.add(ATHLETICS);
        expectedResult.add(ACROBATICS);
        expectedResult.add(PERFORMANCE);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set tool proficiency")
    void setToolProficiencyForEntertainer() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Disguise Kit");
        expectedResult.add("Test tool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForEntertainer() {

        assertEquals(25, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForEntertainer() {
        String expectedResult = "test equipment. An admirer’s favor, a costume. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }
}
