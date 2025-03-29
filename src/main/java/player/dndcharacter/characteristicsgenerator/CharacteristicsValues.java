package player.dndcharacter.characteristicsgenerator;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicsValues {
    public Integer baseCharacteristicsValue = 0;
    public List<Integer> diceValues = new ArrayList<>(4);

    @Override
    public String toString() {
        return "Characteristics Value = " + baseCharacteristicsValue +
                ", Dice Values = " + diceValues + " \n";
    }
}
