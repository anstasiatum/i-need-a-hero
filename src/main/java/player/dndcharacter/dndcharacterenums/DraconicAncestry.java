package player.dndcharacter.dndcharacterenums;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public enum DraconicAncestry {
    BLACK("Black"),
    BLUE("Blue"),
    BRASS("Brass"),
    BRONZE("Bronze"),
    COPPER("Copper"),
    GOLD("Gold"),
    GREEN("Green"),
    RED("Red"),
    SILVER("Silver"),
    WHITE("White");

    private final String displayName;

    DraconicAncestry(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static String getAllDraconicAncestries() {
        return Arrays.stream(DraconicAncestry.values())
                .map(DraconicAncestry::getDisplayName)
                .collect(Collectors.joining("\n"));
    }
}
