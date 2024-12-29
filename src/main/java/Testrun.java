import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Testrun implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;



    private String testcaseIds;

    private String status;
    private String tester;

    public Testrun() {

    }

    public Testrun(String name, String status,  String tester, String testcaseIds) {
        this.name = name;
        this.status = status;
        this.tester = tester;
        this.testcaseIds = testcaseIds;
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

    public String getTestcaseIds() {
        return testcaseIds;
    }

    public void setTestcaseIds(String testcaseIds) {
        this.testcaseIds = testcaseIds;
    }
}
