package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.CatogeryDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.DTO.CatogeryDto;
import lk.ijse.gdse71.royalbid.Entity.Catogery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatogeryDAOImpl implements CatogeryDAO {
    @Override
    public boolean save(Catogery entity) throws Exception {
        return SQLUtil.execute(
                "insert into category values (?,?,?)",
                entity.getCategoryId(),
                entity.getCategoryName(),
                entity.getCategoryDesc()

        );
    }

    @Override
    public boolean update(Catogery entity) throws Exception {
         return SQLUtil.execute(
                "update category set CategoryName=?,CategoryDesc=? where CategoryId=?",

                 entity.getCategoryName(),
                 entity.getCategoryDesc(),
                 entity.getCategoryId()

        );
    }

    @Override
    public void delete(String id) throws Exception {
         SQLUtil.execute("delete from category where CategoryId=?",id);
    }

    @Override
    public ArrayList<Catogery> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from category");

        ArrayList<Catogery> catogerys = new ArrayList<>();

        while (rst.next()) {
            Catogery catogery = new Catogery(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            catogerys.add(catogery);
        }


        return catogerys ;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select CategoryId from category order by CategoryId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            System.out.println("substring"+substring);
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001";
    }

    @Override
    public ArrayList<String> getAllabaidderIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select CategoryId from category");

        // Create an ArrayList to store the item IDs
        ArrayList<String>  CatogoryIds= new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            CatogoryIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return CatogoryIds;
    }
}
