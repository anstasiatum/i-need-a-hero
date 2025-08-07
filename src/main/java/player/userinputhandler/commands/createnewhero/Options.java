package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.dndcharacterenums.Background;
import player.dndcharacter.dndcharacterenums.CharacterClass;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.dndcharacter.dndcharacterenums.DraconicAncestry;
import player.dndcharacter.dndcharacterenums.Race;
import player.dndcharacter.dndcharacterenums.Skill;
import player.userinputhandler.enums.Alignment;
import player.userinputhandler.enums.ArtisanTool;

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

        return Arrays.stream(Alignment.values())
                .map(Alignment::toString)
                .toList();
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

    public static List<String> getBasicAbilityOptionsWithoutSpecified(Characteristics characteristics) {

        return Arrays.stream(Characteristics.values())
                .filter(n -> n != characteristics)
                .map(Characteristics::toString)
                .toList();
    }

    public static List<String> getArtisanToolOptions() {

        return Arrays.stream(ArtisanTool.values())
                .map(ArtisanTool::toString)
                .toList();
    }

    public static List<String> getPirateFeatureOptions() {

        return List.of(
                "Ship's Passage",
                "Bad Reputation"
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

    public static List<String> getGamingSetOptions() {

        return List.of(
                "Bone dice",
                "Deck of cards"
        );
    }

    public static List<String> getDraconicAncestryOptions() {

        return Arrays.stream(DraconicAncestry.values())
                .map(DraconicAncestry::toString)
                .toList();
    }

    public static List<String> getAllSkillOptions() {
        return Arrays.stream(Skill.values())
                .map(Skill::toString)
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

    public static List<String> getSkillOptions(Set<Skill> availableSkills) {

        return availableSkills.stream()
                .map(Skill::getDisplayName)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<String> getFavouredEnemyOptions() {

        return List.of(
                "Abberations",
                "Beasts",
                "Celestials",
                "Constructs",
                "Dragons",
                "Elementals",
                "Fey",
                "Fiends",
                "Giants",
                "Monstrosities",
                "Oozes",
                "Plants",
                "Undead"
        );
    }

    public static List<String> getFavouredTerrainOptions() {

        return List.of(
                "Arctic",
                "Coast",
                "Desert",
                "Forest",
                "Grassland",
                "Mountain",
                "Swamp",
                "Underdark"
        );
    }

    public static List<String> getLeaveEmptyOption() {

        return List.of("Leave empty");
    }

    public static List<String> secondProficiencyForRogueOptions(Set<Skill> availableSkills) {
        List<String> availableOptions = availableSkills.stream()
                .map(Skill::getDisplayName)
                .sorted()
                .collect(Collectors.toList());
        availableOptions.add("Thief's tools");
        return availableOptions;
    }
}
