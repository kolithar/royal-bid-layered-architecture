package lk.ijse.gdse71.royalbid.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomePageController {

    @FXML
    private ImageView AssetsCustomer;

    @FXML
    private AnchorPane HomePageAnch;

    @FXML
    private Button Bidders;

    @FXML
    private Button LogOut;

    @FXML
    void LogoutAction(ActionEvent event) {
        navigateTo("/View/LoginPage.fxml");
    }

    @FXML
    void OnAction(MouseEvent event) {

    }
    @FXML
    private Button Evaluator;

    @FXML
    void OnActionBiders(MouseEvent event) {
        navigateTo("/View/BiddersManage.fxml");
    }

    @FXML
    void OnActionCutomer(MouseEvent event) {
        navigateTo("/View/AssetsAndCustomerManeagePage.fxml");
    }

    @FXML
    void OnActionFinance(MouseEvent event) {
        navigateTo("/View/FinancePage.fxml");
    }

    @FXML
    void EvaluatorNgAction(ActionEvent event) {
        navigateTo("/View/AssetsANDEvaluatorePage.fxml");
    }


    @FXML
    void BidderNgAction(ActionEvent event) {
        navigateTo("/View/BiddersAndAssets.fxml");
    }

    @FXML
    void OnActionLegal(MouseEvent event) {
        navigateTo("/View/LegalDocPage.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            HomePageAnch.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            HomePageAnch.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui !").show();
        }
    }



}
