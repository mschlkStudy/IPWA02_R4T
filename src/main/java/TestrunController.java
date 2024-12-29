import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class TestrunController implements Serializable {

    private String name;
    private String status;
    private String selectedTester;
    private List<Long> selectedTestcaseIds = new ArrayList<>();
    private Long selectedTestcaseId;

    private Testrun testrun = new Testrun();


    private final EntityManager em;

    public TestrunController() {
        em = Persistence.createEntityManagerFactory("requireForTesting").createEntityManager();
    }

    public List<String> getAllTesters() {
        return em.createQuery("SELECT u.username FROM User u WHERE u.role = 'tester'", String.class).getResultList();
    }


    public List<Testcase> getAllTestcases() {
        Query q = em.createQuery("Select tc from Testcase tc", Testcase.class);
        return q.getResultList();
    }

    public List<Testrun> getAllTestruns() {
        return em.createQuery("SELECT tr FROM Testrun tr", Testrun.class).getResultList();
    }



    public void saveTestrun() {
        try {
            em.getTransaction().begin();

            // Konvertiere die Liste der Testcase-IDs in eine kommagetrennte Zeichenkette
            String testcaseIds = selectedTestcaseIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            Testrun testrun = new Testrun(name, status, selectedTester, testcaseIds);
            em.persist(testrun);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
        resetFields();
    }

    private void resetFields() {
        name = null;
        status = null;
        selectedTester = null;
        selectedTestcaseIds = null;
    }

    public void addTestcase() {
        if (selectedTestcaseId != null && !selectedTestcaseIds.contains(selectedTestcaseId)) {
            selectedTestcaseIds.add(selectedTestcaseId);
        }
    }



    public Testcase getTestcaseById(Long id) {
        for(Testcase tc : getAllTestcases()) {
            if(tc.getId().equals(id)) {
                return tc;
            }
        }
        return null;
    }

    public List<String> getSelectedTestcaseNames() {
        if(selectedTestcaseIds == null || selectedTestcaseIds.isEmpty()) {
            return List.of();
        }
        return selectedTestcaseIds.stream()
                .map(this::getTestcaseById) // Holt das Testcase-Objekt basierend auf der ID
                .filter(Objects::nonNull)
                .map(Testcase::getName) // Holt den Namen des Testcases
                .toList();
    }

    public String getSelectedTestcaseNamesAsString() {
        return String.join(", ", getSelectedTestcaseNames());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSelectedTester() {
        return selectedTester;
    }

    public void setSelectedTester(String selectedTester) {
        this.selectedTester = selectedTester;
    }

    public List<Long> getSelectedTestcaseIds() {
        return selectedTestcaseIds;
    }

    public void setSelectedTestcaseIds(List<Long> selectedTestcaseIds) {
        this.selectedTestcaseIds = selectedTestcaseIds;
    }

    public Long getSelectedTestcaseId() {
        return selectedTestcaseId;
    }

    public void setSelectedTestcaseId(Long selectedTestcaseId) {
        this.selectedTestcaseId = selectedTestcaseId;
    }

    public Testrun getTestrun() {
        return testrun;
    }

    public void setTestrun(Testrun testrun) {
        this.testrun = testrun;
    }
}

