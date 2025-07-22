package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.dndcharacterenums.Background;
import player.dndcharacter.dndcharacterenums.CharacterClass;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.Race;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Options {
    public static List<String> getCharacteristicsRollingMethodOptions() {

        return List.of(
                "Roll for me, bot",
                "I'll roll myself"
        );
    }

    public static List<String> getClassOptions() {

        return Arrays.stream(CharacterClass.values())
                .map(CharacterClass::toString)
                .toList();
    }

    public static List<String> getRaceOptions() {

        return Arrays.stream(Race.values())
                .map(Race::toString)
                .toList();
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
        return Arrays.stream(Background.values())
                .map(Background::toString)
                .toList();
    }

    public static List<String> getBasicAbilityOptions() {

        return Arrays.stream(Characteristics.values())
                .map(Characteristics::toString)
                .toList();
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
        return Arrays.stream(Skills.values())
                .map(Skills::toString)
                .toList();
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

        return availableSkills.stream()
                .map(Skills::getDisplayName)  // custom string representation
                .sorted()
                .collect(Collectors.toList());
    }
}
