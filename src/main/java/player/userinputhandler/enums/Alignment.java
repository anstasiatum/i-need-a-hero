package player.userinputhandler.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public enum Alignment {
    LAWFUL_GOOD("Lawful good"),
    NEUTRAL_GOOD("Neutral good"),
    CHAOTIC_GOOD("Chaotic good"),
    LAWFUL_NEUTRAL("Lawful neutral"),
    TRUE_NEUTRAL("True neutral"),
    CHAOTIC_NEUTRAL("Chaotic neutral"),
    LAWFUL_EVIL("Lawful evil"),
    NEUTRAL_EVIL("Neutral evil"),
    CHAOTIC_EVIL("Chaotic evil");

    private final String displayName;

    Alignment(String displayName)  {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static String getAllAlignments() {
        return Arrays.stream(Alignment.values())
                .map(Alignment::getDisplayName)
                .collect(Collectors.joining("\n"));
    }
}
