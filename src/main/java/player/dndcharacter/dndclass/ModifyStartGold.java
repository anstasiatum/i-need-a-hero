package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static player.dndcharacter.dndcharacterenums.CharacterClass.MONK;

public class ModifyStartGold {
    public void generateStartGold(DndCharacter dndCharacter) {
        Random RANDOM = new Random();
        List<Integer> startGoldValues = new ArrayList<>(dndCharacter.getStartGoldModifier());

        for (int i = 0; i < dndCharacter.getStartGoldModifier(); i++) {
            startGoldValues.add(RANDOM.nextInt(1, 5));
            dndCharacter.setGold(dndCharacter.getGold() + startGoldValues.get(i));
        }

        if (dndCharacter.getCharacterClass() != MONK) {
            dndCharacter.setGold(dndCharacter.getGold() * 10);
        }
    }
}
