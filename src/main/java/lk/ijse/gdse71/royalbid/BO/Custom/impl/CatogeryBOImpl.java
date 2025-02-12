package lk.ijse.gdse71.royalbid.BO.Custom.impl;
import lk.ijse.gdse71.royalbid.BO.Custom.CatogeryBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.CatogeryDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.FinanceDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.CatogeryDto;
import lk.ijse.gdse71.royalbid.DTO.TransactionDto;
import lk.ijse.gdse71.royalbid.Entity.Catogery;
import lk.ijse.gdse71.royalbid.Entity.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class CatogeryBOImpl implements CatogeryBO {

    CatogeryDAO catogeryDAO =
            (CatogeryDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.Catogery);

    @Override
    public ArrayList<CatogeryDto> getAllCatogery() throws SQLException, ClassNotFoundException {
        ArrayList<Catogery> catogeries = catogeryDAO.getAll();
        ArrayList<CatogeryDto> catogeryDtos = new ArrayList<>();

        for (Catogery catogery : catogeries) {
            catogeryDtos.add(new CatogeryDto(catogery.getCategoryId(),catogery.getCategoryName(),catogery.getCategoryDesc()));

        }

        return catogeryDtos;
    }

    @Override
    public boolean savecategory(CatogeryDto dto)  throws Exception {
        return catogeryDAO.save(new Catogery(dto.getCategoryId(),dto.getCategoryName(),dto.getCategoryDesc()));
    }

    @Override
    public void deletecatogery(String Id) throws Exception {
        catogeryDAO.delete(Id);
    }

    @Override
    public boolean updatecatogery(CatogeryDto dto) throws Exception {
        return catogeryDAO.update(new Catogery(dto.getCategoryId(),dto.getCategoryName(),dto.getCategoryDesc()));
    }

    @Override
    public String getNextCatogeryID() throws SQLException, ClassNotFoundException {
        return catogeryDAO.generateID();
    }

    @Override
    public ArrayList<String> getAllCatogeryIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> catogeryIds = catogeryDAO.getAllabaidderIds();
        return catogeryIds;
    }
}
