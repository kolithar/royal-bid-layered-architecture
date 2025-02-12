package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.BiddersBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.BiddersDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.EvaluatoreDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.BiddersDto;
import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;
import lk.ijse.gdse71.royalbid.Entity.Bidders;
import lk.ijse.gdse71.royalbid.Entity.Evaluator;

import java.sql.SQLException;
import java.util.ArrayList;

public class BiddersBOImpl implements BiddersBO {

    BiddersDAO biddersDAO =
            (BiddersDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.Bidder);



    @Override
    public ArrayList<BiddersDto> getAllBidders() throws SQLException, ClassNotFoundException {
        ArrayList<Bidders> bidders = biddersDAO.getAll(); // Fetch evaluarore from the DAO
        ArrayList<BiddersDto> biddersDtos = new ArrayList<>();

        for (Bidders bidder : bidders) {
            biddersDtos.add(new BiddersDto(
                    bidder.getBidderId(),
                    bidder.getBidersName(),
                    bidder.getBiderAddres(),
                    bidder.getBiddersProfession(),
                    bidder.getBidderRegisDate()

            ));
        }

        return biddersDtos;
    }

    @Override
    public boolean saveBidders(BiddersDto Dto) throws Exception {
          return biddersDAO.save(new Bidders(Dto.getBidderId(),Dto.getBidersName(),Dto.getBiderAddres() ,Dto.getBiddersProfession(),Dto.getBidderRegisDate()));
    }

    @Override
    public void deleteBidder(String biddesId) throws Exception {
           biddersDAO.delete(biddesId);
    }

    @Override
    public ArrayList<String> getAllabaidderIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> biddersid = biddersDAO.getAllBiddersId();
        return biddersid;
    }

    @Override
    public String getNextBidderId() throws SQLException, ClassNotFoundException {
        return biddersDAO.generateID();
    }




}
