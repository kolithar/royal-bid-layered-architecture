package lk.ijse.gdse71.royalbid.DAO.Custom;

import lk.ijse.gdse71.royalbid.DAO.CrudDAO;
import lk.ijse.gdse71.royalbid.Entity.Assets;
import lk.ijse.gdse71.royalbid.Entity.Evaluator;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EvaluatoreDAO extends CrudDAO<Evaluator> {
    ArrayList<String> getAllevaluatorIds() throws SQLException, ClassNotFoundException;

}
