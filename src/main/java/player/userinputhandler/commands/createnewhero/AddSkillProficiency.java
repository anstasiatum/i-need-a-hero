package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Skills;

public class AddSkillProficiency {
    public static void addSkillProficiency(DndCharacter dndCharacter, String userAnswer) {
        switch (userAnswer) {
            case "Survival":
                dndCharacter.getSkillsWithProficiency().add(Skills.SURVIVAL);
                break;
            case "Stealth":
                dndCharacter.getSkillsWithProficiency().add(Skills.STEALTH);
                break;
            case "Sleight of hand":
                dndCharacter.getSkillsWithProficiency().add(Skills.SLEIGHT_OF_HAND);
                break;
            case "Religion":
                dndCharacter.getSkillsWithProficiency().add(Skills.RELIGION);
                break;
            case "Persuasion":
                dndCharacter.getSkillsWithProficiency().add(Skills.PERSUASION);
                break;
            case "Performance":
                dndCharacter.getSkillsWithProficiency().add(Skills.PERFORMANCE);
                break;
            case "Perception":
                dndCharacter.getSkillsWithProficiency().add(Skills.PERCEPTION);
                break;
            case "Nature":
                dndCharacter.getSkillsWithProficiency().add(Skills.NATURE);
                break;
            case "Medicine":
                dndCharacter.getSkillsWithProficiency().add(Skills.MEDICINE);
                break;
            case "Investigation":
                dndCharacter.getSkillsWithProficiency().add(Skills.INVESTIGATION);
                break;
            case "Intimidation":
                dndCharacter.getSkillsWithProficiency().add(Skills.INTIMIDATION);
                break;
            case "Insight":
                dndCharacter.getSkillsWithProficiency().add(Skills.INSIGHT);
                break;
            case "History":
                dndCharacter.getSkillsWithProficiency().add(Skills.HISTORY);
                break;
            case "Deception":
                dndCharacter.getSkillsWithProficiency().add(Skills.DECEPTION);
                break;
            case "Athletics":
                dndCharacter.getSkillsWithProficiency().add(Skills.ATHLETICS);
                break;
            case "Arcana":
                dndCharacter.getSkillsWithProficiency().add(Skills.ARCANA);
                break;
            case "Animal handling":
                dndCharacter.getSkillsWithProficiency().add(Skills.ANIMAL_HANDLING);
                break;
            case "Acrobatics":
                dndCharacter.getSkillsWithProficiency().add(Skills.ACROBATICS);
                break;
            default:
                throw new IllegalArgumentException("Wrong input");
        }
    }
}
