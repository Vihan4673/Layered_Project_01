package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.DTO.OrderItem;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import lk.ijse.project_01.DTO.FoodItem;

public interface FoodOrderBO {
    String getGuestNameByPhone(String phone) throws SQLException;
    boolean saveFoodOrder(String phone, List<OrderItem> orderList) throws SQLException;
    Map<String, List<FoodItem>> getAllFoodItemsGroupedByCategory() throws SQLException;
}
