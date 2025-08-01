package player.dndcharacter.race.elf;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.dndcharacterenums.Skill;
import player.dndcharacter.race.RaceService;

public abstract class ElfRace extends RaceService {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setDexterity(dndCharacter.getDexterity() + 2);
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
        dndCharacter.getSkillsWithProficiency().add(Skill.PERCEPTION);
        dndCharacter.getLanguages().add("Elvish");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + """
                Trance: Elves don’t need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. (The Common word for such meditation is “trance.”) While meditating, you can dream after a fashion; such dreams are actually mental exercises that have become reflexive through years of practice. After resting in this way, you gain the same benefit that a human does from 8 hours of sleep.
                Fey Ancestry: You have advantage on saving throws against being charmed, and magic can’t put you to sleep.
                """);
    }
}
