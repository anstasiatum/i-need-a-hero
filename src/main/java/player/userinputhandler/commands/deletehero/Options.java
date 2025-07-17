package player.userinputhandler.commands.deletehero;

import java.util.List;

public class Options {
    public static List<String> getYesOrNoOptions() {

        return List.of(
                "Yes",
                "No"
        );
    }
}
