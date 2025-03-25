package player.dndcharacter.race.dwarf;

import player.dndcharacter.DndCharacter;

public class HillDwarf extends DwarfRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
        dndCharacter.setHitPoints(dndCharacter.getHitPoints() + 1);
    }
}
