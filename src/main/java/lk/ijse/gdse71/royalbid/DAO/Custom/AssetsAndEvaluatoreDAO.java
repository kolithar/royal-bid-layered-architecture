package lk.ijse.gdse71.royalbid.DAO.Custom;

import lk.ijse.gdse71.royalbid.DAO.CrudDAO;
import lk.ijse.gdse71.royalbid.Entity.AssetAndEvaluator;

public interface AssetsAndEvaluatoreDAO extends CrudDAO<AssetAndEvaluator> {
    public void delete(String assetsId, String evaluatorId) throws Exception;
}
