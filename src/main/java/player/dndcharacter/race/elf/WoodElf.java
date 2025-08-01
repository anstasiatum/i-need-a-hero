package player.dndcharacter.race.elf;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Race.WOOD_ELF;

public class WoodElf extends ElfRace {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        super.modifyByRace(dndCharacter);
        dndCharacter.setRace(WOOD_ELF);
        dndCharacter.setWisdom(dndCharacter.getWisdom() + 1);
        dndCharacter.setSpeed(35);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Mask of the Wild. You can attempt to hide even when you are only lightly obscured by foliage, heavy rain, falling snow, mist, and other natural phenomena."""
        );
        dndCharacter.getWeaponProficiency().add("Longsword");
        dndCharacter.getWeaponProficiency().add("Shortsword");
        dndCharacter.getWeaponProficiency().add("Shortbow");
        dndCharacter.getWeaponProficiency().add("Longbow");
    }
}
