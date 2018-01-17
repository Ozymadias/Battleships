package battleships;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;

class AlertWithProgressIndicator extends Alert {

  private AlertWithProgressIndicator(AlertType alertType) {
    super(alertType);
  }

  static AlertWithProgressIndicator asInstance(Alert.AlertType alertType,
                                               String headerText, String contentText) {
    AlertWithProgressIndicator alert = new AlertWithProgressIndicator(alertType);
    alert.setHeaderText(headerText);
    alert.getDialogPane().setContent(createContentGridPane(contentText));
    alert.getButtonTypes().clear();
    alert.getButtonTypes().add(new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE));
    return alert;
  }

  private static GridPane createContentGridPane(String contentText) {
    GridPane expContent = new GridPane();
    expContent.setHgap(20);
    expContent.setVgap(20);
    expContent.setMaxWidth(Double.MAX_VALUE);
    expContent.add(new Label(contentText), 0, 0);
    expContent.add(new ProgressIndicator(), 1, 0);
    return expContent;
  }


}
