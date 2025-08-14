package lk.ijse.project_01.dao.Custom;

import lk.ijse.project_01.DTO.SupplierDTO;
import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO {
    String getNextSupplierId() throws SQLException, ClassNotFoundException;
    List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean saveSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException;
}
