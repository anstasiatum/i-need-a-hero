package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Background.GLADIATOR;
import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;

public class Gladiator extends Background {

    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(GLADIATOR);
        
        dndCharacter.getSkillsWithProficiency().add(ACROBATICS);
        dndCharacter.getSkillsWithProficiency().add(PERFORMANCE);

        dndCharacter.getToolProficiency().add("Disguise Kit");

        dndCharacter.setGold(dndCharacter.getGold() + 15);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "An admirer’s favor, a costume. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "By Popular Demand\n You can always find a place to perform, usually in an inn or tavern but possibly with a circus, at a theater, or even in a noble's court. At such a place, you receive free lodging and food of a modest or comfortable standard (depending on the quality of the establishment), as long as you perform each night. In addition, your performance makes you something of a local figure. When strangers recognize you in a town where you have performed, they typically take a liking to you. You can find a place to perform in any place that features combat for entertainment-perhaps a gladiatorial arena or secret pit fighting club.\n");
    }
}
