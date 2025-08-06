package player.dndcharacter.background.backgrounds;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundService;

import static player.dndcharacter.dndcharacterenums.Background.CUSTOM;

public class Custom extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(CUSTOM);
    }
}
