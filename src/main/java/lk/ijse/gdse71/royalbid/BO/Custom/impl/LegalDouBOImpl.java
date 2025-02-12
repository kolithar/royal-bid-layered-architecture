package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.LegalDouBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.FinanceDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.LegalDouDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.LegalDouDto;
import lk.ijse.gdse71.royalbid.DTO.TransactionDto;
import lk.ijse.gdse71.royalbid.Entity.LegalDou;
import lk.ijse.gdse71.royalbid.Entity.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;


public class  LegalDouBOImpl implements LegalDouBO {

    LegalDouDAO legalDouDAO =
            (LegalDouDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.LegalDou);


    @Override
    public boolean saveLedalDoc(LegalDouDto dto) throws Exception {
        return legalDouDAO.save(new LegalDou(dto.getLegalDocumentsId(),dto.getInvoiceNumber(),dto.getDocumentIdURL(),dto.getTypeOFLaw()));
    }

    @Override
    public boolean updatelegalDoc(LegalDouDto dto) throws Exception {
        return legalDouDAO.update(new LegalDou(dto.getLegalDocumentsId(),dto.getInvoiceNumber(),dto.getDocumentIdURL(),dto.getTypeOFLaw()));
    }

    @Override
    public String getNextLegalDocId() throws SQLException, ClassNotFoundException {
        return legalDouDAO.generateID();
    }

    @Override
    public void deleteLegalDoc(String id) throws Exception {
        legalDouDAO.delete(id);
    }

    @Override
    public ArrayList<LegalDouDto> getAllDocuments() throws SQLException, ClassNotFoundException {
        ArrayList<LegalDou> legalDous = legalDouDAO.getAll();
        ArrayList<LegalDouDto> legalDouDtos = new ArrayList<>();

        for (LegalDou legalDou : legalDous) {
            legalDouDtos.add(new LegalDouDto(
                    legalDou.getLegalDocumentsId(),
                    legalDou.getInvoiceNumber(),
                    legalDou.getDocumentIdURL(),
                    legalDou.getTypeOFLaw()

            ));
        }

        return legalDouDtos;
    }
}
