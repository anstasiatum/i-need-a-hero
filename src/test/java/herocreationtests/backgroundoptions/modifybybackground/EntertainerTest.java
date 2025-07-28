package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Entertainer;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Background.ENTERTAINER;
import static player.dndcharacter.dndcharacterenums.Skill.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skill.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skill.PERFORMANCE;

public class EntertainerTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skill> skills = new HashSet<>();
        skills.add(ATHLETICS);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.getToolProficiency().add("Test tool");
        dndCharacter.setFeaturesAndTraits("test feature. ");

        Entertainer entertainer = new Entertainer();
        entertainer.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForEntertainer() {

        assertEquals(ENTERTAINER, dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForEntertainer() {
        Set<Skill> expectedResult = new HashSet<>(3);
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

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForEntertainer() {
        String expectedResult = "test feature. By Popular Demand\n You can perform at inns, theaters, circuses, or any place with a stage. While you’re performing there each night, you receive free modest or comfortable lodging and food. This can allow you to take long rests for free as you travel with your party across the land. In addition, your performance makes you famous wherever you perform. When strangers recognize you in the town, they usually like you more. This may make it easier to persuade them to do things for you.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
