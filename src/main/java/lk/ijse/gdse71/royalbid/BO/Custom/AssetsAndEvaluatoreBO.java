package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.DTO.AssetAndEvaluatorDto;
import lk.ijse.gdse71.royalbid.DTO.AuctionDto;
import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AssetsAndEvaluatoreBO {
    boolean AddAssetAadEvaluatore(AssetAndEvaluatorDto dto) throws Exception;
    void removeAssetAndEvaluator(String assetsId, String valuatoreId) throws Exception;
    ArrayList<AssetAndEvaluatorDto> getAllAssetsandEvaluatore() throws SQLException, ClassNotFoundException;
}
