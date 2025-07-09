package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;

public class Entertainer extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.getSkillsWithProficiency().add(ACROBATICS);
        dndCharacter.getSkillsWithProficiency().add(PERFORMANCE);

        dndCharacter.getToolProficiency().add("Disguise Kit");

        dndCharacter.setGold(dndCharacter.getGold() + 15);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "An admirer’s favor, a costume. ");
    }
}
