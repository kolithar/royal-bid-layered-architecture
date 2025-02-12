package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.DTO.AuctionDto;
import lk.ijse.gdse71.royalbid.DTO.CatogeryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AuctionDetailsBO {
    ArrayList<AuctionDto> getAllAction() throws SQLException, ClassNotFoundException;
    boolean saveAuction(AuctionDto dto) throws Exception;
    void deleteAuction(String Id) throws Exception;
    boolean updatecatogery(AuctionDto dto) throws Exception;
    String getNexAuctionId() throws SQLException, ClassNotFoundException;
}
