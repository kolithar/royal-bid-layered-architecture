package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.CustomerDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.Entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {



    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO CUSTOMER(CustomerId, CustomerName, CustomersAssetName, CustomerAddress, CustomerNumber) VALUES (?,?,?,?,?)",
                entity.getCustomerId(), entity.getCustomerName(), entity.getCustomersAssetName(),
                entity.getCustomerAddress(), entity.getCustomerNumber()
        );

    }

    @Override
    public void delete(String customerId) throws SQLException, ClassNotFoundException { //  Implement delete method
         SQLUtil.execute("DELETE FROM CUSTOMER WHERE CustomerId = ?", customerId);
    }




    @Override
    public  boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE CUSTOMER SET CustomerName = ?, CustomersAssetName = ?, CustomerAddress = ?, CustomerNumber = ? WHERE CustomerId = ?",
                entity.getCustomerName(),
                entity.getCustomersAssetName(),
                entity.getCustomerAddress(),
                entity.getCustomerNumber(),
                entity.getCustomerId()
        );
    }




    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> customers = new ArrayList<>();

        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString("CustomerId"),
                    resultSet.getString("CustomerName"),
                    resultSet.getString("CustomersAssetName"),
                    resultSet.getString("CustomerAddress"),
                    resultSet.getString("CustomerNumber")
                             );
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select CustomerId from Customer order by CustomerId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            System.out.println("substring"+substring);
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001";
    }


    @Override
    public ArrayList<String> getAllcustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select CustomerId from Customer");

        // Create an ArrayList to store the item IDs
        ArrayList<String>  CustomerIds= new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            CustomerIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return CustomerIds;
    }
}
