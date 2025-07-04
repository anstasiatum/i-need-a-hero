package player.userinputhandler.commands.createnewhero;

import com.pengrad.telegrambot.model.request.KeyboardButton;

import java.util.List;

public class Options {
    public static List<String> getCharacteristicsRollingMethodOptions() {

        return List.of(
                "Roll for me, bot",
                "I'll roll myself"
        );
    }

    public static List<String> getClassOptions() {

        return List.of(
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
    }

    public static List<String> getRaceOptions() {

        return List.of(
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
    }

    public static List<String> getAlignmentOptions() {

        return List.of(
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
    }

    public static List<String> getBackgroundOptions() {

        return List.of(
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
    }

    public static List<String> getBasicAbilityOptions() {

        return List.of(
                "Strength",
                "Dexterity",
                "Constitution",
                "Intelligence",
                "Wisdom",
                "Charisma");
    }

    public static List<String> getArtisanToolOptions() {

        return List.of(
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
    }

    public static List<String> getKnightOrNobleOptions() {

        return List.of(
                "Knight",
                "Noble"
        );
    }

    public static List<String> getSailorOrPirateOptions() {

        return List.of(
                "Sailor",
                "Pirate"
        );
    }

    public static List<String> getEntertainerOrGladiatorOptions() {

        return List.of(
                "Entertainer",
                "Gladiator"
        );
    }

    public static List<String> getPossessionsForGuildMerchantOptions() {

        return List.of(
                "Artisan's tools",
                "A mule and a cart"
        );
    }


    public static List<String> getProficienciesForGuildMerchantOptions() {

        return List.of(
                "Additional language",
                "Navigator's tools",
                "Artisan's tools"
        );
    }

    public static List<String> getGuildMerchantOrArtisanOptions() {

        return List.of(
                "Guild merchant",
                "Guild artisange"
        );
    }


    public static List<String> getGamingSetOptions() {

        return List.of(
                "Bone dice",
                "Deck of cards"
        );
    }

    public static List<String> getDraconicAncestryOptions() {

        return List.of(
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
    }

    public static List<String> getAllSkillOptions() {

        return List.of(
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
        "Acrobatics");
    }
}
