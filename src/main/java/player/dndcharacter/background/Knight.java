package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Background.KNIGHT;
import static player.dndcharacter.dndcharacterenums.Skill.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skill.PERSUASION;

public class Knight extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(KNIGHT);

        dndCharacter.getSkillsWithProficiency().add(HISTORY);
        dndCharacter.getSkillsWithProficiency().add(PERSUASION);

        dndCharacter.setGold(dndCharacter.getGold() + 25);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A set of fine clothes, a signet ring, a scroll of pedigree. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Retainers\n You have the service of three retainers loyal to your family. These retainers can be attendants or messengers, and one might be a majordomo. Your retainers are commoners who can perform mundane tasks for you, but they do not fight for you, will not follow you into obviously dangerous areas (such as dungeons), and will leave if they are frequently endangered or abused.\n");
    }
}
