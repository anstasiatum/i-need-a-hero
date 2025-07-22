package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skills;

import javax.management.relation.Relation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skills.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skills.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skills.INTIMIDATION;
import static player.dndcharacter.dndcharacterenums.Skills.INVESTIGATION;
import static player.dndcharacter.dndcharacterenums.Skills.MEDICINE;
import static player.dndcharacter.dndcharacterenums.Skills.NATURE;
import static player.dndcharacter.dndcharacterenums.Skills.PERCEPTION;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;
import static player.dndcharacter.dndcharacterenums.Skills.RELIGION;
import static player.dndcharacter.dndcharacterenums.Skills.SLEIGHT_OF_HAND;
import static player.dndcharacter.dndcharacterenums.Skills.STEALTH;
import static player.dndcharacter.dndcharacterenums.Skills.SURVIVAL;

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
                "Charisma"
        );
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
                "Guild artisan"
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
                "Acrobatics"
        );
    }

    public static List<String> getCharlatanConItemOptions() {

        return List.of(
                "Bottles filled with coloured liquid",
                "Weighted dice",
                "Marked cards",
                "Signet ring"
        );
    }

    public static List<String> getDwarfArtisanToolOptions() {

        return List.of(
                "Smith's tools",
                "Brewer’s supplies",
                "Mason’s tools"
        );
    }

    public static List<String> getSkillOptions(Set<Skills> availableSkills) {

        List<String> skillOptions = new ArrayList<>();

        if (availableSkills.contains(SURVIVAL)) {
            skillOptions.add("Survival");
        }

        if (availableSkills.contains(STEALTH)) {
            skillOptions.add("Stealth");
        }

        if (availableSkills.contains(SLEIGHT_OF_HAND)) {
            skillOptions.add("Sleight Of Hand");
        }

        if (availableSkills.contains(RELIGION)) {
            skillOptions.add("Religion");
        }

        if (availableSkills.contains(PERSUASION)) {
            skillOptions.add("Persuasion");
        }

        if (availableSkills.contains(PERFORMANCE)) {
            skillOptions.add("Performance");
        }

        if (availableSkills.contains(PERCEPTION)) {
            skillOptions.add("Perception");
        }

        if (availableSkills.contains(NATURE)) {
            skillOptions.add("Nature");
        }

        if (availableSkills.contains(MEDICINE)) {
            skillOptions.add("Medicine");
        }

        if (availableSkills.contains(INVESTIGATION)) {
            skillOptions.add("Investigation");
        }

        if (availableSkills.contains(INTIMIDATION)) {
            skillOptions.add("Intimidation");
        }

        if (availableSkills.contains(INSIGHT)) {
            skillOptions.add("Insight");
        }

        if (availableSkills.contains(HISTORY)) {
            skillOptions.add("History");
        }

        if (availableSkills.contains(DECEPTION)) {
            skillOptions.add("Deception");
        }

        if (availableSkills.contains(ATHLETICS)) {
            skillOptions.add("Athletics");
        }

        if (availableSkills.contains(ARCANA)) {
            skillOptions.add("Arcana");
        }

        if (availableSkills.contains(ANIMAL_HANDLING)) {
            skillOptions.add("Animal handling");
        }

        if (availableSkills.contains(ACROBATICS)) {
            skillOptions.add("Acrobatics");
        }

        return skillOptions;
    }
}
