package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

public class Sorcerer extends DndClass {
    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.ARCANA,
                Skills.DECEPTION,
                Skills.INSIGHT,
                Skills.INTIMIDATION,
                Skills.PERSUASION,
                Skills.RELIGION
        );
    }

    public Sorcerer() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        super.modifyByClass(dndCharacter);
        dndCharacter.setStartGoldModifier(3);

        dndCharacter.setHitDice(6);

        dndCharacter.getWeaponProficiency().add("Daggers");
        dndCharacter.getWeaponProficiency().add("Darts");
        dndCharacter.getWeaponProficiency().add("Slings");
        dndCharacter.getWeaponProficiency().add("Quarterstaffs");
        dndCharacter.getWeaponProficiency().add("Light Crossbows");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CONSTITUTION);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CHARISMA);

        dndCharacter.setSpellcastingAbility(SpellcastingAbility.CHARISMA);
        dndCharacter.setSpellsKnown(2);

        dndCharacter.setSpellsKnownPerLevel(0, 4);
        dndCharacter.setSpellsKnownPerLevel(1, 2);
    }
}
