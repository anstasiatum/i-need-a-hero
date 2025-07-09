package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Noble;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;

public class NobleTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skills> skills = new HashSet<>(1);
        skills.add(ATHLETICS);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");

        Noble noble = new Noble();
        noble.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForNoble() {

        assertEquals("Noble", dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForNoble() {
        Set<Skills> expectedResult = new HashSet<>(3);
        expectedResult.add(ATHLETICS);
        expectedResult.add(HISTORY);
        expectedResult.add(PERSUASION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForNoble() {

        assertEquals(35, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForNoble() {
        String expectedResult = "test equipment. A set of fine clothes, a signet ring, a scroll of pedigree. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }
}
