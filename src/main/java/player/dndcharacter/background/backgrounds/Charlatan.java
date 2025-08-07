package player.dndcharacter.background.backgrounds;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundService;

import static player.dndcharacter.dndcharacterenums.Background.CHARLATAN;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.SLEIGHT_OF_HAND;

public class Charlatan extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(CHARLATAN);

        dndCharacter.getSkillsWithProficiency().put(DECEPTION, PROFICIENT);
        dndCharacter.getSkillsWithProficiency().put(SLEIGHT_OF_HAND, PROFICIENT);

        dndCharacter.getToolProficiency().add("Disguise Kit");
        dndCharacter.getToolProficiency().add("Forgery Kit");

        dndCharacter.setGold(dndCharacter.getGold() + 15);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A set of fine clothes, a disguise kit. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "False Identity\n You have created a second identity that includes documentation, established acquaintances, and disguises that allow you to assume that persona. Additionally, you can forge documents including official papers and personal letters, as long as you have seen an example of the kind of document or the handwriting you are trying to copy. ");
    }
}
