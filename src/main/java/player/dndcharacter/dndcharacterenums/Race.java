package player.dndcharacter.dndcharacterenums;

public enum Race {
    HILL_DWARF("Hill Dwarf"),
    MOUNTAIN_DWARF("Mountain Dwarf"),
    DARK_ELF("Dark Elf"),
    WOOD_ELF("Wood Elf"),
    HIGH_ELF("High Elf"),
    LIGHTFOOT_HALFLING("Lightfoot Halfling"),
    STOUT_HALFLING("Stout Halfling"),
    BASE_HUMAN("Base Human"),
    VARIANT_HUMAN("Variant Human"),
    DRAGONBORN("Dragonborn"),
    FOREST_GNOME("Forest Gnome"),
    ROCK_GNOME("Rock Gnome"),
    HALF_ELF("Half Elf"),
    HALF_ORC("Half Orc"),
    TIEFLING("Tiefling");

    private final String displayName;

    Race(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

