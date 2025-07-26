package player.dndcharacter.dndcharacterenums;

import lombok.Getter;

@Getter
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

    public static Characteristics fromString(String value) {
        if (value == null || value.trim().isEmpty())
            return null;

        String normalizedInput = value.trim().toLowerCase();

        for (Characteristics characteristics : Characteristics.values()) {
            if (characteristics.displayName.toLowerCase().equals(normalizedInput)) {
                return characteristics;
            }
        }

        return null;
    }
}
