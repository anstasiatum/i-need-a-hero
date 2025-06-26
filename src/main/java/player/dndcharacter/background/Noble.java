package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;

public class Noble extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground("Noble");

        dndCharacter.getSkillsWithProficiency().add(HISTORY);
        dndCharacter.getSkillsWithProficiency().add(PERSUASION);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A set of fine clothes, a signet ring, a scroll of pedigree, and a purse containing 25 gp");
    }
}
