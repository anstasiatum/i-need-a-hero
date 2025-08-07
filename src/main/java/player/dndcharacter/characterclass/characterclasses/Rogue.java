package player.dndcharacter.characterclass.characterclasses;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.characterclass.CharacterClassService;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.EnumSet;
import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.ROGUE;

public class Rogue extends CharacterClassService {
    public static Set<Skill> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skill.ACROBATICS,
                Skill.ATHLETICS,
                Skill.DECEPTION,
                Skill.INSIGHT,
                Skill.INTIMIDATION,
                Skill.INVESTIGATION,
                Skill.PERCEPTION,
                Skill.PERFORMANCE,
                Skill.PERSUASION,
                Skill.SLEIGHT_OF_HAND,
                Skill.STEALTH
        );
    }

    public Rogue() {
        super(buildAvailableProficiencySkills(), 4);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        dndCharacter.setCharacterClass(ROGUE);
        dndCharacter.setStartGoldModifier(4);

        dndCharacter.setHitDice(8);

        dndCharacter.getArmourProficiency().add("Light Armour");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");
        dndCharacter.getWeaponProficiency().add("Hand Crossbows");
        dndCharacter.getWeaponProficiency().add("Rapiers");
        dndCharacter.getWeaponProficiency().add("Shortswords");

        dndCharacter.getToolProficiency().add("Thieves' tools");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.DEXTERITY);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.INTELLIGENCE);

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Sneak Attack
                You know how to strike subtly and exploit a foe's distraction. Once per turn, you can deal an extra 1d6 damage to one creature you hit with an attack if you have advantage on the attack roll. The attack must use a finesse or a ranged weapon.
                You don't need advantage on the attack roll if another enemy of the target is within 5 feet of it, that enemy isn't incapacitated, and you don't have disadvantage on the attack roll.
                Thieves' Cant
                During your rogue training you learned thieves' cant, a secret mix of dialect, jargon, and code that allows you to hide messages in seemingly normal conversation. Only another creature that knows thieves' cant understands such messages. It takes four times longer to convey such a message than it does to speak the same idea plainly.
                In addition, you understand a set of secret signs and symbols used to convey short, simple messages, such as whether an area is dangerous or the territory of a thieves' guild, whether loot is nearby, or whether the people in an area are easy marks or will provide a safe house for thieves on the run.
                """);
        super.modifyByClass(dndCharacter);
    }
}
