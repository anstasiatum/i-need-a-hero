package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.EnumSet;
import java.util.Set;

public class Barbarian extends DndClass {

    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.ANIMAL_HANDLING,
                Skills.ATHLETICS,
                Skills.INTIMIDATION,
                Skills.NATURE,
                Skills.PERCEPTION,
                Skills.SURVIVAL
        );
    }

    public Barbarian() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        super.modifyByClass(dndCharacter);
        dndCharacter.setHitDice(12);
        dndCharacter.setArmourClass(10 + dndCharacter.getDexterityModifier() + dndCharacter.getConstitutionModifier());
        dndCharacter.setStartGoldModifier(2);

        dndCharacter.getArmourProficiency().add("Light Armour");
        dndCharacter.getArmourProficiency().add("Medium Armour");
        dndCharacter.getArmourProficiency().add("Shields");

        dndCharacter.getWeaponProficiency().add("Simple Weapon");
        dndCharacter.getWeaponProficiency().add("Martial Weapon");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.STRENGTH);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CONSTITUTION);


        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
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
                """.formatted(dndCharacter.getArmourClass()));
    }
}
