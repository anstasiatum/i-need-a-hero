package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

public class Warlock extends DndClass {
    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.ARCANA,
                Skills.DECEPTION,
                Skills.HISTORY,
                Skills.INVESTIGATION,
                Skills.INTIMIDATION,
                Skills.NATURE,
                Skills.RELIGION
        );
    }

    public Warlock() {
        super(buildAvailableProficiencySkills(), 2);
    }


    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        super.modifyByClass(dndCharacter);
        dndCharacter.setStartGoldModifier(4);

        dndCharacter.setHitDice(8);

        dndCharacter.getArmourProficiency().add("Light Armour");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.WISDOM);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CHARISMA);

        dndCharacter.setSpellcastingAbility(SpellcastingAbility.CHARISMA);
        dndCharacter.setSpellsKnownPerLevel(0, 2);
        dndCharacter.setSpellsKnownPerLevel(1, 2);

        //• (a) a light crossbow and 20 bolts or (b) any simple weapon
        //• (a) a component pouch or (b) an arcane focus
        //• (a) a scholar's pack or (b) a dungeoneer's pack
        //• Leather Armor, any simple weapon, and two daggers
    }
}
