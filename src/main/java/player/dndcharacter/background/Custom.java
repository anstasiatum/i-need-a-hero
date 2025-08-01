package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Background.CUSTOM;

public class Custom extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(CUSTOM);
    }
}
