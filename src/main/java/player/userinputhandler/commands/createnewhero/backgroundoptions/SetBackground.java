package player.userinputhandler.commands.createnewhero.backgroundoptions;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Acolyte;
import player.dndcharacter.background.Charlatan;
import player.dndcharacter.background.Criminal;
import player.dndcharacter.background.Custom;
import player.dndcharacter.background.Entertainer;
import player.dndcharacter.background.FolkHero;
import player.dndcharacter.background.Gladiator;
import player.dndcharacter.background.GuildArtisan;
import player.dndcharacter.background.GuildMerchant;
import player.dndcharacter.background.Hermit;
import player.dndcharacter.background.Knight;
import player.dndcharacter.background.Noble;
import player.dndcharacter.background.Outlander;
import player.dndcharacter.background.Pirate;
import player.dndcharacter.background.Sage;
import player.dndcharacter.background.Sailor;
import player.dndcharacter.background.Soldier;
import player.dndcharacter.background.Urchin;
import player.dndcharacter.dndcharacterenums.Background;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import java.util.Optional;

import static java.lang.String.format;
import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
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
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_NOBLE;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_OUTLANDER;
import static player.userinputhandler.enums.Steps.CHOOSE_LUCKY_CHARM_FOR_PIRATE;
import static player.userinputhandler.enums.Steps.CHOOSE_LUCKY_CHARM_FOR_SAILOR;
import static player.userinputhandler.enums.Steps.CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER;
import static player.userinputhandler.enums.Steps.CHOOSE_WEAPON_FOR_GLADIATOR;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

public class SetBackground {
    public static Response setBackground(DndCharacter dndCharacter, String userAnswer) {
        Response response;
        State newState;

        Background background;

        try {
            background = parseBackground(userAnswer);
        } catch (IllegalArgumentException exception) {
            newState = new State(CREATE_HERO, CHOOSE_BACKGROUND, dndCharacter);
            response = new Response(newState, chooseBackground);
            return response;
        }

        switch (background) {
            case ACOLYTE:
                Acolyte acolyte = new Acolyte();
                acolyte.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE, dndCharacter);
                response = new Response(newState, format(chooseFirstLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case CHARLATAN:
                Charlatan charlatan = new Charlatan();
                charlatan.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CON_FOR_CHARLATAN, dndCharacter);
                response = new Response(newState, "Choose your con item: ten stoppered bottles filled with coloured liquid, a set of weighted dice, a deck of marked cards, or a signet ring of an imaginary duke", getCharlatanConItemOptions());
                break;
            case CRIMINAL:
                Criminal criminal = new Criminal();
                criminal.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_FOR_CRIMINAL, dndCharacter);
                response = new Response(newState, chooseGamingSet);
                break;
            case ENTERTAINER:
                Entertainer entertainer = new Entertainer();
                entertainer.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER, dndCharacter);
                response = new Response(newState, chooseMusicalInstrumentProficiency);
                break;
            case GLADIATOR:
                Gladiator gladiator = new Gladiator();
                gladiator.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_WEAPON_FOR_GLADIATOR, dndCharacter);
                response = new Response(newState, "Enter an inexpensive, but unusual weapon (such as a trident or net) your hero will possess");
                break;
            case FOLK_HERO:
                FolkHero folkHero = new FolkHero();
                folkHero.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO, dndCharacter);
                response = new Response(newState, chooseArtisanTools, getArtisanToolOptions());
                break;
            case NOBLE:
                Noble noble = new Noble();
                noble.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE, dndCharacter);
                response = new Response(newState,  format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case KNIGHT:
                Knight knight = new Knight();
                knight.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE, dndCharacter);
                response = new Response(newState,  format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case OUTLANDER:
                Outlander outlander = new Outlander();
                outlander.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_OUTLANDER, dndCharacter);
                response = new Response(newState,  format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case SAGE:
                Sage sage = new Sage();
                sage.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_SAGE, dndCharacter);
                response = new Response(newState, format(chooseFirstLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case SAILOR:
                Sailor sailor = new Sailor();
                sailor.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_SAILOR, dndCharacter);
                response = new Response(newState, chooseLuckyCharm);
                break;
            case PIRATE:
                Pirate pirate = new Pirate();
                pirate.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_PIRATE, dndCharacter);
                response = new Response(newState, chooseLuckyCharm);
                break;
            case SOLDIER:
                Soldier soldier = new Soldier();
                soldier.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER, dndCharacter);
                response = new Response(newState, chooseGamingSet);
                break;
            case URCHIN:
                Urchin urchin = new Urchin();
                urchin.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                response = new Response(newState, chooseTraits);
                break;
            case HERMIT:
                Hermit hermit = new Hermit();
                hermit.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HERMIT, dndCharacter);
                response = new Response(newState,  format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case GUILD_ARTISAN:
                GuildArtisan guildArtisan = new GuildArtisan();
                guildArtisan.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN, dndCharacter);
                response = new Response(newState,  format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case GUILD_MERCHANT:
                GuildMerchant guildMerchant = new GuildMerchant();
                guildMerchant.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT, dndCharacter);
                response = new Response(newState, format(chooseLanguage, dndCharacter.getBackground().getDisplayName().toLowerCase()));
                break;
            case CUSTOM:
                Custom custom = new Custom();
                custom.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                response = new Response(newState, chooseTraits);
                break;
            default:
                newState = new State(CREATE_HERO, CHOOSE_BACKGROUND, dndCharacter);
                response = new Response(newState, chooseBackground);
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
