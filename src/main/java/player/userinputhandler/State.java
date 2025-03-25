package player.userinputhandler;

import lombok.Data;
import player.dndcharacter.DndCharacter;
import player.userinputhandler.enums.Steps;
import player.userinputhandler.enums.Processes;

@Data
public class State {
    final Processes process;
    final Steps stepId;
    final DndCharacter dndCharacter;
}
