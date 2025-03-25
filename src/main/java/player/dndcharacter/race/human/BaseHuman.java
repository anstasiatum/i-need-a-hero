package player.dndcharacter.race.human;

import player.dndcharacter.DndCharacter;

public class BaseHuman extends HumanRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);

        dndCharacter.setStrength(dndCharacter.getStrength() + 1);
        dndCharacter.setDexterity(dndCharacter.getDexterity() + 1);
        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
    }
}
