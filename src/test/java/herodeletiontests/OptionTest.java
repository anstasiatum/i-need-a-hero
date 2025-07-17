package herodeletiontests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.commands.deletehero.Options.getYesOrNoOptions;

public class OptionTest {
    @Test
    @DisplayName("Get Yes or No Option Test")
    void getYesOrNoOptionTest() {
        List<String> expectedResult = List.of(
                "Yes",
                "No"
        );

        assertEquals(expectedResult, getYesOrNoOptions());
    }
}
