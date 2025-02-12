package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.AssetsDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.DTO.AssetsDto;
import lk.ijse.gdse71.royalbid.Entity.Assets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssetsDAOImpl implements AssetsDAO {


    @Override
    public boolean save(Assets entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO assets(AssetsId, AssetsName, AssetsType, AssetsSpecialties, CustomerId, CategoryId, StartAndEnd) VALUES (?,?,?,?,?,?,?)",
                entity.getAssetsId(), entity.getAssetsName(), entity.getAsssetType(),
                entity.getAssetsSpecialties(), entity.getCustomerId(), entity.getCategoryId(), entity.getStartAndEnd()
        );

    }


    @Override
    public boolean  update(Assets entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update assets set  AssetsName=?, AssetsType=?, AssetsSpecialties=? , CustomerId=? , CategoryId =?,StartAndEnd = ? where AssetsId=?",

                entity.getAssetsName(),
                entity.getAsssetType(),
                entity.getAssetsSpecialties(),
                entity.getCustomerId(),
                entity.getCategoryId(),
                entity.getStartAndEnd(),
                entity.getAssetsId()

        );
    }





    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("DELETE FROM Assets WHERE AssetsId = ?", id);
    }

    @Override
    public ArrayList<Assets> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Assets");

        ArrayList<Assets> allAssets = new ArrayList<>();

        while (rst.next()) {
            Assets entity = new Assets(
                    rst.getString("AssetsId"),
                    rst.getString("AssetsName"),
                    rst.getString("AssetsType"),
                    rst.getString("AssetsSpecialties"),
                    rst.getString("CustomerId"),
                    rst.getString("CategoryId"),
                    rst.getString("StartAndEnd")
            );
            allAssets.add(entity);
        }
        return allAssets;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select AssetsId from assets order by AssetsId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "A001";
    }

    @Override
    public ArrayList<String> getAllAssetIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select AssetsId from assets");

        // Create an ArrayList to store the item IDs
        ArrayList<String>  assetsId= new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            assetsId.add(rst.getString(1));
        }

        // Return the list of item IDs
        return assetsId;
    }
}
