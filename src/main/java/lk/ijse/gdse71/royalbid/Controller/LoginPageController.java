package lk.ijse.gdse71.royalbid.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class LoginPageController {

    @FXML
    private AnchorPane LoginPageAnch;

    @FXML
    private ImageView MainPageImage;

    @FXML
    private TextField Nametxt;

    @FXML
    private TextField Paaswordtxt;

    @FXML
    private ImageView PassWordImage;

    @FXML
    private ImageView UserNmaeImage;

    @FXML
    void OnAction(ActionEvent event) {
        String username = Nametxt.getText().trim();
        String password = Paaswordtxt.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields!").show();
            return;
        }

        // Add authentication logic here
        if (isValidUser(username, password)) {
            navigateTo("/View/HomePage.fxml");
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
        }
    }

    private boolean isValidUser(String username, String password) {
        // Mock authentication logic (replace with actual database or service call)
        return "admin".equals(username) && "1234".equals(password);
    }


    private void navigateTo(String fxmlPath) {
        try {
            LoginPageAnch.getChildren().clear();
            // Use the correct absolute path starting from the root of the resources folder
            AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            LoginPageAnch.getChildren().add(load);
        } catch (NullPointerException e) {
            System.out.println("FXML file not found at path: " + fxmlPath);
            new Alert(Alert.AlertType.ERROR, "FXML file not found!").show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Failed to load UI!").show();
        }
    }

}
