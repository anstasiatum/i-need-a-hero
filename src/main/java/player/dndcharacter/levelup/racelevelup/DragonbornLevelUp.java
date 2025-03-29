package player.dndcharacter.levelup.racelevelup;

import player.dndcharacter.DndCharacter;

public class DragonbornLevelUp {
    public static void dragonbornLevelUp(DndCharacter dndCharacter, int level) {
        if (level >= 6 && level < 11) {
            dndCharacter.setDraconicAncestryDamage(3);
        } else if (level >= 11 && level < 16) {
            dndCharacter.setDraconicAncestryDamage(4);
        } else if (level >= 16) {
            dndCharacter.setDraconicAncestryDamage(5);
        }
    }
}
