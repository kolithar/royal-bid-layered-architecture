package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.BO.SuperBO;
import lk.ijse.gdse71.royalbid.DTO.LegalDouDto;
import lk.ijse.gdse71.royalbid.DTO.TransactionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LegalDouBO extends SuperBO {
    ArrayList<LegalDouDto> getAllDocuments() throws SQLException, ClassNotFoundException;
    boolean saveLedalDoc(LegalDouDto legalDouDto) throws Exception;
    void deleteLegalDoc(String Id) throws Exception;
    boolean updatelegalDoc(LegalDouDto legalDouDto) throws Exception;
    String getNextLegalDocId() throws SQLException, ClassNotFoundException;
}
