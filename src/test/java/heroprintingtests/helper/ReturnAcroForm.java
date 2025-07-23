package heroprintingtests.helper;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import player.userinputhandler.commands.db.Character;
import player.userinputhandler.commands.db.CharacterDao;
import player.userinputhandler.commands.printhero.PDFCreator;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static testdata.TestData.chatID;

public class ReturnAcroForm {
    private static final CharacterDao characterDaoMock = mock(CharacterDao.class);
    private static final PDFCreator pdfCreator = new PDFCreator(characterDaoMock);

    public static PDAcroForm returnAcroForm(Character character) {
        PDAcroForm acroForm;
        Integer characterID = 1;
        when(characterDaoMock.findByCharacterId(characterID, chatID)).thenReturn(character);
        File generatedFile = pdfCreator.createPDF(characterID, chatID);
        try (PDDocument document = Loader.loadPDF(new RandomAccessReadBufferedFile(generatedFile.getPath()))) {
            PDDocumentCatalog docCatalog = document.getDocumentCatalog();
            acroForm = docCatalog.getAcroForm();
            File tempFile = File.createTempFile("TESTDndCharacter-", ".pdf");
            document.save(tempFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return acroForm;
    }
}
