package herocreationtests.raceoptions.increasebasecharacteristics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.dndcharacterenums.Characteristics;
import player.userinputhandler.commands.createnewhero.increasebasecharacteristics.IncrementAbility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static player.dndcharacter.dndcharacterenums.Characteristics.CHARISMA;
import static player.dndcharacter.dndcharacterenums.Characteristics.CONSTITUTION;
import static player.dndcharacter.dndcharacterenums.Characteristics.DEXTERITY;
import static player.dndcharacter.dndcharacterenums.Characteristics.INTELLIGENCE;
import static player.dndcharacter.dndcharacterenums.Characteristics.STRENGTH;
import static player.dndcharacter.dndcharacterenums.Characteristics.WISDOM;

public class IncrementAbilityTest {

    private IncrementAbility incrementAbility;
    private DndCharacter dndCharacter;

    @BeforeEach
    void createCharacter() {
        incrementAbility = new IncrementAbility();
        dndCharacter = new DndCharacter();
        dndCharacter.setStrength(10);
        dndCharacter.setDexterity(10);
        dndCharacter.setConstitution(10);
        dndCharacter.setIntelligence(10);
        dndCharacter.setWisdom(10);
        dndCharacter.setCharisma(10);
    }

    @ParameterizedTest(name = "Increase {0} test")
    @EnumSource(Characteristics.class)
    @DisplayName("Test increment ability - parametrized")
    void testIncrementAbility(Characteristics characteristic) {

        incrementAbility.incrementAbility(characteristic, dndCharacter);

        switch (characteristic) {
            case STRENGTH:
                assertEquals(11, dndCharacter.getStrength());
                assertEquals(10, dndCharacter.getDexterity());
                break;
            case DEXTERITY:
                assertEquals(11, dndCharacter.getDexterity());
                assertEquals(10, dndCharacter.getStrength());
                break;
            case CONSTITUTION:
                assertEquals(11, dndCharacter.getConstitution());
                break;
            case INTELLIGENCE:
                assertEquals(11, dndCharacter.getIntelligence());
                break;
            case WISDOM:
                assertEquals(11, dndCharacter.getWisdom());
                break;
            case CHARISMA:
                assertEquals(11, dndCharacter.getCharisma());
                break;
        }

        if (characteristic != STRENGTH) assertEquals(10, dndCharacter.getStrength());
        if (characteristic != DEXTERITY) assertEquals(10, dndCharacter.getDexterity());
        if (characteristic != CONSTITUTION) assertEquals(10, dndCharacter.getConstitution());
        if (characteristic != INTELLIGENCE) assertEquals(10, dndCharacter.getIntelligence());
        if (characteristic != WISDOM) assertEquals(10, dndCharacter.getWisdom());
        if (characteristic != CHARISMA) assertEquals(10, dndCharacter.getCharisma());
    }
}
