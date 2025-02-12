package lk.ijse.gdse71.royalbid.DAO.Custom;

import lk.ijse.gdse71.royalbid.DAO.CrudDAO;
import lk.ijse.gdse71.royalbid.Entity.Bidders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BiddersDAO extends CrudDAO<Bidders> {
    ArrayList<String> getAllBiddersId() throws SQLException, ClassNotFoundException;
}
