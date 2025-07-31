package herocreationtests.raceoptions;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.dndcharacter.DndCharacter;
import player.dndcharacter.race.dragonborn.Dragonborn;
import player.userinputhandler.Response;
import player.userinputhandler.State;
import player.userinputhandler.commands.createnewhero.SelectRace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static player.dndcharacter.dndcharacterenums.Race.DRAGONBORN;
import static player.userinputhandler.commands.createnewhero.Options.getDraconicAncestryOptions;
import static player.userinputhandler.enums.Processes.CREATE_HERO;
import static player.userinputhandler.enums.Steps.CHOOSE_DRACONIC_ANCESTRY;

public class SelectRaceTest {
    private final DndCharacter dndCharacter = new DndCharacter();
    private Response actualResponse;
    private State incomingState;
    private Response expectedResponse;
    private final Faker faker = new Faker();
    private final String userAnswerWithSpecialSymbols = "! @ # $ % ^ & * ( ) - _ = + [ ] { } ; : ' \" , . / ? \\ | £ $ ¥ ¢ € + - * / = < > ≤ ≥ ± ∓ © ® ™ § ° é à ç ö ♥ 1";
    private final String userAnswerNumeric = String.valueOf(faker.number().numberBetween(3, 18));
    private final String userAnswerText = faker.harryPotter().quote();
    private final SelectRace selectRace = new SelectRace();
    private final Dragonborn dragonbornSpy = spy(new Dragonborn());

    @Test
    @DisplayName("CHOOSE_RACE -> starts selectRace()")
    void selectRaceDragonborn() {
        expectedResponse = new Response(new State(CREATE_HERO, CHOOSE_DRACONIC_ANCESTRY, dndCharacter), "Enter your draconic ancestry", getDraconicAncestryOptions());

        actualResponse = selectRace.selectRace("dragonborn", dndCharacter);

        verify(dragonbornSpy, times(1)).modifyByRace(dndCharacter);
        assertEquals(DRAGONBORN, dndCharacter.getRace());
        assertEquals(expectedResponse, actualResponse);
    }
}
