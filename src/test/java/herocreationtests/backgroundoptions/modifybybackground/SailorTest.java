package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.backgrounds.Sailor;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Background.SAILOR;
import static player.dndcharacter.dndcharacterenums.Skill.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skill.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skill.PERCEPTION;

public class SailorTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skill> skills = new HashSet<>(1);
        skills.add(INSIGHT);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.setFeaturesAndTraits("test feature. ");
        dndCharacter.getToolProficiency().add("Test tool");
        dndCharacter.setFeaturesAndTraits("test feature. ");

        Sailor sailor = new Sailor();
        sailor.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForSailor() {

        assertEquals(SAILOR, dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForSailor() {
        Set<Skill> expectedResult = new HashSet<>(3);
        expectedResult.add(INSIGHT);
        expectedResult.add(ATHLETICS);
        expectedResult.add(PERCEPTION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set tool proficiency")
    void setToolProficiencyForSailor() {
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
    void setEquipmentForSailor() {
        String expectedResult = "test equipment. A belaying pin (club) or iron dagger, 50 feet of silk rope, a set of common clothes. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForSailor() {
        String expectedResult = "test feature. Ship's Passage: When you need to, you can secure free passage on a sailing ship for yourself and your adventuring companions. You might sail on the ship you served on, or another ship you have good relations with (perhaps one captained by a former crewmate). Because you're calling in a favor, you can't be certain of a schedule or route that will meet your every need. Your DM will determine how long it takes to get where you need to go. In return for your free passage, you and your companions are expected to assist the crew during the voyage.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
