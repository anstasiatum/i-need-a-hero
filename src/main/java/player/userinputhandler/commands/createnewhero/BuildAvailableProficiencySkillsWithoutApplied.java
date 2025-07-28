package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.dndcharacterenums.Skill;

import java.util.HashSet;
import java.util.Set;

public class BuildAvailableProficiencySkillsWithoutApplied {
    public Set<Skill> buildAvailableProficiencySkillsWithoutApplied(Set<Skill> proficiencySkillsAlreadyApplied, Set<Skill> allAvailableProficiencySkills) {
        if (allAvailableProficiencySkills == null) {
            return Set.of();
        }

        Set<Skill> availableProficiencySkills = new HashSet<>(Set.copyOf(allAvailableProficiencySkills));
        availableProficiencySkills.removeAll(proficiencySkillsAlreadyApplied);
        return availableProficiencySkills;
    }
}
