package player.dndcharacter.race.human;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.RaceService;

public class HumanRace extends RaceService {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
    }
}
