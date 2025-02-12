package lk.ijse.gdse71.royalbid.BO.Custom.impl;

import lk.ijse.gdse71.royalbid.BO.Custom.EvaluatoreBO;
import lk.ijse.gdse71.royalbid.DAO.Custom.AssetsDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.EvaluatoreDAO;
import lk.ijse.gdse71.royalbid.DAO.DAOFactory;
import lk.ijse.gdse71.royalbid.DTO.AssetsDto;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;
import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;
import lk.ijse.gdse71.royalbid.Entity.Assets;
import lk.ijse.gdse71.royalbid.Entity.Evaluator;

import java.sql.SQLException;
import java.util.ArrayList;

public class EvaluatoreBOImpl implements EvaluatoreBO {

    EvaluatoreDAO evaluatoreDAO =
            (EvaluatoreDAO) DAOFactory.getInstance().
                    getDAO(DAOFactory.DAOType.Evaluatore);

    @Override
    public ArrayList<EvaluatorDto> getAllEvaluator() throws SQLException, ClassNotFoundException {
        ArrayList<Evaluator> evaluators = evaluatoreDAO.getAll(); // Fetch evaluarore from the DAO
        ArrayList<EvaluatorDto> evaluatorDtos = new ArrayList<>();

        for (Evaluator evaluator : evaluators) {
            evaluatorDtos.add(new EvaluatorDto(
                    evaluator.getEvaluatorId(),
                    evaluator.getEvaluatorName(),
                    evaluator.getEvaluatorAddress(),
                    evaluator.getEvaluatorNumber(),
                    evaluator.getEvaluatorOk()

            ));
        }
        return evaluatorDtos;
    }

    @Override
    public boolean saveevaluatore(EvaluatorDto dto) throws Exception {
        return evaluatoreDAO.save(new Evaluator(dto.getEvaluatorId(),dto.getEvaluatorName(),dto.getEvaluatorAddress(),dto.getEvaluatorNumber(),dto.getEvaluatorOk()));
    }

    @Override
    public void deleteevaluatore(String assetsId) throws Exception {
     evaluatoreDAO.delete(assetsId);
    }

    @Override
    public boolean updateEvaluator(EvaluatorDto dto) throws Exception {
        return evaluatoreDAO.update(new Evaluator(dto.getEvaluatorId(),dto.getEvaluatorName(),dto.getEvaluatorAddress(),dto.getEvaluatorNumber(),dto.getEvaluatorOk()) );
    }

    @Override
    public String getNextEvaluatoerId() throws SQLException, ClassNotFoundException {
        return evaluatoreDAO.generateID();
    }

    @Override
    public ArrayList<String> getAllEvaluatorIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> evaluatoreId = evaluatoreDAO.getAllevaluatorIds();
        return evaluatoreId;
    }
}
