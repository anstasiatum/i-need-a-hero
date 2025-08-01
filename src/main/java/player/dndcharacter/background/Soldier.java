package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Background.SOLDIER;
import static player.dndcharacter.dndcharacterenums.Skill.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skill.INTIMIDATION;

public class Soldier extends BackgroundService {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground(SOLDIER);

        dndCharacter.getSkillsWithProficiency().add(ATHLETICS);
        dndCharacter.getSkillsWithProficiency().add(INTIMIDATION);

        dndCharacter.getToolProficiency().add("Vehicles (land)");

        dndCharacter.setGold(dndCharacter.getGold() + 10);

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "An insignia of rank, a set of common clothes. ");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Military Rank\nYou have a military rank from your career as a soldier. Soldiers loyal to your former military organization still recognize your authority and influence, and they defer to you if they are of a lower rank. You can invoke your rank to exert influence over other soldiers and requisition simple equipment or horses for temporary use. You can also usually gain access to friendly military encampments and fortresses where your rank is recognized. ");
    }
}
