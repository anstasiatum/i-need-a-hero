package player.userinputhandler.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public enum ArtisanTool {
    ALCHEMISTS_SUPPLIES("Alchemist's supplies"),
    BREWERS_SUPPLIES("Brewer's supplies"),
    CALLIGRAPHERS_SUPPLIES("Calligrapher's supplies"),
    CARPENTERS_TOOLS("Carpenter's tools"),
    CARTOGRAPHERS_TOOLS("Cartographer's tools"),
    COBBLERS_TOOLS("Cobbler's tools"),
    COOKS_UTENSILS("Cook's utensils"),
    GLASSBLOWERS_TOOLS("Glassblower's tools"),
    JEWELERS_TOOLS("Jeweler's tools"),
    LEATHERWORKERS_TOOLS("Leatherworker's tools"),
    MASONS_TOOLS("Mason's tools"),
    PAINTERS_SUPPLIES("Painter's supplies"),
    POTTERS_TOOLS("Potter's tools"),
    SMITHS_TOOLS("Smith's tools"),
    TINKERS_TOOLS("Tinker's tools"),
    WEAVERS_TOOLS("Weaver's tools"),
    WOODCARVERS_TOOLS("Woodcarver's tools");

    private final String displayName;

    ArtisanTool(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static String getAllArtisanTools() {
        return Arrays.stream(ArtisanTool.values())
                .map(ArtisanTool::getDisplayName)
                .collect(Collectors.joining("\n"));
    }
}

