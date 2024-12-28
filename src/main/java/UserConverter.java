import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = User.class)
public class UserConverter implements Converter<User> {

    @Override
    public String getAsString(FacesContext context, UIComponent component, User user) {
        if (user == null) {
            return "";
        }
        return String.valueOf(user.getId()); // Hier die eindeutige ID als String zurückgeben
    }

    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        // User anhand der ID laden (z. B. aus der Datenbank oder einem Service)
        Long userId = Long.valueOf(value);
        return RequireForTestingManagement.getUserById(userId); // Implementierung von UserService erforderlich
    }
}
