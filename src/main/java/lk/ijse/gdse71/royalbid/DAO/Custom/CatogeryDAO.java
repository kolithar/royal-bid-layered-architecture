package lk.ijse.gdse71.royalbid.DAO.Custom;

import lk.ijse.gdse71.royalbid.DAO.CrudDAO;
import lk.ijse.gdse71.royalbid.Entity.Catogery;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CatogeryDAO extends CrudDAO<Catogery> {
    ArrayList<String> getAllabaidderIds() throws SQLException, ClassNotFoundException;

}
