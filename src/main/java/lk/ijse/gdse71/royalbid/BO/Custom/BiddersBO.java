package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.BO.SuperBO;
import lk.ijse.gdse71.royalbid.DTO.BiddersDto;
import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BiddersBO extends SuperBO {
    ArrayList<BiddersDto> getAllBidders() throws SQLException, ClassNotFoundException;
    boolean saveBidders(BiddersDto biddersDto) throws Exception;
    void deleteBidder(String biddesId) throws Exception;
    ArrayList<String>  getAllabaidderIds() throws SQLException, ClassNotFoundException;
    String getNextBidderId() throws SQLException, ClassNotFoundException;

}
