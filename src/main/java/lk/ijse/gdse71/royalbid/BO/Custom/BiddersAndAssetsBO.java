package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.DTO.AssetAndEvaluatorDto;
import lk.ijse.gdse71.royalbid.DTO.BiddersAndAssetsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BiddersAndAssetsBO {
    boolean AddAssetAadEvaluatore(BiddersAndAssetsDto  dto) throws Exception;
    void removeAssetAndEvaluator(String assetsId, String biddersId) throws Exception;
    ArrayList<BiddersAndAssetsDto> getAllab() throws SQLException, ClassNotFoundException;

}
