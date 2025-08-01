package player.dndcharacter.race.human;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Race.BASE_HUMAN;

public class BaseHuman extends HumanRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setRace(BASE_HUMAN);
        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
    }
}
