package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Background.NOBLE;
import static player.dndcharacter.dndcharacterenums.Skill.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skill.PERSUASION;

public class Noble extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(NOBLE);

        dndCharacter.getSkillsWithProficiency().add(HISTORY);
        dndCharacter.getSkillsWithProficiency().add(PERSUASION);

        dndCharacter.setGold(dndCharacter.getGold() + 25);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A set of fine clothes, a signet ring, a scroll of pedigree. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Position of Privilege\n You are welcome in high society, and people assume you have the right to be wherever you are. The common folk make every effort to accommodate you and avoid your displeasure, and other people of high birth treat you as a member of the same social sphere. You can secure an audience with a local noble if you need to.\n");
    }
}
