package cacttus.education.cacttusproductcrud.infrastructure;

import javafx.scene.control.Alert;

public class AlertDialogHelper {
    public static void showAlert(String title, String heading, String content,
                                 Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(heading);
        alert.setContentText(content);
        alert.show();
    }
}
