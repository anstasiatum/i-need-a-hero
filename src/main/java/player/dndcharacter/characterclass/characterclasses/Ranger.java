package player.dndcharacter.characterclass.characterclasses;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.characterclass.CharacterClassService;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.EnumSet;
import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.RANGER;

public class Ranger extends CharacterClassService {
    public static Set<Skill> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skill.ANIMAL_HANDLING,
                Skill.ATHLETICS,
                Skill.INSIGHT,
                Skill.INVESTIGATION,
                Skill.NATURE,
                Skill.PERCEPTION,
                Skill.STEALTH,
                Skill.SURVIVAL
        );
    }

    public Ranger() {
        super(buildAvailableProficiencySkills(), 3);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        dndCharacter.setCharacterClass(RANGER);
        dndCharacter.setStartGoldModifier(5);
        dndCharacter.setHitDice(10);

        dndCharacter.getArmourProficiency().add("Light Armour");
        dndCharacter.getArmourProficiency().add("Medium Armour");
        dndCharacter.getArmourProficiency().add("Shields");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");
        dndCharacter.getWeaponProficiency().add("Martial Weapons");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.STRENGTH);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.DEXTERITY);

        super.modifyByClass(dndCharacter);
    }
}
