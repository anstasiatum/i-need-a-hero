package player.dndcharacter.dndclass;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.Set;

public abstract class DndClass {

    Set<Skills> availableSkills;
    int numberOfAvailableSkills;

    public DndClass(Set<Skills> availableSkills, int numberOfAvailableSkills) {
        this.availableSkills = availableSkills;
        this.numberOfAvailableSkills = numberOfAvailableSkills;
    }

    public void modifyByClass(DndCharacter dndCharacter) {

        dndCharacter.setHitPoints(dndCharacter.getHitDice() + dndCharacter.getConstitutionModifier());
        dndCharacter.setArmourClass(10 + dndCharacter.getDexterityModifier());
        dndCharacter.getLanguages().add("Common");
    }
    public Set<Skills> getAvailableSkills() {
        return availableSkills;
    }

    public void setAvailableSkills(Set<Skills> availableSkills) {
        this.availableSkills = availableSkills;
    }

    public int getNumberOfAvailableSkills() {
        return numberOfAvailableSkills;
    }

    public void setNumberOfAvailableSkills(int numberOfAvailableSkills) {
        this.numberOfAvailableSkills = numberOfAvailableSkills;
    }
}

