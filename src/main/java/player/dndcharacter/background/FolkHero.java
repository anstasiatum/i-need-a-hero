package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Background.FOLK_HERO;
import static player.dndcharacter.dndcharacterenums.Skill.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skill.SURVIVAL;

public class FolkHero extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(FOLK_HERO);

        dndCharacter.getSkillsWithProficiency().add(ANIMAL_HANDLING);
        dndCharacter.getSkillsWithProficiency().add(SURVIVAL);

        dndCharacter.getToolProficiency().add("Vehicles (land)");

        dndCharacter.setGold(dndCharacter.getGold() + 10);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A shovel, an iron pot, a set of common clothes. ");

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Rustic Hospitality\nSince you come from the ranks of the common folk, you fit in among them with ease. You can find a place to hide, rest, or recuperate among other commoners, unless you have shown yourself to be a danger to them. They will shield you from the law or anyone else searching for you, though they will not risk their lives for you. ");
    }
}
