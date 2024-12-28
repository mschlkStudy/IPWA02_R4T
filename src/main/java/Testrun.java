import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Testrun implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    @OneToMany
    private List<Testcase> assignedTestcases;

    private String status;
    private String tester;

    public Testrun() {

    }

    public Testrun(String name, String status,  String tester, List<Testcase> testcases) {
        this.name = name;
        this.status = status;
        this.tester = tester;
        this.assignedTestcases = testcases;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getTester() {
        return tester;
    }
    public void setTester(String tester) {
        this.tester = tester;
    }

    public List<Testcase> getAssignedTestcases() {
        return assignedTestcases;
    }

    public void setAssignedTestcases(List<Testcase> assignedTestcases) {
        this.assignedTestcases = assignedTestcases;
    }
}
