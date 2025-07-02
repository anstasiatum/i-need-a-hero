package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.SLEIGHT_OF_HAND;
import static player.dndcharacter.dndcharacterenums.Skills.STEALTH;

public class Urchin extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground("Urchin");

        dndCharacter.getSkillsWithProficiency().add(SLEIGHT_OF_HAND);
        dndCharacter.getSkillsWithProficiency().add(STEALTH);

        dndCharacter.getToolProficiency().add("Disguise Kit");
        dndCharacter.getToolProficiency().add("Thieves' Tools");

        dndCharacter.setGold(dndCharacter.getGold() + 10);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A small knife, a map of the city you grew up in, a pet mouse, a token to remember your parents by, a set of common clothes. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "City Secrets\nYou know the secret patterns and flow to cities and can find passages through the urban sprawl that others would miss. When you are not in combat, you (and companions you lead) can travel between any two locations in the city twice as fast as your speed would normally allow. ");

    }
}
