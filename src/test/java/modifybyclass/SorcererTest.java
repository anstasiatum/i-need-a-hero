package modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;
import player.dndcharacter.dndclass.Sorcerer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SorcererTest {
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
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        assertEquals(7, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        assertEquals(6, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        assertEquals(3, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(1);
        expectedResult.add("test armour");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(6);
        expectedResult.add("test weapon");
        expectedResult.add("Daggers");
        expectedResult.add("Darts");
        expectedResult.add("Slings");
        expectedResult.add("Quarterstaffs");
        expectedResult.add("Light Crossbows");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.CONSTITUTION);
        expectedResult.add(Characteristics.CHARISMA);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Spellcasting Ability")
    void setSpellcastingAbilityForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        assertEquals(SpellcastingAbility.CHARISMA, dndCharacter.getSpellcastingAbility());
    }

    @Test
    @DisplayName("Set Spells Known")
    void setSpellsKnownForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        assertEquals(2, dndCharacter.getSpellsKnown());
    }

    @Test
    @DisplayName("Set Spells Known Per Level")
    void setSpellsKnownPerLevelForSorcerer() {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.modifyByClass(dndCharacter);

        List<Integer> expectedResult = new ArrayList<>(Collections.nCopies(8, 0));
        expectedResult.add(0, 4);
        expectedResult.add(1, 2);

        assertEquals(expectedResult, dndCharacter.getSpellsKnownPerLevel());
    }
}
