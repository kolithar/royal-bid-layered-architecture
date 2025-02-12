package lk.ijse.gdse71.royalbid.DAO.Custom;

import lk.ijse.gdse71.royalbid.DAO.CrudDAO;
import lk.ijse.gdse71.royalbid.DAO.SuperDAO;
import lk.ijse.gdse71.royalbid.DTO.AssetsDto;
import lk.ijse.gdse71.royalbid.Entity.Assets;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AssetsDAO extends CrudDAO<Assets> {
    ArrayList<String>  getAllAssetIds() throws SQLException, ClassNotFoundException;

}
