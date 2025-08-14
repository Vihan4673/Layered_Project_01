package lk.ijse.project_01.dao.Custom;

import lk.ijse.project_01.DTO.AddFoodbevarge;

import java.sql.SQLException;
import java.util.List;

public interface FoodBeverageAddDAO {
    String getLastItemIdByPrefix(String prefix) throws SQLException;
    List<AddFoodbevarge> getAllItems() throws SQLException;
    boolean addItem(AddFoodbevarge item) throws SQLException;
    boolean updateItem(AddFoodbevarge item) throws SQLException;
    boolean deleteItem(String id) throws SQLException;
}
