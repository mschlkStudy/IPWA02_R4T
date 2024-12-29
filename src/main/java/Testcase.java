import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Testcase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String result;
    private String name;
    private String task;

    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private Requirement requirement;

    public Testcase() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Testcase testcase = (Testcase) obj;
        return Objects.equals(id, testcase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Testcase(String result, String name, String task) {
        this.result = result;
        this.name = name;
        this.task = task;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }
}
