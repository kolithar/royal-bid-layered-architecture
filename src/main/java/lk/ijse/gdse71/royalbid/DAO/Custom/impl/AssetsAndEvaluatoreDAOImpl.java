package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.AssetsAndEvaluatoreDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.DTO.AssetAndEvaluatorDto;
import lk.ijse.gdse71.royalbid.Entity.AssetAndEvaluator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssetsAndEvaluatoreDAOImpl implements AssetsAndEvaluatoreDAO {
    @Override
    public boolean save(AssetAndEvaluator entity) throws Exception {
        String query = "INSERT INTO AssetsForEvaluator (EvaluatorId, AssetsId) VALUES (?, ?)"; // Correct table name
        try {
            // Debugging: Print the values being inserted
            System.out.println("Saving data with EvaluatorId: " + entity.getEvaluatorId() + " and AssetsId: " + entity.getAssetsId());

            // Check if the EvaluatorId and AssetsId exist in their respective tables


            return SQLUtil.execute(query, entity.getEvaluatorId(), entity.getAssetsId());
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error while saving asset-evaluator: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(AssetAndEvaluator entity) throws Exception {
        return false;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public void delete(String assetsId, String evaluatorId) throws Exception {
        String query = "DELETE FROM AssetsForEvaluator WHERE EvaluatorId = ? AND AssetsId = ?";
        try {
             SQLUtil.execute(query, evaluatorId,assetsId);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error while deleting asset-evaluator: " + e.getMessage());
            e.printStackTrace();

        }
    }

    @Override
    public ArrayList<AssetAndEvaluator> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM AssetsForEvaluator");  // Corrected table name
        ArrayList<AssetAndEvaluator> assetAndEvaluatorList = new ArrayList<>();

        while (rst.next()) {
            AssetAndEvaluator dto = new AssetAndEvaluator(
                    rst.getString("EvaluatorId"),
                    rst.getString("AssetsId")
            );
            assetAndEvaluatorList.add(dto);
        }
        return assetAndEvaluatorList;
    }


    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
