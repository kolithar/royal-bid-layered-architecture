package lk.ijse.gdse71.royalbid.BO;

import lk.ijse.gdse71.royalbid.BO.Custom.CustomerBO;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.AssetsBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.BiddersBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.CustomerBOImpl;
import lk.ijse.gdse71.royalbid.BO.Custom.impl.EvaluatoreBOImpl;
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
        Customer,Assets,Evaluatore
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

            default:
                throw new IllegalArgumentException("Invalid BOType: " + boType);
        }
    }
}
