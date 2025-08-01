package player.dndcharacter.race;

import player.dndcharacter.dndcharacterenums.Race;
import player.dndcharacter.race.dragonborn.Dragonborn;
import player.dndcharacter.race.dwarf.HillDwarf;
import player.dndcharacter.race.dwarf.MountainDwarf;
import player.dndcharacter.race.elf.DarkElf;
import player.dndcharacter.race.elf.HighElf;
import player.dndcharacter.race.elf.WoodElf;
import player.dndcharacter.race.gnome.ForestGnome;
import player.dndcharacter.race.gnome.RockGnome;
import player.dndcharacter.race.halfelf.HalfElf;
import player.dndcharacter.race.halfling.Lightfoot;
import player.dndcharacter.race.halfling.Stout;
import player.dndcharacter.race.halforc.HalfOrc;
import player.dndcharacter.race.human.BaseHuman;
import player.dndcharacter.race.human.VariantHuman;
import player.dndcharacter.race.teifling.Tiefling;


public class RaceFactory {
    public RaceService createRaceFactory(Race race) {
        switch (race) {
            case HILL_DWARF -> {
                return new HillDwarf();
            }
            case MOUNTAIN_DWARF -> {
                return new MountainDwarf();
            }
            case DARK_ELF -> {
                return new DarkElf();
            }
            case WOOD_ELF -> {
                return new WoodElf();
            }
            case HIGH_ELF -> {
                return new HighElf();
            }
            case LIGHTFOOT_HALFLING -> {
                return new Lightfoot();
            }
            case STOUT_HALFLING -> {
                return new Stout();
            }
            case BASE_HUMAN -> {
                return new BaseHuman();
            }
            case VARIANT_HUMAN -> {
                return new VariantHuman();
            }
            case DRAGONBORN -> {
                return new Dragonborn();
            }
            case FOREST_GNOME -> {
                return new ForestGnome();
            }
            case ROCK_GNOME -> {
                return new RockGnome();
            }
            case HALF_ELF -> {
                return new HalfElf();
            }
            case HALF_ORC -> {
                return new HalfOrc();
            }
            case TIEFLING -> {
                return new Tiefling();
            }
            default -> {
                throw new IllegalArgumentException("Invalid race");
            }
        }
    }
}
