package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Background.HERMIT;
import static player.dndcharacter.dndcharacterenums.Skill.MEDICINE;
import static player.dndcharacter.dndcharacterenums.Skill.RELIGION;

public class Hermit extends BackgroundService {

    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(HERMIT);

        dndCharacter.getSkillsWithProficiency().add(MEDICINE);
        dndCharacter.getSkillsWithProficiency().add(RELIGION);

        dndCharacter.getToolProficiency().add("Herbalism Kit");

        dndCharacter.setGold(dndCharacter.getGold() + 5);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A scroll case stuffed full of notes from your studies or prayers, a winter blanket, a set of common clothes, an herbalism kit. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Discovery\n The quiet seclusion of your extended hermitage gave you access to a unique and powerful discovery. The exact nature of this revelation depends on the nature of your seclusion. It might be a great truth about the cosmos, the deities, the powerful beings of the outer planes, or the forces of nature. It could be a site that no one else has ever seen. You might have uncovered a fact that has long been forgotten, or unearthed some relic of the past that could rewrite history. It might be information that would be damaging to the people who or consigned you to exile, and hence the reason for your return to society. ");

    }
}
