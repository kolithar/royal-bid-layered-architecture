package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.AssetsBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.AssetsDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.AssetsDto;
import lk.ijse.gdse71.royalbid.Entity.Assets;

import java.sql.SQLException;
import java.util.ArrayList;

public class AssetsBOImpl implements AssetsBO {
    AssetsDAO assetsDAO =
            (AssetsDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.Assets);


    @Override
    public ArrayList<AssetsDto> getAllAssets() throws SQLException, ClassNotFoundException {
        ArrayList<Assets> assets = assetsDAO.getAll(); // Fetch assets from the DAO
        ArrayList<AssetsDto> assetsDtos = new ArrayList<>();

        for (Assets asset : assets) {
            assetsDtos.add(new AssetsDto(
                    asset.getAssetsId(),
                    asset.getAssetsName(),
                    asset.getAsssetType(),
                    asset.getAssetsSpecialties(),
                    asset.getCustomerId(),
                    asset.getCategoryId(),
                    asset.getStartAndEnd()
            ));
        }
        return assetsDtos;
    }

    @Override
    public boolean saveAssets(AssetsDto dto) throws Exception {
        return assetsDAO.save(new Assets(dto.getAssetsId(),
                dto.getAssetsName(),
                dto.getAsssetType(),
                dto.getAssetsSpecialties(),
                dto.getCustomerId(),
                dto.getCategoryId(),
                dto.getStartAndEnd()));
    }

    @Override
    public void deleteAssets(String assetsId) throws Exception {
         assetsDAO.delete(assetsId); ;
    }

    @Override
    public boolean updateAssets(AssetsDto dto) throws Exception {
        return assetsDAO.update(new Assets(dto.getAssetsId(),dto.getAssetsName(), dto.getAsssetType(), dto.getAssetsSpecialties(), dto.getCustomerId(), dto.getCategoryId(), dto.getStartAndEnd()));
    }

    @Override
    public ArrayList<String> getAllAssetIds() throws SQLException, ClassNotFoundException {

        ArrayList<String> assetsIds = assetsDAO.getAllAssetIds();
        return assetsIds;
    }

    @Override
    public String getNextAssetsId() throws SQLException, ClassNotFoundException {
        return assetsDAO.generateID();
    }


}