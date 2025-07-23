package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.EnumSet;
import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.FIGHTER;

public class Fighter extends DndClass {
    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.ACROBATICS,
                Skills.ANIMAL_HANDLING,
                Skills.ATHLETICS,
                Skills.HISTORY,
                Skills.INSIGHT,
                Skills.INTIMIDATION,
                Skills.PERCEPTION,
                Skills.SURVIVAL
        );
    }

    public Fighter() {

        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        dndCharacter.setCharacterClass(FIGHTER);
        dndCharacter.setStartGoldModifier(5);

        dndCharacter.setHitDice(10);

        dndCharacter.getArmourProficiency().add("Light Armour");
        dndCharacter.getArmourProficiency().add("Medium Armour");
        dndCharacter.getArmourProficiency().add("Heavy Armour");
        dndCharacter.getArmourProficiency().add("Shields");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");
        dndCharacter.getWeaponProficiency().add("Martial Weapons");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.STRENGTH);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CONSTITUTION);

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Great Weapon Fighting
                When you roll a 1 or 2 on a damage die for an attack you make with a melee weapon that you are wielding with two hands, you can reroll the die and must use the new roll, even if the new roll is a 1 or a 2. The weapon must have the two-handed or versatile property for you to gain this benefit.
                Protection
                When a creature you can see attacks a target other than you that is within 5 feet of you, you can use your reaction to impose disadvantage on the attack roll. You must be wielding a shield..
                Two-Weapon Fighting
                When you engage in two-weapon fighting, you can add your ability modifier to the damage of the second attack.
                Second Wind
                You have a limited well of stamina that you can draw on to protect yourself from harm. On your turn, you can use a bonus action to regain hit points equal to 1d10 + your fighter level.                
                Once you use this feature, you must finish a short or long rest before you can use it again.
                """);
        super.modifyByClass(dndCharacter);
    }
}
