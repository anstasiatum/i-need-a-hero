package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.ACROBATICS;
import static player.dndcharacter.dndcharacterenums.Skills.PERFORMANCE;

public class Entertainer extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.getSkillsWithProficiency().add(ACROBATICS);
        dndCharacter.getSkillsWithProficiency().add(PERFORMANCE);

        dndCharacter.getToolProficiency().add("One type of musical instrument");
        dndCharacter.getToolProficiency().add("Disguise Kit");
        // V a r i a n t E n t e r t a i n e r : G l a d i a t o r
        //A gladiator is as much an entertainer as any minstrel
        //or circus performer, trained to make the arts of combat
        //into a spectacle the crow d can enjoy. This kind of
        //flashy com bat is your entertainer routine, though you
        //might also have som e skills as a tumbler or actor.
        //Using your By Popular D em and feature, you can find a
        //place to perform in any place that features com bat for
        //entertainment—perhaps a gladiatorial arena or secret
        //pit fighting club. You can replace the musical instrument
        //in your equipment package with an inexpensive but
        //unusual weapon, such as a trident or net.
        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A musical instrument of your choice, an admirer’s favor (like a love letter, lock of hair, or trinket), a costume, and a pouch with 15 gp");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "A character with the entertainer background can perform at inns, theaters, circuses, or any place with a stage. While you’re performing there each night, you receive free modest or comfortable lodging and food. This can allow you to take long rests for free as you travel with your party across the land. In addition, your performance makes you famous wherever you perform. When strangers recognize you in the town, they usually like you more. This may make it easier to persuade them to do things for you.");
    }
}
