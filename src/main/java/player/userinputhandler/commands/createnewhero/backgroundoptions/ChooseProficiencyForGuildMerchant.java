package player.userinputhandler.commands.createnewhero.backgroundoptions;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getPossessionsForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.Options.getProficienciesForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseArtisanTools;
import static player.userinputhandler.commands.createnewhero.OutputTexts.choosePossessionsForGuildMerchantText;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ADDITIONAL_LANGUAGE_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISAN_TOOL_PROFICIENCY_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT;
import static player.userinputhandler.enums.Steps.CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT;

public class ChooseProficiencyForGuildMerchant {
    public Response chooseProficiencyForGuildMerchant(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        response = switch (userAnswer.toLowerCase().trim()) {
            case "additional language":
                newState = new State(CREATE_HERO, CHOOSE_ADDITIONAL_LANGUAGE_FOR_GUILD_MERCHANT, dndCharacter);
                yield new Response(newState, "Enter the additional language your hero will know");
            case "artisan's tools":
                newState = new State(CREATE_HERO, CHOOSE_ARTISAN_TOOL_PROFICIENCY_FOR_GUILD_MERCHANT, dndCharacter);
                yield new Response(newState, chooseArtisanTools, getArtisanToolOptions());
            case "navigator's tools":
                dndCharacter.getToolProficiency().add("Navigator's tools");
                newState = new State(CREATE_HERO, CHOOSE_POSSESSIONS_FOR_GUILD_MERCHANT, dndCharacter);
                yield new Response(newState, choosePossessionsForGuildMerchantText, getPossessionsForGuildMerchantOptions());
            default:
                newState = new State(CREATE_HERO, CHOOSE_PROFICIENCY_FOR_GUILD_MERCHANT, dndCharacter);
                yield new Response(newState, wrongInput, getProficienciesForGuildMerchantOptions());
        };
        return response;
    }
}
