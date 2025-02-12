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
import lk.ijse.gdse71.royalbid.BO.Custom.CatogeryBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.CatogeryBOImpl;
import lk.ijse.gdse71.royalbid.DTO.CatogeryDto;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CatogeryController implements Initializable {

    @FXML
    private TableColumn<CatogeryDto, String> CategoryDEsc;

    @FXML
    private TableColumn<CatogeryDto, String> CategoryID;

    @FXML
    private TableColumn<CatogeryDto, String> CategoryNmae;

    @FXML
    private TableView<CatogeryDto> CategoryTable;

    @FXML
    private AnchorPane CatogeryPane;

    @FXML
    private TextField CatoguryDesc;

    @FXML
    private Label CatogeryIDtxt;

    @FXML
    private TextField CatoguryName;

    @FXML
    private Button DeletButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Button UpdateButton;



    CatogeryBO catogeryBO = new CatogeryBOImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set table column to cell factory value
        CategoryID.setCellValueFactory(new PropertyValueFactory<>("CategoryId"));
        CategoryNmae.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
        CategoryDEsc.setCellValueFactory(new PropertyValueFactory<>("CategoryDesc"));


        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load catogery id").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextCatogeryId();
        loadTableData();

        SaveButton.setDisable(false);

        UpdateButton.setDisable(true);
        DeletButton.setDisable(true);

        CatoguryName.setText("");
        CatoguryDesc.setText("");

    }



    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<CatogeryDto> catogeryDtos = catogeryBO.getAllCatogery();

        ObservableList<CatogeryDto> catogeryTMS = FXCollections.observableArrayList();

        ObservableList<CatogeryDto> catogeryDtos1 = FXCollections.observableArrayList();
        catogeryDtos1.addAll(catogeryBO.getAllCatogery().stream().map(CatogeryDto -> new CatogeryDto(
                CatogeryDto.getCategoryId(),
                CatogeryDto.getCategoryName(),
                CatogeryDto.getCategoryDesc()
        )).collect(Collectors.toList()));
        CategoryTable.setItems(catogeryTMS);

        for (CatogeryDto catogeryDTO : catogeryDtos) {
            CatogeryDto catogeryTM = new CatogeryDto(
                    catogeryDTO.getCategoryId(),
                    catogeryDTO.getCategoryName(),
                    catogeryDTO.getCategoryDesc()

            );
            catogeryTMS.add(catogeryTM);
        }

        CategoryTable.setItems(catogeryTMS);
    }

    public void loadNextCatogeryId() throws SQLException, ClassNotFoundException {
//        customerModel.helloCustomerModel();
        String nextCatogeryId = catogeryBO.getNextCatogeryID();
        CatogeryIDtxt.setText(nextCatogeryId);
    }

    @FXML
    void SaveAction(ActionEvent event) throws Exception {
        String CatogeryId = CatogeryIDtxt.getText();
        String CategoryName = CatoguryName.getText();
        String Catogerydesc = CatoguryDesc.getText();


        CatoguryName.setStyle(CatoguryName.getStyle() + ";-fx-border-color: #7367F0;");
        CatoguryDesc.setStyle(CatoguryDesc.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String descPatteren = "^[A-Za-z ]+$";

        boolean isValidName = CategoryName.matches(namePattern);
        boolean isValiddesc = Catogerydesc.matches(descPatteren);


        if (!isValidName) {
            System.out.println(CatoguryName.getStyle());
            CatoguryName.setStyle(CatoguryName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValiddesc) {
            CatoguryDesc.setStyle(CatoguryDesc.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (isValidName && isValiddesc) {
            CatogeryDto catogeryDto = new CatogeryDto(
                    CatogeryId,
                    CategoryName,
                    Catogerydesc

            );

            CatogeryDto catogeryDtO = new CatogeryDto(
                    CatogeryId,
                    CategoryName,
                    Catogerydesc
            );

            boolean isSaved = catogeryBO.savecategory(catogeryDtO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }
    }

    @FXML
    void OnclickTable(MouseEvent event) {
        CatogeryDto catogeryDto = CategoryTable.getSelectionModel().getSelectedItem();
        if (catogeryDto != null) {
            CatogeryIDtxt.setText(catogeryDto.getCategoryId());
            CatoguryName.setText(catogeryDto.getCategoryName());
            CatoguryDesc.setText(catogeryDto.getCategoryDesc());


            SaveButton.setDisable(true);

            DeletButton.setDisable(false);
            UpdateButton.setDisable(false);
        }


    }


    @FXML
    void DeletAction(ActionEvent event) throws Exception  {
        String catogeryId = CatogeryIDtxt.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            catogeryBO.deletecatogery(catogeryId);
            CategoryTable.getItems().remove(CategoryTable.getSelectionModel().getSelectedItem());
            CategoryTable.getSelectionModel().clearSelection();
                new Alert(Alert.AlertType.INFORMATION, "catogery deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete catogery...!").show();
            }
        }


    @FXML
    void UpdateAction(ActionEvent event) throws Exception {
            String  catogeryId = CatogeryIDtxt.getText();
            String  catogeryname = CatoguryName.getText();
            String  catogerydesc = CatoguryDesc.getText();


            CatogeryDto catogeryDto = new CatogeryDto(
                    catogeryId,catogeryname,
                    catogerydesc

            );

            boolean isUpdate = catogeryBO.updatecatogery(catogeryDto);
            if (isUpdate) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Catogery update...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update Catogery...!").show();
            }
        }
    }



