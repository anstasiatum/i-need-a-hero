package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.backgroundoptions.SetPirateFeature;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_FEATURE_FOR_PIRATE;
import static player.userinputhandler.enums.Steps.SET_PERSONALITY_TRAITS;

public class PirateFeatureTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private final String chooseTraits = "Type any personality traits you'd like to mention";
    private final SetPirateFeature setPirateFeature = new SetPirateFeature();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setFeaturesAndTraits("Existing traits");
    }

    @Test
    @DisplayName("'Ship's Passage' user answer should set 'Ship's Passage' feature and transition to SET_PERSONALITY_TRAITS")
    void addSetShipsPassageFeature() {
        dndCharacter.setFeaturesAndTraits("Existing traits. ");
        String expectedFeatures = "Existing traits. Ship's Passage: When you need to, you can secure free passage on a sailing ship for yourself and your adventuring companions. You might sail on the ship you served on, or another ship you have good relations with (perhaps one captained by a former crewmate). Because you're calling in a favor, you can't be certain of a schedule or route that will meet your every need. Your DM will determine how long it takes to get where you need to go. In return for your free passage, you and your companions are expected to assist the crew during the voyage.\n";
        Response expectedResponse = new Response(new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);

        Response actualResponse = setPirateFeature.setPirateFeature("ship's passage", dndCharacter);

        assertEquals(expectedFeatures, dndCharacter.getFeaturesAndTraits());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("'Bad Reputation' user answer should set 'Bad Reputation' feature and transition to SET_PERSONALITY_TRAITS")
    void addBadReputationPassageFeature() {
        dndCharacter.setFeaturesAndTraits("Existing traits. ");
        String expectedFeatures = "Existing traits. Bad Reputation: No matter where you go, people are afraid of you due to your reputation. When you are in a civilized settlement, you can get away with minor criminal offenses, such as refusing to pay for food at a tavern or breaking down doors at a local shop, since most people will not report your activity to the authorities.\n";
        Response expectedResponse = new Response(new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);

        Response actualResponse = setPirateFeature.setPirateFeature("bad reputation", dndCharacter);

        assertEquals(expectedFeatures, dndCharacter.getFeaturesAndTraits());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should handle input with whitespace and case insensitivity")
    void addBadReputationPassageFeatureTrimTest() {
        dndCharacter.setFeaturesAndTraits("Existing traits. ");
        String expectedFeatures = "Existing traits. Bad Reputation: No matter where you go, people are afraid of you due to your reputation. When you are in a civilized settlement, you can get away with minor criminal offenses, such as refusing to pay for food at a tavern or breaking down doors at a local shop, since most people will not report your activity to the authorities.\n";
        Response expectedResponse = new Response(new State(CREATE_HERO, SET_PERSONALITY_TRAITS, dndCharacter), chooseTraits);

        Response actualResponse = setPirateFeature.setPirateFeature(" BaD repuTation ", dndCharacter);

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFeatures, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Should handle invalid input and return to CHOOSE_FEATURE_FOR_PIRATE")
    void testInvalidInput() {
        dndCharacter.setFeaturesAndTraits("Existing traits. ");
        String expectedFeatures = "Existing traits. ";
        Response expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_FEATURE_FOR_PIRATE, dndCharacter), "Sorry, I don't understand. Maybe there is a typo?", null, true, List.of("Ship's Passage", "Bad Reputation"));

        Response actualResponse = setPirateFeature.setPirateFeature("test", dndCharacter);

        assertEquals(expectedResponse, actualResponse);
        assertEquals(expectedFeatures, dndCharacter.getFeaturesAndTraits());
    }
}
