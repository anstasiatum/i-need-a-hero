package player.dndcharacter.background;

import player.dndcharacter.DndCharacter;

import static player.dndcharacter.dndcharacterenums.Skills.INSIGHT;
import static player.dndcharacter.dndcharacterenums.Skills.PERSUASION;

public class GuildArtisan extends Background {
    @Override
    public void modifyByBackground(DndCharacter dndCharacter) {
        dndCharacter.getSkillsWithProficiency().add(INSIGHT);
        dndCharacter.getSkillsWithProficiency().add(PERSUASION);

        dndCharacter.getToolProficiency().add("One type of artisan's tools");
        // Languages: One of your choice

        dndCharacter.setEquipment(dndCharacter.getEquipment() + "A set of artisan's tools (one of your choice), a letter of introduction from your guild, a set of traveler's clothes, and a pouch containing 15 gp");
        // Rather than proficiency with artisan’s tools, you might
        // be proficient with navigator’s tools or an additional
        //language. And instead of artisan’s tools, you can start
        //with a mule and a cart.
        dndCharacter.setFeaturesAndTraits("Feature: Guild Membership\n As an established and respected member of a guild, you can rely on certain benefits that membership provides. Your fellow guild members will provide you with lodging and food if necessary, and pay for your funeral if needed. In some cities and towns, a guildhall offers a central place to meet other members of your profession, which can be a good place to meet potential patrons, allies, or hirelings.\n Guilds often wield tremendous political power. If you are accused of a crime, your guild will support you if a good case can be made for your innocence or the crime is justifiable. You can also gain access to powerful political figures through the guild, if you are a member in good standing. Such connections might require the donation of money or magic items to the guild's coffers.\n You must pay dues of 5 gp per month to the guild. If you miss payments, you must make up back dues to remain in the guild's good graces.");
    }
}
