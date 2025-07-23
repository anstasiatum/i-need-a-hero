package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.dndcharacterenums.Skills;

import java.util.HashSet;
import java.util.Set;

public class BuildAvailableProficiencySkillsWithoutApplied {
    public Set<Skills> buildAvailableProficiencySkillsWithoutApplied(Set<Skills> proficiencySkillsAlreadyApplied, Set<Skills> allAvailableProficiencySkills) {
        Set<Skills> availableProficiencySkills = new HashSet<>(Set.copyOf(allAvailableProficiencySkills));
        availableProficiencySkills.removeAll(proficiencySkillsAlreadyApplied);
        return availableProficiencySkills;
    }
}
