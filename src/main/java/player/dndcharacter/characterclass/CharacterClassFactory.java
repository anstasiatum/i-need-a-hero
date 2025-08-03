package player.dndcharacter.characterclass;

import player.dndcharacter.characterclass.characterclasses.Barbarian;
import player.dndcharacter.characterclass.characterclasses.Bard;
import player.dndcharacter.characterclass.characterclasses.Cleric;
import player.dndcharacter.characterclass.characterclasses.Druid;
import player.dndcharacter.characterclass.characterclasses.Fighter;
import player.dndcharacter.characterclass.characterclasses.Monk;
import player.dndcharacter.characterclass.characterclasses.Paladin;
import player.dndcharacter.characterclass.characterclasses.Ranger;
import player.dndcharacter.characterclass.characterclasses.Rogue;
import player.dndcharacter.characterclass.characterclasses.Sorcerer;
import player.dndcharacter.characterclass.characterclasses.Warlock;
import player.dndcharacter.characterclass.characterclasses.Wizard;
import player.dndcharacter.dndcharacterenums.CharacterClass;

public class CharacterClassFactory {
    public CharacterClassService createClassFactory(CharacterClass characterClass) {
        switch (characterClass) {
            case BARBARIAN -> {
                return new Barbarian();
            }
            case BARD -> {
                return new Bard();
            }
            case CLERIC -> {
                return new Cleric();
            }
            case DRUID -> {
                return new Druid();
            }
            case FIGHTER -> {
                return new Fighter();
            }
            case MONK -> {
                return new Monk();
            }
            case PALADIN -> {
                return new Paladin();
            }
            case RANGER -> {
                return new Ranger();
            }
            case ROGUE -> {
                return new Rogue();
            }
            case SORCERER -> {
                return new Sorcerer();
            }
            case WARLOCK -> {
                return new Warlock();
            }
            case WIZARD -> {
                return new Wizard();
            }
            default -> throw new IllegalArgumentException("Invalid character class");
        }
    }
}
