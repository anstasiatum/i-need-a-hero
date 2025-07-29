package player.dndcharacter.dndcharacterenums;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public enum Skill {
    SURVIVAL("Survival"),
    STEALTH("Stealth"),
    SLEIGHT_OF_HAND("Sleight Of Hand"),
    RELIGION("Religion"),
    PERSUASION("Persuasion"),
    PERFORMANCE("Performance"),
    PERCEPTION("Perception"),
    NATURE("Nature"),
    MEDICINE("Medicine"),
    INVESTIGATION("Investigation"),
    INTIMIDATION("Intimidation"),
    INSIGHT("Insight"),
    HISTORY("History"),
    DECEPTION("Deception"),
    ATHLETICS("Athletics"),
    ARCANA("Arcana"),
    ANIMAL_HANDLING("Animal Handling"),
    ACROBATICS("Acrobatics");

    private final String displayName;

    Skill(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static String getAllSkills() {
        return Arrays.stream(Skill.values())
                .map(Skill::getDisplayName)
                .collect(Collectors.joining("\n"));
    }
}

