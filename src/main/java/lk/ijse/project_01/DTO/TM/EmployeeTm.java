package lk.ijse.project_01.DTO.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTm {
    String employeeId;
    String employeeName;
    String employeeAddress;
    String employeeTel;
    String employeePosition;
    double employeeSalary;

}
