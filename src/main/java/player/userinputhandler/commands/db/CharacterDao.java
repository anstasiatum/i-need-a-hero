package player.userinputhandler.commands.db;

import java.util.List;

public interface CharacterDao {
    void save(Character dndCharacter);

    List<Character> findByChatId(Long chatId);

    void deleteByCharacterId(Integer characterId, Long chatId);

    Character findByCharacterId(Integer characterId, Long chatId);
}
