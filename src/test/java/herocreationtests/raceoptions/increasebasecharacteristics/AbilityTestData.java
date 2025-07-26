package herocreationtests.raceoptions.increasebasecharacteristics;

import lombok.Getter;
import lombok.Setter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.userinputhandler.enums.Steps;

import java.util.List;

@Getter
@Setter
public class AbilityTestData {
    private final String input;
    private final int originalValue;
    private final int expectedValue;
    private final Characteristics characteristic;
    private final Steps step;
    private final Steps expectedNextStep;
    private final String expectedMessage;
    private final List<String> expectedOptions;

    public AbilityTestData(String input, int originalValue, int expectedValue, Characteristics characteristic, Steps step, Steps expectedNextStep, String expectedMessage, List<String>  expectedOptions) {
        this.input = input;
        this.originalValue = originalValue;
        this.expectedValue = expectedValue;
        this.characteristic = characteristic;
        this.step = step;
        this.expectedNextStep = expectedNextStep;
        this.expectedMessage = expectedMessage;
        this.expectedOptions = expectedOptions;
    }

    @Override
    public String toString() {
        return characteristic.name();
    }
}
