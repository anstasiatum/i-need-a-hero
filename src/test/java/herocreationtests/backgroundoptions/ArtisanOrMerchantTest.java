package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseArtisanOrMerchant.chooseArtisanOrMerchant;

public class ArtisanOrMerchantTest {
    DndCharacter dndCharacter = new DndCharacter();

    @Test
    @DisplayName("Set guild merchant background")
    void setGuildMerchantBackground() {
        chooseArtisanOrMerchant("guild merchant", dndCharacter);

        assertEquals("Guild merchant", dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set guild artisan background")
    void setGuildArtisanBackground() {
        chooseArtisanOrMerchant("guild artisan", dndCharacter);

        assertEquals("Guild artisan", dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set background for wrong input")
    void setBackgroundForWrongInput() {
        chooseArtisanOrMerchant("test", dndCharacter);

        assertNull(dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set guild artisan background: trim test")
    void setBackgroundWithTrim() {
        chooseArtisanOrMerchant("Guild artisan ", dndCharacter);

        assertEquals("Guild artisan", dndCharacter.getBackground());
    }
}
