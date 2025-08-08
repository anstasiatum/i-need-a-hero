package herocreationtests.classoptions.modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.characterclass.characterclasses.Cleric;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.CharacterClass.CLERIC;

public class ClericTest {
    private final DndCharacter dndCharacter = new DndCharacter();

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

        Cleric cleric = new Cleric();
        cleric.modifyByClass(dndCharacter);
    }

    @Test
    @DisplayName("Set Class Name")
    void setClassNameForCleric() {

        assertEquals(CLERIC, dndCharacter.getCharacterClass());
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForCleric() {

        assertEquals(9, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForCleric() {

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForCleric() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForCleric() {

        assertEquals(8, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForCleric() {

        assertEquals(5, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForCleric() {
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
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("test weapon");
        expectedResult.add("Simple Weapons");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForCleric() {
        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.WISDOM);
        expectedResult.add(Characteristics.CHARISMA);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Features And Proficiencies")
    void setFeaturesAndTraitsForCleric() {
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

        assertEquals(SpellcastingAbility.WISDOM, dndCharacter.getSpellcastingAbility());
    }

    @Test
    @DisplayName("Set Spells Known")
    void setSpellsKnownForCleric() {

        assertEquals(3, dndCharacter.getSpellsKnown());
    }

    @Test
    @DisplayName("Set Spells Known Per Level")
    void setSpellsKnownPerLevelForCleric() {
        List<Integer> expectedResult = new ArrayList<>(Collections.nCopies(8, 0));
        expectedResult.add(0, 3);
        expectedResult.add(1, 2);

        assertEquals(expectedResult, dndCharacter.getSpellsKnownPerLevel());
    }
}
