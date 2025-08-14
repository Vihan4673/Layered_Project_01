package lk.ijse.project_01.dao.Custom;

import lk.ijse.project_01.DTO.OrderItem;
import lk.ijse.project_01.DTO.FoodItem;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FoodOrderDAO {
    String getGuestNameByPhone(String phone) throws SQLException;
    boolean saveFoodOrder(String phone, List<OrderItem> orderList) throws SQLException;
    Map<String, List<FoodItem>> getAllFoodItemsGroupedByCategory() throws SQLException;
}
