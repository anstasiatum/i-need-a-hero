package player.dndcharacter.race.gnome;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.abilities.Abilities;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.Race;

public class GnomeRace extends Race {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 2);
        dndCharacter.setSize(Size.SMALL);
        dndCharacter.setSpeed(25);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + Abilities.BASEDARKVISION +
                """
                You have advantage on all Intelligence, Wisdom, and Charisma saving throws against magic.
                """);
        dndCharacter.getLanguages().add("Gnomish");
    }
}
