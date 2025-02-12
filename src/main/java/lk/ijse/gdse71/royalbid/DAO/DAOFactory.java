package lk.ijse.gdse71.royalbid.DAO;

import lk.ijse.gdse71.royalbid.BO.Custom.impl.BiddersBOImpl;
import lk.ijse.gdse71.royalbid.DAO.Custom.CustomerDAO;
import lk.ijse.gdse71.royalbid.DAO.Custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType {
        Customer,Assets,Evaluatore,Bidder,Finance,LegalDou,Catogery,ActionDetails,AssetsAndEvaluatore,BiddersAndAssets

    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case Customer:
                return new CustomerDAOImpl();
                case Assets:
                    return new AssetsDAOImpl();
                    case Evaluatore:
                        return new EvaluatoreDAOImpl();
                        case Bidder:
                            return new BiddersDAOImpl();
                            case Finance:
                                return new FinanceDAOImpl();
                                case LegalDou:
                                    return new LegalDouDAOImpl();
                                    case Catogery:
                                        return new CatogeryDAOImpl();
                                        case ActionDetails:
                                            return new AuctionDetailsDAOImpl();
                                            case AssetsAndEvaluatore:
                                                return new AssetsAndEvaluatoreDAOImpl();
                                                case BiddersAndAssets:
                                                    return new BiddersAndAssetsDAOImpl();




            default:
                throw new IllegalArgumentException("Invalid DAO type");
        }
    }
}
