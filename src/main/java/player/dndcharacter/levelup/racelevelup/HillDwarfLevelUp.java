package player.dndcharacter.levelup.racelevelup;

import player.dndcharacter.DndCharacter;

public class HillDwarfLevelUp {
    public static void hillDwarfLevelUp(DndCharacter dndCharacter, int level) {
        dndCharacter.setHitPoints(dndCharacter.getHitPoints() + level - 1);
    }
}
