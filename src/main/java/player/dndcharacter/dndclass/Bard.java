package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skill;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.BARD;

public class Bard extends CharacterClassService {
    public static Set<Skill> buildAvailableProficiencySkills() {
        return EnumSet.allOf(Skill.class);
    }

    public Bard() {
        super(buildAvailableProficiencySkills(), 3);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        dndCharacter.setCharacterClass(BARD);
        dndCharacter.setHitDice(8);

        dndCharacter.getArmourProficiency().add("Light Armour");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");
        dndCharacter.getWeaponProficiency().add("Hand crossbows");
        dndCharacter.getWeaponProficiency().add("Longswords");
        dndCharacter.getWeaponProficiency().add("Rapiers");
        dndCharacter.getWeaponProficiency().add("Shortswords");

        dndCharacter.setStartGoldModifier(5);

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.DEXTERITY);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CHARISMA);

        dndCharacter.setSpellcastingAbility(SpellcastingAbility.CHARISMA);
        dndCharacter.setSpellsKnown(4);

        dndCharacter.setSpellsKnownPerLevel(0, 2);
        dndCharacter.setSpellsKnownPerLevel(1, 2);

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Ritual Casting
                You can cast any bard spell you know as a ritual if that spell has the ritual tag.
                Bardic Inspiration
                You can inspire others through stirring words or music. To do so, you use a bonus action on your turn to choose one creature other than yourself within 60 feet of you who can hear you. That creature gains one Bardic Inspiration die, a d6.
                Once within the next 10 minutes, the creature can roll the die and add the number rolled to one ability check, attack roll, or saving throw it makes. The creature can wait until after it rolls the d20 before deciding to use the Bardic Inspiration die, but must decide before the DM says whether the roll succeeds or fails. Once the Bardic Inspiration die is rolled, it is lost. A creature can have only one Bardic Inspiration die at a time.
                You can use this feature a number of times equal to your Charisma modifier (a minimum of once). You regain any expended uses when you finish a long rest.
                Spellcasting Focus
                You can use a musical instrument as a spellcasting focus for your bard spells.
                """);
        super.modifyByClass(dndCharacter);
    }
}


