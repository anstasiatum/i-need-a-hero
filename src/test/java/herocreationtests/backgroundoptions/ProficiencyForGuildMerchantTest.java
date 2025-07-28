package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseProficiencyForGuildMerchant.chooseProficiencyForGuildMerchant;

public class ProficiencyForGuildMerchantTest {
    private final DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.getToolProficiency().add("testTool");
    }

    @Test
    @DisplayName("Set proficiency for additional language")
    void setAdditionalLanguageProficiency() {
        chooseProficiencyForGuildMerchant("Additional language", dndCharacter);
        Set<String> expectedResult = Set.of("testTool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set proficiency for artisan's tools")
    void setArtisanToolProficiency() {
        chooseProficiencyForGuildMerchant("Artisan's tools", dndCharacter);
        Set<String> expectedResult = Set.of("testTool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set proficiency for navigator's tools")
    void setNavigatorToolProficiency() {
        chooseProficiencyForGuildMerchant("Navigator's tools", dndCharacter);
        Set<String> expectedResult = Set.of("testTool", "Navigator's tools");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set proficiency for wrong option")
    void setToolProficiencyForWrongOption() {
        chooseProficiencyForGuildMerchant("testUA", dndCharacter);
        Set<String> expectedResult = Set.of("testTool");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }

    @Test
    @DisplayName("Set proficiency for navigator's tools: trim test")
    void setNavigatorToolProficiencyWithTrim() {
        chooseProficiencyForGuildMerchant("navigator's tools ", dndCharacter);
        Set<String> expectedResult = Set.of("testTool", "Navigator's tools");

        assertEquals(expectedResult, dndCharacter.getToolProficiency());
    }
}
