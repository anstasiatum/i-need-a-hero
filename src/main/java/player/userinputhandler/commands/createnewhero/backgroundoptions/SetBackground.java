package player.userinputhandler.commands.createnewhero.backgroundoptions;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.Acolyte;
import player.dndcharacter.background.Charlatan;
import player.dndcharacter.background.Criminal;
import player.dndcharacter.background.Entertainer;
import player.dndcharacter.background.FolkHero;
import player.dndcharacter.background.GuildArtisan;
import player.dndcharacter.background.Hermit;
import player.dndcharacter.background.Noble;
import player.dndcharacter.background.Outlander;
import player.dndcharacter.background.Sage;
import player.dndcharacter.background.Sailor;
import player.dndcharacter.background.Soldier;
import player.dndcharacter.background.Urchin;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getCharlatanConItemOptions;
import static player.userinputhandler.commands.createnewhero.Options.getEntertainerOrGladiatorOptions;
import static player.userinputhandler.commands.createnewhero.Options.getGuildMerchantOrArtisanOptions;
import static player.userinputhandler.commands.createnewhero.Options.getKnightOrNobleOptions;
import static player.userinputhandler.commands.createnewhero.Options.getSailorOrPirateOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseArtisanTools;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseTraits;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISAN_OR_MERCHANT_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_CON_FOR_CHARLATAN;
import static player.userinputhandler.enums.Steps.CHOOSE_ENTERTAINER_OR_GLADIATOR;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_LANGUAGE_FOR_SAGE;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_FOR_CRIMINAL;
import static player.userinputhandler.enums.Steps.CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_HERMIT;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_OUTLANDER;
import static player.userinputhandler.enums.Steps.CHOOSE_NOBLE_OR_KNIGHT_FOR_NOBLE;
import static player.userinputhandler.enums.Steps.CHOOSE_SAILOR_OR_PIRATE_FOR_SAILOR;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

public class SetBackground {
    public static Response setBackground(DndCharacter dndCharacter, String userAnswer) {
        Response response;
        State newState;
        switch (userAnswer.toLowerCase().trim()) {
            case "acolyte":
                Acolyte acolyte = new Acolyte();
                acolyte.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_ACOLYTE, dndCharacter);
                response = new Response(newState, "Enter the first language for your acolyte");
                break;
            case "charlatan":
                Charlatan charlatan = new Charlatan();
                charlatan.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CON_FOR_CHARLATAN, dndCharacter);
                response = new Response(newState, "Choose your con item: ten stoppered bottles filled with coloured liquid, a set of weighted dice, a deck of marked cards, or a signet ring of an imaginary duke", getCharlatanConItemOptions());
                break;
            case "criminal":
                Criminal criminal = new Criminal();
                criminal.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_FOR_CRIMINAL, dndCharacter);
                response = new Response(newState, "Choose the gaming set your character will be proficient with");
                break;
            case "entertainer":
                Entertainer entertainer = new Entertainer();
                entertainer.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_ENTERTAINER_OR_GLADIATOR, dndCharacter);
                response = new Response(newState, "Are you a common entertainer or a gladiator?", getEntertainerOrGladiatorOptions());
                break;
            case "folk hero":
                FolkHero folkHero = new FolkHero();
                folkHero.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_FOLK_HERO, dndCharacter);
                response = new Response(newState, chooseArtisanTools, getArtisanToolOptions());
                break;
            case "noble":
                Noble noble = new Noble();
                noble.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_NOBLE_OR_KNIGHT_FOR_NOBLE , dndCharacter);
                response = new Response(newState, "Is your character a common noble or a knight?", getKnightOrNobleOptions());
                break;
            case "outlander":
                Outlander outlander = new Outlander();
                outlander.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_OUTLANDER, dndCharacter);
                response = new Response(newState, "Choose a language your outlander will know");
                break;
            case "sage":
                Sage sage = new Sage();
                sage.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_LANGUAGE_FOR_SAGE, dndCharacter);
                response = new Response(newState, "Choose the first language your sage will know");
                break;
            case "sailor":
                Sailor sailor = new Sailor();
                sailor.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_SAILOR_OR_PIRATE_FOR_SAILOR, dndCharacter);
                response = new Response(newState, "Is your character a common sailor or a pirate?", getSailorOrPirateOptions());
                break;
            case "soldier":
                Soldier soldier = new Soldier();
                soldier.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_GAMING_SET_PROFICIENCY_FOR_SOLDIER, dndCharacter);
                response = new Response(newState, "Enter a gaming set your hero will be proficient with");
                break;
            case "urchin":
                Urchin urchin = new Urchin();
                urchin.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                response = new Response(newState, chooseTraits);
                break;
            case "hermit":
                Hermit hermit = new Hermit();
                hermit.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HERMIT, dndCharacter);
                response = new Response(newState, "Choose a language your hermit will know");
                break;
            case "guild artisan":
                GuildArtisan guildArtisan = new GuildArtisan();
                guildArtisan.modifyByBackground(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_ARTISAN_OR_MERCHANT_FOR_GUILD_ARTISAN, dndCharacter);
                response = new Response(newState, "Are you are merchant or an artisan?", getGuildMerchantOrArtisanOptions());
                break;
            default:
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                response = new Response(newState, chooseTraits);
                break;
        }
        return response;
    }
}
