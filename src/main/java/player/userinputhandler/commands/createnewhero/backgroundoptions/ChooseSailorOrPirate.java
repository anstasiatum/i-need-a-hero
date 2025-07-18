package player.userinputhandler.commands.createnewhero.backgroundoptions;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.dndcharacter.dndcharacterenums.Background.PIRATE;
import static player.dndcharacter.dndcharacterenums.Background.SAILOR;
import static player.userinputhandler.commands.createnewhero.Options.getSailorOrPirateOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.chooseLuckyCharmForSailor;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_LUCKY_CHARM_FOR_SAILOR;
import static player.userinputhandler.enums.Steps.CHOOSE_SAILOR_OR_PIRATE_FOR_SAILOR;

public class ChooseSailorOrPirate {
    public static Response chooseSailorOrPirate(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        response = switch (userAnswer.toLowerCase().trim()) {
            case "sailor":
                dndCharacter.setBackground(SAILOR);
                dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Ship's Passage When you need to, you can secure free passage on a sailing ship for yourself and your adventuring companions. You might sail on the ship you served on, or another ship you have good relations with (perhaps one captained by a former crewmate). Because you're calling in a favor, you can't be certain of a schedule or route that will meet your every need. Your DM will determine how long it takes to get where you need to go. In return for your free passage, you and your companions are expected to assist the crew during the voyage.\n");
                newState = new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_SAILOR, dndCharacter);
                yield new Response(newState, chooseLuckyCharmForSailor);
            case "pirate":
                dndCharacter.setBackground(PIRATE);
                dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "No matter where you go, people are afraid of you due to your reputation. When you are in a civilized settlement, you can get away with minor criminal offenses, such as refusing to pay for food at a tavern or breaking down doors at a local shop, since most people will not report your activity to the authorities.\n");
                newState = new State(CREATE_HERO, CHOOSE_LUCKY_CHARM_FOR_SAILOR, dndCharacter);
                yield new Response(newState, chooseLuckyCharmForSailor);
            default:
                newState = new State(CREATE_HERO, CHOOSE_SAILOR_OR_PIRATE_FOR_SAILOR, dndCharacter);
                yield new Response(newState, wrongInput, getSailorOrPirateOptions());
        };
        return response;
    }
}
