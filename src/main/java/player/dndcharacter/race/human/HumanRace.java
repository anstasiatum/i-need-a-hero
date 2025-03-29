package player.dndcharacter.race.human;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.Race;

public class HumanRace extends Race {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
    }
}
