package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.ProficiencyLevel;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.Arrays;
import java.util.Optional;

import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.EXPERTISE;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;

public class AddSkillProficiency {

    public boolean addSkillProficiency(DndCharacter dndCharacter, String userAnswer) {
        Skill skill = skillFromUserInput(userAnswer)
                .orElseThrow(() -> new IllegalArgumentException("Wrong input"));

        if (dndCharacter.getSkillsWithProficiency().containsKey(skill)) {
            return false;
        } else {
            dndCharacter.getSkillsWithProficiency().put(skill, PROFICIENT);
            return true;
        }
    }

    public boolean addSkillExpertise(DndCharacter dndCharacter, String userAnswer) {
        Skill skill = skillFromUserInput(userAnswer)
                .orElseThrow(() -> new IllegalArgumentException("Wrong input"));

        if (dndCharacter.getSkillsWithProficiency().get(skill) == null) {
            return false;
        }

        if (dndCharacter.getSkillsWithProficiency().get(skill).equals(PROFICIENT)) {
            dndCharacter.getSkillsWithProficiency().put(skill, EXPERTISE);
            return true;
        } else {
            return false;
        }
    }

    public static Optional<Skill> skillFromUserInput(String userAnswer) {
        String normalizedInput = userAnswer.toLowerCase().trim();

        return Arrays.stream(Skill.values())
                .filter(skill -> skill.getDisplayName().toLowerCase().equals(normalizedInput))
                .findFirst();
    }
}
