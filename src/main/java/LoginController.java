import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {
    @Inject
    RequireForTestingManagement rftManagement;

    private String username;
    private String password;
    public LoginController() {

    }

    public String login(){
        User u = authenticate(username, password);
        if(u != null) {
            if (u.getRole().equals("tester")) {
                return "tester.xhtml?faces-redirect=true";
            }
            if (u.getRole().equals("testManager")) {
                return "testmanager.xhtml?faces-redirect=true";
            }
            if (u.getRole().equals("requirementEngineer")) {
                return "requirementsEngineer.xhtml?faces-redirect=true";
            }
            if (u.getRole().equals("testcaseCreator")) {
                return "testcasecreator.xhtml?faces-redirect=true";
            }
        }
        return "Fehler bei der Anmeldung";

    }

    public String logout() {
        // Sitzung invalidieren
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        // Zurück zur Login-Seite
        return "login.xhtml?faces-redirect=true";
    }

    public User authenticate(String username, String password) {
        for (User u : rftManagement.getAllUsers()) {
            if(username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                return u;
            }
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
