import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class TestrunController implements Serializable {

    private String name;
    private String status;
    private String selectedTester;
    private List<Testcase> selectedTestcases = new ArrayList<>();
    private Testcase selectedTestcase;

    private Testrun testrun = new Testrun();


    private final EntityManager em;


    public TestrunController() {
        em = Persistence.createEntityManagerFactory("requireForTesting").createEntityManager();
    }

    public List<String> getAllTesters() {
        List<User> tester = em.createQuery("SELECT u FROM User u WHERE u.role = 'tester'", User.class).getResultList();
        List<String> testerAsString = new ArrayList<>();
        for(User u : tester) {
            testerAsString.add(u.getUsername());
        }
        return testerAsString;
    }

    public List<Testcase> getAllTestcases() {
        Query q = em.createQuery("Select tc from Testcase tc", Testcase.class);
        return q.getResultList();
    }

    public List<Testrun> getAllTestruns() {
        return em.createQuery("SELECT tr FROM Testrun tr", Testrun.class).getResultList();
    }


    public void saveTestrun() {

        // Testrun speichern
        Testrun testrun = new Testrun(name, status, selectedTester, selectedTestcases);
        em.getTransaction().begin();
        em.persist(testrun);
        em.getTransaction().commit();

        // Felder zurücksetzen
        name = null;
        status = null;
        selectedTester = null;
        selectedTestcases = null;
    }

    public void addTestcase() {
        if (selectedTestcases == null) {
            selectedTestcases = new ArrayList<>();
        }
        if (selectedTestcase != null && !selectedTestcases.contains(selectedTestcase)) {
            selectedTestcases.add(selectedTestcase);
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

    public List<Testcase> getSelectedTestcases() {
        return selectedTestcases;
    }

    public void setSelectedTestcases(List<Testcase> selectedTestcases) {
        this.selectedTestcases = selectedTestcases;
    }


    public Testcase getSelectedTestcase() {
        return selectedTestcase;
    }

    public void setSelectedTestcase(Testcase selectedTestcase) {
        this.selectedTestcase = selectedTestcase;
    }

    public Testrun getTestrun() {
        return testrun;
    }

    public void setTestrun(Testrun testrun) {
        this.testrun = testrun;
    }
}

