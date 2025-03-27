package modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;
import player.dndcharacter.dndclass.Cleric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClericTest {
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
    void setHitPointsForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        assertEquals(9, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        assertEquals(8, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        assertEquals(5, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(4);
        expectedResult.add("test armour");
        expectedResult.add("Light Armour");
        expectedResult.add("Medium Armour");
        expectedResult.add("Shields");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("test weapon");
        expectedResult.add("Simple Weapons");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.WISDOM);
        expectedResult.add(Characteristics.CHARISMA);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Features And Proficiencies")
    void setFeaturesAndTraitsForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        String expectedResult = """
                test feature
                Ritual Casting
                You can cast a cleric spell as a ritual if that spell has the ritual tag and you have the spell prepared.
                """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set Spellcasting Ability")
    void setSpellcastingAbilityForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        assertEquals(SpellcastingAbility.WISDOM, dndCharacter.getSpellcastingAbility());
    }

    @Test
    @DisplayName("Set Spells Known")
    void setSpellsKnownForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        assertEquals(3, dndCharacter.getSpellsKnown());
    }

    @Test
    @DisplayName("Set Spells Known Per Level")
    void setSpellsKnownPerLevelForCleric() {
        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);

        List<Integer> expectedResult = new ArrayList<>(Collections.nCopies(8, 0));
        expectedResult.add(0, 3);
        expectedResult.add(1, 2);

        assertEquals(expectedResult, dndCharacter.getSpellsKnownPerLevel());
    }
}
