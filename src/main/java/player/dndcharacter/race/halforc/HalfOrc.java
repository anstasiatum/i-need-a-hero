package player.dndcharacter.race.halforc;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.race.Abilities;
import player.dndcharacter.dndcharacterenums.Size;
import player.dndcharacter.dndcharacterenums.Skill;
import player.dndcharacter.race.Race;

public class HalfOrc extends Race {
    @Override
    public void modifyByRace(DndCharacter dndCharacter) {
        dndCharacter.setStrength(dndCharacter.getStrength() + 2);
        dndCharacter.setConstitution(dndCharacter.getConstitution() + 1);
        dndCharacter.setSize(Size.MEDIUM);
        dndCharacter.setSpeed(30);
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() +
                """
                Relentless Endurance. When you are reduced to 0 hit points but not killed outright, you can drop to 1 hit point instead. You can’t use this feature again until you finish a long rest.
                Savage Attacks. When you score a critical hit with a melee weapon attack, you can roll one of the weapon’s damage dice one additional time and add it to the extra damage of the critical hit.
                """
                + Abilities.BASEDARKVISION);
        dndCharacter.getLanguages().add("Orc");
        dndCharacter.getSkillsWithProficiency().add(Skill.INTIMIDATION);

    }
}
