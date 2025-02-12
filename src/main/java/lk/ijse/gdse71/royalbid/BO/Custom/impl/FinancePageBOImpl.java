package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.FinanceBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.EvaluatoreDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.FinanceDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;
import lk.ijse.gdse71.royalbid.DTO.TransactionDto;
import lk.ijse.gdse71.royalbid.Entity.Evaluator;
import lk.ijse.gdse71.royalbid.Entity.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class FinancePageBOImpl implements FinanceBO {


    FinanceDAO financeDAO =
            (FinanceDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.Finance);


    @Override
    public ArrayList<TransactionDto> getAllFinance() throws SQLException, ClassNotFoundException {
        ArrayList<Transaction> transactions = financeDAO.getAll();
        ArrayList<TransactionDto> transactionDtos = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDtos.add(new TransactionDto(
                    transaction.getInvoiceNumber(),
                    transaction.getAmount(),
                    transaction.getMinimumBid(),
                    transaction.getProfit(),
                    transaction.getAssetsId()
            ));
        }

        return transactionDtos;
    }
    
    @Override
    public boolean saveTransaction(TransactionDto dto) throws Exception {
        return financeDAO.save(new Transaction(dto.getInvoiceNumber(),dto.getAmount(),dto.getMinimumBid(),dto.getProfit(),dto.getAssetsId()));
    }

    @Override
    public void deleteevaluatore(String assetsId) throws Exception {
        financeDAO.delete(assetsId);

    }

    @Override
    public boolean updateTransaction(TransactionDto dto) throws Exception {
        return financeDAO.update(new Transaction(dto.getInvoiceNumber(),dto.getAmount(),dto.getMinimumBid(),dto.getProfit(),dto.getAssetsId()  ));
    }

    @Override
    public String getNextInvoic() throws SQLException, ClassNotFoundException {
        return financeDAO.generateID();
    }
}
