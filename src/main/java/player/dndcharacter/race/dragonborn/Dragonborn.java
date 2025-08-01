package player.dndcharacter.race.dragonborn;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.RaceService;

import static player.dndcharacter.dndcharacterenums.Race.DRAGONBORN;

public class Dragonborn extends RaceService {

    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setRace(DRAGONBORN);
        dndCharacter.setStrength(dndCharacter.getStrength() + 2);
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
        dndCharacter.getLanguages().add("Draconic");
        dndCharacter.setDraconicAncestryDamage(2);
    }
}
