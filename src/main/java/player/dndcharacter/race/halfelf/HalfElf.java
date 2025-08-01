package player.dndcharacter.race.halfelf;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.race.Abilities;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.RaceService;

import static player.dndcharacter.dndcharacterenums.Race.HALF_ELF;

public class HalfElf extends RaceService {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setRace(HALF_ELF);
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
