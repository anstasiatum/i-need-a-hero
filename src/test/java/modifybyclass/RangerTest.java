package modifybyclass;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndclass.Ranger;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangerTest {
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
    void setHitPointsForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        assertEquals(11, dndCharacter.getHitPoints());
    }

    @Test
    @DisplayName("Set Armour Class")
    void setArmourClassForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        assertEquals(11, dndCharacter.getArmourClass());
    }

    @Test
    @DisplayName("Set Languages")
    void setLanguagesForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(2);
        expectedResult.add("Elvish");
        expectedResult.add("Common");

        assertEquals(expectedResult, dndCharacter.getLanguages());
    }

    @Test
    @DisplayName("Set Hit Dice")
    void setHitDiceForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        assertEquals(10, dndCharacter.getHitDice());
    }

    @Test
    @DisplayName("Set Gold Modifier")
    void setStartGoldModifierForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        assertEquals(5, dndCharacter.getStartGoldModifier());
    }

    @Test
    @DisplayName("Set Armour Proficiency")
    void setArmourProficiencyForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(4);
        expectedResult.add("test armour");
        expectedResult.add("Light Armour");
        expectedResult.add("Medium Armour");
        expectedResult.add("Shields");

        assertEquals(expectedResult, dndCharacter.getArmourProficiency());
    }

    @Test
    @DisplayName("Set Weapon Proficiency")
    void setWeaponProficiencyForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        Set<String> expectedResult = new HashSet<>(3);
        expectedResult.add("test weapon");
        expectedResult.add("Simple Weapons");
        expectedResult.add("Martial Weapons");

        assertEquals(expectedResult, dndCharacter.getWeaponProficiency());
    }

    @Test
    @DisplayName("Set Saving Throws With Proficiency")
    void setSavingThrowsWithProficiencyForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        Set<Characteristics> expectedResult = new HashSet<>(2);
        expectedResult.add(Characteristics.STRENGTH);
        expectedResult.add(Characteristics.DEXTERITY);

        assertEquals(expectedResult, dndCharacter.getSavingThrowsWithProficiency());
    }

    @Test
    @DisplayName("Set Features And Proficiencies")
    void setFeaturesAndTraitsForRanger() {
        Ranger ranger = new Ranger();
        ranger.modifyByClass(dndCharacter);

        String expectedResult = """
                test feature
                Favoured Enemy
                Choose a type of favored enemy: aberrations, beasts, celestials, constructs, dragons, elementals, fey, fiends, giants, monstrosities, oozes, plants, or undead. Alternatively, you can select two races of humanoid (such as gnolls and orcs) as favored enemies.
                You have advantage on Wisdom (Survival) checks to track your favored enemies, as well as on Intelligence checks to recall information about them.
                When you gain this feature, you also learn one language of your choice that is spoken by your favored enemies, if they speak one at all.
                You can use a holy symbol as a spellcasting focus for your cleric spells.
                Natural Explorer
                You are particularly familiar with one type of natural environment and are adept at traveling and surviving in such regions. Choose one type of favored terrain: arctic, coast, desert, forest, grassland, mountain, swamp, or the Underdark. When you make an Intelligence or Wisdom check related to your favored terrain, your proficiency bonus is doubled if you are using a skill that you're proficient in.
                While traveling for an hour or more in your favored terrain, you gain the following benefits:
                Difficult terrain doesn't slow your group's travel.
                Your group can't become lost except by magical means.
                Even when you are engaged in another activity while traveling (such as foraging, navigating, or tracking), you remain alert to danger.
                If you are traveling alone, you can move stealthily at a normal pace.
                When you forage, you find twice as much food as you normally would.
                While tracking other creatures, you also learn their exact number, their sizes, and how long ago they passed through the area.
                """;

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }
}
