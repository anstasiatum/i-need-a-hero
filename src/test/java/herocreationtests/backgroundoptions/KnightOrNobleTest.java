package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseNobleOrKnight.chooseNobleOrKnight;

public class KnightOrNobleTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setFeaturesAndTraits("test feature. ");
    }

    @Test
    @DisplayName("Set knight feature")
    void setKnightFeature() {
        chooseNobleOrKnight("knight", dndCharacter);
        String expectedResult = "test feature. You have the service of three retainers loyal to your family. These retainers can be attendants or messengers, and one might be a majordomo. Your retainers are commoners who can perform mundane tasks for you, but they do not fight for you, will not follow you into obviously dangerous areas (such as dungeons), and will leave if they are frequently endangered or abused.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set noble feature")
    void setNobleFeature() {
        chooseNobleOrKnight("noble", dndCharacter);
        String expectedResult = "test feature. You are welcome in high society, and people assume you have the right to be wherever you are. The common folk make every effort to accommodate you and avoid your displeasure, and other people of high birth treat you as a member of the same social sphere. You can secure an audience with a local noble if you need to.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set knight background")
    void setKnightBackground() {
        chooseNobleOrKnight("knight", dndCharacter);

        assertEquals("Knight", dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set noble background")
    void setNobleBackground() {
        chooseNobleOrKnight("noble", dndCharacter);

        assertEquals("Noble", dndCharacter.getBackground());
    }


    @Test
    @DisplayName("Set feature for wrong input")
    void setFeatureForWrongInput() {
        chooseNobleOrKnight("test", dndCharacter);
        String expectedResult = "test feature. ";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set background for wrong input")
    void setBackgroundForWrongInput() {
        chooseNobleOrKnight("test", dndCharacter);

        assertNull(dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set noble background: trim test")
    void setNobleBackgroundWithTrim() {
        chooseNobleOrKnight("Noble ", dndCharacter);

        assertEquals("Noble", dndCharacter.getBackground());
    }
}
