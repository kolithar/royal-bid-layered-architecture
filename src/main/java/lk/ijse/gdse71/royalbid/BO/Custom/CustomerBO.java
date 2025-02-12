package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.BO.SuperBO;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
     boolean saveCustomer(CustomerDto customerDto) throws Exception;
     boolean deleteCustomer(String customerId) throws Exception;
     boolean updateCustomer(CustomerDto customerDto) throws Exception;
     ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;
     String getNextCustomerId() throws SQLException, ClassNotFoundException;
     ArrayList<String>  getAllCustomerIds() throws SQLException, ClassNotFoundException;

}
