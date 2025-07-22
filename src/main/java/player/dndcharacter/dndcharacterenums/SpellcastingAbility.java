package player.dndcharacter.dndcharacterenums;

public enum SpellcastingAbility {
    STRENGTH("Strength"),
    DEXTERITY("Dexterity"),
    CONSTITUTION("Constitution"),
    INTELLIGENCE("Intelligence"),
    WISDOM("Wisdom"),
    CHARISMA("Charisma");


    private final String displayName;

    SpellcastingAbility(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}