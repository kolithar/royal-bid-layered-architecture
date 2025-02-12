package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.AuctionDetailsBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.AuctionDetailsDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.BiddersDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.AuctionDto;
import lk.ijse.gdse71.royalbid.DTO.CatogeryDto;
import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;
import lk.ijse.gdse71.royalbid.Entity.Auction;
import lk.ijse.gdse71.royalbid.Entity.Catogery;
import lk.ijse.gdse71.royalbid.Entity.Evaluator;

import java.sql.SQLException;
import java.util.ArrayList;

public class AuctionDetailsBOImpl implements AuctionDetailsBO {

    AuctionDetailsDAO auctionDetailsDAO =
            (AuctionDetailsDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.ActionDetails);



    @Override
    public ArrayList<AuctionDto> getAllAction() throws SQLException, ClassNotFoundException {
        ArrayList<Auction> auctions = auctionDetailsDAO.getAll();
        ArrayList<AuctionDto> auctionDtos = new ArrayList<>();
        for (Auction auction : auctions) {
            auctionDtos.add(new AuctionDto(
                    auction.getAuctionID(),
                    auction.getLegalMatters(),
                    auction.getAuctionTime(),
                    auction.getLocation(),
                    auction.getAssetsId()

            ));
        }

        return auctionDtos;
    }

    @Override
    public boolean saveAuction(AuctionDto dto) throws Exception {
        return auctionDetailsDAO.save(new Auction(dto.getAuctionID(), dto.getLegalMatters(), dto.getAuctionTime(), dto.getLocation(), dto.getAssetsId()));
    }

    @Override
    public void deleteAuction(String Id) throws Exception {
    auctionDetailsDAO.delete(Id);
    }

    @Override
    public boolean updatecatogery(AuctionDto dto) throws Exception {
        return auctionDetailsDAO.update(new Auction(dto.getAuctionID(),dto.getLegalMatters(), dto.getAuctionTime(), dto.getLocation(), dto.getAssetsId()));
    }

    @Override
    public String getNexAuctionId() throws SQLException, ClassNotFoundException {
        return auctionDetailsDAO.generateID();
    }
}
