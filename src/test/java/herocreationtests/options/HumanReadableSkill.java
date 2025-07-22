package herocreationtests.options;

import player.dndcharacter.dndcharacterenums.Skills;

public class HumanReadableSkill {
    public static String humanReadable(Skills skill) {
        return switch (skill) {
            case SURVIVAL -> "Survival";
            case STEALTH -> "Stealth";
            case SLEIGHT_OF_HAND -> "Sleight Of Hand";
            case RELIGION -> "Religion";
            case PERSUASION -> "Persuasion";
            case PERFORMANCE -> "Performance";
            case PERCEPTION -> "Perception";
            case NATURE -> "Nature";
            case MEDICINE -> "Medicine";
            case INVESTIGATION -> "Investigation";
            case INTIMIDATION -> "Intimidation";
            case INSIGHT -> "Insight";
            case HISTORY -> "History";
            case DECEPTION -> "Deception";
            case ATHLETICS -> "Athletics";
            case ARCANA -> "Arcana";
            case ANIMAL_HANDLING -> "Animal Handling";
            case ACROBATICS -> "Acrobatics";
        };
    }
}
