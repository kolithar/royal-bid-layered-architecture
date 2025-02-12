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
import lk.ijse.gdse71.royalbid.BO.Custom.AuctionDetailsBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.AuctionDetailsBOImpl;
import lk.ijse.gdse71.royalbid.DTO.AuctionDto;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ActionDetailsController implements Initializable {

    @FXML
    private AnchorPane ActionPne;

    @FXML
    private TableColumn<AuctionDto, String> ActionalId;
    @FXML
    private TableColumn<AuctionDto, String> AssetsIdColom;


    @FXML
    private TableView<AuctionDto> AuctionTable;

    @FXML
    private Button Delete;

    @FXML
    private TableColumn<AuctionDto, String> LegalMatters;

    @FXML
    private Label Locatio;

    @FXML
    private TableColumn<AuctionDto, String> Location;

    @FXML
    private Label Matter;

    @FXML
    private TextField AssetsIDtxt;

    @FXML
    private TextField Matterstxt;

    @FXML
    private Button Save;

    @FXML
    private TableColumn<AuctionDto, String> TimeDetals;

    @FXML
    private Button Update;

    @FXML
    private Label id;

    @FXML
    private Label idlable;

    @FXML
    private TextField locationtxt;

    @FXML
    private TextField timetxt;

    AuctionDetailsBO auctionDetailsBO = new AuctionDetailsBOImpl();


    @FXML
    void UpdateAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set table column to cell factory value
        ActionalId.setCellValueFactory(new PropertyValueFactory<>("AuctionID"));
        LegalMatters.setCellValueFactory(new PropertyValueFactory<>("LegalMatters"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        TimeDetals.setCellValueFactory(new PropertyValueFactory<>("AuctionTime"));
        AssetsIdColom.setCellValueFactory(new PropertyValueFactory<>("AssetsId"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }

    private void refreshPage() throws Exception {
        loadNextAuctionId();
        loadTableData();

        Save.setDisable(false);

        Update.setDisable(true);
        Delete.setDisable(true);

        Matterstxt.setText("");
        locationtxt.setText("");
        AssetsIDtxt.setText("");
        timetxt.setText("");
    }



    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<AuctionDto> auctionDTOS = auctionDetailsBO.getAllAction();

        ObservableList<AuctionDto> auctionTMS = FXCollections.observableArrayList();

        ObservableList<AuctionDto> auctionDtos = FXCollections.observableArrayList();
        auctionDtos.addAll(auctionDetailsBO.getAllAction().stream().map(auctionDTO -> new AuctionDto(
                auctionDTO.getAuctionID(),
                auctionDTO.getLegalMatters(),
                auctionDTO.getAuctionTime(),
                auctionDTO.getLocation(),
                auctionDTO.getAssetsId()
        )).collect(Collectors.toList()));
        AuctionTable.setItems(auctionTMS);

        for (AuctionDto auctionDTO : auctionDTOS) {
            AuctionDto auctionDtoTM = new AuctionDto(
                    auctionDTO.getAuctionID(),
                    auctionDTO.getLegalMatters(),
                    auctionDTO.getAuctionTime(),
                    auctionDTO.getLocation(),
                    auctionDTO.getAssetsId()
            );
            auctionTMS.add(auctionDtoTM);
        }
        AuctionTable.setItems(auctionTMS);
    }


    public void loadNextAuctionId() throws SQLException, ClassNotFoundException {
//        customerModel.helloCustomerModel();
        String nextAuctionId = auctionDetailsBO.getNexAuctionId();
        idlable.setText(nextAuctionId);
    }

    @FXML
    void SaveAction(ActionEvent event) throws Exception {
        String auctionId = idlable.getText();
        String matters = Matterstxt.getText();
        String locationtxtText = locationtxt.getText();
        String assetsid = AssetsIDtxt.getText();
        String time  = timetxt.getText();

        Matterstxt.setStyle(Matterstxt.getStyle() + ";-fx-border-color: #7367F0;");
        locationtxt.setStyle(locationtxt.getStyle() + ";-fx-border-color: #7367F0;");
        AssetsIDtxt.setStyle(AssetsIDtxt.getStyle() + ";-fx-border-color: #7367F0;");
        timetxt.setStyle(timetxt.getStyle() + ";-fx-border-color: #7367F0;");

        String matterPattern = "^[A-Za-z ]+$";
        String locatiosPattern = "^[A-Za-z ]+$";
        String assetidPattern = "^[A-Za-z ]+$";
        String timePattern = "^[A-Za-z ]+$";

        boolean isValidmatter = matters.matches(matterPattern);
        boolean isValidloca = locationtxtText.matches(locatiosPattern);
        boolean isValidassets = assetsid.matches(assetidPattern);
        boolean isValidtime = time.matches(timePattern);

        if (!isValidmatter) {
            System.out.println(Matterstxt.getStyle());
            Matterstxt.setStyle(Matterstxt.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValidloca) {
            locationtxt.setStyle(locationtxt.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (!isValidassets) {
            AssetsIDtxt.setStyle(AssetsIDtxt.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidtime) {
            timetxt.setStyle(timetxt.getStyle() + ";-fx-border-color: red;");
        }

        if (isValidmatter && isValidloca && isValidassets && isValidtime) {
            AuctionDto auctionDto = new AuctionDto(
                    auctionId,
                    matters,
                    locationtxtText,
                    assetsid,
                    time
            );

            AuctionDto auctionDTO = new AuctionDto(
                    auctionId,
                    matters,
                    locationtxtText,
                    assetsid,
                    time
            );

            boolean isSaved = auctionDetailsBO.saveAuction(auctionDTO);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
            }
        }
    }


    @FXML
    void ClickTable(MouseEvent event) {
            AuctionDto auctionTM = AuctionTable.getSelectionModel().getSelectedItem();
            if (auctionTM != null) {
                idlable.setText(auctionTM.getAuctionID());
                Matterstxt.setText(auctionTM.getLegalMatters());
                locationtxt.setText(auctionTM.getAuctionTime());
                AssetsIDtxt.setText(auctionTM.getLocation());
                timetxt.setText(auctionTM.getAssetsId());

                Save.setDisable(true);

                Delete.setDisable(false);
                Update.setDisable(false);
            }
    }

    @FXML
    void DeletAction(ActionEvent event) throws Exception {
        String austionId =idlable .getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            auctionDetailsBO.deleteAuction(austionId);
            AuctionTable.getItems().remove(AuctionTable.getSelectionModel().getSelectedItem());
            AuctionTable.getSelectionModel().clearSelection();
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
    }


