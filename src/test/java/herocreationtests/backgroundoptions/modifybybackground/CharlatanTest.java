package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.backgrounds.Charlatan;
import player.dndcharacter.dndcharacterenums.ProficiencyLevel;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Background.CHARLATAN;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skill.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.SLEIGHT_OF_HAND;

public class CharlatanTest {
    private final  DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.getSkillsWithProficiency().put(ATHLETICS, PROFICIENT);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.setFeaturesAndTraits("test feature. ");


        Charlatan charlatan = new Charlatan();
        charlatan.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForCharlatan() {

        assertEquals(CHARLATAN, dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForCharlatan() {
        Map<Skill, ProficiencyLevel> expectedResult = new HashMap<>(3);
        expectedResult.put(ATHLETICS, PROFICIENT);
        expectedResult.put(DECEPTION, PROFICIENT);
        expectedResult.put(SLEIGHT_OF_HAND, PROFICIENT);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForCharlatan() {

        assertEquals(25, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForCharlatan() {
        String expectedResult = "test equipment. A set of fine clothes, a disguise kit. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForCharlatan() {
        String expectedResult = "test feature. False Identity\n You have created a second identity that includes documentation, established acquaintances, and disguises that allow you to assume that persona. Additionally, you can forge documents including official papers and personal letters, as long as you have seen an example of the kind of document or the handwriting you are trying to copy. ";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
