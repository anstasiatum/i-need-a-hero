package heroprintingtests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Background;
import player.dndcharacter.dndcharacterenums.CharacterClass;
import player.dndcharacter.dndcharacterenums.Race;
import player.dndcharacter.dndcharacterenums.SpellcastingAbility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.commands.printhero.PDFCreator.getBackgroundForPDF;
import static player.userinputhandler.commands.printhero.PDFCreator.getClassForPDF;
import static player.userinputhandler.commands.printhero.PDFCreator.getRaceForPDF;
import static player.userinputhandler.commands.printhero.PDFCreator.getSpellcastingAbilityForPDF;

public class GetStringFromEnumsForPDF {

    private final DndCharacter dndCharacter = new DndCharacter();

    @ParameterizedTest(name = "Return String alternative for {0} background")
    @EnumSource(Background.class)
    void getBackgroundForPDFReturnsCorrectStringForAllEnumValues(Background background) {
        dndCharacter.setBackground(background);

        String expectedResult = switch (background) {
            case ACOLYTE -> "Acolyte";
            case CHARLATAN -> "Charlatan";
            case CRIMINAL -> "Criminal";
            case ENTERTAINER -> "Entertainer";
            case GLADIATOR -> "Gladiator";
            case FOLK_HERO -> "Folk Hero";
            case GUILD_ARTISAN -> "Guild Artisan";
            case GUILD_MERCHANT -> "Guild Merchant";
            case HERMIT -> "Hermit";
            case NOBLE -> "Noble";
            case KNIGHT -> "Knight";
            case OUTLANDER -> "Outlander";
            case SAGE -> "Sage";
            case SAILOR -> "Sailor";
            case PIRATE -> "Pirate";
            case SOLDIER -> "Soldier";
            case URCHIN -> "Urchin";
            case CUSTOM -> "Custom";
        };

        assertEquals(expectedResult, getBackgroundForPDF(dndCharacter));
    }

    @ParameterizedTest(name = "Return String alternative for {0} class")
    @EnumSource(CharacterClass.class)
    void getClassForPDFReturnsCorrectStringForAllEnumValues(CharacterClass characterClass) {
        dndCharacter.setCharacterClass(characterClass);

        String expectedResult = switch (characterClass) {
            case BARBARIAN -> "Barbarian";
            case BARD -> "Bard";
            case CLERIC -> "Cleric";
            case DRUID -> "Druid";
            case FIGHTER -> "Fighter";
            case MONK -> "Monk";
            case PALADIN -> "Paladin";
            case RANGER -> "Ranger";
            case ROGUE -> "Rogue";
            case SORCERER -> "Sorcerer";
            case WARLOCK -> "Warlock";
            case WIZARD -> "Wizard";
        };

        assertEquals(expectedResult, getClassForPDF(dndCharacter));
    }

    @ParameterizedTest(name = "Return String alternative for {0} race")
    @EnumSource(Race.class)
    void getRaceForPDFReturnsCorrectStringForAllEnumValues(Race race) {
        dndCharacter.setRace(race);

        String expectedResult = switch (race) {
            case HILL_DWARF -> "Hill Dwarf";
            case MOUNTAIN_DWARF -> "Mountain Dwarf";
            case DARK_ELF -> "Dark Elf";
            case WOOD_ELF -> "Wood Elf";
            case HIGH_ELF -> "High Elf";
            case LIGHTFOOT_HALFLING -> "Lightfoot Halfling";
            case STOUT_HALFLING -> "Stout Halfling";
            case BASE_HUMAN, VARIANT_HUMAN -> "Human";
            case DRAGONBORN -> "Dragonborn";
            case FOREST_GNOME -> "Forest Gnome";
            case ROCK_GNOME -> "Rock Gnome";
            case HALF_ELF -> "Half Elf";
            case HALF_ORC -> "Half Orc";
            case TIEFLING -> "Tiefling";
        };

        assertEquals(expectedResult, getRaceForPDF(dndCharacter));
    }

    @ParameterizedTest(name = "Return String alternative for {0} Spellcasting Ability")
    @EnumSource(SpellcastingAbility.class)
    void getSpellcastingAbilityForPDFReturnsCorrectStringForAllEnumValues(SpellcastingAbility spellcastingAbility) {
        dndCharacter.setSpellcastingAbility(spellcastingAbility);

        String expectedResult = switch (spellcastingAbility) {
            case STRENGTH -> "Strength";
            case DEXTERITY -> "Dexterity";
            case CONSTITUTION -> "Constitution";
            case INTELLIGENCE -> "Intelligence";
            case WISDOM -> "Wisdom";
            case CHARISMA -> "Charisma";
        };

        assertEquals(expectedResult, getSpellcastingAbilityForPDF(dndCharacter));
    }
}


