package player.dndcharacter.race.elf;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.race.Abilities;

import static player.dndcharacter.dndcharacterenums.Race.DARK_ELF;

public class DarkElf extends ElfRace {

    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setRace(DARK_ELF);
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 1);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Cantrip. You know the dancing lights cantrip.
                """
                + Abilities.SUPERIORDARKVISION);

        dndCharacter.getWeaponProficiency().add("Rapiers");
        dndCharacter.getWeaponProficiency().add("Shortsword");
        dndCharacter.getWeaponProficiency().add("Hand crossbows");
    }
}
