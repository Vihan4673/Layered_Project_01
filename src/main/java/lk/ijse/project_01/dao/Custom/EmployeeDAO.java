package lk.ijse.project_01.dao.Custom;

import lk.ijse.project_01.dao.SuperDAO;
import lk.ijse.project_01.DTO.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends SuperDAO {
    boolean save(Employee employee) throws SQLException;
    boolean update(Employee employee) throws SQLException;
    boolean delete(String id) throws SQLException;
    Employee searchById(String id) throws SQLException;
    List<Employee> getAll() throws SQLException;
    List<String> getNameList() throws SQLException;
    String getName(String employeeId) throws SQLException;
    Employee searchByName(String nameValue) throws SQLException;
}
