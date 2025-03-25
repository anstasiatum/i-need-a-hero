package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

public class Druid extends DndClass {
    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.ARCANA,
                Skills.ANIMAL_HANDLING,
                Skills.INSIGHT,
                Skills.MEDICINE,
                Skills.NATURE,
                Skills.PERCEPTION,
                Skills.RELIGION,
                Skills.SURVIVAL
        );
    }

    public Druid() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        super.modifyByClass(dndCharacter);
        dndCharacter.setStartGoldModifier(2);

        dndCharacter.setHitDice(8);

        dndCharacter.getArmourProficiency().add("Light Armour");
        dndCharacter.getArmourProficiency().add("Medium Armour");
        dndCharacter.getArmourProficiency().add("Shields (druids will not wear armor or use shields made of metal)");

        dndCharacter.getWeaponProficiency().add("Clubs");
        dndCharacter.getWeaponProficiency().add("Daggers");
        dndCharacter.getWeaponProficiency().add("Darts");
        dndCharacter.getWeaponProficiency().add("Javelins");
        dndCharacter.getWeaponProficiency().add("Maces");
        dndCharacter.getWeaponProficiency().add("Quarterstuffs");
        dndCharacter.getWeaponProficiency().add("Scimitars");
        dndCharacter.getWeaponProficiency().add("Sickles");
        dndCharacter.getWeaponProficiency().add("Slings");
        dndCharacter.getWeaponProficiency().add("Spears");

        dndCharacter.getToolProficiency().add("Herbalism kit");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.INTELLIGENCE);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.WISDOM);

        dndCharacter.setSpellcastingAbility(SpellcastingAbility.WISDOM);
        dndCharacter.setSpellsKnown(dndCharacter.getWisdomModifier() + 1);

        dndCharacter.setSpellsKnownPerLevel(0, 2);
        dndCharacter.setSpellsKnownPerLevel(1, 2);

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Druidic
                You know Druidic, the secret language of druids. You can speak the language and use it to leave hidden messages. You and others who know this language automatically spot such a message. Others spot the message's presence with a successful DC 15 Wisdom (Perception) check but can't decipher it without magic.
                Ritual Casting
                You can cast a druid spell as a ritual if that spell has the ritual tag and you have the spell prepared.
                Spellcasting Focus
                You can use a druidic focus as a spellcasting focus for your druid spells.
                """);
        dndCharacter.getLanguages().add("Druidic");
    }
}
