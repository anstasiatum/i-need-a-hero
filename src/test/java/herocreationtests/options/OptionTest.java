package herocreationtests.options;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.commands.createnewhero.Options.getAlignmentOptions;
import static player.userinputhandler.commands.createnewhero.Options.getAllSkillOptions;
import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBackgroundOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptions;
import static player.userinputhandler.commands.createnewhero.Options.getCharacteristicsRollingMethodOptions;
import static player.userinputhandler.commands.createnewhero.Options.getCharlatanConItemOptions;
import static player.userinputhandler.commands.createnewhero.Options.getClassOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDraconicAncestryOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDwarfArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getEntertainerOrGladiatorOptions;
import static player.userinputhandler.commands.createnewhero.Options.getGamingSetOptions;
import static player.userinputhandler.commands.createnewhero.Options.getGuildMerchantOrArtisanOptions;
import static player.userinputhandler.commands.createnewhero.Options.getKnightOrNobleOptions;
import static player.userinputhandler.commands.createnewhero.Options.getPossessionsForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.Options.getProficienciesForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.Options.getRaceOptions;
import static player.userinputhandler.commands.createnewhero.Options.getSailorOrPirateOptions;

public class OptionTest {
    @Test
    @DisplayName("Get Characteristics Rolling Method Options Test")
    void getCharacteristicsRollingMethodOptionsTest() {
        List<String> expectedResult = List.of(
                "Roll for me, bot",
                "I'll roll myself"
        );

        assertEquals(expectedResult, getCharacteristicsRollingMethodOptions());
    }

    @Test
    @DisplayName("Get Class Options Test")
    void getClassOptionsTest() {
        List<String> expectedResult = List.of(
                "Barbarian",
                "Bard",
                "Cleric",
                "Druid",
                "Fighter",
                "Monk",
                "Paladin",
                "Ranger",
                "Rogue",
                "Sorcerer",
                "Warlock",
                "Wizard");

        assertEquals(expectedResult, getClassOptions());
    }

    @Test
    @DisplayName("Get Race Options Test")
    void getRaceOptionsTest() {
        List<String> expectedResult = List.of(
                "Dragonborn",
                "Hill Dwarf",
                "Mountain Dwarf",
                "Dark Elf",
                "High Elf",
                "Wood Elf",
                "Forest Gnome",
                "Rock Gnome",
                "Half Elf",
                "Lightfoot Halfling",
                "Stout Halfling",
                "Half Orc",
                "Base Human",
                "Variant Human",
                "Tiefling"
        );

        assertEquals(expectedResult, getRaceOptions());
    }

    @Test
    @DisplayName("Get Alignment Options Test")
    void getAlignmentOptionsTest() {
        List<String> expectedResult = List.of(
                "Lawful good",
                "Neutral good",
                "Chaotic good",
                "Lawful neutral",
                "True neutral",
                "Chaotic neutral",
                "Lawful evil",
                "Neutral evil",
                "Chaotic evil"
        );

        assertEquals(expectedResult, getAlignmentOptions());
    }

    @Test
    @DisplayName("Get Background Options Test")
    void getBackgroundOptionsTest() {
        List<String> expectedResult = List.of(
                "Acolyte",
                "Charlatan",
                "Criminal",
                "Entertainer",
                "Folk Hero",
                "Guild Artisan",
                "Hermit",
                "Noble",
                "Outlander",
                "Sage",
                "Sailor",
                "Soldier",
                "Urchin",
                "Custom"
        );

        assertEquals(expectedResult, getBackgroundOptions());
    }

    @Test
    @DisplayName("Get Ability Options Test")
    void getAbilityOptionsTest() {
        List<String> expectedResult = List.of(
                "Strength",
                "Dexterity",
                "Constitution",
                "Intelligence",
                "Wisdom",
                "Charisma"
        );

        assertEquals(expectedResult, getBasicAbilityOptions());
    }

    @Test
    @DisplayName("Get Artisan Tool Options Test")
    void getArtisanToolOptionsTest() {
        List<String> expectedResult = List.of(
                "Alchemist's supplies",
                "Brewer's supplies",
                "Calligrapher's supplies",
                "Carpenter's tools",
                "Cartographer's tools",
                "Cobbler's tools",
                "Cook's utensils",
                "Glassblower's tools",
                "Jeweler's tools",
                "Leatherworker's tools",
                "Mason's tools",
                "Painter's supplies",
                "Potter's tools",
                "Smith's tools",
                "Tinker's tools",
                "Weaver's tools",
                "Woodcarver's tools"
        );

        assertEquals(expectedResult, getArtisanToolOptions());
    }

