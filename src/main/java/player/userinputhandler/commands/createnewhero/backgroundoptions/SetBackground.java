package player.userinputhandler.commands.createnewhero.backgroundoptions;

import lombok.AllArgsConstructor;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundFactory;
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
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static java.lang.String.format;
import static player.dndcharacter.dndcharacterenums.Background.ACOLYTE;
import static player.dndcharacter.dndcharacterenums.Background.CHARLATAN;
import static player.dndcharacter.dndcharacterenums.Background.CRIMINAL;
import static player.dndcharacter.dndcharacterenums.Background.CUSTOM;
import static player.dndcharacter.dndcharacterenums.Background.ENTERTAINER;
import static player.dndcharacter.dndcharacterenums.Background.FOLK_HERO;
import static player.dndcharacter.dndcharacterenums.Background.GLADIATOR;
import static player.dndcharacter.dndcharacterenums.Background.GUILD_ARTISAN;
import static player.dndcharacter.dndcharacterenums.Background.GUILD_MERCHANT;
import static player.dndcharacter.dndcharacterenums.Background.HERMIT;
import static player.dndcharacter.dndcharacterenums.Background.KNIGHT;
import static player.dndcharacter.dndcharacterenums.Background.NOBLE;
import static player.dndcharacter.dndcharacterenums.Background.OUTLANDER;
import static player.dndcharacter.dndcharacterenums.Background.PIRATE;
import static player.dndcharacter.dndcharacterenums.Background.SAGE;
import static player.dndcharacter.dndcharacterenums.Background.SAILOR;
import static player.dndcharacter.dndcharacterenums.Background.SOLDIER;
import static player.dndcharacter.dndcharacterenums.Background.URCHIN;
import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBackgroundOptions;
import static player.userinputhandler.commands.createnewhero.Options.getCharlatanConItemOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseArtisanTools;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseBackground;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseFirstLanguage;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseGamingSet;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseLanguage;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseLuckyCharm;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseMusicalInstrumentProficiency;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseTraits;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_BACKGROUND;
import static player.userinputhandler.enums.Steps.CHOOSE_CON_FOR_CHARLATAN;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_LANGUAGE_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_FOR_CRIMINAL;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_HERMIT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_OUTLANDER;
import static player.userinputhandler.enums.Steps.CHOOSE_LUCKY_CHARM_FOR_PIRATE;
import static player.userinputhandler.enums.Steps.CHOOSE_LUCKY_CHARM_FOR_SAILOR;
import static player.userinputhandler.enums.Steps.CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER;
import static player.userinputhandler.enums.Steps.CHOOSE_WEAPON_FOR_GLADIATOR;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

@AllArgsConstructor
public class SetBackground {
    private final BackgroundFactory backgroundFactory;

