package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.DTO.AddFoodbevarge;
import lk.ijse.project_01.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface FoodBeverageAddBO extends SuperBO {
    String getLastItemIdByPrefix(String prefix) throws SQLException;
    List<AddFoodbevarge> getAllItems() throws SQLException;
    boolean addItem(AddFoodbevarge item) throws SQLException;
    boolean updateItem(AddFoodbevarge item) throws SQLException;
    boolean deleteItem(String id) throws SQLException;
}
