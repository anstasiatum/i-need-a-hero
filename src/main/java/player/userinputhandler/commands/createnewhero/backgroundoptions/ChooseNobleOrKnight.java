package player.userinputhandler.commands.createnewhero.backgroundoptions;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.dndcharacter.dndcharacterenums.Background.KNIGHT;
import static player.dndcharacter.dndcharacterenums.Background.NOBLE;
import static player.userinputhandler.commands.createnewhero.Options.getKnightOrNobleOptions;
import static player.userinputhandler.commands.createnewhero.OutputTexts.wrongInput;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_NOBLE;
import static player.userinputhandler.enums.Steps.CHOOSE_NOBLE_OR_KNIGHT_FOR_NOBLE;

public class ChooseNobleOrKnight {
    public static Response chooseNobleOrKnight(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        response = switch (userAnswer.toLowerCase().trim()) {
            case "knight":
                dndCharacter.setBackground(KNIGHT);
                dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "You have the service of three retainers loyal to your family. These retainers can be attendants or messengers, and one might be a majordomo. Your retainers are commoners who can perform mundane tasks for you, but they do not fight for you, will not follow you into obviously dangerous areas (such as dungeons), and will leave if they are frequently endangered or abused.\n");
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE, dndCharacter);
                yield new Response(newState, "Choose a language your knight will know");
            case "noble":
                dndCharacter.setBackground(NOBLE);
                dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "You are welcome in high society, and people assume you have the right to be wherever you are. The common folk make every effort to accommodate you and avoid your displeasure, and other people of high birth treat you as a member of the same social sphere. You can secure an audience with a local noble if you need to.\n");
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_NOBLE, dndCharacter);
                yield new Response(newState, "Choose a language your noble will know");
            default:
                newState = new State(CREATE_HERO, CHOOSE_NOBLE_OR_KNIGHT_FOR_NOBLE, dndCharacter);
                yield new Response(newState, wrongInput, getKnightOrNobleOptions());
        };
        return response;
    }
}
