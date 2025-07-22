package player.dndcharacter.dndcharacterenums;

public enum Characteristics {
    STRENGTH("Strength"),
    DEXTERITY("Dexterity"),
    CONSTITUTION("Constitution"),
    INTELLIGENCE("Intelligence"),
    WISDOM("Wisdom"),
    CHARISMA("Charisma");


    private final String displayName;

    Characteristics(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
