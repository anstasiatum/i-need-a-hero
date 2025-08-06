package player.dndcharacter.background.backgrounds;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundService;

import static player.dndcharacter.dndcharacterenums.Background.CRIMINAL;
import static player.dndcharacter.dndcharacterenums.Skill.DECEPTION;
import static player.dndcharacter.dndcharacterenums.Skill.STEALTH;

public class Criminal extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(CRIMINAL);

        dndCharacter.getSkillsWithProficiency().add(DECEPTION);
        dndCharacter.getSkillsWithProficiency().add(STEALTH);

        dndCharacter.getToolProficiency().add("Thieves' tools");

        dndCharacter.setGold(dndCharacter.getGold() + 15);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A crowbar, a set of dark common clothes including a hood. ");

        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Criminal Contact \nYou have a reliable and trustworthy contact who acts as your liaison to a network of other criminals. You know how to get messages to and from your contact, even over great distances; specifically, you know the local messengers, corrupt caravan masters, and seedy sailors who can deliver messages for you. ");
    }
}
