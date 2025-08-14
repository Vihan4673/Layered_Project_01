package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.bo.Custom.SupplierBO;
import lk.ijse.project_01.dao.Custom.SupplierDAO;
import lk.ijse.project_01.dao.Custom.impl.SupplierDAOImpl;
import lk.ijse.project_01.DTO.SupplierDTO;
import java.sql.SQLException;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    private final SupplierDAO supplierDAO = new SupplierDAOImpl();

    @Override
    public String getNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getNextSupplierId();
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllSuppliers();
    }

    @Override
    public boolean saveSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException {

        return supplierDAO.saveSupplier(supplier);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws SQLException, ClassNotFoundException {

        return supplierDAO.updateSupplier(supplier);
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {

        return supplierDAO.deleteSupplier(supplierId);
    }
}
