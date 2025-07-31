package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.Arrays;
import java.util.Optional;

public class AddSkillProficiency {

    public boolean addSkillProficiency(DndCharacter dndCharacter, String userAnswer) {
        Skill skill = skillFromUserInput(userAnswer)
                .orElseThrow(() -> new IllegalArgumentException("Wrong input"));

        return dndCharacter.getSkillsWithProficiency().add(skill);
    }

    public static Optional<Skill> skillFromUserInput(String userAnswer) {
        String normalizedInput = userAnswer.toLowerCase().trim();

        return Arrays.stream(Skill.values())
                .filter(skill -> skill.getDisplayName().toLowerCase().equals(normalizedInput))
                .findFirst();
    }
}
