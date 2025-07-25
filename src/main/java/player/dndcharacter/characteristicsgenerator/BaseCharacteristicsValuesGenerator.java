package player.dndcharacter.characteristicsgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class BaseCharacteristicsValuesGenerator {
    private static final Random RANDOM = new Random();

    public List<CharacteristicsValues> generateCharacteristics() {

        List<CharacteristicsValues> characteristics = new ArrayList<>(6);

        for (int characteristicsCount = 0; characteristicsCount < 6; characteristicsCount++) {
            CharacteristicsValues characteristicsValues = new CharacteristicsValues();
            int diceSum = 0;
            for (int diceCount = 0; diceCount < 4; diceCount++) {
                int diceValue = RANDOM.nextInt(1, 7);
                characteristicsValues.diceValues.add(diceValue);
                diceSum = diceSum + diceValue;
            }
            characteristicsValues.baseCharacteristicsValue = (diceSum - Collections.min(characteristicsValues.diceValues));
            characteristics.add(characteristicsValues);

        }
        return characteristics;
    }
}

