package player.dndcharacter.race.dwarf;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Race.HILL_DWARF;

public class HillDwarf extends DwarfRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setRace(HILL_DWARF);
        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
        dndCharacter.setHitPoints(dndCharacter.getHitPoints() + 1);
    }
}
