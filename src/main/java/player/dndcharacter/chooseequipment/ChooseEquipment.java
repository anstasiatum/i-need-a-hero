package player.dndcharacter.chooseequipment;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.CharacterClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChooseEquipment {
    public void generateStartGold(DndCharacter dndCharacter) {

        Random RANDOM = new Random();
        List<Integer> startGoldValues = new ArrayList<>(dndCharacter.getStartGoldModifier());

        for (int i = 0; i < dndCharacter.getStartGoldModifier(); i++) {
            startGoldValues.add(RANDOM.nextInt(1, 5));
            dndCharacter.setStartGold(dndCharacter.getStartGold() + startGoldValues.get(i));
        }

        if (dndCharacter.getCharacterClass() != CharacterClass.MONK) {
            dndCharacter.setStartGold(dndCharacter.getStartGold() * 10);
        }
    }
}
