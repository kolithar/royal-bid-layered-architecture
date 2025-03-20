package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.BiddersAndAssetsBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.BiddersAndAssetsDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.EvaluatoreDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.AssetAndEvaluatorDto;
import lk.ijse.gdse71.royalbid.DTO.BiddersAndAssetsDto;
import lk.ijse.gdse71.royalbid.Entity.AssetAndEvaluator;
import lk.ijse.gdse71.royalbid.Entity.BiddersAndAssets;

import java.sql.SQLException;
import java.util.ArrayList;

public class BiddersAndAssetsBOImpl implements BiddersAndAssetsBO {


    BiddersAndAssetsDAO biddersAndAssetsDAO =
            (BiddersAndAssetsDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.BiddersAndAssets);

    @Override
    public boolean AddAssetAadEvaluatore(BiddersAndAssetsDto dto) throws Exception {
        return biddersAndAssetsDAO.save(new BiddersAndAssets(dto.getAssetsId(),dto.getBiddersId()));
    }

    @Override
    public void removeAssetAndEvaluator(String assetsId, String biddersId) throws Exception {
        biddersAndAssetsDAO.delete(assetsId,biddersId);
    }

    @Override
    public ArrayList<BiddersAndAssetsDto> getAllab () throws SQLException, ClassNotFoundException {
        ArrayList<BiddersAndAssets> biddersAndAssets = biddersAndAssetsDAO.getAll();// Fetch evaluarore from the DAO
        ArrayList<BiddersAndAssetsDto> biddersAndAssetsDtos = new ArrayList<>();

        for (BiddersAndAssets biddersAndAssets1 : biddersAndAssets) {
            biddersAndAssetsDtos.add(new BiddersAndAssetsDto(biddersAndAssets1.getAssetsId(),biddersAndAssets1.getBiddersId()));
        }

        return biddersAndAssetsDtos;

    }
}
