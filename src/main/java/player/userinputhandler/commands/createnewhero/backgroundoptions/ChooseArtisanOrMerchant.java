package player.userinputhandler.commands.createnewhero.backgroundoptions;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.userinputhandler.commands.createnewhero.Options.getGuildMerchantOrArtisanOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISAN_OR_MERCHANT_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT;

public class ChooseArtisanOrMerchant {
    public static Response chooseArtisanOrMerchant(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        response = switch (userAnswer.toLowerCase().trim()) {
            case "guild merchant":
                dndCharacter.setBackground(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_MERCHANT, dndCharacter);
                yield new Response(newState, "Choose a language your guild merchant will know");
            case "guild artisan":
                dndCharacter.setBackground(userAnswer);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_GUILD_ARTISAN, dndCharacter);
                yield new Response(newState, "Choose a language your guild artisan will know");
            default:
                newState = new State(CREATE_HERO, CHOOSE_ARTISAN_OR_MERCHANT_FOR_GUILD_ARTISAN, dndCharacter);
                yield new Response(newState, wrongInput, getGuildMerchantOrArtisanOptions());
        };
        return response;
    }
}
