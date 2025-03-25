package player.dndcharacter.race.halfling;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.race.Race;

public class HalflingRace extends Race {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setDexterity(dndCharacter.getDexterity() + 2);
        dndCharacter.setSize(Size.SMALL);
        dndCharacter.setSpeed(25);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Lucky. When you roll a 1 on an attack roll, ability check, or saving throw, you can reroll the die and must use the new roll.
                Brave. You have advantage on saving throws against being frightened.
                Halfling Nimbleness. You can move through the space of any creature that is of a size larger than yours.
                """
        );
        dndCharacter.getLanguages().add("Halfling");
    }
}
