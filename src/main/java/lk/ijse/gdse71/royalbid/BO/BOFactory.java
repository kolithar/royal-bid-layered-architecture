package lk.ijse.gdse71.royalbid.BO;

import lk.ijse.gdse71.royalbid.BO.Custom.CustomerBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.*;
import lk.ijse.gdse71.royalbid.Entity.Evaluator;

public class BOFactory {
    private static BOFactory boFactory;

    // Private constructor to enforce Singleton pattern
    private BOFactory() {}

    // Get Singleton instance of BOFactory
    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    // Enum for Business Object types
    public enum BOType {
        Customer,Assets,Evaluatore,Catogery,LegalDou,Finance
    }

    // Factory method to return correct BO instance
    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case Customer:
                return new CustomerBOImpl();
                case Assets:
                    return new AssetsBOImpl();
                    case Evaluatore:
                        return new EvaluatoreBOImpl();
                        case Catogery:
                            return new CatogeryBOImpl();
                            case LegalDou:
                                return new LegalDouBOImpl();
                                case Finance:
                                    return new FinancePageBOImpl();

            default:
                throw new IllegalArgumentException("Invalid BOType: " + boType);
        }
    }
}
