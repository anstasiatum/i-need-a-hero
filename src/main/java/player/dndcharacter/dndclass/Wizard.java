package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skills;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import java.util.EnumSet;
import java.util.Set;

public class Wizard extends DndClass {

    public static Set<Skills> buildAvailableProficiencySkills() {
        return EnumSet.of(
                Skills.ARCANA,
                Skills.HISTORY,
                Skills.INSIGHT,
                Skills.INVESTIGATION,
                Skills.MEDICINE,
                Skills.RELIGION
        );
    }

    public Wizard() {
        super(buildAvailableProficiencySkills(), 2);
    }

    @Override
    public void modifyByClass(DndCharacter dndCharacter) {
        super.modifyByClass(dndCharacter);
        dndCharacter.setStartGoldModifier(4);

        dndCharacter.setHitDice(6);

        dndCharacter.getWeaponProficiency().add("Daggers");
        dndCharacter.getWeaponProficiency().add("Darts");
        dndCharacter.getWeaponProficiency().add("Slings");
        dndCharacter.getWeaponProficiency().add("Quarterstaffs");
        dndCharacter.getWeaponProficiency().add("Light Crossbows");

        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.WISDOM);
        dndCharacter.getSavingThrowsWithProficiency().add(Characteristics.INTELLIGENCE);

        dndCharacter.setSpellcastingAbility(SpellcastingAbility.INTELLIGENCE);
        dndCharacter.setSpellsKnown(dndCharacter.getIntelligenceModifier() + 1);

        dndCharacter.setSpellsKnownPerLevel(0, 3);
        dndCharacter.setSpellsKnownPerLevel(1, 2);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Ritual Casting
                You can cast a wizard spell as a ritual if that spell has the ritual tag and you have the spell in your spellbook. You don't need to have the spell prepared.
                Arcane Recovery
                You have learned to regain some of your magical energy by studying your spellbook. Once per day when you finish a short rest, you can choose expended spell slots to recover. The spell slots can have a combined level that is equal to or less than half your wizard level (rounded up), and none of the slots can be 6th level or higher.
                Copying a Spell into the Book: When you find a wizard spell of 1st level or higher, you can add it to your spellbook if it is of a spell level you can prepare and if you can spare the time to decipher and copy it.
                Copying that spell into your spellbook involves reproducing the basic form of the spell, then deciphering the unique system of notation used by the wizard who wrote it. You must practice the spell until you understand the sounds or gestures required, then transcribe it into your spellbook using your own notation.
                For each level of the spell, the process takes 2 hours and costs 50 gp. The cost represents material components you expend as you experiment with the spell to master it, as well as the fine inks you need to record it. Once you have spent this time and money, you can prepare the spell just like your other spells.
                Replacing the Book: You can copy a spell from your own spellbook into another book—for example, if you want to make a backup copy of your spellbook. This is just like copying a new spell into your spellbook, but faster and easier, since you understand your own notation and already know how to cast the spell. You need spend only 1 hour and 10 gp for each level of the copied spell.
                If you lose your spellbook, you can use the same procedure to transcribe the spells that you have prepared into a new spellbook. Filling out the remainder of your spellbook requires you to find new spells to do so, as normal. For this reason, many wizards keep backup spellbooks in a safe place. 
                """);
    }
}
