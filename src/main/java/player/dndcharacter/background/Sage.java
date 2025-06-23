package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.ARCANA;
import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;

public class Sage extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground("Sage");

        dndCharacter.getSkillsWithProficiency().add(ARCANA);
        dndCharacter.getSkillsWithProficiency().add(HISTORY);

        dndCharacter.getToolProficiency().add("One type of musical instrument");

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A bottle of black ink, a quill, a small knife, a letter from a dead colleague posing a question you have not yet been able to answer, a set of common clothes, and a pouch containing 10 gp");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Feature: Researcher\nWhen you attempt to learn or recall a piece of lore, if you do not know that information, you often know where and from whom you can obtain it. Usually, this information comes from a library, scriptorium, university, or a sage or other learned person or creature.");

    }
}
