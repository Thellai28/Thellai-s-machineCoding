package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementSystem {
    List<Employee> employeeList;

    public EmployeeManagementSystem() {
        this.employeeList = new ArrayList<>();
        createHigherAuthorityMembers();
        createManagers();
        createEmployees();
    }

    public void createHigherAuthorityMembers(){
        String  names[] = {"Ramanan", "Apurva", "Raja guru"};
        String designation[] = { "CEO", "VICE PRESIDENT", "MANAGING LEAD"};
        int age[] = {45, 42, 40};

        employeeList.add( new Employee("Ramanan", 45, null, "CEO", null));
        employeeList.add( new Employee("Apurva", 42, "Ramanan","VICE PRESIDENT", null));
        employeeList.add( new Employee("Benjamin", 40, "Apurva", "MANAGING LEAD", null));
    }

    private void createManagers(){
        String[] managerNames= {"Joseph", "Manigandan", "Fathima"};
        int age[] = { 43, 37, 34};
        String department[] = { "TESTING", "Accounts", "Development"};

        for( int i = 0; i< age.length; i++ ) {
            employeeList.add(new Employee(managerNames[i], age[i],
                    "Benjamin", "MANAGER", department[i]));
        }
    }

    private void createEmployees(){
        String[] names = {"Rajesh", "Yazhini", "Jasmine", "Abdul rahuman", "Jebin", "Nazriya"};
        int age[] = {23, 25, 27, 28, 29, 32};
        String designation[] = {"QUALITY ANALYST", "TESTING LEAD" , "ACCOUNTS LEAD",
                "ACCOUNTANT", "FRONT END ENGINEER", "BACK END DEVELOPER"};
        String manager[] = {"Joseph", "Joseph", "Manigandan", "Manigandan", "Fathima", "Fathima"};
        String department[] = {"TESTING", "TESTING", "Accounts", "Accounts", "Development", "Development"};

        for( int i = 0; i < names.length; i++){
            employeeList.add( new Employee(names[i], age[i], manager[i], designation[i], department[i]));
        }
    }



    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
