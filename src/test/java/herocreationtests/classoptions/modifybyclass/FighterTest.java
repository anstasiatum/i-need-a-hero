package herocreationtests.classoptions.modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.characterclass.characterclasses.Fighter;
import player.dndcharacter.dndcharacterenums.Characteristics;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.CharacterClass.FIGHTER;

public class FighterTest {
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

        Fighter fighter = new Fighter();
        fighter.modifyByClass(dndCharacter);
    }

    @Test
    @DisplayName("Set Class Name")
    void setClassNameForFighter() {

        assertEquals(FIGHTER, dndCharacter.getCharacterClass());
    }

    @Test
    @DisplayName("Set Hit Points")
    void setHitPointsForFighter() {

        assertEquals(11, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForFighter() {

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForFighter() {
        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForFighter() {

        assertEquals(10, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForFighter() {

        assertEquals(5, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForFighter() {
        Set<String> expectedResult = new HashSet<>(5);
        expectedResult.add("test armour");
        expectedResult.add("Light Armour");
        expectedResult.add("Medium Armour");
        expectedResult.add("Heavy Armour");
        expectedResult.add("Shields");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForFighter() {
        Set<String> expectedResult = new HashSet<>(3);
        expectedResult.add("test weapon");
        expectedResult.add("Simple Weapons");
        expectedResult.add("Martial Weapons");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForFighter() {
        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.STRENGTH);
        expectedResult.add(Characteristics.CONSTITUTION);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Features And Proficiencies")
    void setFeaturesAndTraitsForFighter() {
        String expectedResult = """
                test feature
                Great Weapon Fighting
                When you roll a 1 or 2 on a damage die for an attack you make with a melee weapon that you are wielding with two hands, you can reroll the die and must use the new roll, even if the new roll is a 1 or a 2. The weapon must have the two-handed or versatile property for you to gain this benefit.
                Protection
                When a creature you can see attacks a target other than you that is within 5 feet of you, you can use your reaction to impose disadvantage on the attack roll. You must be wielding a shield..
                Two-Weapon Fighting
                When you engage in two-weapon fighting, you can add your ability modifier to the damage of the second attack.
                Second Wind
                You have a limited well of stamina that you can draw on to protect yourself from harm. On your turn, you can use a bonus action to regain hit points equal to 1d10 + your fighter level.                
                Once you use this feature, you must finish a short or long rest before you can use it again.
                """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
