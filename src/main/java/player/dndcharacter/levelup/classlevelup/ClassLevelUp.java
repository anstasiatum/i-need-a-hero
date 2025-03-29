package player.dndcharacter.levelup.classlevelup;

import player.dndcharacter.DndCharacter;

public class ClassLevelUp {
    public static void modifyByClass(DndCharacter dndCharacter, int level) {
        if (level <= 5) {
            dndCharacter.setProficiencyBonus(2);
        } else if (level <= 9) {
            dndCharacter.setProficiencyBonus(3);
        } else if (level <= 13) {
            dndCharacter.setProficiencyBonus(4);
        } else if (level <= 17) {
            dndCharacter.setProficiencyBonus(5);
        } else {
            dndCharacter.setProficiencyBonus(6);
        }
    }
}