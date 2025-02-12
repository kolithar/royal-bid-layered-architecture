package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.EvaluatoreDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.Entity.Assets;
import lk.ijse.gdse71.royalbid.Entity.Evaluator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EvaluatoreDAOImpl implements EvaluatoreDAO {

    @Override
    public boolean save(Evaluator entity) throws Exception {
        return SQLUtil.execute("INSERT INTO evaluator(EvaluatorId,EvaluatorName,EvaluatorAddress,EvaluatorNumber,EvaluatorOk) VALUES(?,?,?,?,?)",
                entity.getEvaluatorId(),entity.getEvaluatorName(),entity.getEvaluatorAddress(),entity.getEvaluatorNumber(),entity.getEvaluatorOk()
        );
    }

    @Override
    public boolean update(Evaluator entity) throws Exception {
        return SQLUtil.execute(
                "update evaluator set  EvaluatorName=?, EvaluatorAddress=?, EvaluatorNumber=? , EvaluatorOk=? where EvaluatorId=?",
                 entity.getEvaluatorName(),
                entity.getEvaluatorAddress(),
                entity.getEvaluatorNumber(),
                entity.getEvaluatorOk(),
                entity.getEvaluatorId()

                );
    }

    @Override
    public void delete(String id) throws Exception {
SQLUtil.execute("DELETE FROM evaluator WHERE EvaluatorId=?",id);
    }

    @Override
    public ArrayList<Evaluator> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM evaluator");

        ArrayList<Evaluator> allevaluator = new ArrayList<>();

        while (rst.next()) {
            Evaluator entity = new Evaluator(
                    rst.getString("EvaluatorId"),
                    rst.getString("EvaluatorName"),
                    rst.getString("EvaluatorAddress"),
                     rst.getString("EvaluatorNumber"),
                    rst.getString("EvaluatorOk")
            );
            allevaluator.add(entity);
        }
        return allevaluator;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select EvaluatorId from evaluator order by EvaluatorId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("E%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "E001";
    }

    @Override
    public ArrayList<String> getAllevaluatorIds() throws SQLException, ClassNotFoundException {
        // Execute SQL query to get all item IDs
        ResultSet rst = SQLUtil.execute("select EvaluatorId from evaluator");

        // Create an ArrayList to store the item IDs
        ArrayList<String>  EvaluatorIds= new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            EvaluatorIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return EvaluatorIds;
    }
}
