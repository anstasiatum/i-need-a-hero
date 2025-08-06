package player.userinputhandler.commands.createnewhero.backgroundoptions;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.dndcharacter.background.Features.badReputationText;
import static player.dndcharacter.background.Features.shipsPassageText;
import static player.userinputhandler.commands.createnewhero.Options.getPirateFeatureOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseTraits;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_FEATURE_FOR_PIRATE;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

public class SetPirateFeature {
    public Response setPirateFeature(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        response = switch (userAnswer.toLowerCase().trim()) {
            case "ship's passage":
                dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + shipsPassageText);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                yield new Response(newState, chooseTraits);
            case "bad reputation":
                dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + badReputationText);
                newState = new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter);
                yield new Response(newState, chooseTraits);
            default:
                newState = new State(CREATE_HERO, CHOOSE_FEATURE_FOR_PIRATE, dndCharacter);
                yield new Response(newState, wrongInput, getPirateFeatureOptions());
        };
        return response;
    }
}
