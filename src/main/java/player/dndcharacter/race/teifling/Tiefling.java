package player.dndcharacter.race.teifling;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.race.Abilities;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.RaceService;

import static player.dndcharacter.dndcharacterenums.Race.TIEFLING;

public class Tiefling extends RaceService {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setRace(TIEFLING);
        dndCharacter.setIntelligence(dndCharacter.getIntelligence() + 1);
        dndCharacter.setCharisma(dndCharacter.getCharisma() + 2);
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
        dndCharacter.setFeaturesAndTraits("""
                Hellish Resistance. You have resistance to fire damage.
                Infernal Legacy. You know the thaumaturgy cantrip.
                """
                + Abilities.BASEDARKVISION);
        dndCharacter.getLanguages().add("Infernal");
    }
}
