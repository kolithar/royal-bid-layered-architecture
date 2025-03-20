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
import lk.ijse.gdse71.royalbid.BO.Custom.EvaluatoreBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.EvaluatoreBOImpl;
import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EvaluatoreController implements Initializable {

    @FXML
    private TableColumn<EvaluatorDto, String> ColEvaluatorAddress;

    @FXML
    private TableColumn<EvaluatorDto, String> ColEvaluatorID;

    @FXML
    private TableColumn<EvaluatorDto, String> ColEvaluatorName;

    @FXML
    private TableColumn<EvaluatorDto, String> ColOk;

    @FXML
    private TableColumn<EvaluatorDto, String> EvaluatoerNumber;

    @FXML
    private TextField EvaluatoerStatustxt;

    @FXML
    private Button DeletBtton;

    @FXML
    private TextField EvaluatorAddresstxt;

    @FXML
    private Label EvaluatorConechNumber;

    @FXML
    private Label EvaluatorName;

    @FXML
    private TextField EvaluatorNametxt;

    @FXML
    private Label EvaluatoreIDlabal;

    @FXML
    private TextField EvaluatoreNumbertxt;

    @FXML
    private AnchorPane EvaluatorePane;

    @FXML
    private TableView<EvaluatorDto> EvaluatoreTable;

    @FXML
    private Button SaveButton;

    @FXML
    private Button UpdayeButton;

    EvaluatoreBO evaluatoreBO =
            (EvaluatoreBO) BOFactory.getInstance().
                    getBO(BOFactory.BOType.Evaluatore);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set table column to cell factory value
        ColEvaluatorID.setCellValueFactory(new PropertyValueFactory<>("EvaluatorId"));
        ColEvaluatorName.setCellValueFactory(new PropertyValueFactory<>("EvaluatorName"));
        ColEvaluatorAddress.setCellValueFactory(new PropertyValueFactory<>("EvaluatorAddress"));
        EvaluatoerNumber.setCellValueFactory(new PropertyValueFactory<>("EvaluatorNumber"));
        ColOk.setCellValueFactory(new PropertyValueFactory<>("EvaluatorOk"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextCustomerId();
        loadTableData();

        SaveButton.setDisable(false);

        UpdayeButton.setDisable(true);
        DeletBtton.setDisable(true);

        EvaluatorAddresstxt.setText("");
        EvaluatorNametxt.setText("");
        EvaluatoreNumbertxt.setText("");
        EvaluatoerStatustxt.setText("");
    }



    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<EvaluatorDto> evaluatorDTOS = evaluatoreBO.getAllEvaluator();

        ObservableList<EvaluatorDto> evaluatorTMS = FXCollections.observableArrayList();

        ObservableList<EvaluatorDto> evaluatorDtos = FXCollections.observableArrayList();
        evaluatorDtos.addAll(evaluatoreBO.getAllEvaluator().stream().map(evaluatorDTO -> new EvaluatorDto(
                evaluatorDTO.getEvaluatorId(),
                evaluatorDTO.getEvaluatorName(),
                evaluatorDTO.getEvaluatorAddress(),
                evaluatorDTO.getEvaluatorNumber(),
                evaluatorDTO.getEvaluatorOk()

        )).collect(Collectors.toList()));
        EvaluatoreTable.setItems(evaluatorTMS);

        for (EvaluatorDto evaluatorDTO : evaluatorDTOS) {
            EvaluatorDto evaluatoeTM = new EvaluatorDto(
                    evaluatorDTO.getEvaluatorId(),
                    evaluatorDTO.getEvaluatorName(),
                    evaluatorDTO.getEvaluatorAddress(),
                    evaluatorDTO.getEvaluatorNumber(),
                    evaluatorDTO.getEvaluatorOk()
            );
            evaluatorTMS.add(evaluatoeTM);
        }
        EvaluatoreTable.setItems(evaluatorTMS);
    }



    public void loadNextCustomerId() throws SQLException, ClassNotFoundException {
//        customerModel.helloCustomerModel();
        String nextEvaluatorID = evaluatoreBO.getNextEvaluatoerId();
        EvaluatoreIDlabal.setText(nextEvaluatorID);
    }

    @FXML
    void SaveAction(ActionEvent event) throws Exception {
        String evaluatorid = EvaluatoreIDlabal.getText();
        String name = EvaluatorNametxt.getText();
        String adderss = EvaluatorAddresstxt.getText();
        String number = EvaluatoreNumbertxt.getText();
        String status = EvaluatoerStatustxt.getText();


        EvaluatorNametxt.setStyle(EvaluatorNametxt.getStyle() + ";-fx-border-color: #7367F0;");
        EvaluatorAddresstxt.setStyle(EvaluatorAddresstxt.getStyle() + ";-fx-border-color: #7367F0;");
        EvaluatoreNumbertxt.setStyle(EvaluatoreNumbertxt.getStyle() + ";-fx-border-color: #7367F0;");
        EvaluatoerStatustxt.setStyle(EvaluatoerStatustxt.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^[A-Za-z ]+$";
        String numberPattern = "^[A-Za-z ]+$";
        String statusPattern = "^[A-Za-z ]+$";


        boolean isValidName = name.matches(namePattern);
        boolean isValidaddress = adderss.matches(addressPattern);
        boolean isValidnumber = number.matches(numberPattern);
        boolean isstatus = status.matches(statusPattern);


        if (!isValidName) {
            System.out.println(EvaluatorNametxt.getStyle());
            EvaluatorNametxt.setStyle(EvaluatorNametxt.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValidaddress) {
            EvaluatorAddresstxt.setStyle(EvaluatorAddresstxt.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (!isValidnumber) {
            EvaluatoreNumbertxt.setStyle(EvaluatoreNumbertxt.getStyle() + ";-fx-border-color: red;");
        }


        if (!isstatus) {
            EvaluatoerStatustxt.setStyle(EvaluatoerStatustxt.getStyle() + ";-fx-border-color: red;");
        }


        if (isValidName && isValidaddress && isValidnumber && isstatus) {
            EvaluatorDto evaluatordto = new EvaluatorDto(
                    evaluatorid,
                    name,
                    adderss,
                    number,
                    status
            );

            EvaluatorDto evaluatorDto = new EvaluatorDto(
                    evaluatorid,
                    name,
                    adderss,
                    number,
                    status
            );


            boolean isSaved = evaluatoreBO.saveevaluatore(evaluatorDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }
    }


    @FXML
    void TableClick(MouseEvent event) {
        EvaluatorDto evaluatorTM = EvaluatoreTable.getSelectionModel().getSelectedItem();
        if ( evaluatorTM!= null) {
            EvaluatoreIDlabal.setText(evaluatorTM.getEvaluatorId());
            EvaluatorNametxt.setText(evaluatorTM.getEvaluatorName());
            EvaluatorAddresstxt.setText(evaluatorTM.getEvaluatorAddress());
            EvaluatoreNumbertxt.setText(evaluatorTM.getEvaluatorNumber());
            EvaluatoerStatustxt.setText(evaluatorTM.getEvaluatorOk());


            SaveButton.setDisable(true);

            DeletBtton.setDisable(false);
            UpdayeButton.setDisable(false);
        }
    }
    @FXML
    void DeletAction(ActionEvent event) throws Exception {
        String evaluatorId = EvaluatoreIDlabal.getText();

        try {


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();


            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            }
            evaluatoreBO.deleteevaluatore(evaluatorId);
            EvaluatoreTable.getItems().remove(EvaluatoreTable.getSelectionModel().getSelectedItem());
            EvaluatoreTable.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to delete assets...!").show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }

    }

    @FXML
    void UpdateAction(ActionEvent event) throws Exception {
        String  eveluatorId = EvaluatoreIDlabal.getText();
        String  name = EvaluatorNametxt.getText();
        String  Adders = EvaluatorAddresstxt.getText();
        String  Number = EvaluatoreNumbertxt.getText();
        String Ststus = EvaluatoerStatustxt.getText();


        EvaluatorDto evaluatorDTO = new EvaluatorDto(
                eveluatorId,
                name,
                Adders,
                Number,
                Ststus
        );

        boolean isUpdate = evaluatoreBO.updateEvaluator(evaluatorDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
        }
    }
    }











