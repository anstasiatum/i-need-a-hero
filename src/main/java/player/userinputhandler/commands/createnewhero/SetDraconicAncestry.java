package player.userinputhandler.commands.createnewhero;

import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.DraconicAncestry;

public class SetDraconicAncestry {
    public static String setDraconicAncestry(String userAnswer, DndCharacter dndCharacter) {
        String damageType;
        String breathWeapon;
        String damageLine = "5 by 30 ft. line (Dex save). ";
        String damageConeDexSave = "15 ft. cone (Dex save). ";
        String damageConeConSave = "15 ft. cone (Con save). ";
        String error = "ERROR DURING CONFIGURATION ";
        int dc = 8 + dndCharacter.getConstitutionModifier() + dndCharacter.getProficiencyBonus();
        DraconicAncestry draconicAncestry;
        try {
            draconicAncestry = parseDraconicAncestry(userAnswer);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid draconic ancestry option");
        }

        switch (draconicAncestry) {
            case BLACK, COPPER -> {
                damageType = "Acid";
                breathWeapon = damageType + ", " + damageLine;
            }
            case BLUE, BRONZE -> {
                damageType = "Lightning";
                breathWeapon = damageType + ", " + damageLine;
            }
            case BRASS -> {
                damageType = "Fire";
                breathWeapon = damageType + ", " + damageLine;
            }
            case GOLD, RED -> {
                damageType = "Fire";
                breathWeapon = damageType + ", " + damageConeDexSave;
            }
            case GREEN -> {
                damageType = "Poison";
                breathWeapon = damageType + ", " + damageConeConSave;
            }
            case SILVER, WHITE -> {
                damageType = "Cold";
                breathWeapon = damageType + ", " + damageConeConSave;
            }
            default -> {
                damageType = error;
                breathWeapon = error;
            }
        }
        return "Breath Weapon \nYou can use your action to exhale destructive energy: " + breathWeapon + "The DC for this saving throw is " + dc + ". A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to " + damageType.toLowerCase() + ".";
    }

    public static DraconicAncestry parseDraconicAncestry(String input) {
        String normalized = input.trim().toLowerCase();
        for (DraconicAncestry draconicAncestry : DraconicAncestry.values()) {
            if (draconicAncestry.getDisplayName().toLowerCase().equals(normalized)) {
                return draconicAncestry;
            }
        }
        throw new IllegalArgumentException("Cannot parse user input into an existent draconic ancestry");
    }
}
