package lk.ijse.gdse71.royalbid.DAO.Custom;

import lk.ijse.gdse71.royalbid.DAO.CrudDAO;
import lk.ijse.gdse71.royalbid.Entity.BiddersAndAssets;

public interface BiddersAndAssetsDAO extends CrudDAO<BiddersAndAssets> {
    void delete(String assetsId, String valuatoreId) throws Exception;
}
