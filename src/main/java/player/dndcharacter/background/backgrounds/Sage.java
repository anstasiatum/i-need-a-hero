package player.dndcharacter.background.backgrounds;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundService;

import static player.dndcharacter.dndcharacterenums.Background.SAGE;
import static player.dndcharacter.dndcharacterenums.Skill.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skill.HISTORY;

public class Sage extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(SAGE);

        dndCharacter.getSkillsWithProficiency().add(ARCANA);
        dndCharacter.getSkillsWithProficiency().add(HISTORY);

        dndCharacter.setGold(dndCharacter.getGold() + 10);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A bottle of black ink, a quill, a small knife, a letter from a dead colleague posing a question you have not yet been able to answer, a set of common clothes. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Researcher\nWhen you attempt to learn or recall a piece of lore, if you do not know that information, you often know where and from whom you can obtain it. Usually, this information comes from a library, scriptorium, university, or a sage or other learned person or creature. ");

    }
}
