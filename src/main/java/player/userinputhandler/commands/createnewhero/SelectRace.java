package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
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
import player.userinputhandler.Response;
import player.userinputhandler.State;

import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.CHOOSE_DRACONIC_ANCESTRY;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_BASE_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_HIGH_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;

public class SelectRace {
    public static Response selectRace(String userAnswer, DndCharacter dndCharacter) {
        Response response = null;
        State newState;
        String allClasses = """
                Barbarian
                Bard
                Cleric
                Druid
                Fighter
                Monk
                Paladin
                Ranger
                Rogue
                Sorcerer
                Warlock
                Wizard""";
        String chooseClass = """
                Choose your class:
                """ + allClasses;
        String allRaces = """
                Dragonborn
                Hill Dwarf
                Mountain Dwarf
                Dark ELf
                High Elf
                Wood Elf
                Forest Gnome
                Rock Gnome
                Half Elf
                Lightfoot Halfling
                Stout Halfling
                Half Orc
                Base Human
                Variant Human
                Tiefling
                """;
        String chooseFirstAbilityScore = "Enter an ability that will be increased by 1 (Dexterity, Intelligence etc)";
        switch (userAnswer) {
            case "Dragonborn":
                dndCharacter.setRace(Race.DRAGONBORN);
                Dragonborn dragonborn = new Dragonborn();
                dragonborn.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_DRACONIC_ANCESTRY, dndCharacter);
                response = new Response(newState, "Enter your draconic ancestry");
                System.out.println(dndCharacter.getStrength());
                break;
            case "Hill Dwarf":
                dndCharacter.setRace(Race.HILL_DWARF);
                HillDwarf hillDwarf = new HillDwarf();
                hillDwarf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "Mountain Dwarf":
                dndCharacter.setRace(Race.MOUNTAIN_DWARF);
                MountainDwarf mountainDwarf = new MountainDwarf();
                mountainDwarf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "Dark ELf":
                dndCharacter.setRace(Race.DARK_ELF);
                DarkElf darkElf = new DarkElf();
                darkElf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "High Elf":
                dndCharacter.setRace(Race.HIGH_ELF);
                HighElf highElf = new HighElf();
                highElf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HIGH_ELF, dndCharacter);
                response = new Response(newState, "Enter an extra language your character will speak");
                break;
            case "Wood Elf":
                dndCharacter.setRace(Race.WOOD_ELF);
                WoodElf woodElf = new WoodElf();
                woodElf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "Forest Gnome":
                dndCharacter.setRace(Race.FOREST_GNOME);
                ForestGnome forestGnome = new ForestGnome();
                forestGnome.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "Rock Gnome":
                dndCharacter.setRace(Race.ROCK_GNOME);
                RockGnome rockGnome = new RockGnome();
                rockGnome.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "Half Elf":
                dndCharacter.setRace(Race.HALF_ELF);
                HalfElf halfElf = new HalfElf();
                halfElf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                response = new Response(newState, chooseFirstAbilityScore);
                break;
            case "Lightfoot Halfling":
                dndCharacter.setRace(Race.LIGHTFOOT_HALFLING);
                Lightfoot lightfoot = new Lightfoot();
                lightfoot.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "Stout Halfling":
                dndCharacter.setRace(Race.STOUT_HALFLING);
                Stout stout = new Stout();
                stout.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "Half Orc":
                dndCharacter.setRace(Race.HALF_ORC);
                HalfOrc halfOrc = new HalfOrc();
                halfOrc.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            case "Base Human":
                dndCharacter.setRace(Race.BASE_HUMAN);
                BaseHuman baseHuman = new BaseHuman();
                baseHuman.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_BASE_HUMAN, dndCharacter);
                response = new Response(newState, "Enter an additional language your character will know");
                break;
            case "Variant Human":
                dndCharacter.setRace(Race.VARIANT_HUMAN);
                VariantHuman variantHuman = new VariantHuman();
                variantHuman.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                response = new Response(newState, chooseFirstAbilityScore);
                break;
            case "Tiefling":
                dndCharacter.setRace(Race.TIEFLING);
                Tiefling tiefling = new Tiefling();
                tiefling.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass);
                break;
            default:
                newState = new State(CREATE_HERO, CHOOSE_RACE, dndCharacter);
                response = new Response(newState, """
                                                        Sorry, I don't understand your input. Here is the list of available races:
                        """ + allRaces);
                break;
        }
        return response;
    }
}
