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
import lk.ijse.gdse71.royalbid.BO.Custom.BiddersBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.BiddersBOImpl;
import lk.ijse.gdse71.royalbid.DTO.BiddersDto;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class  BiddersManageController implements Initializable {

    @FXML
    private TextField BidderAdders;

    @FXML
    private TextField BidderName;

    @FXML
    private TextField BidderProfession;

    @FXML
    private DatePicker BidderRegisDate;

    @FXML
    private AnchorPane BiddersPageAnch;

    @FXML
    private TableView<BiddersDto> BiddersTable;

    @FXML
    private TableColumn<BiddersDto, String> ColAddress;

    @FXML
    private TableColumn<BiddersDto, String> ColBidderID;

    @FXML
    private TableColumn<BiddersDto, String> ColBidderName;

    @FXML
    private TableColumn<BiddersDto, LocalDate> ColDateOfRegi;

    @FXML
    private TableColumn<BiddersDto, String> ColProfession;

    @FXML
    private Label BidderIDLable;

    @FXML
    private Button BidderSave;

    @FXML
    private Button BidderDelete;


    @FXML
    void UpdateAction(ActionEvent event) {

    }

    @FXML
    private Button BidderUpdate;


    BiddersBO biddersBO = new BiddersBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColBidderID.setCellValueFactory(new PropertyValueFactory<>("bidderId"));
        ColBidderName.setCellValueFactory(new PropertyValueFactory<>("bidersName"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("biderAddres"));
        ColProfession.setCellValueFactory(new PropertyValueFactory<>("biddersProfession"));
        ColDateOfRegi.setCellValueFactory(new PropertyValueFactory<>("bidderRegisDate"));

        // Set custom cell factory to handle null dates
        ColDateOfRegi.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? "N/A" : item.toString());
            }
        });

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load data").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextBidderID();
        loadTableData();

        BidderSave.setDisable(false);
        BidderDelete.setDisable(true);
        BidderUpdate.setDisable(true);

        BidderName.clear();
        BidderAdders.clear();
        BidderProfession.clear();
        BidderRegisDate.setValue(null);
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<BiddersDto> biddersList = biddersBO.getAllBidders();

        ObservableList<BiddersDto> observableBidders = FXCollections.observableArrayList();
        observableBidders.addAll(biddersList);
        BiddersTable.setItems(observableBidders);
    }

    private void loadNextBidderID() throws SQLException, ClassNotFoundException {
        String nextBidderId = biddersBO.getNextBidderId();
        BidderIDLable.setText(nextBidderId);
    }

    @FXML
    void SaveAction(ActionEvent event) throws Exception {
        String bidderId = BidderIDLable.getText();
        String bidderName = BidderName.getText();
        String bidderAddress = BidderAdders.getText();
        String bidderProfession = BidderProfession.getText();
        LocalDate registrationDate = BidderRegisDate.getValue();

        if (validateInput(bidderName, bidderAddress, bidderProfession)) {
            BiddersDto bidder = new BiddersDto(bidderId, bidderName, bidderAddress, bidderProfession, registrationDate);
            boolean isSaved = biddersBO.saveBidders(bidder);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Bidder saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save bidder.").show();
            }
        }
    }





    private boolean validateInput(String name, String address, String profession) {
        boolean valid = true;

        if (!name.matches("^[A-Za-z ]+$")) {
            BidderName.setStyle("-fx-border-color: red;");
            valid = false;
        }

        if (!address.matches("^[A-Za-z ]+$")) {
            BidderAdders.setStyle("-fx-border-color: red;");
            valid = false;
        }

        if (!profession.matches("^[A-Za-z ]+$")) {
            BidderProfession.setStyle("-fx-border-color: red;");
            valid = false;
        }

        return valid;
    }
// CLONE

    @FXML
    void TableCliced(MouseEvent event) {
        BiddersDto bidderTM = BiddersTable.getSelectionModel().getSelectedItem();
        if (bidderTM != null) {
            BidderIDLable.setText(bidderTM.getBidderId());
            BidderName.setText(bidderTM.getBidersName());
            BidderAdders.setText(bidderTM.getBiderAddres());
            BidderProfession.setText(bidderTM.getBiddersProfession());
            BidderRegisDate.setValue(bidderTM.getBidderRegisDate());

            BidderSave.setDisable(true);

            BidderDelete.setDisable(false);
            BidderUpdate.setDisable(false);
        }
    }

    @FXML
    void DeleteAction(ActionEvent event) throws Exception {
        String bidderId = BidderIDLable.getText();


        try {


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();


            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            }
            biddersBO.deleteBidder(bidderId);
            BiddersTable.getItems().remove(BiddersTable.getSelectionModel().getSelectedItem());
            BiddersTable.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to delete assets...!").show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }

    }




    @FXML
    void LogoutAction(ActionEvent event) {
        navigateTo("/View/HomePage.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            BiddersPageAnch.getChildren().clear();
            // Use the correct absolute path starting from the root of the resources folder
            AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            BiddersPageAnch.getChildren().add(load);
        } catch (NullPointerException e) {
            System.out.println("FXML file not found at path: " + fxmlPath);
            new Alert(Alert.AlertType.ERROR, "FXML file not found!").show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Failed to load UI!").show();
        }
    }
}



