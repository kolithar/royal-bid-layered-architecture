package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.AssetsAndEvaluatoreBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.AssetsAndEvaluatoreDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.EvaluatoreDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.AssetAndEvaluatorDto;
import lk.ijse.gdse71.royalbid.DTO.AssetsDto;
import lk.ijse.gdse71.royalbid.DTO.AuctionDto;
import lk.ijse.gdse71.royalbid.DTO.BiddersDto;
import lk.ijse.gdse71.royalbid.Entity.AssetAndEvaluator;
import lk.ijse.gdse71.royalbid.Entity.Assets;
import lk.ijse.gdse71.royalbid.Entity.Bidders;

import java.sql.SQLException;
import java.util.ArrayList;

public class AssetsAndEvaluatoreBOImpl implements AssetsAndEvaluatoreBO {

    AssetsAndEvaluatoreDAO assetsAndEvaluatoreDAO =
            (AssetsAndEvaluatoreDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.AssetsAndEvaluatore);

    @Override
    public boolean AddAssetAadEvaluatore(AssetAndEvaluatorDto dto) throws Exception {
        return assetsAndEvaluatoreDAO.save(new AssetAndEvaluator(dto.getAssetsId(),dto.getEvaluatorId()));
    }

    @Override
    public void removeAssetAndEvaluator(String assetsId,String valuatoreId) throws Exception {
             assetsAndEvaluatoreDAO.delete(assetsId,valuatoreId);
    }

    @Override
    public ArrayList<AssetAndEvaluatorDto> getAllAssetsandEvaluatore() throws SQLException, ClassNotFoundException {
        ArrayList<AssetAndEvaluator> assetAndEvaluators = assetsAndEvaluatoreDAO.getAll();// Fetch evaluarore from the DAO
        ArrayList<AssetAndEvaluatorDto> assetAndEvaluatorDtos = new ArrayList<>();

        for (AssetAndEvaluator asset : assetAndEvaluators) {
            assetAndEvaluatorDtos.add(new AssetAndEvaluatorDto(asset.getAssetsId(),asset.getEvaluatorId()));
        }



            return assetAndEvaluatorDtos;
    }
}
