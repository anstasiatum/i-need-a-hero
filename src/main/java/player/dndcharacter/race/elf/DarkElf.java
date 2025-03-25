package player.dndcharacter.race.elf;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.abilities.Abilities;

public class DarkElf extends ElfRace {

    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
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
