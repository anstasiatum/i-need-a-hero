package modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;
import player.dndcharacter.dndclass.Warlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarlockTest {
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

        Warlock warlock = new Warlock();
        warlock.modifyByClass(dndCharacter);
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForWarlock() {

        assertEquals(9, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForWarlock() {

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForWarlock() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForWarlock() {

        assertEquals(8, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForWarlock() {

        assertEquals(4, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForWarlock() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("test armour");
        expectedResult.add("Light Armour");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForWarlock() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("test weapon");
        expectedResult.add("Simple Weapons");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForWarlock() {
        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.WISDOM);
        expectedResult.add(Characteristics.CHARISMA);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Spellcasting Ability")
    void setSpellcastingAbilityForWarlock() {

        assertEquals(SpellcastingAbility.CHARISMA, dndCharacter.getSpellcastingAbility());
    }

    @Test
    @DisplayName("Set Spells Known")
    void setSpellsKnownForWarlock() {

        assertEquals(2, dndCharacter.getSpellsKnown());
    }

    @Test
    @DisplayName("Set Spells Known Per Level")
    void setSpellsKnownPerLevelForWarlock() {
        List<Integer> expectedResult = new ArrayList<>(Collections.nCopies(8, 0));
        expectedResult.add(0, 2);
        expectedResult.add(1, 2);

        assertEquals(expectedResult, dndCharacter.getSpellsKnownPerLevel());
    }
}
