package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.BO.SuperBO;
import lk.ijse.gdse71.royalbid.DTO.AssetsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AssetsBO extends SuperBO {

    ArrayList<AssetsDto> getAllAssets() throws SQLException, ClassNotFoundException;
    boolean saveAssets(AssetsDto assetsDto) throws Exception;
    void deleteAssets(String assetsId) throws Exception;
    boolean updateAssets(AssetsDto assetsDto) throws Exception;
    ArrayList<String>  getAllAssetIds() throws SQLException, ClassNotFoundException;
    String getNextAssetsId() throws SQLException, ClassNotFoundException;


}
