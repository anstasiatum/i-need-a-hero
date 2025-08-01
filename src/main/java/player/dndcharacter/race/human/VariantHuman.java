package player.dndcharacter.race.human;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Race.VARIANT_HUMAN;

public class VariantHuman extends HumanRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setRace(VARIANT_HUMAN);
    }
}
