package player.dndcharacter.levelup.racelevelup;

import player.dndcharacter.DndCharacter;

public class TieflingLevelUp {
    public static void tieflingLevelUp(DndCharacter dndCharacter, int level) {
        if (level >= 3) {
            dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                    You can cast the hellish rebuke spell as a 2nd-level spell once with this trait and regain the ability to do so when you finish a long rest.
                    """);
        }
        if (level >= 5) {
            dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                    You can cast the darkness spell once with this trait and regain the ability to do so when you finish a long rest.
                    """);
        }
    }
}
