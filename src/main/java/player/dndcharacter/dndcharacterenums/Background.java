package player.dndcharacter.dndcharacterenums;

import lombok.Getter;

@Getter
public enum Background {
    ACOLYTE("Acolyte"),
    CHARLATAN("Charlatan"),
    CRIMINAL("Criminal"),
    ENTERTAINER("Entertainer"),
    GLADIATOR("Gladiator"),
    FOLK_HERO("Folk Hero"),
    GUILD_ARTISAN("Guild Artisan"),
    GUILD_MERCHANT("Guild Merchant"),
    HERMIT("Hermit"),
    NOBLE("Noble"),
    KNIGHT("Knight"),
    OUTLANDER("Outlander"),
    SAGE("Sage"),
    SAILOR("Sailor"),
    PIRATE("Pirate"),
    SOLDIER("Soldier"),
    URCHIN("Urchin"),
    CUSTOM("Custom");

    private final String displayName;

    Background(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
