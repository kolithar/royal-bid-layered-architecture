package lk.ijse.gdse71.royalbid.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AssetsAndCustomerManageController {


    @FXML
    private Button Catogery;

    @FXML
    private Button Assets;

    @FXML
    private Button AuctionDetalis;

    @FXML
    private Button BackButtone;

    @FXML
    private Button EvaluatoreButtone;

    @FXML
    private AnchorPane Asst;

    @FXML
    private Button Customer;

    @FXML
    private AnchorPane NavigatePane;

    @FXML
    void Assetnavigate(ActionEvent event) {
        navigateTo("/View/AssetsPage.fxml");
    }

    @FXML
    void AuctionOnAction(ActionEvent event) {
        navigateTo("/View/AuctionDetalisPage.fxml");
    }


    @FXML
    void BackAction(ActionEvent event) {
        navigate("/View/HomePage.fxml");
    }

    @FXML
    void CatogeryAction(ActionEvent event) {
        navigateTo("/View/CatogeryPage.fxml");
    }

    @FXML
    void Custnaviget(ActionEvent event) {
        navigateTo("/View/CustomerPage.fxml");
    }

    @FXML
    void EvaluatoreAction(ActionEvent event) {
        navigateTo("/View/EvaluatorePage.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            NavigatePane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            NavigatePane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui !").show();
        }
    }

    private void navigate(String fxmlPath) {
        try {
            Asst.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            Asst.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui !").show();
        }
    }




}