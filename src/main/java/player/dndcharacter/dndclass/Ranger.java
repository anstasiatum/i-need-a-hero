package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.EnumSet;
import java.util.Set;

public class Ranger extends DndClass {
    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.ANIMAL_HANDLING,
                Skills.ATHLETICS,
                Skills.INSIGHT,
                Skills.INVESTIGATION,
                Skills.NATURE,
                Skills.PERCEPTION,
                Skills.STEALTH,
                Skills.SURVIVAL
        );
    }

    public Ranger() {
        super(buildAvailableProficiencySkills(), 3);
    }
    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        super.modifyByClass(dndCharacter);
        dndCharacter.setStartGoldModifier(5);
        dndCharacter.setHitDice(10);

        dndCharacter.getArmourProficiency().add("Light Armour");
        dndCharacter.getArmourProficiency().add("Medium Armour");
        dndCharacter.getArmourProficiency().add("Shields");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");
        dndCharacter.getWeaponProficiency().add("Martial Weapons");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.STRENGTH);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.DEXTERITY);

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
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
                """);
    }
}
