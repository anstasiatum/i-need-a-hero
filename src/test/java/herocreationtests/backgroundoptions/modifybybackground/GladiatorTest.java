package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Gladiator;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Background.GLADIATOR;
import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;

public class GladiatorTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skills> skills = new HashSet<>();
        skills.add(ATHLETICS);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.getToolProficiency().add("Test tool");
        dndCharacter.setFeaturesAndTraits("test feature. ");

        Gladiator gladiator = new Gladiator();
        gladiator.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForGladiator() {

        assertEquals(GLADIATOR, dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForGladiator() {
        Set<Skills> expectedResult = new HashSet<>(3);
        expectedResult.add(ATHLETICS);
        expectedResult.add(ACROBATICS);
        expectedResult.add(PERFORMANCE);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set tool proficiency")
    void setToolProficiencyForGladiator() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Disguise Kit");
        expectedResult.add("Test tool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForGladiator() {

        assertEquals(25, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForGladiator() {
        String expectedResult = "test equipment. An admirer’s favor, a costume. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForGladiator() {
        String expectedResult = "test feature. By Popular Demand\n You can always find a place to perform, usually in an inn or tavern but possibly with a circus, at a theater, or even in a noble's court. At such a place, you receive free lodging and food of a modest or comfortable standard (depending on the quality of the establishment), as long as you perform each night. In addition, your performance makes you something of a local figure. When strangers recognize you in a town where you have performed, they typically take a liking to you. You can find a place to perform in any place that features combat for entertainment-perhaps a gladiatorial arena or secret pit fighting club.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
