package player.userinputhandler.commands.createnewhero.increasebasecharacteristics;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;

public class IncrementAbility {
    public void incrementAbility(Characteristics characteristics, DndCharacter dndCharacter) {
        switch (characteristics) {
            case STRENGTH:
                dndCharacter.setStrength(dndCharacter.getStrength() + 1);
                break;
            case DEXTERITY:
                dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
                break;
            case CONSTITUTION:
                dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
                break;
            case INTELLIGENCE:
                dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
                break;
            case WISDOM:
                dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
                break;
            case CHARISMA:
                dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
                break;
        }
    }
}
