package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.DTO.Employee;
import lk.ijse.project_01.bo.Custom.EmployeeBO;
import lk.ijse.project_01.dao.DAOFactory;
import lk.ijse.project_01.dao.Custom.EmployeeDAO;
import java.sql.SQLException;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    private final EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean saveEmployee(Employee employee) throws SQLException {
        return employeeDAO.save(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {
        return employeeDAO.update(employee);
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public Employee getEmployeeById(String id) throws SQLException {
        return employeeDAO.searchById(id);
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAll();
    }
}
