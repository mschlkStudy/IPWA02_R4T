import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TestcaseController implements Serializable {

    private final EntityManager em;
    private String name;
    private String task;
    private String result;

    private Requirement selectedRequirement;

    public TestcaseController() {
        em = Persistence.createEntityManagerFactory("requireForTesting").createEntityManager();
    }
    public void saveTestcase(String result, String name, String task) {
        Testcase ts = new Testcase(result, name, task);
        selectedRequirement.addTestcase(ts);
        em.getTransaction().begin();
        em.persist(ts);
        em.merge(selectedRequirement);
        em.getTransaction().commit();

    }

    public long getOpenCount() {
        return em.createQuery("SELECT COUNT(t) FROM Testcase t WHERE t.result = 'Offen'", Long.class).getSingleResult();
        }
    public long getInProgressCount() {
        return em.createQuery("SELECT COUNT(t) FROM Testcase t WHERE t.result = 'In Arbeit'", Long.class).getSingleResult();
    }
    public long getCompletedCount() {
        return em.createQuery("SELECT COUNT(t) FROM Testcase t WHERE t.result = 'Erledigt'", Long.class).getSingleResult();
    }

    public void updateTestcaseResult(Testcase testcase) {
        em.getTransaction().begin();
        Testcase managedTestcase = em.find(Testcase.class, testcase.getId());
        managedTestcase.setResult(testcase.getResult());
        em.getTransaction().commit();
    }


    public List<Testcase> getAllTestcases() {
        Query q = em.createQuery("SELECT tc from Testcase tc");
        return q.getResultList();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Requirement getSelectedRequirement() {
        return selectedRequirement;
    }

    public void setSelectedRequirement(Requirement selectedRequirement) {
        this.selectedRequirement = selectedRequirement;
    }
}
