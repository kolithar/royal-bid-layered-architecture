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
import lk.ijse.gdse71.royalbid.BO.Custom.AssetsAndEvaluatoreBO;
import lk.ijse.gdse71.royalbid.BO.Custom.AssetsBO;
import lk.ijse.gdse71.royalbid.BO.Custom.EvaluatoreBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.AssetsAndEvaluatoreBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.AssetsBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.EvaluatoreBOImpl;
import lk.ijse.gdse71.royalbid.DTO.AssetAndEvaluatorDto;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AssetsAndEvaluatoreController implements Initializable {

    @FXML
    private ComboBox<String> AssetIdCombo;

    @FXML
    private TableColumn<AssetsAndEvaluatoreController, String> AssetsIDCol;

    @FXML
    private Button BackButton;


    @FXML
    private TableView<AssetAndEvaluatorDto> EvaluatoAndAssetsTable;

    @FXML
    private ComboBox<String> EvaluatorIdCombo;

    @FXML
    private AnchorPane EvaluatoreAndAsstsPane;

    @FXML
    private TableColumn<AssetAndEvaluatorDto, String> EvaluatoreIDCol;

    @FXML
    private Button RemoveButton;

    @FXML
    private Button addbutton;



    AssetsAndEvaluatoreBO assetsAndEvaluatoreBO = new AssetsAndEvaluatoreBOImpl();

    AssetsBO assetsBO = new AssetsBOImpl();

    EvaluatoreBO evaluatoreBO = new EvaluatoreBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EvaluatoreIDCol.setCellValueFactory(new PropertyValueFactory<>("EvaluatorId"));
        AssetsIDCol.setCellValueFactory(new PropertyValueFactory<>("AssetsId"));



        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        lodeAssetsId();
        EvaluatorId();
        loadTableData();
    }


    @FXML
    void TableClick(MouseEvent event) {
        AssetAndEvaluatorDto assetsANDevaluatorTM = EvaluatoAndAssetsTable.getSelectionModel().getSelectedItem();
        if (assetsANDevaluatorTM != null) {
            AssetIdCombo.setValue(assetsANDevaluatorTM.getAssetsId());
            EvaluatorIdCombo.setValue(assetsANDevaluatorTM.getEvaluatorId());


        }


    }


    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<AssetAndEvaluatorDto> aeDTOS = assetsAndEvaluatoreBO.getAllAssetsandEvaluatore();

        ObservableList<AssetAndEvaluatorDto> aeTMS = FXCollections.observableArrayList();

        ObservableList<AssetAndEvaluatorDto> aeDtos = FXCollections.observableArrayList();
        aeDtos.addAll(assetsAndEvaluatoreBO.getAllAssetsandEvaluatore().stream().map(aeDTO -> new AssetAndEvaluatorDto(
                aeDTO.getAssetsId(),
                aeDTO.getEvaluatorId()


        )).collect(Collectors.toList()));
        EvaluatoAndAssetsTable.setItems(aeTMS);

        for (AssetAndEvaluatorDto AeDTO : aeDTOS) {
            AssetAndEvaluatorDto AeTM = new AssetAndEvaluatorDto(
                    AeDTO.getAssetsId(),
                    AeDTO.getEvaluatorId()

            );
            aeTMS.add(AeTM);
        }
        EvaluatoAndAssetsTable.setItems(aeTMS);
    }


    @FXML
    void addAction(ActionEvent event) throws Exception {
        String AssetsID = AssetIdCombo.getValue(); // Selected customer
        String EvaluatoeID = EvaluatorIdCombo.getValue();

        AssetAndEvaluatorDto assetsDTo = new AssetAndEvaluatorDto(
                AssetsID,
                EvaluatoeID

        );

        boolean isSaved = assetsAndEvaluatoreBO.AddAssetAadEvaluatore(assetsDTo);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Assets saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Assets...!").show();
        }
    }


    @FXML
    void removeAction(ActionEvent event) {

            AssetAndEvaluatorDto selectedRow = EvaluatoAndAssetsTable.getSelectionModel().getSelectedItem();
            try {


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> optionalButtonType = alert.showAndWait();


                if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                }
                assetsAndEvaluatoreBO.removeAssetAndEvaluator(selectedRow.getAssetsId(), selectedRow.getEvaluatorId());
                EvaluatoAndAssetsTable.getItems().remove(EvaluatoAndAssetsTable.getSelectionModel().getSelectedItem());
                EvaluatoAndAssetsTable.getSelectionModel().clearSelection();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Fail to delete assets...!").show();
            } catch (Exception e) {
                e.printStackTrace();

            }



    }


    private void navigateTo(String fxmlPath) {
        try {
            EvaluatoreAndAsstsPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            EvaluatoreAndAsstsPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load ui !").show();
        }
    }

    @FXML
    void BackAction(ActionEvent event) {
        navigateTo("/View/HomePage.fxml");
    }


    private void lodeAssetsId() throws SQLException, ClassNotFoundException {
        System.out.println("66666666666666666666666666666666666666666666666666");
        ArrayList<String> AssetsIds = assetsBO.getAllAssetIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(AssetsIds);
        System.out.println("9999999999999999999999999999999999" + AssetsIds.size());
        AssetIdCombo.setItems(observableList);


    }


    private void EvaluatorId() throws SQLException, ClassNotFoundException {
        System.out.println("66666666666666666666666666666666666666666666666666");
        ArrayList<String> EvaluatorIds = evaluatoreBO.getAllEvaluatorIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(EvaluatorIds);
        System.out.println("9999999999999999999999999999999999" + EvaluatorIds.size());
        EvaluatorIdCombo.setItems(observableList);

    }
}
