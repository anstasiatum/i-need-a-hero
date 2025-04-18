package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skills.STEALTH;

public class Criminal extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.getSkillsWithProficiency().add(DECEPTION);
        dndCharacter.getSkillsWithProficiency().add(STEALTH);

        dndCharacter.getToolProficiency().add("A gaming set");
        dndCharacter.getToolProficiency().add("Thieves' tools");

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A crowbar, a set of dark common clothes including a hood, and a pouch containing 15 gp");

        dndCharacter.setFeaturesAndTraits("Feature: Criminal Contact \nYou have a reliable and trustworthy contact who acts as your liaison to a network of other criminals. You know how to get messages to and from your contact, even over great distances; specifically, you know the local messengers, corrupt caravan masters, and seedy sailors who can deliver messages for you.");
    }
}
