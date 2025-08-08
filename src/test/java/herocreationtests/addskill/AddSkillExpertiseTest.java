package herocreationtests.addskill;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.ProficiencyLevel;
import player.dndcharacter.dndcharacterenums.Skill;
import player.userinputhandler.commands.createnewhero.AddSkill;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.EXPERTISE;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.SURVIVAL;

public class AddSkillExpertiseTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private final AddSkill skillExpertise = new AddSkill();
    private Map<Skill, ProficiencyLevel> expectedResult = new HashMap<>();

    @Test
    @DisplayName("Add expertise to a skill character proficient with")
    void addExpertiseToSkillCharacterProficientWith() {
        dndCharacter.getSkillsWithProficiency().put(SURVIVAL, PROFICIENT);
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        expectedResult.put(SURVIVAL, EXPERTISE);
        expectedResult.put(DECEPTION, PROFICIENT);

        assertTrue(skillExpertise.addSkillExpertise(dndCharacter, "survival"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add expertise to a skill character is not proficient with")
    void addExpertiseToSkillCharacterNotProficientWith() {
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        expectedResult.put(DECEPTION, PROFICIENT);

        assertFalse(skillExpertise.addSkillExpertise(dndCharacter, "survival"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Add expertise to a skill character is already expert with")
    void addExpertiseToSkillCharacterExpertWith() {
        dndCharacter.getSkillsWithProficiency().put(SURVIVAL, EXPERTISE);
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        expectedResult.put(SURVIVAL, EXPERTISE);
        expectedResult.put(DECEPTION, PROFICIENT);

        assertFalse(skillExpertise.addSkillExpertise(dndCharacter, "survival"));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Invalid skill test")
    void wrongSkillInput() {
        dndCharacter.getSkillsWithProficiency().put(SURVIVAL, EXPERTISE);
        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        expectedResult.put(SURVIVAL, EXPERTISE);
        expectedResult.put(DECEPTION, PROFICIENT);
        String expectedMessage = "Wrong input";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> assertFalse(skillExpertise.addSkillExpertise(dndCharacter, "test")));
        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
        assertEquals(expectedMessage, illegalArgumentException.getMessage());
    }
}
