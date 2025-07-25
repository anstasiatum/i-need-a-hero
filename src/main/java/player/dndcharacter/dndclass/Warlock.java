package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.WARLOCK;

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
        dndCharacter.setCharacterClass(WARLOCK);
        super.modifyByClass(dndCharacter);
        dndCharacter.setStartGoldModifier(4);

        dndCharacter.setHitDice(8);

        dndCharacter.getArmourProficiency().add("Light Armour");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.WISDOM);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CHARISMA);

        dndCharacter.setSpellcastingAbility(SpellcastingAbility.CHARISMA);
        dndCharacter.setSpellsKnown(2);
        dndCharacter.setSpellsKnownPerLevel(0, 2);
        dndCharacter.setSpellsKnownPerLevel(1, 2);
        super.modifyByClass(dndCharacter);
    }
}
