package herodeletiontests.testdata;

import player.dndcharacter.DndCharacter;
import player.userinputhandler.commands.db.Character;

public class TestData {
    public static Character mockCharacterAna = new Character();
    public static DndCharacter mockDNDCharacterAna = new DndCharacter();
    public static Character mockCharacterHanzo = new Character();
    public static DndCharacter mockDNDCharacterHanzo = new DndCharacter();
    public static final Long chatID = 123L;

    public static Character getMockCharacterAna() {
        mockDNDCharacterAna.setCharacterName("Ana Amari");

        mockCharacterAna.setId(31);
        mockCharacterAna.setChatId(chatID);
        mockCharacterAna.setDndCharacter(mockDNDCharacterAna);

        return mockCharacterAna;
    }

    public static Character getMockCharacterHanzo() {
        mockDNDCharacterHanzo.setCharacterName("Hanzo Shimada");

        mockCharacterHanzo.setId(32);
        mockCharacterHanzo.setChatId(chatID);
        mockCharacterHanzo.setDndCharacter(mockDNDCharacterHanzo);

        return mockCharacterHanzo;
    }
}
