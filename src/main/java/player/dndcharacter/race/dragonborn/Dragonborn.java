package player.dndcharacter.race.dragonborn;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.Race;

public class Dragonborn extends Race {

    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setStrength(dndCharacter.getStrength() + 2);
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
        dndCharacter.getLanguages().add("Draconic");
        dndCharacter.setDraconicAncestryDamage(2);
    }
}
