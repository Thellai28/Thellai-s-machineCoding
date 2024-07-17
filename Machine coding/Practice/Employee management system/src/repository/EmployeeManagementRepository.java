package repository;

import model.Employee;
import model.EmployeeManagementSystem;

import java.util.List;

public class EmployeeManagementRepository {
    private static EmployeeManagementSystem employeeManagementSystem;

    public static void initializeService(){
        employeeManagementSystem = new EmployeeManagementSystem();
    }

    public static List<Employee> getAllEmployees(){
        return employeeManagementSystem.getEmployeeList();
    }

    public static String getStringValueFromEmployee( Employee employee, String fieldName ){
        if( fieldName.equals("name") )
            return employee.getName();
        else if( fieldName.equals("reportingTo"))
            return employee.getReportingTo();
        else if( fieldName.equals("designation"))
            return employee.getDesignation();
        else if(fieldName.equals("department"))
            return employee.getDepartment();
        return null;
    }

    public static int getIntegerValueFromEmployee(Employee employee, String fieldName ){
        if( fieldName.equals("employeeId") ){
            return employee.getEmployeeId();
        }else if( fieldName.equals("age") ){
            return employee.getAge();
        }
        return -1;
    }

    public static Employee findEmployeeByName( String name){
        for( Employee emp : employeeManagementSystem.getEmployeeList()){
            if( name != null && emp.getName().equalsIgnoreCase(name) ){ // names are unique
                return emp;
            }
        }
        return null;
    }

    public static Employee findEmployeeByEmployeeId( int employeeId ){
        for( Employee emp : employeeManagementSystem.getEmployeeList()){
            if( emp.getEmployeeId() == employeeId ){
                return emp;
            }
        }
        return null;
    }
}
