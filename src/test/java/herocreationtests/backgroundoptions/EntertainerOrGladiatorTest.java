package herocreationtests.backgroundoptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static player.userinputhandler.commands.createnewhero.backgroundoptions.ChooseEntertainerOrGladiator.chooseEntertainerOrGladiator;

public class EntertainerOrGladiatorTest {
    DndCharacter dndCharacter = new DndCharacter();

    @BeforeEach
    public void createCharacter() {
        dndCharacter.setFeaturesAndTraits("test feature. ");
    }

    @Test
    @DisplayName("Set entertainer feature")
    void setEntertainerFeature() {
        chooseEntertainerOrGladiator("entertainer", dndCharacter);
        String expectedResult = "test feature. You can perform at inns, theaters, circuses, or any place with a stage. While you’re performing there each night, you receive free modest or comfortable lodging and food. This can allow you to take long rests for free as you travel with your party across the land. In addition, your performance makes you famous wherever you perform. When strangers recognize you in the town, they usually like you more. This may make it easier to persuade them to do things for you.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set gladiator feature")
    void setGladiatorFeature() {
        chooseEntertainerOrGladiator("gladiator", dndCharacter);
        String expectedResult = "test feature. You can find a place to perform in any place that features combat for entertainment-perhaps a gladiatorial arena or secret pit fighting club.\n";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set gladiator background")
    void setGladiatorBackground() {
        chooseEntertainerOrGladiator("gladiator", dndCharacter);

        assertEquals("gladiator", dndCharacter.getBackground());
    }

    @Test
    @DisplayName("Set entertainer background")
    void setEntertainerBackground() {
        chooseEntertainerOrGladiator("entertainer", dndCharacter);

        assertEquals("entertainer", dndCharacter.getBackground());
    }


    @Test
    @DisplayName("Set feature for wrong input")
    void setFeatureForWrongInput() {
        chooseEntertainerOrGladiator("test", dndCharacter);
        String expectedResult = "test feature. ";

        assertEquals(expectedResult, dndCharacter.getFeaturesAndTraits());
    }

    @Test
    @DisplayName("Set background for wrong input")
    void setBackgroundForWrongInput() {
        chooseEntertainerOrGladiator("test", dndCharacter);

        assertNull(dndCharacter.getBackground());
    }
}
