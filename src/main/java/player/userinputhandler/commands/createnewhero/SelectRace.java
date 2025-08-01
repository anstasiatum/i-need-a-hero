package player.userinputhandler.commands.createnewhero;

import lombok.AllArgsConstructor;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Race;
import player.dndcharacter.race.RaceFactory;
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

import static player.dndcharacter.dndcharacterenums.CharacterClass.getAllClasses;
import static player.dndcharacter.dndcharacterenums.Race.BASE_HUMAN;
import static player.dndcharacter.dndcharacterenums.Race.DARK_ELF;
import static player.dndcharacter.dndcharacterenums.Race.DRAGONBORN;
import static player.dndcharacter.dndcharacterenums.Race.FOREST_GNOME;
import static player.dndcharacter.dndcharacterenums.Race.HALF_ELF;
import static player.dndcharacter.dndcharacterenums.Race.HALF_ORC;
import static player.dndcharacter.dndcharacterenums.Race.HIGH_ELF;
import static player.dndcharacter.dndcharacterenums.Race.HILL_DWARF;
import static player.dndcharacter.dndcharacterenums.Race.LIGHTFOOT_HALFLING;
import static player.dndcharacter.dndcharacterenums.Race.MOUNTAIN_DWARF;
import static player.dndcharacter.dndcharacterenums.Race.ROCK_GNOME;
import static player.dndcharacter.dndcharacterenums.Race.STOUT_HALFLING;
import static player.dndcharacter.dndcharacterenums.Race.TIEFLING;
import static player.dndcharacter.dndcharacterenums.Race.VARIANT_HUMAN;
import static player.dndcharacter.dndcharacterenums.Race.WOOD_ELF;
import static player.dndcharacter.dndcharacterenums.Race.getAllRaces;
import static player.userinputhandler.commands.createnewhero.Options.getBasicAbilityOptions;
import static player.userinputhandler.commands.createnewhero.Options.getClassOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDraconicAncestryOptions;
import static player.userinputhandler.commands.createnewhero.Options.getDwarfArtisanToolOptions;
import static player.userinputhandler.commands.createnewhero.Options.getRaceOptions;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF;
import static player.userinputhandler.enums.Steps.CHOOSE_CLASS;
import static player.userinputhandler.enums.Steps.CHOOSE_DRACONIC_ANCESTRY;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_BASE_HUMAN;
import static player.userinputhandler.enums.Steps.CHOOSE_LANGUAGE_FOR_HIGH_ELF;
import static player.userinputhandler.enums.Steps.CHOOSE_RACE;

@AllArgsConstructor
public class SelectRace {
    RaceFactory raceFactory;

    public Response selectRace(String userAnswer, DndCharacter dndCharacter) {
        Response response;
        State newState;
        String chooseClass = "Choose your class:\n " + getAllClasses();
        String allRaces = getAllRaces();
        String chooseFirstAbilityScore = "Enter an ability that will be increased by 1 (Dexterity, Intelligence etc)";
        String chooseArtisanToolProficiencyForDwarf = "Which artisan tool would you like to be proficient with? Smith’s tools, brewer’s supplies, or mason’s tools.";
        Race race;

        Response defaultResponse = new Response(new State(CREATE_HERO, CHOOSE_RACE, dndCharacter), """
                Sorry, I don't understand your input. Here is the list of available races:
                """ + allRaces, getRaceOptions());

        try {
            race = parseRace(userAnswer);
        } catch (IllegalArgumentException exception) {
            return defaultResponse;
        }

        switch (race) {
            case DRAGONBORN:
                Dragonborn dragonborn = (Dragonborn) raceFactory.createRaceFactory(DRAGONBORN);
                dragonborn.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_DRACONIC_ANCESTRY, dndCharacter);
                response = new Response(newState, "Enter your draconic ancestry", getDraconicAncestryOptions());
                break;
            case HILL_DWARF:
                HillDwarf hillDwarf = (HillDwarf) raceFactory.createRaceFactory(HILL_DWARF);
                hillDwarf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF, dndCharacter);
                response = new Response(newState, chooseArtisanToolProficiencyForDwarf, getDwarfArtisanToolOptions());
                break;
            case MOUNTAIN_DWARF:
                MountainDwarf mountainDwarf = (MountainDwarf) raceFactory.createRaceFactory(MOUNTAIN_DWARF);
                mountainDwarf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_ARTISANS_TOOL_PROFICIENCY_FOR_DWARF, dndCharacter);
                response = new Response(newState, chooseArtisanToolProficiencyForDwarf, getDwarfArtisanToolOptions());
                break;
            case DARK_ELF:
                DarkElf darkElf = (DarkElf) raceFactory.createRaceFactory(DARK_ELF);
                darkElf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case HIGH_ELF:
                HighElf highElf = (HighElf) raceFactory.createRaceFactory(HIGH_ELF);
                highElf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_HIGH_ELF, dndCharacter);
                response = new Response(newState, "Enter an extra language your character will know");
                break;
            case WOOD_ELF:
                WoodElf woodElf = (WoodElf) raceFactory.createRaceFactory(WOOD_ELF);
                woodElf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case FOREST_GNOME:
                ForestGnome forestGnome = (ForestGnome) raceFactory.createRaceFactory(FOREST_GNOME);
                forestGnome.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case ROCK_GNOME:
                RockGnome rockGnome = (RockGnome) raceFactory.createRaceFactory(ROCK_GNOME);
                rockGnome.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case HALF_ELF:
                HalfElf halfElf = (HalfElf) raceFactory.createRaceFactory(HALF_ELF);
                halfElf.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_HALF_ELF, dndCharacter);
                response = new Response(newState, chooseFirstAbilityScore, getBasicAbilityOptions());
                break;
            case LIGHTFOOT_HALFLING:
                Lightfoot lightfoot = (Lightfoot) raceFactory.createRaceFactory(LIGHTFOOT_HALFLING);
                lightfoot.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case STOUT_HALFLING:
                Stout stout = (Stout) raceFactory.createRaceFactory(STOUT_HALFLING);
                stout.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case HALF_ORC:
                HalfOrc halfOrc = (HalfOrc) raceFactory.createRaceFactory(HALF_ORC);
                halfOrc.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            case BASE_HUMAN:
                BaseHuman baseHuman = (BaseHuman) raceFactory.createRaceFactory(BASE_HUMAN);
                baseHuman.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_LANGUAGE_FOR_BASE_HUMAN, dndCharacter);
                response = new Response(newState, "Enter an additional language your character will know");
                break;
            case VARIANT_HUMAN:
                VariantHuman variantHuman = (VariantHuman) raceFactory.createRaceFactory(VARIANT_HUMAN);
                variantHuman.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_FIRST_ABILITY_SCORE_FOR_VARIANT_HUMAN, dndCharacter);
                response = new Response(newState, chooseFirstAbilityScore, getBasicAbilityOptions());
                break;
            case TIEFLING:
                Tiefling tiefling = (Tiefling) raceFactory.createRaceFactory(TIEFLING);
                tiefling.modifyByRace(dndCharacter);
                newState = new State(CREATE_HERO, CHOOSE_CLASS, dndCharacter);
                response = new Response(newState, chooseClass, getClassOptions());
                break;
            default:
                response = defaultResponse;
                break;
        }
        return response;
    }

    public static Race parseRace(String input) {
        String normalized = input.trim().toLowerCase();
        for (Race race : Race.values()) {
            if (race.getDisplayName().toLowerCase().equals(normalized)) {
                return race;
            }
        }
        throw new IllegalArgumentException("Cannot parse user input into an existent race");
    }
}
