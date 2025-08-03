package player.dndcharacter.characterclass.characterclasses;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.characterclass.CharacterClassService;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skill;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.SORCERER;

public class Sorcerer extends CharacterClassService {
    public static Set<Skill> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skill.ARCANA,
                Skill.DECEPTION,
                Skill.INSIGHT,
                Skill.INTIMIDATION,
                Skill.PERSUASION,
                Skill.RELIGION
        );
    }

    public Sorcerer() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        dndCharacter.setCharacterClass(SORCERER);
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
        super.modifyByClass(dndCharacter);
    }
}
