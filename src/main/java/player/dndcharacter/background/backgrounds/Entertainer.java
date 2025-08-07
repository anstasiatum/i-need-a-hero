package player.dndcharacter.background.backgrounds;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundService;

import static player.dndcharacter.dndcharacterenums.Background.ENTERTAINER;
import static player.dndcharacter.dndcharacterenums.ProficiencyLevel.PROFICIENT;
import static player.dndcharacter.dndcharacterenums.Skill.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skill.PERFORMANCE;

public class Entertainer extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(ENTERTAINER);

        dndCharacter.getSkillsWithProficiency().put(ACROBATICS, PROFICIENT);
        dndCharacter.getSkillsWithProficiency().put(PERFORMANCE, PROFICIENT);

        dndCharacter.getToolProficiency().add("Disguise Kit");

        dndCharacter.setGold(dndCharacter.getGold() + 15);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "An admirer’s favor, a costume. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "By Popular Demand\n You can perform at inns, theaters, circuses, or any place with a stage. While you’re performing there each night, you receive free modest or comfortable lodging and food. This can allow you to take long rests for free as you travel with your party across the land. In addition, your performance makes you famous wherever you perform. When strangers recognize you in the town, they usually like you more. This may make it easier to persuade them to do things for you.\n");
    }
}
