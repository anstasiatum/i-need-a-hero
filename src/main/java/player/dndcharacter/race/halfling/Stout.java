package player.dndcharacter.race.halfling;

import player.dndcharacter.DndCharacter;

public class Stout extends HalflingRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Stout Resilience. You have advantage on saving throws against poison, and you have resistance against poison damage."""
        );
    }
}

