package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.bo.Custom.Impl.*;
import lk.ijse.project_01.bo.SuperBO;

public class BOFactory {

    private static BOFactory boFactory;

    public enum BOTypes {
        GUEST, EMPLOYEE,REPORT, BFDASHBOARD, PAYMENT, FOOD_ITEM, SUPPLIER, FOODORDER, SIGNUP,ROOM, LOGIN,BOOKING
    }

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes boType) {
        switch (boType) {
            case GUEST:
                return new GuestBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case BFDASHBOARD:
                return new BFDashboardBOImpl();
            case PAYMENT:
                return null;
            case FOOD_ITEM:
                return new FoodBeverageAddBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SIGNUP:
                return new UserBOImpl();

            case LOGIN:
                return new UserBOImpl();
            case FOODORDER:
                return null;
            case BOOKING:
                return new BookingBOImpl();
            case ROOM:
                return new RoomBOImpl();
                default:
                return null;
        }
    }
}
