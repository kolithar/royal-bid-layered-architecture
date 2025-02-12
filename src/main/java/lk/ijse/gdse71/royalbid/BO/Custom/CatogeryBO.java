package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.BO.SuperBO;
import lk.ijse.gdse71.royalbid.DTO.CatogeryDto;
import lk.ijse.gdse71.royalbid.DTO.LegalDouDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CatogeryBO extends SuperBO {
    ArrayList<CatogeryDto> getAllCatogery() throws SQLException, ClassNotFoundException;
    boolean savecategory(CatogeryDto dto) throws Exception;
    void deletecatogery(String Id) throws Exception;
    boolean updatecatogery(CatogeryDto dto) throws Exception;
    String getNextCatogeryID() throws SQLException, ClassNotFoundException;
    ArrayList<String>  getAllCatogeryIds() throws SQLException, ClassNotFoundException;
}
