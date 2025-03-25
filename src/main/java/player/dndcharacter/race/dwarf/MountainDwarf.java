package player.dndcharacter.race.dwarf;

import player.dndcharacter.DndCharacter;

public class MountainDwarf extends DwarfRace {

    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setStrength(dndCharacter.getStrength() + 2);
        dndCharacter.setProficiencies(dndCharacter.getProficiencies() + "Dwarven Armor Training. You have proficiency with light and medium armor.");
        dndCharacter.getArmourProficiency().add("Light armour");
        dndCharacter.getArmourProficiency().add("Medium armour");
    }
}
