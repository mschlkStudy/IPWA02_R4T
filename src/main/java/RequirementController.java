import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RequirementController implements Serializable {

    private String description;
    private String title;
    private EntityManager em;
    public RequirementController() {
        em = Persistence.createEntityManagerFactory("requireForTesting").createEntityManager();
    }

    public void saveRequirement(String description, String title){
        Requirement re = new Requirement(description, title);
        em.getTransaction().begin();
        em.persist(re);
        em.getTransaction().commit();
    }

    public List<Requirement> getAllRequirements() {
        Query q = em.createQuery("Select re From Requirement re");
        return q.getResultList();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
