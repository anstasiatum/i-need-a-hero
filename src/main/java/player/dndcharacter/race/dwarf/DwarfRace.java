package player.dndcharacter.race.dwarf;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.race.Abilities;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.Race;

public abstract class DwarfRace extends Race {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setConstitution(dndCharacter.getConstitution() + 2);
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(25);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Your speed is not reduced by wearing heavy armor.
                Dwarven Resilience. You have advantage on saving throws against poison, and you have resistance against poison damage.
                Stonecunning. Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered proficient in the History skill and add double your proficiency bonus to the check, instead of your normal proficiency bonus.
                """
                + Abilities.BASEDARKVISION);
        dndCharacter.getLanguages().add("Dwarfish");

        dndCharacter.getWeaponProficiency().add("Battleaxe");
        dndCharacter.getWeaponProficiency().add("Handaxe");
        dndCharacter.getWeaponProficiency().add("Throwing hammer");
        dndCharacter.getWeaponProficiency().add("Warhammer");
    }
}
