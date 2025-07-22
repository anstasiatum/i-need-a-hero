package player.userinputhandler.commands.db;

import player.HibernateHelper;

import java.util.List;

public class CharacterDaoImpl implements CharacterDao {
    @Override
    public void save(Character dndCharacter) {
        HibernateHelper.runInTransaction(entityManager -> {
            if (dndCharacter.getId() == null) {
                Character character = entityManager.merge(dndCharacter);
                System.out.println(character.getId());
            } else {
                entityManager.persist(dndCharacter);
            }
        });
    }

    public void deleteByCharacterId(Integer characterId, Long chatId) {
        HibernateHelper.runInTransaction(session -> {
            int updatedEntity = session.createNativeQuery("delete from characters where id = :id AND chat_id = :chatId")
                    .setParameter("id", characterId)
                    .setParameter("chatId", chatId)
                    .executeUpdate();

            if (updatedEntity == 0) {
                throw new RuntimeException();
            }
        });
    }

    @Override
    public List<Character> findByChatId(Long chatId) {
        return HibernateHelper.runInTransaction(entityManager -> {
            return entityManager.createQuery("select character from Character character where character.chatId = :chatId")
                    .setParameter("chatId", chatId)
                    .getResultList();
        });
    }

    @Override
    public Character findByCharacterId(Integer characterId, Long chatId) {
        return (Character) HibernateHelper.runInTransaction(entityManager -> {
            return entityManager.createQuery("select character from Character character where character.id = :id AND character.chatId = :chatId")
                    .setParameter("id", characterId)
                    .setParameter("chatId", chatId)
                    .getSingleResult();
        });
    }
}
