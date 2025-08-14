package lk.ijse.project_01.dao;

import lk.ijse.project_01.bo.Custom.Impl.FoodOrderBOImpl;
import lk.ijse.project_01.bo.Custom.Impl.SupplierBOImpl;
import lk.ijse.project_01.bo.Custom.Impl.UserBOImpl;
import lk.ijse.project_01.dao.Custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public enum DAOTypes {
        GUEST, EMPLOYEE,ROOM, BF_DASHBOARD, PAYMENT, SUPPLIER, FOOD_ITEM, FOODORDER, SIGNUP, LOGIN,BOOKING
    }

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public Object getDAO(DAOTypes daoType) {
        switch (daoType) {
            case GUEST:
                return new GuestDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case BF_DASHBOARD:
                return new BFDashboardDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case FOOD_ITEM:
                return new FoodOrderBOImpl();
            case FOODORDER:
                return new FoodOrderBOImpl();
            case SIGNUP:
                return new UserBOImpl();
            case LOGIN:
                return new UserBOImpl();
            case BOOKING:
                return new BookingDAOImpl();
            case ROOM:
                return new RoomDAOImpl();

            default:
                return null;
        }
    }
}
