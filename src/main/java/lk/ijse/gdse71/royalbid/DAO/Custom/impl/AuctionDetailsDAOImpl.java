package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.AuctionDetailsDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.DTO.AuctionDto;
import lk.ijse.gdse71.royalbid.Entity.Auction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuctionDetailsDAOImpl implements AuctionDetailsDAO {


    @Override
    public boolean save(Auction entity) throws Exception {
        return SQLUtil.execute(
                "insert into auctiondetails values (?,?,?,?,?)",
                entity.getAuctionID(),
                entity.getLegalMatters(),
                entity.getAuctionTime(),
                entity.getLocation(),
                entity.getAssetsId()
        );
    }

    @Override
    public boolean update(Auction entity) throws Exception {
        return false;
    }

    @Override
    public void delete(String id) throws Exception {
        SQLUtil.execute("delete from auctiondetails where AuctionID=?", id);
    }

    @Override
    public ArrayList<Auction> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("select * from auctiondetails");

        ArrayList<Auction> auctioDTOS = new ArrayList<>();

        while (rst.next()) {
            Auction auctionDTO = new Auction(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getString(4),  // Email
                    rst.getString(5)   // Phone
            );
            auctioDTOS.add(auctionDTO);
        }


        return auctioDTOS;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select AuctionID from auctiondetails order by AuctionID desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            System.out.println("substring"+substring);
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("A%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "A001";
    }
}
