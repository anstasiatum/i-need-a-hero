package herocreationtests.modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;
import player.dndcharacter.dndclass.Wizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WizardTest {
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

        Wizard wizard = new Wizard();
        wizard.modifyByClass(dndCharacter);
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForWizard() {

        assertEquals(7, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForWizard() {

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForWizard() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForWizard() {

        assertEquals(6, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForWizard() {

        assertEquals(4, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForWizard() {
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
    void setSavingThrowsWithProficiencyForWizard() {
        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.WISDOM);
        expectedResult.add(Characteristics.INTELLIGENCE);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Spellcasting Ability")
    void setSpellcastingAbilityForWizard() {

        assertEquals(SpellcastingAbility.INTELLIGENCE, dndCharacter.getSpellcastingAbility());
    }

    @Test
    @DisplayName("Set Spells Known Per Level")
    void setSpellsKnownPerLevelForWizard() {
        List<Integer> expectedResult = new ArrayList<>(Collections.nCopies(8, 0));
        expectedResult.add(0, 3);
        expectedResult.add(1, 2);

        assertEquals(expectedResult, dndCharacter.getSpellsKnownPerLevel());
    }

    @Test
    @DisplayName("Set Features And Proficiencies")
    void setFeaturesAndTraitsForWizard() {
        String expectedResult = """
                test feature
                Ritual Casting
                You can cast a wizard spell as a ritual if that spell has the ritual tag and you have the spell in your spellbook. You don't need to have the spell prepared.
                Arcane Recovery
                You have learned to regain some of your magical energy by studying your spellbook. Once per day when you finish a short rest, you can choose expended spell slots to recover. The spell slots can have a combined level that is equal to or less than half your wizard level (rounded up), and none of the slots can be 6th level or higher.
                Copying a Spell into the Book: When you find a wizard spell of 1st level or higher, you can add it to your spellbook if it is of a spell level you can prepare and if you can spare the time to decipher and copy it.
                Copying that spell into your spellbook involves reproducing the basic form of the spell, then deciphering the unique system of notation used by the wizard who wrote it. You must practice the spell until you understand the sounds or gestures required, then transcribe it into your spellbook using your own notation.
                For each level of the spell, the process takes 2 hours and costs 50 gp. The cost represents material components you expend as you experiment with the spell to master it, as well as the fine inks you need to record it. Once you have spent this time and money, you can prepare the spell just like your other spells.
                Replacing the Book: You can copy a spell from your own spellbook into another book—for example, if you want to make a backup copy of your spellbook. This is just like copying a new spell into your spellbook, but faster and easier, since you understand your own notation and already know how to cast the spell. You need spend only 1 hour and 10 gp for each level of the copied spell.
                If you lose your spellbook, you can use the same procedure to transcribe the spells that you have prepared into a new spellbook. Filling out the remainder of your spellbook requires you to find new spells to do so, as normal. For this reason, many wizards keep backup spellbooks in a safe place. 
                """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
