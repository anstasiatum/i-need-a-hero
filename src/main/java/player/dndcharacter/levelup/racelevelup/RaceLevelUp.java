package player.dndcharacter.levelup.racelevelup;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Race;

public class RaceLevelUp {
    public DndCharacter raceLevelUp(DndCharacter dndCharacter, Race race, int level) {
        switch (race) {
            case HILL_DWARF:
                HillDwarfLevelUp.hillDwarfLevelUp(dndCharacter, level);
                break;
            case TIEFLING:
                TieflingLevelUp.tieflingLevelUp(dndCharacter, level);
                break;
            case DRAGONBORN:
                DragonbornLevelUp.dragonbornLevelUp(dndCharacter, level);
                break;
            case DARK_ELF:
                DarkElfLevelUp.darkElfLevelUp(dndCharacter, level);
                break;
        }
        return dndCharacter;
    }
}
