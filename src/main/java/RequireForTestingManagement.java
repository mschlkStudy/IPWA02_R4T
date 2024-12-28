import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class RequireForTestingManagement implements Serializable {
    private static EntityManager em;

    public RequireForTestingManagement() {
        em = Persistence.createEntityManagerFactory("requireForTesting").createEntityManager();
        createTables();
    }

    public static User getUserById(Long userId) {
        return em.find(User.class, userId);
    }

    public List<User> getAllUsers() {
        Query q = em.createQuery("Select u From User u");
        return q.getResultList();
    }

    public void createTables() {
        if(getAllUsers().isEmpty()) {
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO users (username, password, role) VALUES ('tester', 'testerPass', 'tester')").executeUpdate();
            em.createNativeQuery("INSERT INTO users (username, password, role) VALUES ('testManager', 'testManagerPass', 'testManager')").executeUpdate();
            em.createNativeQuery("INSERT INTO users (username, password, role) VALUES ('requirementEngineer', 'requirementEngineerPass', 'requirementEngineer')").executeUpdate();
            em.createNativeQuery("INSERT INTO users (username, password, role) VALUES ('testcaseCreator', 'testcaseCreatorPass', 'testcaseCreator')").executeUpdate();
            em.getTransaction().commit();
        }
    }
}
