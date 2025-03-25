package player.dndcharacter.levelup.racelevelup;

import player.dndcharacter.DndCharacter;

public class DarkElfLevelUp {
    public static void darkElfLevelUp(DndCharacter dndCharacter, int level) {
        if (level >= 3) {
            dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                    You can cast the faerie fire spell once per day.
                    """);
        }
        if (level >= 5) {
            dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                    You can cast the darkness spell once per day.
                    """);
        }
    }
}
