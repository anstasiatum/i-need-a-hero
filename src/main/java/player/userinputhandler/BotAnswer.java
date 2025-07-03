package player.userinputhandler;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class BotAnswer {
    private final String answer;
    private final File file;
    private final boolean hasOptions;
    private final List<String> optionTexts;
}
