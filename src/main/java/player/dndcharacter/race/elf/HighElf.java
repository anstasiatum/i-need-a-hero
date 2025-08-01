package player.dndcharacter.race.elf;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.race.Abilities;

import static player.dndcharacter.dndcharacterenums.Race.HIGH_ELF;

public class HighElf extends ElfRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setRace(HIGH_ELF);
        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Cantrip. You know one cantrip of your choice from the wizard spell list. Intelligence is your spellcasting ability for it.
                """ + Abilities.BASEDARKVISION);
        dndCharacter.getWeaponProficiency().add("Longsword");
        dndCharacter.getWeaponProficiency().add("Shortsword");
        dndCharacter.getWeaponProficiency().add("Shortbow");
        dndCharacter.getWeaponProficiency().add("Longbow");
    }
}
