package herocreationtests.backgroundoptions.modifybybackground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Soldier;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skills.INTIMIDATION;

public class SoldierTest {
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

        Soldier soldier = new Soldier();
        soldier.modifyByBackground(dndCharacter);
    }

    @Test
    @DisplayName("Set background")
    void setBackgroundForSoldier() {

        assertEquals("Soldier", dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set skills with proficiency")
    void setSkillsWithProficiencyForSoldier() {
        Set<Skills> expectedResult = new HashSet<>(3);
        expectedResult.add(INSIGHT);
        expectedResult.add(ATHLETICS);
        expectedResult.add(INTIMIDATION);

        assertEquals(expectedResult, dndCharacter.getSkillsWithProficiency());
    }

    @Test
    @DisplayName("Set tool proficiency")
    void setToolProficiencyForSoldier() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Vehicles (land)");
        expectedResult.add("Test tool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set gold")
    void setGoldForSoldier() {

        assertEquals(20, dndCharacter.getGold());
    }

    @Test
    @DisplayName("Set equipment")
    void setEquipmentForSoldier() {
        String expectedResult = "test equipment. An insignia of rank, a set of common clothes. ";

        assertEquals(expectedResult, dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set features and traits")
    void setFeaturesAndTraitsForSoldier() {
        String expectedResult = "test feature. Military Rank\nYou have a military rank from your career as a soldier. Soldiers loyal to your former military organization still recognize your authority and influence, and they defer to you if they are of a lower rank. You can invoke your rank to exert influence over other soldiers and requisition simple equipment or horses for temporary use. You can also usually gain access to friendly military encampments and fortresses where your rank is recognized. ";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
