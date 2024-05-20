package controller;

import model.Employee;
import repository.EmployeeManagementRepository;
import service.UserInputOutputService;

import java.util.List;

public class DisplayEmployeeController {
    public static void printAllEmployeeAsTable(){
        List<Employee> employees = EmployeeManagementRepository.getAllEmployees();
        UserInputOutputService.printEmployees(employees);
    }
}