    @Test
    @DisplayName("Get Knight or Noble Options Test")
    void getKnightOrNobleOptionsTest() {
        List<String> expectedResult = List.of(
                "Knight",
                "Noble"
        );

        assertEquals(expectedResult, getKnightOrNobleOptions());
    }

    @Test
    @DisplayName("Get Sailor or Pirate Options Test")
    void getSailorOrPirateOptionsTest() {
        List<String> expectedResult = List.of(
                "Sailor",
                "Pirate"
        );

        assertEquals(expectedResult, getSailorOrPirateOptions());
    }

    @Test
    @DisplayName("Get Entertainer or Gladiator Options Test")
    void getEntertainerOrGladiatorOptionsTest() {
        List<String> expectedResult = List.of(
                "Entertainer",
                "Gladiator"
        );

        assertEquals(expectedResult, getEntertainerOrGladiatorOptions());
    }

    @Test
    @DisplayName("Get Possessions for Guild Merchant Options Test")
    void getPossessionsForGuildMerchantOptionsTest() {
        List<String> expectedResult = List.of(
                "Artisan's tools",
                "A mule and a cart"
        );

        assertEquals(expectedResult, getPossessionsForGuildMerchantOptions());
    }

    @Test
    @DisplayName("Get Proficiencies for Guild Merchant Options Test")
    void getProficienciesForGuildMerchantOptionsTest() {
        List<String> expectedResult = List.of(
                "Additional language",
                "Navigator's tools",
                "Artisan's tools"
        );

        assertEquals(expectedResult, getProficienciesForGuildMerchantOptions());
    }

    @Test
    @DisplayName("Get Guild Merchant or Artisan Options Test")
    void getGuildMerchantOrArtisanOptionsTest() {
        List<String> expectedResult = List.of(
                "Guild merchant",
                "Guild artisan"
        );

        assertEquals(expectedResult, getGuildMerchantOrArtisanOptions());
    }

    @Test
    @DisplayName("Get Gaming Set Options Test")
    void getGamingSetOptionsTest() {
        List<String> expectedResult = List.of(
                "Bone dice",
                "Deck of cards"
        );

        assertEquals(expectedResult, getGamingSetOptions());
    }

    @Test
    @DisplayName("Get Draconic Ancestry Options Test")
    void getDraconicAncestryOptionsTest() {
        List<String> expectedResult = List.of(
                "Black",
                "Blue",
                "Brass",
                "Bronze",
                "Copper",
                "Gold",
                "Green",
                "Red",
                "Silver",
                "White"
        );

        assertEquals(expectedResult, getDraconicAncestryOptions());
    }

    @Test
    @DisplayName("Get All Skill Options Test")
    void getAllSkillOptionsTest() {
        List<String> expectedResult = List.of(
                "Survival",
                "Stealth",
                "Sleight of hand",
                "Religion",
                "Persuasion",
                "Performance",
                "Perception",
                "Nature",
                "Medicine",
                "Investigation",
                "Intimidation",
                "Insight",
                "History",
                "Deception",
                "Athletics",
                "Arcana",
                "Animal handling",
                "Acrobatics"
        );

        assertEquals(expectedResult, getAllSkillOptions());
    }

    @Test
    @DisplayName("Get Charlatan Con Item Options Test")
    void getCharlatanConItemOptionsTest() {
        List<String> expectedResult = List.of(
                "Bottles filled with coloured liquid",
                "Weighted dice",
                "Marked cards",
                "Signet ring"
        );

        assertEquals(expectedResult, getCharlatanConItemOptions());
    }

    @Test
    @DisplayName("Get Dwarf Artisan Tool Options Test")
    void getDwarfArtisanToolOptionsTest() {
        List<String> expectedResult = List.of(
                "Smith's tools",
                "Brewer’s supplies",
                "Mason’s tools"
        );

        assertEquals(expectedResult, getDwarfArtisanToolOptions());
    }
}
