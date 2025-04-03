package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skills.SLEIGHT_OF_HAND;

public class Charlatan extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.getSkillsWithProficiency().add(DECEPTION);
        dndCharacter.getSkillsWithProficiency().add(SLEIGHT_OF_HAND);

        dndCharacter.getToolProficiency().add("Disguise Kit");
        dndCharacter.getToolProficiency().add("Forgery Kit");

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A set of fine clothes, a disguise kit, tools of the con of your choice, and a belt pouch containing 15 gp. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "You have created a second identity that includes documentation, established acquaintances, and disguises that allow you to assume that persona. Additionally, you can forge documents including official papers and personal letters, as long as you have seen an example of the kind of document or the handwriting you are trying to copy.");
    }
}
