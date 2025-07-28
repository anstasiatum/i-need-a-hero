package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.commands.createnewhero.backgroundoptions.ChoosePossessionsForGuildMerchant.choosePossessionsForGuildMerchant;

public class GuildMerchantPossessionsTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setEquipment("test equipment. ");
    }

    @Test
    @DisplayName("Set equipment for artisan tools")
    void setArtisanToolEquipment() {
        choosePossessionsForGuildMerchant("artisan's tools", dndCharacter);

        assertEquals("test equipment. ", dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set equipment for a mule and a cart")
    void setMuleAndCartEquipment() {
        choosePossessionsForGuildMerchant("A mule and a cart", dndCharacter);

        assertEquals("test equipment.  A mule and a cart", dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set equipment for wrong item")
    void setEquipmentForWrongInput() {
        choosePossessionsForGuildMerchant("test", dndCharacter);

        assertEquals("test equipment. ", dndCharacter.getEquipment());
    }

    @Test
    @DisplayName("Set equipment for a mule and a cart: trim test")
    void setMuleAndCartEquipmentTrimTest() {
        choosePossessionsForGuildMerchant("a mule and a cart ", dndCharacter);

        assertEquals("test equipment.  A mule and a cart", dndCharacter.getEquipment());
    }
}
