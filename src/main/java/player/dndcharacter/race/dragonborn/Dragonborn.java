package player.dndcharacter.race.dragonborn;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.Race;

public class Dragonborn extends Race {

    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setStrength(dndCharacter.getStrength() + 2);
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
        dndCharacter.getLanguages().add("Draconic");
        dndCharacter.setDraconicAncestryDamage(2);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                        Draconic Ancestry
                        You have draconic ancestry. Choose one type of dragon from the Draconic Ancestry table. Your breath weapon and damage resistance are determined by the dragon type, as shown in the table.
                        Breath Weapon
                        You can use your action to exhale destructive energy. Your draconic ancestry determines the size, shape, and damage type of the exhalation. When you use your breath weapon, each creature in the area of the exhalation must make a saving throw, the type of which is determined by your draconic ancestry. The DC for this saving throw equals 8 + your Constitution modifier + your proficiency bonus. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. The damage increases to 3d6 at 6th level, 4d6 at 11th level, and 5d6 at 16th level. After you use your breath weapon, you can’t use it again until you complete a short or long rest.
                        Damage Resistance
                        You have resistance to the damage type associated with your draconic ancestry.
                """);


    }
}
