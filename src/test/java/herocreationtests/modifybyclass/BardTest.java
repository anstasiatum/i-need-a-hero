package herocreationtests.modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;
import player.dndcharacter.dndclass.Bard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BardTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(12);
        dndCharacter.setConstitution(12);
        dndCharacter.setIntelligence(13);
        dndCharacter.setWisdom(14);
        dndCharacter.setCharisma(15);
        dndCharacter.getLanguages().add("Elvish");
        dndCharacter.getArmourProficiency().add("test armour");
        dndCharacter.getWeaponProficiency().add("test weapon");
        dndCharacter.setFeaturesAndTraits("test feature\n");

        Bard bard = new Bard();
        bard.modifyByClass(dndCharacter);
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForBard() {

        assertEquals(9, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForBard() {

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForBard() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForBard() {

        assertEquals(8, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForBard() {

        assertEquals(5, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForBard() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("test armour");
        expectedResult.add("Light Armour");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForBard() {
        Set<String> expectedResult = new HashSet<>(6);
        expectedResult.add("test weapon");
        expectedResult.add("Simple Weapons");
        expectedResult.add("Hand crossbows");
        expectedResult.add("Longswords");
        expectedResult.add("Rapiers");
        expectedResult.add("Shortswords");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Tool Proficiency")
    void setToolProficiencyForBard() {
        Set<String> expectedResult = new HashSet<>(1);
        expectedResult.add("Three musical instruments");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForBard() {
        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.DEXTERITY);
        expectedResult.add(Characteristics.CHARISMA);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Features And Proficiencies")
    void setFeaturesAndTraitsForBard() {
        String expectedResult = """
                test feature
                Ritual Casting
                You can cast any bard spell you know as a ritual if that spell has the ritual tag.
                Bardic Inspiration
                You can inspire others through stirring words or music. To do so, you use a bonus action on your turn to choose one creature other than yourself within 60 feet of you who can hear you. That creature gains one Bardic Inspiration die, a d6.
                Once within the next 10 minutes, the creature can roll the die and add the number rolled to one ability check, attack roll, or saving throw it makes. The creature can wait until after it rolls the d20 before deciding to use the Bardic Inspiration die, but must decide before the DM says whether the roll succeeds or fails. Once the Bardic Inspiration die is rolled, it is lost. A creature can have only one Bardic Inspiration die at a time.
                You can use this feature a number of times equal to your Charisma modifier (a minimum of once). You regain any expended uses when you finish a long rest.
                Spellcasting Focus
                You can use a musical instrument as a spellcasting focus for your bard spells.
                """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set Spellcasting Ability")
    void setSpellcastingAbilityForBard() {

        assertEquals(SpellcastingAbility.CHARISMA, dndCharacter.getSpellcastingAbility());
    }

    @Test
    @DisplayName("Set Spells Known")
    void setSpellsKnownForBard() {

        assertEquals(4, dndCharacter.getSpellsKnown());
    }

    @Test
    @DisplayName("Set Spells Known Per Level")
    void setSpellsKnownPerLevelForBard() {
        List<Integer> expectedResult = new ArrayList<>(Collections.nCopies(8, 0));
        expectedResult.add(0, 2);
        expectedResult.add(1, 2);

        assertEquals(expectedResult, dndCharacter.getSpellsKnownPerLevel());
    }
}
