package herocreationtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Background.getAllBackgrounds;
import static player.dndcharacter.dndcharacterenums.CharacterClass.getAllClasses;
import static player.dndcharacter.dndcharacterenums.Race.getAllRaces;
import static player.dndcharacter.dndcharacterenums.Skill.getAllSkills;
import static player.userinputhandler.enums.Alignment.getAllAlignments;

public class OutputTextsTest {
    @Test
    @DisplayName("Should contain all classes")
    void getAllClassesTest() {
        String expectedResult = """
                Barbarian
                Bard
                Cleric
                Druid
                Fighter
                Monk
                Paladin
                Ranger
                Rogue
                Sorcerer
                Warlock
                Wizard""";
        assertEquals(expectedResult, getAllClasses());
    }

    @Test
    @DisplayName("Should contain all races")
    void getAllRacesTest() {
        String expectedResult = """
                Hill Dwarf
                Mountain Dwarf
                Dark Elf
                Wood Elf
                High Elf
                Lightfoot Halfling
                Stout Halfling
                Base Human
                Variant Human
                Dragonborn
                Forest Gnome
                Rock Gnome
                Half Elf
                Half Orc
                Tiefling""";
        assertEquals(expectedResult, getAllRaces());
    }

    @Test
    @DisplayName("Should contain all skills")
    void getAllSkillsTest() {
        String expectedResult = """
                Survival
                Stealth
                Sleight Of Hand
                Religion
                Persuasion
                Performance
                Perception
                Nature
                Medicine
                Investigation
                Intimidation
                Insight
                History
                Deception
                Athletics
                Arcana
                Animal Handling
                Acrobatics""";
        assertEquals(expectedResult, getAllSkills());
    }

    @Test
    @DisplayName("Should contain all backgrounds")
    void getAllBackgroundsTest() {
        String expectedResult = """
                Acolyte
                Charlatan
                Criminal
                Entertainer
                Gladiator
                Folk Hero
                Guild Artisan
                Guild Merchant
                Hermit
                Noble
                Knight
                Outlander
                Sage
                Sailor
                Pirate
                Soldier
                Urchin
                Custom""";
        assertEquals(expectedResult, getAllBackgrounds());
    }

    @Test
    @DisplayName("Should contain all alignments")
    void getAllAlignmentsTest() {
        String expectedResult = """
                Lawful good
                Neutral good
                Chaotic good
                Lawful neutral
                True neutral
                Chaotic neutral
                Lawful evil
                Neutral evil
                Chaotic evil""";
        assertEquals(expectedResult, getAllAlignments());
    }
}
