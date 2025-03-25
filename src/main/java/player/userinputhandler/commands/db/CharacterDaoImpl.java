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

    public void delete(Integer characterId) {
        HibernateHelper.runInTransaction(session -> {
            session.createNativeQuery("delete from characters where id = :id")
                    .setParameter("id", characterId)
                    .executeUpdate();
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
    public Character findById(Integer characterId) {
        return (Character) HibernateHelper.runInTransaction(entityManager -> {
            return entityManager.createQuery("select character from Character character where character.id = :id")
                    .setParameter("id", characterId)
                    .getSingleResult();
        });
    }
}
