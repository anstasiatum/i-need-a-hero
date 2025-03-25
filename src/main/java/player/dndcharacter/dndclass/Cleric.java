package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

public class Cleric extends DndClass {
    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.HISTORY,
                Skills.INSIGHT,
                Skills.MEDICINE,
                Skills.PERSUASION,
                Skills.RELIGION
        );
    }

    public Cleric() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        super.modifyByClass(dndCharacter);
        dndCharacter.setStartGoldModifier(5);

        dndCharacter.setHitDice(8);

        dndCharacter.getArmourProficiency().add("Light Armour");
        dndCharacter.getArmourProficiency().add("Medium Armour");
        dndCharacter.getArmourProficiency().add("Shields");

        dndCharacter.getWeaponProficiency().add("Simple Weapons");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.WISDOM);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.CHARISMA);

        dndCharacter.setSpellcastingAbility(SpellcastingAbility.WISDOM);
        dndCharacter.setSpellsKnown(dndCharacter.getWisdomModifier() + 1);

        dndCharacter.setSpellsKnownPerLevel(0, 3);
        dndCharacter.setSpellsKnownPerLevel(1, 2);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Ritual Casting
                You can cast a cleric spell as a ritual if that spell has the ritual tag and you have the spell prepared.
                """);

//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A mace, ");
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A warhammer, ");
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A scale Mail, ");
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A leather Armour, ");
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A chain Mail (if proficient), ");
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A light crossbow and 20 bolts, ")
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "An explorer's pack, ");
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "Any simple weapon, ");
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A priest's pack, ");
//        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A shield and a holy symbol, ");

// (a) a mace or (b) a warhammer (if proficient)
// (a) Scale Mail, (b) Leather Armor, or (c) Chain Mail (if proficient)
// (a) a light crossbow and 20 bolts or (b) any simple weapon
// (a) a priest's pack or (b) an explorer's pack
// A shield and a holy symbol
    }
}
