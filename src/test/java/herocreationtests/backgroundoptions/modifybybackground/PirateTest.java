package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Pirate;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Background.PIRATE;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skills.PERCEPTION;

public class PirateTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skills> skills = new HashSet<>(1);
        skills.add(INSIGHT);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.setFeaturesAndTraits("test feature. ");
        dndCharacter.getToolProficiency().add("Test tool");
        dndCharacter.setFeaturesAndTraits("test feature. ");

        Pirate pirate = new Pirate();
        pirate.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForPirate() {

        assertEquals(PIRATE, dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForPirate() {
        Set<Skills> expectedResult = new HashSet<>(3);
        expectedResult.add(INSIGHT);
        expectedResult.add(ATHLETICS);
        expectedResult.add(PERCEPTION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set tool proficiency")
    void setToolProficiencyForPirate() {
        Set<String> expectedResult = new HashSet<>(3);
        expectedResult.add("Navigator's tools");
        expectedResult.add("Vehicles (water)");
        expectedResult.add("Test tool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForSailor() {

        assertEquals(20, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForPirate() {
        String expectedResult = "test equipment. A belaying pin (club) or iron dagger, 50 feet of silk rope, a set of common clothes. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }
}
