package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.BiddersAndAssetsDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.DTO.BiddersAndAssetsDto;
import lk.ijse.gdse71.royalbid.Entity.BiddersAndAssets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BiddersAndAssetsDAOImpl implements BiddersAndAssetsDAO {
    @Override
    public boolean save(BiddersAndAssets entity) throws Exception {
       String sql = "INSERT INTO assetsandbidders (AssetsId, BiddersId) VALUES (?, ?)";
        return SQLUtil.execute(sql, entity.getAssetsId(), entity.getBiddersId());
    }

    @Override
    public boolean update(BiddersAndAssets entity) throws Exception {
        return false;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public void delete(String assetsId, String bidderId
    ) throws Exception {
        String sql = "DELETE FROM assetsandbidders WHERE AssetsId = ? AND BiddersId = ?";
         SQLUtil.execute(sql, assetsId, bidderId);
    }

    @Override
    public ArrayList<BiddersAndAssets> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from assetsandbidders");

        ArrayList<BiddersAndAssets> ab = new ArrayList<>();

        while (rst.next()) {
            BiddersAndAssets abDto = new BiddersAndAssets(
                    rst.getString(1),
                    rst.getString(2)

            );
            ab.add(abDto);
        }
        return ab;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
