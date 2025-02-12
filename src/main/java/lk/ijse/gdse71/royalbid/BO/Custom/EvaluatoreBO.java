package lk.ijse.gdse71.royalbid.BO.Custom;

import lk.ijse.gdse71.royalbid.BO.SuperBO;
import lk.ijse.gdse71.royalbid.DTO.AssetsDto;
import lk.ijse.gdse71.royalbid.DTO.CustomerDto;
import lk.ijse.gdse71.royalbid.DTO.EvaluatorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EvaluatoreBO extends SuperBO {
    ArrayList<EvaluatorDto> getAllEvaluator() throws SQLException, ClassNotFoundException;
    boolean saveevaluatore(EvaluatorDto evaluatorDto) throws Exception;
    void deleteevaluatore(String assetsId) throws Exception;
    boolean updateEvaluator(EvaluatorDto evaluatorDto) throws Exception;
    String getNextEvaluatoerId() throws SQLException, ClassNotFoundException;
    ArrayList<String>  getAllEvaluatorIds() throws SQLException, ClassNotFoundException;
}
