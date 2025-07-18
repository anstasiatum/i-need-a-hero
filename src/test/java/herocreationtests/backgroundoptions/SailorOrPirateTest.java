package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.dndcharacter.dndcharacterenums.Background.PIRATE;
import static player.dndcharacter.dndcharacterenums.Background.SAILOR;
import static player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseSailorOrPirate.chooseSailorOrPirate;

public class SailorOrPirateTest {

    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setFeaturesAndTraits("test feature. ");
    }

    @Test
    @DisplayName("Set sailor feature")
    void setSailorFeature() {
        chooseSailorOrPirate("Sailor", dndCharacter);
        String expectedResult = "test feature. Ship's Passage When you need to, you can secure free passage on a sailing ship for yourself and your adventuring companions. You might sail on the ship you served on, or another ship you have good relations with (perhaps one captained by a former crewmate). Because you're calling in a favor, you can't be certain of a schedule or route that will meet your every need. Your DM will determine how long it takes to get where you need to go. In return for your free passage, you and your companions are expected to assist the crew during the voyage.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set pirate feature")
    void setPirateFeature() {
        chooseSailorOrPirate("Pirate", dndCharacter);
        String expectedResult = "test feature. No matter where you go, people are afraid of you due to your reputation. When you are in a civilized settlement, you can get away with minor criminal offenses, such as refusing to pay for food at a tavern or breaking down doors at a local shop, since most people will not report your activity to the authorities.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set sailor background")
    void setSailorBackground() {
        chooseSailorOrPirate("Sailor", dndCharacter);

        assertEquals(SAILOR, dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set pirate background")
    void setNobleBackground() {
        chooseSailorOrPirate("Pirate", dndCharacter);

        assertEquals(PIRATE, dndCharacter.getBackground());
    }


    @Test
    @DisplayName("Set feature for wrong input")
    void setFeatureForWrongInput() {
        chooseSailorOrPirate("test", dndCharacter);
        String expectedResult = "test feature. ";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set background for wrong input")
    void setBackgroundForWrongInput() {
        chooseSailorOrPirate("test", dndCharacter);

        assertNull(dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set pirate background: trim test")
    void setNobleBackgroundWithTrim() {
        chooseSailorOrPirate("Pirate ", dndCharacter);

        assertEquals(PIRATE, dndCharacter.getBackground());
    }
}
