package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.bo.Custom.FoodBeverageAddBO;
import lk.ijse.project_01.DTO.AddFoodbevarge;
import lk.ijse.project_01.dao.Custom.FoodBeverageAddDAO;
import lk.ijse.project_01.dao.Custom.impl.FoodBeverageAddDAOImpl;
import java.sql.SQLException;
import java.util.List;

public class FoodBeverageAddBOImpl implements FoodBeverageAddBO {

    private final FoodBeverageAddDAO dao = new FoodBeverageAddDAOImpl();

    @Override
    public String getLastItemIdByPrefix(String prefix) throws SQLException {
        return dao.getLastItemIdByPrefix(prefix);
    }

    @Override
    public List<AddFoodbevarge> getAllItems() throws SQLException {
        return dao.getAllItems();
    }

    @Override
    public boolean addItem(AddFoodbevarge item) throws SQLException {
        return dao.addItem(item);
    }

    @Override
    public boolean updateItem(AddFoodbevarge item) throws SQLException {
        return dao.updateItem(item);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return dao.deleteItem(id);
    }
}
