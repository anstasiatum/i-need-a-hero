package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseProficiencyForGuildMerchant;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProficiencyForGuildMerchantTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private final ChooseProficiencyForGuildMerchant chooseProficiencyForGuildMerchant = new ChooseProficiencyForGuildMerchant();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.getToolProficiency().add("testTool");
    }

    @Test
    @DisplayName("Set proficiency for additional language")
    void setAdditionalLanguageProficiency() {
        chooseProficiencyForGuildMerchant.chooseProficiencyForGuildMerchant("Additional language", dndCharacter);
        Set<String> expectedResult = Set.of("testTool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set proficiency for artisan's tools")
    void setArtisanToolProficiency() {
        chooseProficiencyForGuildMerchant.chooseProficiencyForGuildMerchant("Artisan's tools", dndCharacter);
        Set<String> expectedResult = Set.of("testTool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set proficiency for navigator's tools")
    void setNavigatorToolProficiency() {
        chooseProficiencyForGuildMerchant.chooseProficiencyForGuildMerchant("Navigator's tools", dndCharacter);
        Set<String> expectedResult = Set.of("testTool", "Navigator's tools");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set proficiency for wrong option")
    void setToolProficiencyForWrongOption() {
        chooseProficiencyForGuildMerchant.chooseProficiencyForGuildMerchant("testUA", dndCharacter);
        Set<String> expectedResult = Set.of("testTool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set proficiency for navigator's tools: trim test")
    void setNavigatorToolProficiencyWithTrim() {
        chooseProficiencyForGuildMerchant.chooseProficiencyForGuildMerchant("navigator's tools ", dndCharacter);
        Set<String> expectedResult = Set.of("testTool", "Navigator's tools");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }
}
