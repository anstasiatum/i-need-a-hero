package player.userinputhandler.commands.db;

import java.util.List;

public interface CharacterDao {
    void save(Character dndCharacter);

    List<Character> findByChatId(Long chatId);

    void delete(Integer characterId);

    Character findById(Integer characterId);
}
