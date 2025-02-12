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
import lk.ijse.gdse71.royalbid.BO.Custom.CustomerBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.CustomerBOImpl;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;
import lk.ijse.gdse71.royalbid.Model.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerController implements Initializable {

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnReport;

    @FXML
    private Button BtnSave;

    @FXML
    private Button BtnUpdate;

    @FXML
    private TableColumn<CustomerDto, String> ColumCostomerAssetsName;

    @FXML
    private TableColumn<CustomerDto, String> ColumCustomerAddress;

    @FXML
    private TableColumn<CustomerDto, String> ColumCustomerId;

    @FXML
    private TableColumn<CustomerDto, String> ColumCustomerName;

    @FXML
    private TableColumn<CustomerDto, String> ColumCustomerNumber;

    @FXML
    private TableView<CustomerDto> CustTable;

    @FXML
    private TextField CustomerAddressTxt;

    @FXML
    private TextField CustomerAssetsTxt;

    @FXML
    private TextField CustomerNameTxt;

    @FXML
    private TextField CustomerNumberTxt;

    @FXML
    private AnchorPane CustomerPane;

    @FXML
    private Label IDLable;

    @FXML
    void btnReoortOnAction(ActionEvent event) {

    }




CustomerBO customerBO = new CustomerBOImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set table column to cell factory value
        ColumCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        ColumCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        ColumCostomerAssetsName.setCellValueFactory(new PropertyValueFactory<>("CustomersAssetName"));
        ColumCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        ColumCustomerNumber.setCellValueFactory(new PropertyValueFactory<>("CustomerNumber"));

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

        BtnSave.setDisable(false);

        BtnUpdate.setDisable(true);
        BtnDelete.setDisable(true);

        CustomerNameTxt.setText("");
        CustomerAssetsTxt.setText("");
        CustomerAddressTxt.setText("");
        CustomerNumberTxt.setText("");
    }


        private void loadTableData() throws Exception {
            ArrayList<CustomerDto> customerDTOS = customerBO.getAllCustomers(); // Use initialized instance
            ObservableList<CustomerDto> customerTMS = FXCollections.observableArrayList();

            for (CustomerDto customerDTO : customerDTOS) {
                customerTMS.add(new CustomerDto(
                        customerDTO.getCustomerId(),
                        customerDTO.getCustomerName(),
                        customerDTO.getCustomersAssetName(),
                        customerDTO.getCustomerAddress(),
                        customerDTO.getCustomerNumber()
                ));
            }
            CustTable.setItems(customerTMS);
        }

    public void loadNextCustomerId() throws SQLException, ClassNotFoundException {
//        customerModel.helloCustomerModel();
        String nextCustomerId = customerBO.getNextCustomerId();
        IDLable.setText(nextCustomerId);
    }


    @FXML
    public void btnSaveOnAction(ActionEvent event) throws Exception {
        // Get data from UI fields
        String customerId = IDLable.getText();
        String name = CustomerNameTxt.getText();
        String assets = CustomerAssetsTxt.getText();
        String address = CustomerAddressTxt.getText();
        String number = CustomerNumberTxt.getText();

        // Reset border colors
        CustomerNameTxt.setStyle("-fx-border-color: #7367F0;");
        CustomerAssetsTxt.setStyle("-fx-border-color: #7367F0;");
        CustomerAddressTxt.setStyle("-fx-border-color: #7367F0;");
        CustomerNumberTxt.setStyle("-fx-border-color: #7367F0;");

        // Corrected Regex Patterns
        String namePattern = "^[A-Za-z ]+$";
        String assetsPattern = "^[A-Za-z0-9 ]+$";
        String addressPattern = "^[A-Za-z0-9 ,.-]+$";
        String numberPattern = "^[A-Za-z0-9 ,.-]+$";

        // Validate user inputs
        boolean isValidName = name.matches(namePattern);
        boolean isValidAssets = assets.matches(assetsPattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidNumber = number.matches(numberPattern);

        if (!isValidName) {
            CustomerNameTxt.setStyle("-fx-border-color: red;");
            System.out.println("Invalid name.");
        }

        if (!isValidAssets) {
            CustomerAssetsTxt.setStyle("-fx-border-color: red;");
            System.out.println("Invalid assets.");
        }

        if (!isValidAddress) {
            CustomerAddressTxt.setStyle("-fx-border-color: red;");
            System.out.println("Invalid address.");
        }

        if (!isValidNumber) {
            CustomerNumberTxt.setStyle("-fx-border-color: red;");
            System.out.println("Invalid number.");
        }

        // If all inputs are valid, save the customer
        if (isValidName && isValidAssets && isValidAddress && isValidNumber) {
            CustomerDto customerDto = new CustomerDto(customerId, name, assets, address, number);

            // Get an instance of CustomerBO
            CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.Customer);

            // Call saveCustomer on the instance
            boolean isSaved = customerBO.saveCustomer(customerDto);

            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save customer.").show();
            }
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        CustomerDto customerTM = CustTable.getSelectionModel().getSelectedItem();
        if (customerTM != null) {
            IDLable.setText(customerTM.getCustomerId());
            CustomerNameTxt.setText(customerTM.getCustomerName());
            CustomerAssetsTxt.setText(customerTM.getCustomersAssetName());
            CustomerAddressTxt.setText(customerTM.getCustomerAddress());
            CustomerNumberTxt.setText(customerTM.getCustomerNumber());

            BtnSave.setDisable(true);

            BtnDelete.setDisable(false);
            BtnUpdate.setDisable(false);
        }
    }

    @FXML
    void btnSDeletOnAction(ActionEvent event) {
        try {
            String customerId = IDLable.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                // ✅ Get CustomerBO instance from BOFactory
                CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.Customer);

                // ✅ Call deleteCustomer method
                boolean isDeleted = customerBO.deleteCustomer(customerId);

                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete customer!").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }


    @FXML
     void btnUpdateOnAction(ActionEvent event) throws Exception {
        String customerId = IDLable.getText();
        String name = CustomerNameTxt.getText();
        String assets = CustomerAssetsTxt.getText();
        String address = CustomerAddressTxt.getText();
        String number = CustomerNumberTxt.getText();


        // Create DTO
        CustomerDto customerDTO = new CustomerDto(
                customerId,
                name,
                assets,
                address,
                number
        );

        CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.Customer);

        // Call BO method
         boolean isUpdated = customerBO.updateCustomer(customerDTO);

        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update customer!").show();
        }
    }



    }
