package controller;

import model.Employee;
import model.EmployeeManagementSystem;
import repository.EmployeeManagementRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class ReportingManagerController {
    public static void getAllEmployeesUnderSpecificManager(){
        String managerName = UserInputOutputService.getManagerName();
        Employee manager = EmployeeManagementRepository.findEmployeeByName(managerName);
        if( manager != null ){
            List<Employee> reportingEmployee = getAllReportingEmployee(manager);
            UserInputOutputService.printEmployees(reportingEmployee);
        }else{
            UserInputOutputService.printMessageAndHorizontalLine("❌---Invalid manager name---❌");
        }
    }

    private static List<Employee> getAllReportingEmployee(Employee manager ){
        List<Employee> allEmployee = EmployeeManagementRepository.getAllEmployees();
        List<Employee> reportingEmployees = new ArrayList<>();

        for( Employee employee : allEmployee ){
            if (employee.getReportingTo() != null
                    && employee.getReportingTo().equalsIgnoreCase(manager.getName())){
                reportingEmployees.add(employee);
            }
        }
        return reportingEmployees;
    }
}
