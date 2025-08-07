package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.dndcharacterenums.ProficiencyLevel;
import player.dndcharacter.dndcharacterenums.Skill;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BuildAvailableProficiencySkillsWithoutApplied {
    public Set<Skill> buildAvailableProficiencySkillsWithoutApplied(Map<Skill, ProficiencyLevel> proficiencySkillsAlreadyAppliedMap, Set<Skill> allAvailableProficiencySkills) {
        if (allAvailableProficiencySkills == null) {
            return Set.of();
        }

        Set<Skill> proficiencySkillsAlreadyAppliedSet = proficiencySkillsAlreadyAppliedMap.keySet();

        Set<Skill> availableProficiencySkills = new HashSet<>(Set.copyOf(allAvailableProficiencySkills));
        availableProficiencySkills.removeAll(proficiencySkillsAlreadyAppliedSet);
        return availableProficiencySkills;
    }
}
