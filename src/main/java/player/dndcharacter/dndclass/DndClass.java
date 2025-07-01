package player.dndcharacter.dndclass;

import lombok.Getter;
import lombok.Setter;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skills;

import java.util.Set;

@Getter
@Setter
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

        ModifyStartGold modifyStartGold = new ModifyStartGold();
        modifyStartGold.generateStartGold(dndCharacter);
    }
}

