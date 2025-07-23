package player.userinputhandler.commands.createnewhero.backgroundoptions;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getPossessionsForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.allArtisansTools;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseTraits;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

public class ChoosePossessionsForGuildMerchant {
    public static Response choosePossessionsForGuildMerchant(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        response = switch (userAnswer.toLowerCase().trim()) {
            case "artisan's tools":
                newState = new State(CREATE_HERO, CHOOSE_ARTISAN_TOOL_POSSESSIONS_FOR_GUILD_MERCHANT, dndCharacter);
                yield new Response(newState, "Choose a set of artisan's tools your hero will be proficient with \n" + allArtisansTools, getArtisanToolOptions());
            case "a mule and a cart":
                dndCharacter.setEquipment(dndCharacter.getEquipment()  + " A mule and a cart");
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                yield new Response(newState, chooseTraits);
            default:
                newState = new State(CREATE_HERO, CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT, dndCharacter);
                yield new Response(newState, wrongInput, getPossessionsForGuildMerchantOptions());
        };
        return response;
    }
}
