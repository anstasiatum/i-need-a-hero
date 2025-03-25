package player;

import jakarta.persistence.EntityManager;
import lombok.experimental.UtilityClass;
import org.hibernate.cfg.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@UtilityClass
public class HibernateHelper {

    private static final Configuration CONFIG = new Configuration().configure();

    public EntityManager getEntityManager() {
        return CONFIG.buildSessionFactory().createEntityManager();
    }

    public void runInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        entityManager.getTransaction().begin();
        consumer.accept(entityManager);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public <T> T runInTransaction(Function<EntityManager, T> consumer) {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        entityManager.getTransaction().begin();
        T result = consumer.apply(entityManager);
        entityManager.getTransaction().commit();
        return result;
    }
}