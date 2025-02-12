package lk.ijse.gdse71.royalbid.DAO.Custom;

import lk.ijse.gdse71.royalbid.DAO.CrudDAO;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;
import lk.ijse.gdse71.royalbid.Entity.Customer;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {
    ArrayList<String>  getAllcustomerIds() throws SQLException, ClassNotFoundException;


}
