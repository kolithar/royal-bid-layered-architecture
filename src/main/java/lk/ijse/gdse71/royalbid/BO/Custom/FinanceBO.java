package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;
import lk.ijse.gdse71.royalbid.DTO.TransactionDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FinanceBO  {
    ArrayList<TransactionDto> getAllFinance() throws SQLException, ClassNotFoundException;
    boolean saveTransaction(TransactionDto dto) throws Exception;
    void deleteevaluatore(String assetsId) throws Exception;
    boolean updateTransaction(TransactionDto dto) throws Exception;
    String getNextInvoic() throws SQLException, ClassNotFoundException;
}
