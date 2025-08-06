package player.dndcharacter.background.backgrounds;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundService;

import static player.dndcharacter.dndcharacterenums.Background.PIRATE;
import static player.dndcharacter.dndcharacterenums.Skill.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skill.PERCEPTION;

public class Pirate extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(PIRATE);

        dndCharacter.getSkillsWithProficiency().add(ATHLETICS);
        dndCharacter.getSkillsWithProficiency().add(PERCEPTION);

        dndCharacter.getToolProficiency().add("Navigator's tools");
        dndCharacter.getToolProficiency().add("Vehicles (water)");

        dndCharacter.setGold(dndCharacter.getGold() + 10);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A belaying pin (club) or iron dagger, 50 feet of silk rope, a set of common clothes. ");
    }
}
