package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.CustomerBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.CatogeryDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DAO.Custom.CustomerDAO;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;
import lk.ijse.gdse71.royalbid.Entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO =
            (CustomerDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.Customer);

    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            customerDtos.add(new CustomerDto(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomersAssetName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerNumber()
            ));
        }

        return customerDtos;
    }

    @Override
    public String getNextCustomerId () throws SQLException, ClassNotFoundException {
        return customerDAO.generateID();
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = customerDAO.getAllcustomerIds();
        return customerIds;
    }


    @Override
      public boolean saveCustomer(CustomerDto customerDto) throws Exception {


        // Convert DTO to Entity
        Customer customer = new Customer(
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getCustomersAssetName(),
                customerDto.getCustomerAddress(),
                customerDto.getCustomerNumber()
        );


        return customerDAO.save(customer);
    }

    @Override
    public boolean deleteCustomer(String customerId) throws Exception { //  Implement deleteCustomer
         customerDAO.delete(customerId);
        return false;
    }


    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws Exception {
        Customer customer = new Customer(
                customerDto.getCustomerId(),
                customerDto.getCustomerName(),
                customerDto.getCustomersAssetName(),
                customerDto.getCustomerAddress(),
                customerDto.getCustomerNumber()
        );
        return customerDAO.update(customer);
    }





}



