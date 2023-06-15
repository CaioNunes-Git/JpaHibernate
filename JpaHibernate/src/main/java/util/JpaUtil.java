package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("clinica");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
