import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("testcaseConverter")
@ApplicationScoped
public class TestcaseConverter implements Converter {

    @Inject
    TestrunController testrunController;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            Long id = Long.parseLong(value); // Umwandlung von String in Long
            return testrunController.getTestcaseById(id); // Aufruf der Methode im TestrunController
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Testcase) {
            return String.valueOf(((Testcase) value).getId());
        } else {
            throw new IllegalArgumentException("Value is not of type Testcase: " + value);
        }
    }

}

