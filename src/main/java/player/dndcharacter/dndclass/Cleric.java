package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skill;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

import static player.dndcharacter.dndcharacterenums.CharacterClass.CLERIC;

public class Cleric extends CharacterClassService {
    public static Set<Skill> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skill.HISTORY,
                Skill.INSIGHT,
                Skill.MEDICINE,
                Skill.PERSUASION,
                Skill.RELIGION
        );
    }

    public Cleric() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        dndCharacter.setCharacterClass(CLERIC);
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
        super.modifyByClass(dndCharacter);
    }
}
