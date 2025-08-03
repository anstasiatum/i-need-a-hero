package player.dndcharacter.characterclass.characterclasses;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.characterclass.CharacterClassService;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.EnumSet;
import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.PALADIN;

public class Paladin extends CharacterClassService {
    public static Set<Skill> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skill.ATHLETICS,
                Skill.INSIGHT,
                Skill.INTIMIDATION,
                Skill.MEDICINE,
                Skill.PERSUASION,
                Skill.RELIGION
        );
    }

    public Paladin() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        dndCharacter.setCharacterClass(PALADIN);
        super.modifyByClass(dndCharacter);
        dndCharacter.setStartGoldModifier(5);

        dndCharacter.setHitDice(10);

        dndCharacter.getArmourProficiency().add("Light Armour");
        dndCharacter.getArmourProficiency().add("Medium Armour");
        dndCharacter.getArmourProficiency().add("Heavy Armour");
        dndCharacter.getArmourProficiency().add("Shields");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");
        dndCharacter.getWeaponProficiency().add("Martial Weapons");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.WISDOM);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CHARISMA);

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Divine Sense
                The presence of strong evil registers on your senses like a noxious odor, and powerful good rings like heavenly music in your ears. As an action, you can open your awareness to detect such forces. Until the end of your next turn, you know the location of any celestial, fiend, or undead within 60 feet of you that is not behind total cover. You know the type (celestial, fiend, or undead) of any being whose presence you sense, but not its identity (the vampire Count Strahd von Zarovich, for instance). Within the same radius, you also detect the presence of any place or object that has been consecrated or desecrated, as with the hallow spell.
                You can use this feature a number of times equal to %d. When you finish a long rest, you regain all expended uses.
                Lay on hands
                Your blessed touch can heal wounds. You have a pool of healing power that replenishes when you take a long rest. With that pool, you can restore a total number of hit points equal to your paladin level x 5.
                As an action, you can touch a creature and draw power from the pool to restore a number of hit points to that creature, up to the maximum amount remaining in your pool.
                Alternatively, you can expend 5 hit points from your pool of healing to cure the target of one disease or neutralize one poison affecting it. You can cure multiple diseases and neutralize multiple poisons with a single use of Lay on Hands, expending hit points separately for each one. 
                This feature has no effect on undead and constructs.
                """.formatted((1 + dndCharacter.getCharismaModifier())));
        super.modifyByClass(dndCharacter);
    }
}
