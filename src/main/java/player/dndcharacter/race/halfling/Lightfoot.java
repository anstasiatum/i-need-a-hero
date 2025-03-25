package player.dndcharacter.race.halfling;

import player.dndcharacter.DndCharacter;

public class Lightfoot extends HalflingRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Naturally Stealthy. You can attempt to hide even when you are obscured only by a creature that is at least one size larger than you."""
        );
    }
}
