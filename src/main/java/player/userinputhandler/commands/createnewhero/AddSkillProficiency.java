package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.Arrays;
import java.util.Optional;

public class AddSkillProficiency {

    public static boolean addSkillProficiency(DndCharacter dndCharacter, String userAnswer) {
        Skills skill = skillFromUserInput(userAnswer)
                .orElseThrow(() -> new IllegalArgumentException("Wrong input"));

        return dndCharacter.getSkillsWithProficiency().add(skill);
    }

    public static Optional<Skills> skillFromUserInput(String userAnswer) {
        String normalizedInput = userAnswer.toLowerCase().trim();

        return Arrays.stream(Skills.values())
                .filter(skill -> skill.getDisplayName().toLowerCase().equals(normalizedInput))
                .findFirst();
    }
}
