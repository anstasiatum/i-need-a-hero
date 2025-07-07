package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.userinputhandler.commands.createnewhero.Options.getEntertainerOrGladiatorOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseMusicalInstrumentProficiency;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ENTERTAINER_OR_GLADIATOR;
import static player.userinputhandler.enums.Steps.CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER;
import static player.userinputhandler.enums.Steps.CHOOSE_WEAPON_FOR_GLADIATOR;

public class ChooseEntertainerOrGladiator {
    public static Response chooseEntertainerOrGladiator(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        response = switch (userAnswer.toLowerCase().trim()) {
            case "entertainer":
                dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "You can perform at inns, theaters, circuses, or any place with a stage. While you’re performing there each night, you receive free modest or comfortable lodging and food. This can allow you to take long rests for free as you travel with your party across the land. In addition, your performance makes you famous wherever you perform. When strangers recognize you in the town, they usually like you more. This may make it easier to persuade them to do things for you.\n");
                newState = new State(CREATE_HERO, CHOOSE_MUSICAL_INSTRUMENT_YOU_ARE_PROFICIENT_WITH_FOR_ENTERTAINER, dndCharacter);
                yield new Response(newState, chooseMusicalInstrumentProficiency);
            case "gladiator":
                dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "You can find a place to perform in any place that features combat for entertainment-perhaps a gladiatorial arena or secret pit fighting club.\n");
                newState = new State(CREATE_HERO, CHOOSE_WEAPON_FOR_GLADIATOR, dndCharacter);
                yield new Response(newState, "Enter an inexpensive, but unusual weapon (such as a trident or net) your hero will possess");
            default:
                newState = new State(CREATE_HERO, CHOOSE_ENTERTAINER_OR_GLADIATOR, dndCharacter);
                yield new Response(newState, wrongInput, getEntertainerOrGladiatorOptions());
        };
        return response;
    }
}