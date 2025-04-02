package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.HISTORY;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;

public class Noble extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.getSkillsWithProficiency().add(HISTORY);
        dndCharacter.getSkillsWithProficiency().add(PERSUASION);

        dndCharacter.getToolProficiency().add("One type of gaming set");
        // One language of your choice
        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A set of fine clothes, a signet ring, a scroll of pedigree, and a purse containing 25 gp");
        //Variant Noble: Knight
        //A knighthood is among the lowest noble titles in most societies, but it can be a path to higher status. If you wish to be a knight, choose the Retainers feature (see the sidebar) instead of the Position of Privilege feature. One of your commoner retainers is replaced by a noble who serves as your squire, aiding you in exchange for training on his or her own path to knighthood. Your two remaining retainers might include a groom to care for your horse and a servant who polishes your armor (and even helps you put it on).
        //
        //As an emblem of chivalry and the ideals of courtly love, you might include among your equipment a banner or other token from a noble lord or lady to whom you have given your heart — in a chaste sort of devotion. (This person could be your bond.)
        //
        //
        //Variant Feature: Retainers
        //If your character has a noble background, you may select this background feature instead of Position of Privilege.
        //
        //You have the service of three retainers loyal to your family. These retainers can be attendants or messengers, and one might be a majordomo. Your retainers are commoners who can perform mundane tasks for you, but they do not fight for you, will not follow you into obviously dangerous areas (such as dungeons), and will leave if they are frequently endangered or abused.
        dndCharacter.setFeaturesAndTraits("Feature: Position of Privilege\nThanks to your noble birth, people are inclined to think the best of you. You are welcome in high society, and people assume you have the right to be wherever you are. The common folk make every effort to accommodate you and avoid your displeasure, and other people of high birth treat you as a member of the same social sphere. You can secure an audience with a local noble if you need to.");
    }
}
