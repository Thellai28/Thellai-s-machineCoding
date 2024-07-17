package controller;

import model.Employee;
import repository.EmployeeManagementRepository;
import service.UserInputOutputService;

import java.util.ArrayList;
import java.util.List;

public class ReportingTreeController {
    public static void handleReportingTreeRequest(){
        int employeeId = UserInputOutputService.getEmployeeId();
        Employee employee = EmployeeManagementRepository.findEmployeeByEmployeeId(employeeId);

        if( employee != null ){
            List<Employee> employeeTreeList = getEmployeeTree(employee);
            UserInputOutputService.printEmployeeInReverseOrder(employeeTreeList);
            // employee with higher post will be at last, to print from top, we traverse from last :

        }else {
            UserInputOutputService.printMessageAndHorizontalLine("❌---Invalid employee Id---❌");
        }

    }

    private static List<Employee> getEmployeeTree( Employee employee ){
        List<Employee> employeeTree = new ArrayList<>();

        while( employee != null
                && (employee.getReportingTo() != null || employee.getDesignation().equals("CEO")) ){
            employeeTree.add(employee);
            employee = EmployeeManagementRepository.findEmployeeByName(employee.getReportingTo());
        }
        return employeeTree;
    }
}
