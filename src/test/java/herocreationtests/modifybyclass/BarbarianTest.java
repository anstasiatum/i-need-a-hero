package herocreationtests.modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndclass.Barbarian;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarbarianTest {
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

        Barbarian barbarian = new Barbarian();
        barbarian.modifyByClass(dndCharacter);
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForBarbarian() {

        assertEquals(13, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForBarbarian() {

        assertEquals(12, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForBarbarian() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForBarbarian() {

        assertEquals(12, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForBarbarian() {

        assertEquals(2, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForBarbarian() {
        Set<String> expectedResult = new HashSet<>(4);
        expectedResult.add("test armour");
        expectedResult.add("Shields");
        expectedResult.add("Light Armour");
        expectedResult.add("Medium Armour");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForBarbarian() {
        Set<String> expectedResult = new HashSet<>(3);
        expectedResult.add("test weapon");
        expectedResult.add("Simple Weapon");
        expectedResult.add("Martial Weapon");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForBarbarian() {
        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.STRENGTH);
        expectedResult.add(Characteristics.CONSTITUTION);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Features And Proficiencies")
    void setFeaturesAndTraitsForBarbarian() {
        String expectedResult = """
                test feature
                Rage
                On your turn, you can enter a rage as a bonus action. Number of rages: 2;
                While raging, you gain the following benefits if you aren't wearing heavy armour:
                - You have advantage on Strength checks and Strength saving throws.
                - When you make a melee weapon attack using Strength, you gain a +2 bonus to the damage roll. This bonus increases as you level.
                - You have resistance to bludgeoning, piercing, and slashing damage.
                If you are able to cast spells, you can't cast them or concentrate on them while raging.
                Your rage lasts for 1 minute. It ends early if you are knocked unconscious or if your turn ends and you haven't attacked a hostile creature since your last turn or taken damage since then. You can also end your rage on your turn as a bonus action.
                Once you have raged the maximum number, you must finish a long rest before you can rage again.
                Unarmoured Defense
                While you are not wearing any armor, your Armor Class equals %s. You can use a shield and still gain this benefit.
                """.formatted(dndCharacter.getArmourClass());

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
