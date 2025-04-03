package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skills.SURVIVAL;

public class FolkHero extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.getSkillsWithProficiency().add(ANIMAL_HANDLING);
        dndCharacter.getSkillsWithProficiency().add(SURVIVAL);

        dndCharacter.getToolProficiency().add("Vehicles (land)");

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A shovel, an iron pot, a set of common clothes, and a pouch containing 10 gp");

        dndCharacter.setFeaturesAndTraits("Feature: Rustic Hospitality\nSince you come from the ranks of the common folk, you fit in among them with ease. You can find a place to hide, rest, or recuperate among other commoners, unless you have shown yourself to be a danger to them. They will shield you from the law or anyone else searching for you, though they will not risk their lives for you.");
    }
}
