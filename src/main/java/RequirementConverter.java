import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.List;

@FacesConverter(forClass = Requirement.class)
public class RequirementConverter implements Converter<Requirement> {

    private RequirementController requirementController;

    public RequirementConverter() {
        // Zugriff auf den RequirementController (z. B. via CDI)
        FacesContext context = FacesContext.getCurrentInstance();
        requirementController = context.getApplication()
                .evaluateExpressionGet(context, "#{requirementController}", RequirementController.class);
    }

    @Override
    public Requirement getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        // Findet das Requirement anhand der ID
        List<Requirement> requirements = requirementController.getAllRequirements();
        return requirements.stream()
                .filter(r -> r.getId() == Integer.parseInt(value))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Requirement requirement) {
        if (requirement == null) {
            return "";
        }
        return String.valueOf(requirement.getId());
    }
}
