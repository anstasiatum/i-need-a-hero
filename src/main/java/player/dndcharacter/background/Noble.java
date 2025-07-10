package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;

public class Noble extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.getSkillsWithProficiency().add(HISTORY);
        dndCharacter.getSkillsWithProficiency().add(PERSUASION);

        dndCharacter.setGold(dndCharacter.getGold() + 25);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A set of fine clothes, a signet ring, a scroll of pedigree. ");
    }
}
