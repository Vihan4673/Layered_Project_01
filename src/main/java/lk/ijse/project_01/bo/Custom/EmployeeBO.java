package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.DTO.Employee;
import lk.ijse.project_01.bo.SuperBO;


import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    boolean saveEmployee(Employee employee) throws SQLException;
    boolean updateEmployee(Employee employee) throws SQLException;
    boolean deleteEmployee(String id) throws SQLException;
    Employee getEmployeeById(String id) throws SQLException;
    List<Employee> getAllEmployees() throws SQLException;
}
