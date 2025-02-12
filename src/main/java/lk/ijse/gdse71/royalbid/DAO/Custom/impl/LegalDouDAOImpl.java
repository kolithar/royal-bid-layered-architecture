package lk.ijse.gdse71.royalbid.DAO.Custom.impl;

import lk.ijse.gdse71.royalbid.DAO.Custom.LegalDouDAO;
import lk.ijse.gdse71.royalbid.DAO.SQLUtil;
import lk.ijse.gdse71.royalbid.DTO.LegalDouDto;
import lk.ijse.gdse71.royalbid.Entity.LegalDou;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LegalDouDAOImpl implements LegalDouDAO {
    @Override
    public boolean save(LegalDou entity) throws Exception {
        return SQLUtil.execute(
                "insert into legaldocuments values (?,?,?,?)",
                entity.getLegalDocumentsId(),
                entity.getInvoiceNumber(),
                entity.getDocumentIdURL(),
                entity.getTypeOFLaw()

        );
    }


    @Override
    public boolean update(LegalDou entity) throws Exception {
        return SQLUtil.execute(
                "update legaldocuments set InvoiceNumber=?,DocumentIdURL=?,TypeOFLaw=? where LegalDocumentsId=?",

                entity.getInvoiceNumber(),
                entity.getDocumentIdURL(),
                entity.getTypeOFLaw(),
                entity.getLegalDocumentsId()
        );
    }



    @Override
    public void delete(String id) throws Exception {
         SQLUtil.execute("delete from legaldocuments where LegalDocumentsId=?", id);
    }

    @Override
    public ArrayList<LegalDou> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("select * from legaldocuments");

        ArrayList<LegalDou> legalDous = new ArrayList<>();

        while (rst.next()) {
            LegalDou legalDou = new LegalDou(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getString(3)
            );
            legalDous.add(legalDou);
        }
        return legalDous;
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select LegalDocumentsId from legaldocuments order by LegalDocumentsId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            System.out.println("substring"+substring);
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("D%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "D001";
    }


}