    public Response setBackground(DndCharacter dndCharacter, String userAnswer) {
        Response response;
        State newState;
        Response defaultResponse = new Response(new State(CREATE_HERO, CHOOSE_BACKGROUND, dndCharacter), chooseBackground, getBackgroundOptions());

        Background background;

        try {
            background = parseBackground(userAnswer);
        } catch (IllegalArgumentException exception) {
            return defaultResponse;
        }

        switch (background) {
            case ACOLYTE:
                Acolyte acolyte = (Acolyte) backgroundFactory.createBackgroundFactory(ACOLYTE);
                acolyte.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE, dndCharacter);
                response = new Response(newState, format(chooseFirstLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case CHARLATAN:
                Charlatan charlatan = (Charlatan) backgroundFactory.createBackgroundFactory(CHARLATAN);
                charlatan.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CON_FOR_CHARLATAN, dndCharacter);
                response = new Response(newState, "Choose your con item: ten stoppered bottles filled with coloured liquid, a set of weighted dice, a deck of marked cards, or a signet ring of an imaginary duke", getCharlatanConItemOptions());
                break;
            case CRIMINAL:
                Criminal criminal = (Criminal) backgroundFactory.createBackgroundFactory(CRIMINAL);
                criminal.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_FOR_CRIMINAL, dndCharacter);
                response = new Response(newState, chooseGamingSet);
                break;
            case ENTERTAINER:
                Entertainer entertainer = (Entertainer) backgroundFactory.createBackgroundFactory(ENTERTAINER);
                entertainer.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER, dndCharacter);
                response = new Response(newState, chooseMusicalInstrumentProficiency);
                break;
            case GLADIATOR:
                Gladiator gladiator = (Gladiator) backgroundFactory.createBackgroundFactory(GLADIATOR);
                gladiator.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_WEAPON_FOR_GLADIATOR, dndCharacter);
                response = new Response(newState, "Enter an inexpensive, but unusual weapon (such as a trident or net) your hero will possess");
                break;
            case FOLK_HERO:
                FolkHero folkHero = (FolkHero) backgroundFactory.createBackgroundFactory(FOLK_HERO);
                folkHero.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO, dndCharacter);
                response = new Response(newState, chooseArtisanTools, getArtisanToolOptions());
                break;
            case NOBLE:
                Noble noble = (Noble) backgroundFactory.createBackgroundFactory(NOBLE);
                noble.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT, dndCharacter);
                response = new Response(newState, format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case KNIGHT:
                Knight knight = (Knight) backgroundFactory.createBackgroundFactory(KNIGHT);
                knight.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE_AND_KNIGHT, dndCharacter);
                response = new Response(newState, format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case OUTLANDER:
                Outlander outlander = (Outlander) backgroundFactory.createBackgroundFactory(OUTLANDER);
                outlander.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_OUTLANDER, dndCharacter);
                response = new Response(newState, format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case SAGE:
                Sage sage = (Sage) backgroundFactory.createBackgroundFactory(SAGE);
                sage.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_SAGE, dndCharacter);
                response = new Response(newState, format(chooseFirstLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case SAILOR:
                Sailor sailor = (Sailor) backgroundFactory.createBackgroundFactory(SAILOR);
                sailor.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_SAILOR, dndCharacter);
                response = new Response(newState, chooseLuckyCharm);
                break;
            case PIRATE:
                Pirate pirate = (Pirate) backgroundFactory.createBackgroundFactory(PIRATE);
                pirate.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_PIRATE, dndCharacter);
                response = new Response(newState, chooseLuckyCharm);
                break;
            case SOLDIER:
                Soldier soldier = (Soldier) backgroundFactory.createBackgroundFactory(SOLDIER);
                soldier.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER, dndCharacter);
                response = new Response(newState, chooseGamingSet);
                break;
            case URCHIN:
                Urchin urchin = (Urchin) backgroundFactory.createBackgroundFactory(URCHIN);
                urchin.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                response = new Response(newState, chooseTraits);
                break;
            case HERMIT:
                Hermit hermit = (Hermit) backgroundFactory.createBackgroundFactory(HERMIT);
                hermit.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HERMIT, dndCharacter);
                response = new Response(newState, format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case GUILD_ARTISAN:
                GuildArtisan guildArtisan = (GuildArtisan) backgroundFactory.createBackgroundFactory(GUILD_ARTISAN);
                guildArtisan.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN, dndCharacter);
                response = new Response(newState, format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case GUILD_MERCHANT:
                GuildMerchant guildMerchant = (GuildMerchant) backgroundFactory.createBackgroundFactory(GUILD_MERCHANT);
                guildMerchant.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT, dndCharacter);
                response = new Response(newState, format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case CUSTOM:
                Custom custom = (Custom) backgroundFactory.createBackgroundFactory(CUSTOM);
                custom.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                response = new Response(newState, chooseTraits);
                break;
            default:
                response = defaultResponse;
                break;
        }
        return response;
    }

    public static Background parseBackground(String input) {
        String normalized = input.trim().toLowerCase();
        for (Background background : Background.values()) {
            if (background.getDisplayName().toLowerCase().equals(normalized)) {
                return background;
            }
        }
        throw new IllegalArgumentException("Cannot parse user input into an existent background");
    }
}
