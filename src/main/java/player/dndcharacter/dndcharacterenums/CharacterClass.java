package player.dndcharacter.dndcharacterenums;

public enum CharacterClass {
    BARBARIAN("Barbarian"),
    BARD("Bard"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    FIGHTER("Fighter"),
    MONK("Monk"),
    PALADIN("Paladin"),
    RANGER("Ranger"),
    ROGUE("Rogue"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard");

    private final String displayName;

    CharacterClass(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
