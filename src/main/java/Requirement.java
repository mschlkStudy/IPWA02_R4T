import jakarta.persistence.*;
import org.hibernate.mapping.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Requirement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "requirement", cascade = CascadeType.ALL)
    private List<Testcase> testcases;


    public Requirement() {

    }
    public Requirement(String description, String title) {
        this.description = description;
        this.title = title;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Testcase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<Testcase> testcases) {
        this.testcases = testcases;
    }
    public void addTestcase(Testcase testcase) {

    }
}
