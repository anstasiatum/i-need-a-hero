package player.dndcharacter.race.gnome;

import player.dndcharacter.DndCharacter;

public class ForestGnome extends GnomeRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Natural Illusionist. You know the minor illusion cantrip. Intelligence is your spellcasting ability for it. 
                Speak with Small Beasts. Through sounds and gestures, you can communicate simple ideas with Small or smaller beasts. Forest gnomes love animals and often keep squirrels, badgers, rabbits, moles, woodpeckers, and other creatures as beloved pets.
                """);
    }
}
