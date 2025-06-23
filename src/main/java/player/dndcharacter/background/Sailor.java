package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.ATHLETICS;
import static player.dndcharacter.dndcharacterenums.Skills.PERCEPTION;

public class Sailor extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.setBackground("Sailor");

        dndCharacter.getSkillsWithProficiency().add(ATHLETICS);
        dndCharacter.getSkillsWithProficiency().add(PERCEPTION);

        dndCharacter.getToolProficiency().add("Navigator's tools");
        dndCharacter.getToolProficiency().add("Vehicles (water)");

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A belaying pin (club) or iron dagger, 50 feet of silk rope, a lucky charm such as a rabbit foot or a small stone with a hole in the center (or you may roll for a random trinket on the Trinkets table in chapter 5), a set of common clothes, and a pouch containing 10gp");
        dndCharacter.setFeaturesAndTraits(dndCharacter.getFeaturesAndTraits() + "Feature: Researcher\nWhen you attempt to learn or recall a piece of lore, if you do not know that information, you often know where and from whom you can obtain it. Usually, this information comes from a library, scriptorium, university, or a sage or other learned person or creature.");
    }
}
