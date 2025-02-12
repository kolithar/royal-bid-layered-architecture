package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.FinanceDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.DTO.TransactionDto;
import lk.ijse.gdse71.royalbid.Entity.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FinanceDAOImpl implements FinanceDAO {
    @Override
    public boolean save(Transaction entity) throws Exception {
        return SQLUtil.execute("INSERT INTO transaction(InvoiceNumber,Amount,MinimumBid,Profit,AssetsId)  VALUES(?,?,?,?,?)",
                entity.getInvoiceNumber(),entity.getAmount(),entity.getMinimumBid(),entity.getProfit(),entity.getAssetsId());

    }

    @Override
    public boolean update(Transaction entity) throws Exception {
           return SQLUtil.execute(
                "update transaction set  Amount=?, MinimumBid=?, Profit=? , AssetsId=? where InvoiceNumber=?",
                entity.getAmount(),
                entity.getMinimumBid(),
                entity.getProfit(),
                entity.getAssetsId(),
                entity.getInvoiceNumber()

        );
    }

    @Override
    public void delete(String id) throws Exception {
        SQLUtil.execute("DELETE FROM transaction WHERE InvoiceNumber=?",id);

    }


    @Override
    public ArrayList<Transaction> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("select * from Transaction");

        ArrayList<Transaction> transactionS = new ArrayList<>();

        while (rst.next()) {
            Transaction entity = new Transaction(
                    rst.getString(1),  // Customer ID
                    rst.getDouble(2),  // Name
                    rst.getDouble(3),  // NIC
                    rst.getDouble(4),  // Email
                    rst.getString(5)   // Phone
            );
            transactionS.add(entity);
        }
        return transactionS;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select InvoiceNumber from Transaction order by InvoiceNumber desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            System.out.println("substring"+substring);
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("F%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "F001";
    }

}

