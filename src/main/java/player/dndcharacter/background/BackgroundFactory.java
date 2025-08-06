package player.dndcharacter.background;

import player.dndcharacter.background.backgrounds.Acolyte;
import player.dndcharacter.background.backgrounds.Charlatan;
import player.dndcharacter.background.backgrounds.Criminal;
import player.dndcharacter.background.backgrounds.Custom;
import player.dndcharacter.background.backgrounds.Entertainer;
import player.dndcharacter.background.backgrounds.FolkHero;
import player.dndcharacter.background.backgrounds.Gladiator;
import player.dndcharacter.background.backgrounds.GuildArtisan;
import player.dndcharacter.background.backgrounds.GuildMerchant;
import player.dndcharacter.background.backgrounds.Hermit;
import player.dndcharacter.background.backgrounds.Knight;
import player.dndcharacter.background.backgrounds.Noble;
import player.dndcharacter.background.backgrounds.Outlander;
import player.dndcharacter.background.backgrounds.Pirate;
import player.dndcharacter.background.backgrounds.Sage;
import player.dndcharacter.background.backgrounds.Sailor;
import player.dndcharacter.background.backgrounds.Soldier;
import player.dndcharacter.background.backgrounds.Urchin;
import player.dndcharacter.dndcharacterenums.Background;

public class BackgroundFactory {
    public BackgroundService createBackgroundFactory(Background background) {
        switch (background) {
            case ACOLYTE -> {
                return new Acolyte();
            }
            case CHARLATAN -> {
                return new Charlatan();
            }
            case CRIMINAL -> {
                return new Criminal();
            }
            case ENTERTAINER -> {
                return new Entertainer();
            }
            case GLADIATOR -> {
                return new Gladiator();
            }
            case FOLK_HERO -> {
                return new FolkHero();
            }
            case GUILD_ARTISAN -> {
                return new GuildArtisan();
            }
            case GUILD_MERCHANT -> {
                return new GuildMerchant();
            }
            case HERMIT -> {
                return new Hermit();
            }
            case NOBLE -> {
                return new Noble();
            }
            case KNIGHT -> {
                return new Knight();
            }
            case OUTLANDER -> {
                return new Outlander();
            }
            case SAGE -> {
                return new Sage();
            }
            case SAILOR -> {
                return new Sailor();
            }
            case PIRATE -> {
                return new Pirate();
            }
            case SOLDIER -> {
                return new Soldier();
            }
            case URCHIN -> {
                return new Urchin();
            }
            case CUSTOM -> {
                return new Custom();
            }
            default -> throw new IllegalArgumentException("Invalid background");
        }
    }
}
