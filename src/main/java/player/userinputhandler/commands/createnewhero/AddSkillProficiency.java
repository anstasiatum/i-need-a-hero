package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skills;

public class AddSkillProficiency {
    public static void addSkillProficiency(DndCharacter dndCharacter, String userAnswer) {

        switch (userAnswer.toLowerCase()) {
            case "survival":
                dndCharacter.getSkillsWithProficiency().add(Skills.SURVIVAL);
                break;
            case "stealth":
                dndCharacter.getSkillsWithProficiency().add(Skills.STEALTH);
                break;
            case "sleight of hand", "sleight_of_hand":
                dndCharacter.getSkillsWithProficiency().add(Skills.SLEIGHT_OF_HAND);
                break;
            case "religion":
                dndCharacter.getSkillsWithProficiency().add(Skills.RELIGION);
                break;
            case "persuasion":
                dndCharacter.getSkillsWithProficiency().add(Skills.PERSUASION);
                break;
            case "performance":
                dndCharacter.getSkillsWithProficiency().add(Skills.PERFORMANCE);
                break;
            case "perception":
                dndCharacter.getSkillsWithProficiency().add(Skills.PERCEPTION);
                break;
            case "nature":
                dndCharacter.getSkillsWithProficiency().add(Skills.NATURE);
                break;
            case "medicine":
                dndCharacter.getSkillsWithProficiency().add(Skills.MEDICINE);
                break;
            case "investigation":
                dndCharacter.getSkillsWithProficiency().add(Skills.INVESTIGATION);
                break;
            case "intimidation":
                dndCharacter.getSkillsWithProficiency().add(Skills.INTIMIDATION);
                break;
            case "insight":
                dndCharacter.getSkillsWithProficiency().add(Skills.INSIGHT);
                break;
            case "history":
                dndCharacter.getSkillsWithProficiency().add(Skills.HISTORY);
                break;
            case "deception":
                dndCharacter.getSkillsWithProficiency().add(Skills.DECEPTION);
                break;
            case "athletics":
                dndCharacter.getSkillsWithProficiency().add(Skills.ATHLETICS);
                break;
            case "arcana":
                dndCharacter.getSkillsWithProficiency().add(Skills.ARCANA);
                break;
            case "animal handling", "animal_handling":
                dndCharacter.getSkillsWithProficiency().add(Skills.ANIMAL_HANDLING);
                break;
            case "acrobatics":
                dndCharacter.getSkillsWithProficiency().add(Skills.ACROBATICS);
                break;
            default:
                throw new IllegalArgumentException("Wrong input");
        }
    }
}
