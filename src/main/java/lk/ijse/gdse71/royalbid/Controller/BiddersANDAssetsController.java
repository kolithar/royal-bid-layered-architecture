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
import lk.ijse.gdse71.royalbid.BO.Custom.AssetsBO;
import lk.ijse.gdse71.royalbid.BO.Custom.BiddersAndAssetsBO;
import lk.ijse.gdse71.royalbid.BO.Custom.BiddersBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.AssetsBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.BiddersAndAssetsBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.BiddersBOImpl;
import lk.ijse.gdse71.royalbid.DTO.BiddersAndAssetsDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BiddersANDAssetsController implements Initializable {

    @FXML
    private Button AddButton;

    @FXML
    private ComboBox<String> AssetIdCombo;

    @FXML
    private TableColumn<BiddersAndAssetsDto, String> AssetsIDColum;

    @FXML
    private TableView<BiddersAndAssetsDto> AssetsandBidderManegeTable;

    @FXML
    private AnchorPane AssetsandBidderMangegPane;

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<BiddersAndAssetsDto, String> BidderIDColum;

    @FXML
    private ComboBox<String> BiddersIDCombo;

    @FXML
    private Button RemoveButton;

    // Dummy data for demonstration purposes
    private ObservableList<BiddersAndAssetsDto> tableData = FXCollections.observableArrayList();

    AssetsBO assetsBO = new AssetsBOImpl();

    BiddersBO biddersBO = new BiddersBOImpl();

    BiddersAndAssetsBO biddersAndAssetsBO = new BiddersAndAssetsBOImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setting up table columns
        AssetsIDColum.setCellValueFactory(new PropertyValueFactory<>("assetsId"));
        BidderIDColum.setCellValueFactory(new PropertyValueFactory<>("biddersId"));

        // Load initial data
        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load data.").show();
            e.printStackTrace();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadAssetIds();
        loadBidderIds();
        loadTableData();
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<BiddersAndAssetsDto> baDTOS = biddersAndAssetsBO.getAllab();

        ObservableList<BiddersAndAssetsDto> baTMS = FXCollections.observableArrayList();

        ObservableList<BiddersAndAssetsDto> baDtos = FXCollections.observableArrayList();
        baDtos.addAll(biddersAndAssetsBO .getAllab().stream().map(adDTO -> new BiddersAndAssetsDto(
                adDTO.getAssetsId(),
                adDTO.getBiddersId()

        )).collect(Collectors.toList()));
        AssetsandBidderManegeTable.setItems(baTMS);

        for (BiddersAndAssetsDto abDTO : baDTOS) {
            BiddersAndAssetsDto abTM = new BiddersAndAssetsDto(
                    abDTO.getAssetsId(),
                    abDTO.getBiddersId()

            );
            baTMS.add(abTM);
        }
        AssetsandBidderManegeTable.setItems(baTMS);
    }


    private void loadAssetIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> assetIds = assetsBO.getAllAssetIds();
        ObservableList<String> assetList = FXCollections.observableArrayList(assetIds);
        AssetIdCombo.setItems(assetList);
    }

    private void loadBidderIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> bidderIds = biddersBO.getAllabaidderIds();
        ObservableList<String> bidderList = FXCollections.observableArrayList(bidderIds);
        BiddersIDCombo.setItems(bidderList);
    }

    @FXML
    void ClickTable(MouseEvent event) {
        BiddersAndAssetsDto selected = AssetsandBidderManegeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            AssetIdCombo.setValue(selected.getAssetsId());
            BiddersIDCombo.setValue(selected.getBiddersId());
        }
    }

    @FXML
    void AddAction(ActionEvent event) {
        String selectedAssetId = AssetIdCombo.getValue();
        String selectedBidderId = BiddersIDCombo.getValue();

        if (selectedAssetId == null || selectedBidderId == null) {
            new Alert(Alert.AlertType.WARNING, "Please select both an Asset ID and a Bidder ID.").show();
            return;
        }

        BiddersAndAssetsDto newEntry = new BiddersAndAssetsDto(selectedAssetId, selectedBidderId);

        // Add entry to the table (replace this with DB insertion logic)
        tableData.add(newEntry);
        AssetsandBidderManegeTable.setItems(tableData);

        new Alert(Alert.AlertType.INFORMATION, "Added successfully.").show();
    }

    @FXML
    void RemoveAction(ActionEvent event) {
        BiddersAndAssetsDto selected = AssetsandBidderManegeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an entry to remove.").show();
            return;
        }

        // Remove entry from the table (replace this with DB deletion logic)
        tableData.remove(selected);

        new Alert(Alert.AlertType.INFORMATION, "Removed successfully.").show();
    }

    @FXML
    void BackAction(ActionEvent event) {
        navigateTo("/View/HomePage.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            AssetsandBidderMangegPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            AssetsandBidderMangegPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load UI.").show();
            e.printStackTrace();
        }
    }
}
