package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Hermit;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.MEDICINE;
import static player.dndcharacter.dndcharacterenums.Skills.RELIGION;

public class HermitTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        Set<Skills> skills = new HashSet<>(1);
        skills.add(ATHLETICS);
        dndCharacter.setSkillsWithProficiency(skills);

        dndCharacter.setGold(10);

        dndCharacter.setEquipment("test equipment. ");
        dndCharacter.setFeaturesAndTraits("test feature. ");
        dndCharacter.getToolProficiency().add("Test tool");

        Hermit hermit = new Hermit();
        hermit.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForHermit() {

        assertEquals("Hermit", dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForHermit() {
        Set<Skills> expectedResult = new HashSet<>(3);
        expectedResult.add(ATHLETICS);
        expectedResult.add(MEDICINE);
        expectedResult.add(RELIGION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set tool proficiency")
    void setToolProficiencyForHermit() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Herbalism Kit");
        expectedResult.add("Test tool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForHermit() {

        assertEquals(15, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForHermit() {
        String expectedResult = "test equipment. A scroll case stuffed full of notes from your studies or prayers, a winter blanket, a set of common clothes, an herbalism kit. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForHermit() {
        String expectedResult = "test feature. Discovery\n The quiet seclusion of your extended hermitage gave you access to a unique and powerful discovery. The exact nature of this revelation depends on the nature of your seclusion. It might be a great truth about the cosmos, the deities, the powerful beings of the outer planes, or the forces of nature. It could be a site that no one else has ever seen. You might have uncovered a fact that has long been forgotten, or unearthed some relic of the past that could rewrite history. It might be information that would be damaging to the people who or consigned you to exile, and hence the reason for your return to society. ";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
