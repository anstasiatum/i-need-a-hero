package herocreationtests.raceoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.userinputhandler.commands.createnewhero.SetDraconicAncestry.setDraconicAncestry;

public class SetDraconicAncestryTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setConstitution(13);
    }

    @Test
    @DisplayName("Black dragon")
    void setAncestryForBlackDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Acid, 5 by 30 ft. line (Dex save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to acid.";

        assertEquals(expectedResult, setDraconicAncestry("black", dndCharacter));
    }

    @Test
    @DisplayName("Blue dragon")
    void setAncestryForBlueDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Lightning, 5 by 30 ft. line (Dex save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to lightning.";

        assertEquals(expectedResult, setDraconicAncestry("blue", dndCharacter));
    }

    @Test
    @DisplayName("Brass dragon")
    void setAncestryForBrassDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Fire, 5 by 30 ft. line (Dex save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to fire.";

        assertEquals(expectedResult, setDraconicAncestry("brass", dndCharacter));
    }

    @Test
    @DisplayName("Bronze dragon")
    void setAncestryForBronzeDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Lightning, 5 by 30 ft. line (Dex save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to lightning.";

        assertEquals(expectedResult, setDraconicAncestry("bronze", dndCharacter));
    }

    @Test
    @DisplayName("Copper dragon")
    void setAncestryForCopperDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Acid, 5 by 30 ft. line (Dex save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to acid.";

        assertEquals(expectedResult, setDraconicAncestry("copper", dndCharacter));
    }

    @Test
    @DisplayName("Gold dragon")
    void setAncestryForGoldDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Fire, 15 ft. cone (Dex save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to fire.";

        assertEquals(expectedResult, setDraconicAncestry("gold", dndCharacter));
    }

    @Test
    @DisplayName("Green dragon")
    void setAncestryForGreenDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Poison, 15 ft. cone (Con save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to poison.";

        assertEquals(expectedResult, setDraconicAncestry("green", dndCharacter));
    }

    @Test
    @DisplayName("Red dragon")
    void setAncestryForRedDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Fire, 15 ft. cone (Dex save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to fire.";

        assertEquals(expectedResult, setDraconicAncestry("red", dndCharacter));
    }

    @Test
    @DisplayName("Silver dragon")
    void setAncestryForSilverDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Cold, 15 ft. cone (Con save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to cold.";

        assertEquals(expectedResult, setDraconicAncestry("silver", dndCharacter));
    }

    @Test
    @DisplayName("White dragon")
    void setAncestryForWhiteDragon() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Cold, 15 ft. cone (Con save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to cold.";

        assertEquals(expectedResult, setDraconicAncestry("white", dndCharacter));
    }

    @Test
    @DisplayName("Trim test")
    void setAncestryForWhiteDragonWithTrim() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: Cold, 15 ft. cone (Con save). The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to cold.";

        assertEquals(expectedResult, setDraconicAncestry("White ", dndCharacter));
    }

    @Test
    @DisplayName("Wrong input")
    void setAncestryWithWrongInput() {
        String expectedResult = "Breath Weapon \nYou can use your action to exhale destructive energy: ERROR DURING CONFIGURATION The DC for this saving throw is 11. A creature takes 2d6 damage on a failed save, and half as much damage on a successful one. After you use your breath weapon, you can’t use it again until you complete a short or long rest.\n Damage Resistance \n You have resistance to error during configuration .";

        assertEquals(expectedResult, setDraconicAncestry("test", dndCharacter));
    }
}
