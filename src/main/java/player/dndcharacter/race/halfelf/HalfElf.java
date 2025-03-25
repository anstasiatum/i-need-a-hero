package player.dndcharacter.race.halfelf;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.abilities.Abilities;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.Race;

public class HalfElf extends Race {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 2);
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Fey Ancestry. You have advantage on saving throws against being charmed, and magic can’t put you to sleep
                """
                + Abilities.BASEDARKVISION);
        dndCharacter.getLanguages().add("Elvish");
    }
}
