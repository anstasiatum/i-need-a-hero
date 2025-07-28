package herocreationtests.options;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static herocreationtests.options.HumanReadableSkill.humanReadable;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static player.userinputhandler.commands.createnewhero.Options.getAlignmentOptions;
import static player.userinputhandler.commands.createnewhero.Options.getAllSkillOptions;
import static player.userinputhandler.commands.createnewhero.Options.getArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBackgroundOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptions;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptionsWithoutSpecified;
import static player.userinputhandler.commands.createnewhero.Options.getCharacteristicsRollingMethodOptions;
import static player.userinputhandler.commands.createnewhero.Options.getCharlatanConItemOptions;
import static player.userinputhandler.commands.createnewhero.Options.getClassOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDraconicAncestryOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDwarfArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getGamingSetOptions;
import static player.userinputhandler.commands.createnewhero.Options.getPossessionsForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.Options.getProficienciesForGuildMerchantOptions;
import static player.userinputhandler.commands.createnewhero.Options.getRaceOptions;
import static player.userinputhandler.commands.createnewhero.Options.getSkillOptions;

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
                "Hill Dwarf",
                "Mountain Dwarf",
                "Dark Elf",
                "Wood Elf",
                "High Elf",
                "Lightfoot Halfling",
                "Stout Halfling",
                "Base Human",
                "Variant Human",
                "Dragonborn",
                "Forest Gnome",
                "Rock Gnome",
                "Half Elf",
                "Half Orc",
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
                "Gladiator",
                "Folk Hero",
                "Guild Artisan",
                "Guild Merchant",
                "Hermit",
                "Noble",
                "Knight",
                "Outlander",
                "Sage",
                "Sailor",
                "Pirate",
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
    @DisplayName("Get characteristics options excluding the specified value")
    void getCharacteristicsOptionsExcludingSpecifiedOneTest() {
        for (Characteristics characteristics : Characteristics.values()) {
            List<String> options = getBasicAbilityOptionsWithoutSpecified(characteristics);

            assertFalse(options.contains(characteristics.toString()));
            List<String> expected = Arrays.stream(Characteristics.values())
                    .filter(n -> n != characteristics)
                    .map(Characteristics::toString)
                    .toList();
            assertEquals(expected.size(), options.size());
            assertTrue(options.containsAll(expected));
        }
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
                "Sleight Of Hand",
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
                "Animal Handling",
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

    @ParameterizedTest(name = "Get Skill Options: {0} is returned as a readable string when present in the incoming set")
    @EnumSource(Skill.class)
    void getSkillOptionsReturnSingleOptionTest(Skill skill) {
        Set<Skill> availableSkills = EnumSet.of(skill);
        List<String> expectedResult = getSkillOptions(availableSkills);
        assertEquals(1, expectedResult.size());
        assertEquals(humanReadable(skill), expectedResult.getFirst());
    }

    @Test
    @DisplayName("Get Skill Options: Return all skills")
    void getSkillOptionsReturnAllSkillsTest() {
        Set<Skill> availableSkills = EnumSet.allOf(Skill.class);
        List<String> expectedResult = getSkillOptions(availableSkills);
        assertEquals(Skill.values().length, expectedResult.size());

        for (Skill skill : Skill.values()) {
            String expected = humanReadable(skill);
            assertTrue(expectedResult.contains(expected));
        }
    }

    @Test
    @DisplayName("Get Skill Options: Return no skills")
    void getSkillOptionsReturnNoSkill() {
        List<String> result = getSkillOptions(emptySet());
        assertTrue(result.isEmpty());
    }
}
