package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;

public class Entertainer extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground("Entertainer");

        dndCharacter.getSkillsWithProficiency().add(ACROBATICS);
        dndCharacter.getSkillsWithProficiency().add(PERFORMANCE);

        dndCharacter.getToolProficiency().add("One type of musical instrument");
        dndCharacter.getToolProficiency().add("Disguise Kit");

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "An admirer’s favor, a costume, and a pouch with 15 gp.");
    }
}
