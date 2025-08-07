package player.dndcharacter.background.backgrounds;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundService;

import static player.dndcharacter.dndcharacterenums.Background.FOLK_HERO;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.ANIMAL_HANDLING;
import static player.dndcharacter.dndcharacterenums.Skill.SURVIVAL;

public class FolkHero extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(FOLK_HERO);

        dndCharacter.getSkillsWithProficiency().put(ANIMAL_HANDLING, PROFICIENT);
        dndCharacter.getSkillsWithProficiency().put(SURVIVAL, PROFICIENT);

        dndCharacter.getToolProficiency().add("Vehicles (land)");

        dndCharacter.setGold(dndCharacter.getGold() + 10);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A shovel, an iron pot, a set of common clothes. ");

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Rustic Hospitality\nSince you come from the ranks of the common folk, you fit in among them with ease. You can find a place to hide, rest, or recuperate among other commoners, unless you have shown yourself to be a danger to them. They will shield you from the law or anyone else searching for you, though they will not risk their lives for you. ");
    }
}
