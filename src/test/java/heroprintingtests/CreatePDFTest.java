package heroprintingtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.printhero.PDFCreator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static testdata.TestData.getMockCharacterAna;

public class CreatePDFTest {
    private final Long chatID = 123L;
    private final int characterID = 1;
    private final CharacterDao characterDaoMock = mock(CharacterDao.class);
    private final PDFCreator createPDF = new PDFCreator(characterDaoMock);

    @Test
    @DisplayName("Successful PDF generation")
    void successfulPDFGeneration() {
        Character character = getMockCharacterAna();
        when(characterDaoMock.findByCharacterId(characterID, chatID)).thenReturn(character);

        assertTrue(createPDF.createPDF(characterID, chatID).getName().contains("DndCharacter-Ana Amari"));
        assertTrue(createPDF.createPDF(characterID, chatID).getName().contains(".pdf"));
    }

    @Test
    @DisplayName("PDF generation: Character not found")
    void PDFGenerationCharacterNotFound() {
        when(characterDaoMock.findByCharacterId(characterID, chatID)).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> createPDF.createPDF(characterID, chatID));
    }
}
