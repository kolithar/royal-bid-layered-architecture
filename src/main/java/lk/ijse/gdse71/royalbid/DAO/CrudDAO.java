package lk.ijse.gdse71.royalbid.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {

     boolean save(T entity) throws Exception;

     boolean update(T entity) throws Exception; // Added update method

     void delete(String id) throws Exception;

     ArrayList<T>  getAll() throws SQLException,ClassNotFoundException;

     String generateID() throws SQLException, ClassNotFoundException ;

}
