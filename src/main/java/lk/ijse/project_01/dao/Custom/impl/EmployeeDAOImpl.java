package lk.ijse.project_01.dao.Custom.impl;

import lk.ijse.project_01.DB.DBConnection;
import lk.ijse.project_01.DTO.Employee;
import lk.ijse.project_01.Entity.BFDashboardEntity;
import lk.ijse.project_01.dao.Custom.EmployeeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (EmployeeID, eName, address, phoneNo, postion, salary) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getEmployeeName());
        pstm.setObject(3, employee.getEmployeeAddress());
        pstm.setObject(4, employee.getEmployeeTel());
        pstm.setObject(5, employee.getEmployeePosition());
        pstm.setObject(6, employee.getEmployeeSalary());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET eName = ?, address = ?, phoneNo = ?, postion = ?, salary = ? WHERE EmployeeID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, employee.getEmployeeName());
        pstm.setObject(2, employee.getEmployeeAddress()); // address
        pstm.setObject(3, employee.getEmployeeTel());     // phone
        pstm.setObject(4, employee.getEmployeePosition());
        pstm.setObject(5, employee.getEmployeeSalary());
        pstm.setObject(6, employee.getEmployeeId());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE EmployeeID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, id);
        return pstm.executeUpdate() > 0;
    }

    @Override
    public Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmployeeID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Employee(
                    rs.getString("EmployeeID"),
                    rs.getString("eName"),
                    rs.getString("address"),
                    rs.getString("phoneNo"),
                    rs.getString("postion"),
                    rs.getDouble("salary")
            );
        }
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";
        ResultSet rs = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
        List<Employee> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Employee(
                    rs.getString("EmployeeID"),
                    rs.getString("eName"),
                    rs.getString("address"),
                    rs.getString("phoneNo"),
                    rs.getString("postion"),
                    rs.getDouble("salary")
            ));
        }
        return list;
    }

    @Override
    public List<String> getNameList() throws SQLException {
        String sql = "SELECT eName FROM employee";
        ResultSet rs = DBConnection.getInstance().getConnection().prepareStatement(sql).executeQuery();
        List<String> nameList = new ArrayList<>();
        while (rs.next()) {
            nameList.add(rs.getString("eName"));
        }
        return nameList;
    }

    @Override
    public String getName(String employeeId) throws SQLException {
        String sql = "SELECT eName FROM employee WHERE EmployeeID = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, employeeId);
        ResultSet rs = pstm.executeQuery();
        return rs.next() ? rs.getString("eName") : null;
    }

    @Override
    public Employee searchByName(String nameValue) throws SQLException {
        String sql = "SELECT * FROM employee WHERE eName = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, nameValue);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Employee(
                    rs.getString("EmployeeID"),
                    rs.getString("eName"),
                    rs.getString("address"),
                    rs.getString("phoneNo"),
                    rs.getString("postion"),
                    rs.getDouble("salary")
            );
        }
        return null;
    }

    @Override
    public List<BFDashboardEntity> loadAllOrders() {
        return List.of();
    }
}
