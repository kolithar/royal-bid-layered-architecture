package lk.ijse.gdse71.royalbid.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.royalbid.BO.BOFactory;
import lk.ijse.gdse71.royalbid.BO.Custom.AssetsBO;
import lk.ijse.gdse71.royalbid.BO.Custom.CatogeryBO;
import lk.ijse.gdse71.royalbid.BO.Custom.CustomerBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.AssetsBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.CatogeryBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.CustomerBOImpl;
import lk.ijse.gdse71.royalbid.DTO.AssetsDto;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AssetsController implements Initializable {


    @FXML
    private TableView<AssetsDto> AsseteTable;
    @FXML
    private TableColumn<AssetsDto, String> AssetsID;
    @FXML
    private TableColumn<AssetsDto, String> AssetsName;
    @FXML
    private TableColumn<AssetsDto, String> AssetsSpecialties;
    @FXML
    private TableColumn<AssetsDto, String> AssetsType;
    @FXML
    private TableColumn<AssetsDto, String> CategoryId;
    @FXML
    private TableColumn<AssetsDto, String> CustomerId;
    @FXML
    private TableColumn<AssetsDto, String> StratAndEndColm;
    @FXML
    private Button AssetsDelete;
    @FXML
    private Button AssetsSave;
    @FXML
    private Button AssetsUpade;
    @FXML
    private AnchorPane AssetsMainPane;

    @FXML
    private TextField StratAandEndTxt;

    @FXML
    private Label AssetsIDLable;

    @FXML
    private TextField AsseteNametxt;

    @FXML
    private TextField AssetsSpecialtiestxt;

    @FXML
    private TextField AssetsTypestxt;



    @FXML
    private Label Catogerysurge;

    @FXML
    private Label Customernamesurge;

    @FXML
    private ComboBox<String> cmbCatogeryID;

    @FXML
    private ComboBox<String> cmbCustomerID;



    private final ObservableList<AssetsDto> assetsDtos = FXCollections.observableArrayList();

    AssetsBO assetsBO = new AssetsBOImpl();
    CatogeryBO catogeryBO = new CatogeryBOImpl();
    CustomerBO customerBO = new CustomerBOImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AssetsID.setCellValueFactory(new PropertyValueFactory<>("AssetsId"));
        AssetsName.setCellValueFactory(new PropertyValueFactory<>("AssetsName"));
        AssetsType.setCellValueFactory(new PropertyValueFactory<>("AssetsType"));
        AssetsSpecialties.setCellValueFactory(new PropertyValueFactory<>("AssetsSpecialties"));
        CustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        CategoryId.setCellValueFactory(new PropertyValueFactory<>("CategoryId"));
        StratAndEndColm.setCellValueFactory(new PropertyValueFactory<>("StartAndEnd"));


        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }
    }



    private void refreshPage() throws SQLException, ClassNotFoundException {
        AssetsIDLable.setText(assetsBO.getNextAssetsId());
        lodecustomerid();
        lodecatogeryId();
        loadTableData();

        AssetsSave.setDisable(false);

        AssetsUpade.setDisable(true);
        AssetsDelete.setDisable(true);

        AsseteNametxt.setText("");

        AssetsSpecialtiestxt.setText("");
        AssetsTypestxt.setText("");
        Catogerysurge.setText("");
        Customernamesurge.setText("");
        StratAandEndTxt.setText("");
        // Clear the cart observable list
        assetsDtos.clear();

        // Refresh the table to reflect changes
        AsseteTable.refresh();
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        // Get all assets from the BO
        ArrayList<AssetsDto> assetsDtos = assetsBO.getAllAssets();

        ObservableList<AssetsDto> assetsTMS = FXCollections.observableArrayList();

        // Convert AssetsDto to table-friendly data
        for (AssetsDto assetsDTO : assetsDtos) {
            AssetsDto asserTM = new AssetsDto(
                    assetsDTO.getAssetsId(),
                    assetsDTO.getAssetsName(),
                    assetsDTO.getAsssetType(),
                    assetsDTO.getAssetsSpecialties(),
                    assetsDTO.getCustomerId(),
                    assetsDTO.getCategoryId(),
                    assetsDTO.getStartAndEnd()
            );
            assetsTMS.add(asserTM);
        }

        // Set data to the table view
        AsseteTable.setItems(assetsTMS);
    }

    @FXML
    public  void OnSaveAction(ActionEvent event)throws Exception {
        String AssetsId = AssetsIDLable.getText();
        String AssetsName = AsseteNametxt.getText();
        String AssetsType = AssetsTypestxt.getText();
        String AssetsSpecialties = AssetsSpecialtiestxt.getText();
        String customerId = cmbCustomerID.getValue(); // Selected customer
        String categoryId = cmbCatogeryID.getValue(); // Selected category
        String StartAndEnd = StratAandEndTxt.getText();



        AsseteNametxt.setStyle(AsseteNametxt.getStyle() + ";-fx-border-color: #7367F0;");
        AssetsTypestxt.setStyle(AssetsTypestxt.getStyle() + ";-fx-border-color: #7367F0;");
        AssetsSpecialtiestxt.setStyle(AssetsSpecialtiestxt.getStyle() + ";-fx-border-color: #7367F0;");

        String AssstsnamePattern = "^[A-Za-z ]+$";
        String AssetstypePattern = "^[A-Za-z ]+$";
        String AssetsSpecialtiesPattern = "^[A-Za-z ]+$";

        boolean isValidName = AssetsName.matches(AssstsnamePattern);
        boolean isValidAssets = AssetsType.matches(AssetstypePattern);
        boolean isValidAddres = AssetsSpecialties.matches(AssetsSpecialtiesPattern);

        if (!isValidName) {
            System.out.println(AsseteNametxt.getStyle());
            AsseteNametxt.setStyle(AsseteNametxt.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }
        if (!isValidAssets) {
            AssetsTypestxt.setStyle(AssetsTypestxt.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (!isValidAddres) {
            AssetsSpecialtiestxt.setStyle(AssetsSpecialtiestxt.getStyle() + ";-fx-border-color: red;");
        }


        if (isValidName && isValidAssets && isValidAddres) {
            AssetsDto assetsDto = new AssetsDto(
                    AssetsId,
                    AssetsName,
                    AssetsType,
                    AssetsSpecialties,
                    customerId,
                    categoryId,
                    StartAndEnd
            );

            AssetsDto assetsDTo = new AssetsDto(
                    AssetsId,
                    AssetsName,
                    AssetsType,
                    AssetsSpecialties,
                    customerId,
                    categoryId,
                    StartAndEnd
            );

            AssetsBO assetsBO = (AssetsBO) BOFactory.getInstance().getBO(BOFactory.BOType.Assets);

            boolean isSaved = assetsBO.saveAssets(assetsDTo);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Assets saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Assets...!").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        AssetsDto assetsTM = AsseteTable.getSelectionModel().getSelectedItem();
        if (assetsTM != null) {
            AssetsIDLable.setText(assetsTM.getAssetsId());
            AsseteNametxt.setText(assetsTM.getAssetsName());
            AssetsTypestxt.setText(assetsTM.getAsssetType());
            AssetsSpecialtiestxt.setText(assetsTM.getAssetsSpecialties());
            cmbCustomerID.setValue(assetsTM.getCustomerId());
            cmbCatogeryID.setValue(assetsTM.getCategoryId());

            AssetsSave.setDisable(true);

            AssetsDelete.setDisable(false);
            AssetsUpade.setDisable(false);
        }
    }


    @FXML
   public void OnDeleteAction(ActionEvent event) throws Exception {
        String assetsid = AssetsIDLable.getText();
try {


    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
    Optional<ButtonType> optionalButtonType = alert.showAndWait();


    if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
    }
    assetsBO.deleteAssets(assetsid);
    AsseteTable.getItems().remove(AsseteTable.getSelectionModel().getSelectedItem());
    AsseteTable.getSelectionModel().clearSelection();
} catch (SQLException e) {
    new Alert(Alert.AlertType.ERROR, "Fail to delete assets...!").show();
}catch (ClassNotFoundException e){
    e.printStackTrace();

}
    }


    @FXML
    void OnUpdateAction(ActionEvent event)throws Exception {
        String  assetsid = AssetsIDLable.getText();
        String  assetname = AsseteNametxt.getText();
        String  assetsSpecialties = AssetsSpecialtiestxt.getText();
        String  assetsType = AssetsTypestxt.getText();
        String customerid = CustomerId.getText();
        String  catogeryid = CategoryId.getText();
        String  StartAndEnd = CategoryId.getText();



        AssetsDto assetsDto = new AssetsDto(
                assetsid,
                assetname ,
                assetsSpecialties,
                assetsType,
                customerid,
                catogeryid,
                StartAndEnd


        );

        boolean isUpdate = assetsBO.updateAssets(assetsDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
        }
    }


    private void lodecustomerid() throws SQLException, ClassNotFoundException {

        ArrayList<String> CatogeryIds = customerBO .getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(CatogeryIds);
        cmbCatogeryID.setItems(observableList);

    }

    private void lodecatogeryId() throws SQLException, ClassNotFoundException {
        ArrayList<String> CatogeryIds = catogeryBO.getAllCatogeryIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(CatogeryIds);
        cmbCatogeryID.setItems(observableList);
    }

}
