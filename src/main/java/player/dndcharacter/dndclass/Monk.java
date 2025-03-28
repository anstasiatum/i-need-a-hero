package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.EnumSet;
import java.util.Set;

public class Monk extends DndClass {
    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.ACROBATICS,
                Skills.ATHLETICS,
                Skills.HISTORY,
                Skills.INSIGHT,
                Skills.RELIGION,
                Skills.STEALTH
        );
    }

    public Monk() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        super.modifyByClass(dndCharacter);

        dndCharacter.setStartGoldModifier(5);

        dndCharacter.setHitDice(8);
        dndCharacter.setArmourClass(10 + dndCharacter.getDexterityModifier() + dndCharacter.getWisdomModifier());

        dndCharacter.getWeaponProficiency().add("Simple Weapons");
        dndCharacter.getWeaponProficiency().add("Shortswords");

        dndCharacter.getToolProficiency().add("Any one type of artisan's tools or any one musical instrument");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.STRENGTH);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.DEXTERITY);

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Martial Arts
                At 1st level, your practice of martial arts gives you mastery of combat styles that use unarmed strikes and monk weapons, which are shortswords and any simple melee weapons that don't have the two-handed or heavy property.
                You gain the following benefits while you are unarmed or wielding only monk weapons and you aren't wearing armor or wielding a shield.
                • You can use Dexterity instead of Strength for the attack and damage rolls of your unarmed strikes and monk weapons.
                • You can roll a d4 in place of the normal damage of your unarmed strike or monk weapon. This die changes as you gain monk levels, as shown in the Martial Arts column of the Monk table.
                • When you use the Attack action with an unarmed strike or a monk weapon on your turn, you can make one unarmed strike as a bonus action. For example, if you take the Attack action and attack with a quarterstaff, you can also make an unarmed strike as a bonus action, assuming you haven't already taken a bonus action this turn.
                Certain monasteries use specialized forms of the monk weapons. For example, you might use a club that is two lengths of wood connected by a short chain (called a nunchaku) or a sickle with a shorter, straighter blade (called a kama). Whatever name you use for a monk weapon, you can use the game statistics provided for the weapon.
                Unarmoured Defence
                Beginning at 1st level, while you are wearing no armour and not wielding a shield, your AC equals %s
                """.formatted(dndCharacter.getArmourClass()));
    }
}
