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
import lk.ijse.gdse71.royalbid.BO.Custom.FinanceBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.FinancePageBOImpl;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;
import lk.ijse.gdse71.royalbid.DTO.TransactionDto;
import lk.ijse.gdse71.royalbid.Model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class  FinancePageController implements Initializable {

    @FXML
    private TextField Ammounttxt;

    @FXML
    private TextField AssetsIDtxt;

    @FXML
    private Button ButtonDelet;

    @FXML
    private Button ButtonSave;

    @FXML
    private Button ButtonUpdate;

    @FXML
    private TableColumn<TransactionDto, Double> ColAmmount;

    @FXML
    private TableColumn<TransactionDto, String> ColAssetsID;

    @FXML
    private TableColumn<TransactionDto, String> ColInvoicNumber;

    @FXML
    private TableColumn<TransactionDto, Double> ColMinimumBid;

    @FXML
    private TableColumn<TransactionDto, Double> ColProfit;

    @FXML
    private AnchorPane FinancePage;

    @FXML
    private TableView<TransactionDto> FinanceTable;

    @FXML
    private Label InvoicNumberLab;


    @FXML
    private Button LogOutBuuton;

    @FXML
    private TextField MinimumBidtxt;

    @FXML
    private TextField Profittxt;

    @FXML
    void DeleteActio(ActionEvent event) {

    }
FinanceBO financeBO = new FinancePageBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up table columns
        ColInvoicNumber.setCellValueFactory(new PropertyValueFactory<>("InvoiceNumber"));
        ColAmmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        ColMinimumBid.setCellValueFactory(new PropertyValueFactory<>("MinimumBid"));
        ColProfit.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        ColAssetsID.setCellValueFactory(new PropertyValueFactory<>("AssetsId"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load finance data.").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextInvoic();
        loadTableData();

        // Reset buttons
        ButtonSave.setDisable(false);
        ButtonUpdate.setDisable(true);
        ButtonDelet.setDisable(true);

        // Clear text fields
        Ammounttxt.clear();
        Profittxt.clear();
        MinimumBidtxt.clear();
        AssetsIDtxt.clear();
    }



    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<TransactionDto> transactionList = financeBO.getAllFinance();
        ObservableList<TransactionDto> transactionData = FXCollections.observableArrayList(transactionList);
        FinanceTable.setItems(transactionData);
    }

    private void loadNextInvoic() throws SQLException, ClassNotFoundException {
        String nextInvoice = financeBO.getNextInvoic();
        InvoicNumberLab.setText(nextInvoice);
    }



    @FXML
    void TableClick(MouseEvent event) {
        TransactionDto selectedTransaction = FinanceTable.getSelectionModel().getSelectedItem();

        if (selectedTransaction != null) {
            InvoicNumberLab.setText(selectedTransaction.getInvoiceNumber());
            Ammounttxt.setText(String.valueOf(selectedTransaction.getAmount()));
            MinimumBidtxt.setText(String.valueOf(selectedTransaction.getMinimumBid()));
            Profittxt.setText(String.valueOf(selectedTransaction.getProfit()));
            AssetsIDtxt.setText(selectedTransaction.getAssetsId());

            // Enable update and delete buttons
            ButtonUpdate.setDisable(false);
            ButtonDelet.setDisable(false);
            ButtonSave.setDisable(true);
        }
    }
    @FXML
    void DeleteAction(ActionEvent event) {
        TransactionDto selectedTransaction = FinanceTable.getSelectionModel().getSelectedItem();

        if (selectedTransaction != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this transaction?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                try {

                    financeBO.deleteevaluatore(selectedTransaction.getInvoiceNumber());
                    FinanceTable.getItems().remove(FinanceTable.getSelectionModel().getSelectedItem());
                    FinanceTable.getSelectionModel().clearSelection();
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "An unexpected error occurred while deleting.").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a transaction to delete!").show();
        }
    }

    @FXML
    void UpdateAction(ActionEvent event) {
        String invoice = InvoicNumberLab.getText();
        String amountText = Ammounttxt.getText().trim();
        String minimumBidText = MinimumBidtxt.getText().trim();
        String assetID = AssetsIDtxt.getText().trim();

        // Perform validation (reusing Save logic)

        try {
            double amount = Double.parseDouble(amountText);
            double minimumBid = Double.parseDouble(minimumBidText);
            double profit = amount - minimumBid;

            TransactionDto transactionDto = new TransactionDto(
                    invoice, amount, minimumBid, profit, assetID
            );

            boolean isUpdated = financeBO.updateTransaction(transactionDto);
            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Transaction updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update transaction.").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid number format.").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred while updating.").show();
        }
    }



    @FXML
    void SaveAction(ActionEvent event) {
        try {
            String invoice = InvoicNumberLab.getText().trim();
            double amount = Double.parseDouble(Ammounttxt.getText().trim());
            double minimumBid = Double.parseDouble(MinimumBidtxt.getText().trim());
            String assetsId = AssetsIDtxt.getText().trim();

            if (invoice.isEmpty() || assetsId.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill in all fields.").show();
                return;
            }

            // Check if AssetsId exists in the database
//            if (!fInanceModel.isAssetsIdValid(assetsId)) {
//                new Alert(Alert.AlertType.WARNING, "Invalid AssetsId. Please enter a valid ID.").show();
//                return;
//            }

            double profit = amount - minimumBid;
            if (profit < 0) {
                new Alert(Alert.AlertType.WARNING, "Profit cannot be negative.").show();
                return;
            }

            TransactionDto transactionDto = new TransactionDto(invoice, amount, minimumBid, profit, assetsId);
            boolean isSaved = financeBO.saveTransaction(transactionDto);

            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Transaction saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save transaction.").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid number format.").show();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Database error occurred while saving transaction.").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred while saving.").show();
        }
    }


    @FXML
    void LogoutAction(ActionEvent event) {
        navigateTo("/View/HomePage.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            FinancePage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            FinancePage.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui !").show();
        }
    }
}




