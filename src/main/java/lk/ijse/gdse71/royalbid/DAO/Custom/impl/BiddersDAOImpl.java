package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.BiddersDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.DTO.BiddersDto;
import lk.ijse.gdse71.royalbid.Entity.Bidders;
import lk.ijse.gdse71.royalbid.Entity.Evaluator;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BiddersDAOImpl implements BiddersDAO {
    @Override
    public boolean save(Bidders entity) throws Exception {

        return SQLUtil.execute("INSERT INTO bidders(BiddersId,BiddersName,BiddersAddress,BiddersProfession,RegistrationDate) VALUES(?,?,?,?,?)",
                entity.getBidderId(),entity.getBidersName(),entity.getBiderAddres(),entity.getBiddersProfession(),entity.getBidderRegisDate()
        );

    }

    @Override
    public boolean update(Bidders entity) throws Exception {
        return false;
    }

    @Override
    public void delete(String id) throws Exception {
        SQLUtil.execute("DELETE FROM bidders WHERE BiddersId=?",id);
    }

    @Override
    public ArrayList<Bidders> getAll() throws SQLException, ClassNotFoundException {


        ResultSet rst = SQLUtil.execute("SELECT * FROM Bidders");
        ArrayList<Bidders> biddersList = new ArrayList<>();

        while (rst.next()) {
            String bidderId = rst.getString(1);
            String bidderName = rst.getString(2);
            String bidderAddress = rst.getString(3);
            String bidderProfession = rst.getString(4);
            Date registrationDate = rst.getDate(5);

            // Handle null registration date
            LocalDate localDate = (registrationDate != null) ? registrationDate.toLocalDate() : null;

            Bidders bidder = new Bidders(bidderId, bidderName, bidderAddress, bidderProfession, localDate);
            biddersList.add(bidder);
        }
        return biddersList;

       }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT BiddersId FROM Bidders ORDER BY BiddersId DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last bidder ID
            String numericPart = lastId.substring(1); // Extract numeric part
            int newIdIndex = Integer.parseInt(numericPart) + 1; // Increment numeric part
            return String.format("B%03d", newIdIndex); // Format as "Bnnn"
        }
        return "B001";
    }

    @Override
    public ArrayList<String> getAllBiddersId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select BiddersId from bidders");

        // Create an ArrayList to store the item IDs
        ArrayList<String>  BiddersIds= new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            BiddersIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return BiddersIds;

    }
}

