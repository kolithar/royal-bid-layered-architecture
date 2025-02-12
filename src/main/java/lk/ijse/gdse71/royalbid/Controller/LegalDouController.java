
package lk.ijse.gdse71.royalbid.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.royalbid.BO.Custom.LegalDouBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.LegalDouBOImpl;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;
import lk.ijse.gdse71.royalbid.DTO.LegalDouDto;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LegalDouController implements Initializable {

    @FXML
    private TableColumn<LegalDouDto, String> ColDocumantURL;

    @FXML
    private TableColumn<LegalDouDto, String> ColInvoiceId;

    @FXML
    private TableColumn<LegalDouDto, String> ColLegalDocumantId;

    @FXML
    private TableColumn<LegalDouDto, String> ColTypeofaw;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField DocUrl;

    @FXML
    private TextField InvoicTxt;

    @FXML
    private Label LegalDocId;

    @FXML
    private TableView<LegalDouDto> LegalDocTable;

    @FXML
    private TextField LowTypeTxt;


    @FXML
    private AnchorPane LegalPage;


    @FXML
    private Button SaveButton;

    @FXML
    private Button UpdateButton;

  

    LegalDouBO legalDouBO = new LegalDouBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configure table columns
        ColLegalDocumantId.setCellValueFactory(new PropertyValueFactory<>("LegalDocumentsId"));
        ColInvoiceId.setCellValueFactory(new PropertyValueFactory<>("InvoiceNumber"));
        ColDocumantURL.setCellValueFactory(new PropertyValueFactory<>("DocumentIdURL"));
        ColTypeofaw.setCellValueFactory(new PropertyValueFactory<>("TypeOFLaw"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load data").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextLegalDocId();
        loadTableData();

        SaveButton.setDisable(false);
        UpdateButton.setDisable(true);
        DeleteButton.setDisable(true);

        InvoicTxt.clear();
        DocUrl.clear();
        LowTypeTxt.clear();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<LegalDouDto> legalDocDTOS = legalDouBO.getAllDocuments();
        ObservableList<LegalDouDto> legalDocTMS = FXCollections.observableArrayList(
                legalDocDTOS.stream()
                        .map(legalDocDTO -> new LegalDouDto(
                                legalDocDTO.getLegalDocumentsId(),
                                legalDocDTO.getInvoiceNumber(),
                                legalDocDTO.getDocumentIdURL(),
                                legalDocDTO.getTypeOFLaw()))
                        .collect(Collectors.toList())
        );
        LegalDocTable.setItems(legalDocTMS);
    }

    private void loadNextLegalDocId() throws SQLException, ClassNotFoundException {
        String nextDocId = legalDouBO.getNextLegalDocId();
        LegalDocId.setText(nextDocId);
    }

    @FXML
    void SaveAction(ActionEvent event) {
        String legalDocID = LegalDocId.getText();
        String invoiceID = InvoicTxt.getText();
        String docURL = DocUrl.getText();
        String lowType = LowTypeTxt.getText();

        // Validate inputs
        if (!validateInputs(invoiceID, docURL, lowType)) {
            return;
        }

        try {

            LegalDouDto legalDouDto = new LegalDouDto(legalDocID, invoiceID, docURL, lowType);
            boolean isSaved = legalDouBO.saveLedalDoc(legalDouDto);

            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Legal Document saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Legal Document.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while saving the Legal Document.").show();
        }
    }

    private boolean validateInputs(String invoiceID, String docURL, String lowType) {
        boolean isValid = true;

        if (invoiceID.isEmpty() || !invoiceID.matches("^[A-Za-z0-9]+$")) {
            InvoicTxt.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            InvoicTxt.setStyle(null);
        }

        if (docURL.isEmpty() || !docURL.matches("^(http|https)://.*$")) {
            DocUrl.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            DocUrl.setStyle(null);
        }

        if (lowType.isEmpty() || !lowType.matches("^[A-Za-z ]+$")) {
            LowTypeTxt.setStyle("-fx-border-color: red;");
            isValid = false;
        } else {
            LowTypeTxt.setStyle(null);
        }

        if (!isValid) {
            new Alert(Alert.AlertType.ERROR, "Please fix input errors and try again.").show();
        }

        return isValid;
    }


    @FXML
    void TableClick(MouseEvent event) {
        LegalDouDto legaldocTM = LegalDocTable.getSelectionModel().getSelectedItem();
        if (legaldocTM != null) {
            LegalDocId.setText(legaldocTM.getLegalDocumentsId());
            InvoicTxt.setText(legaldocTM.getInvoiceNumber());
            DocUrl.setText(legaldocTM.getDocumentIdURL());
            LowTypeTxt.setText(legaldocTM.getTypeOFLaw());


            SaveButton.setDisable(true);

            DeleteButton.setDisable(false);
            UpdateButton.setDisable(false);
        }
    }




    @FXML
    void DeleteAction(ActionEvent event) throws Exception {
        String LegalDocID = LegalDocId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            legalDouBO.deleteLegalDoc(LegalDocID);
            LegalDocTable.getItems().remove(LegalDocTable.getSelectionModel().getSelectedItem());
            LegalDocTable.getSelectionModel().clearSelection();

                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }

    @FXML
    void UpdateAction(ActionEvent event) throws Exception {
        String  Legaldocid = LegalDocId.getText();
        String  Invoicid = InvoicTxt.getText();
        String  Docurl = DocUrl.getText();
        String  Lowtype = LowTypeTxt.getText();


        LegalDouDto legalDouDto = new LegalDouDto(
                Legaldocid,
                Invoicid,
                Docurl,
                Lowtype
        );

        boolean isUpdate = legalDouBO.updatelegalDoc(legalDouDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "LegalDoc update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update LegalDoc...!").show();
        }
    }


    @FXML
    void LogoutAction(ActionEvent event) {
        navigateTo("/View/HomePage.fxml");
    }
    private void navigateTo(String fxmlPath) {
        try {
            LegalPage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            LegalPage.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui !").show();
        }
    }
}
