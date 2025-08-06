package player.dndcharacter.background.backgrounds;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.background.BackgroundService;

import static player.dndcharacter.dndcharacterenums.Background.ACOLYTE;
import static player.dndcharacter.dndcharacterenums.Skill.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skill.RELIGION;

public class Acolyte extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(ACOLYTE);

        dndCharacter.getSkillsWithProficiency().add(INSIGHT);
        dndCharacter.getSkillsWithProficiency().add(RELIGION);

        dndCharacter.setGold(dndCharacter.getGold() + 15);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A holy symbol (a gift to you when you entered the priesthood), 5 sticks of incense, vestments, a set of common clothes. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Shelter of the Faithful\n As an acolyte, you command the respect of those who share your faith, and you can perform the religious ceremonies of your deity. You and your adventuring companions can expect to receive free healing and care at a temple, shrine, or other established presence of your faith, though you must provide any material components needed for spells. Those who share your religion will support you (but only you) at a modest lifestyle.\n You might also have ties to a specific temple dedicated to your chosen deity or pantheon, and you have a residence there. This could be the temple where you used to serve, if you remain on good terms with it, or a temple where you have found a new home. While near your temple, you can call upon the priests for assistance, provided the assistance you ask for is not hazardous and you remain in good standing with your temple.");
    }
}
