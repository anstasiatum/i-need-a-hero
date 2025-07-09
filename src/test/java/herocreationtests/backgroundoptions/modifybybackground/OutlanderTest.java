package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Outlander;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skills.SURVIVAL;

public class OutlanderTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skills> skills = new HashSet<>(1);
        skills.add(INSIGHT);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.setFeaturesAndTraits("test feature. ");

        Outlander outlander = new Outlander();
        outlander.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForOutlander() {

        assertEquals("Outlander", dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForOutlander() {
        Set<Skills> expectedResult = new HashSet<>(3);
        expectedResult.add(INSIGHT);
        expectedResult.add(ATHLETICS);
        expectedResult.add(SURVIVAL);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForOutlander() {

        assertEquals(20, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForOutlander() {
        String expectedResult = "test equipment. A staff, a hunting trap, a trophy from an animal you killed, a set of traveler's clothes. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForOutlander() {
        String expectedResult = "test feature. Wanderer\nYou have an excellent memory for maps and geography, and you can always recall the general layout of terrain, settlements, and other features around you. In addition, you can find food and fresh water for yourself and up to five other people each day, provided that the land offers berries, small game, water, and so forth. ";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
