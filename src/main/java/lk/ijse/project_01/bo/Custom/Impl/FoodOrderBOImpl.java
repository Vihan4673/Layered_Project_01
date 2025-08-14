package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.bo.Custom.FoodOrderBO;
import lk.ijse.project_01.dao.Custom.FoodOrderDAO;
import lk.ijse.project_01.dao.Custom.impl.FoodOrderDAOImpl;
import lk.ijse.project_01.DTO.OrderItem;
import lk.ijse.project_01.DTO.FoodItem;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class FoodOrderBOImpl implements FoodOrderBO {

    private final FoodOrderDAO dao = new FoodOrderDAOImpl();

    @Override
    public String getGuestNameByPhone(String phone) throws SQLException {
        return dao.getGuestNameByPhone(phone);
    }

    @Override
    public boolean saveFoodOrder(String phone, List<OrderItem> orderList) throws SQLException {
        return dao.saveFoodOrder(phone, orderList);
    }

    @Override
    public Map<String, List<FoodItem>> getAllFoodItemsGroupedByCategory() throws SQLException {
        return dao.getAllFoodItemsGroupedByCategory();
    }
}
