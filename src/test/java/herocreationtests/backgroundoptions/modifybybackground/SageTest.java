package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.backgrounds.Sage;
import player.dndcharacter.dndcharacterenums.ProficiencyLevel;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Background.SAGE;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skill.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skill.HISTORY;

public class SageTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.getSkillsWithProficiency().put(ATHLETICS, PROFICIENT);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.setFeaturesAndTraits("test feature. ");

        Sage sage = new Sage();
        sage.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForSage() {

        assertEquals(SAGE, dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForSage() {
        Map<Skill, ProficiencyLevel> expectedResult = new HashMap<>(3);
        expectedResult.put(ATHLETICS, PROFICIENT);
        expectedResult.put(ARCANA, PROFICIENT);
        expectedResult.put(HISTORY, PROFICIENT);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForSage() {

        assertEquals(20, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForSage() {
        String expectedResult = "test equipment. A bottle of black ink, a quill, a small knife, a letter from a dead colleague posing a question you have not yet been able to answer, a set of common clothes. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForSage() {
        String expectedResult = "test feature. Researcher\nWhen you attempt to learn or recall a piece of lore, if you do not know that information, you often know where and from whom you can obtain it. Usually, this information comes from a library, scriptorium, university, or a sage or other learned person or creature. ";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
