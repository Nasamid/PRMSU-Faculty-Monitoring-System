import com.formdev.flatlaf.themes.FlatMacLightLaf;
public class FacultyManager {
    public static void main(String[] args) throws Exception {
        FlatMacLightLaf.registerCustomDefaultsSource("Properties");
        FlatMacLightLaf.setup();
        new LoginWindow();
    }
}