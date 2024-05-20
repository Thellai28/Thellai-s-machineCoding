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

        employeeList.add( new Employee("Ramanan", 45, null, "CEO", "NOT_APPLICABLE"));
        employeeList.add( new Employee("Apurva", 42, "Ramanan","VICE_PRESIDENT", "NOT_APPLICABLE"));
        employeeList.add( new Employee("Benjamin", 40, "Apurva", "MANAGING_LEAD", "NOT_APPLICABLE"));
    }

    private void createManagers(){
        String[] managerNames= {"Joseph", "Manigandan", "Fathima",
                "Bala", "Catherine", "Arivu",
                "Nandhini", "Dheva", "Eliza"};

        int age[] = { 43, 37, 34, 35, 38, 39, 40, 44, 33};

        String department[] = { "MANUAL_TESTING", "ACCOUNTS", "DEVELOPMENT",
                "HUMAN_RESOURCE", "AUTOMATION_TESTING", "FRAMEWORK_DEVELOPMENT",
                "SCRIPTING", "FIRMWARE_DEVELOPMENT", "OS_DEVELOPMENT" };

        for( int i = 0; i< age.length; i++ ) {
            employeeList.add(new Employee(managerNames[i], age[i],
                    "Benjamin", "MANAGER", department[i]));
        }
    }

    private void createEmployees(){
        String[] names = {"Rajesh", "Yazhini", "Jasmine",
                "AbdulRahuman", "Jebin", "Nazriya",
                "MuthuKumaran", "kumar", "vijay",
                "Santhanam", "Priyanka arul mohan", "Vedhan",
                "Fahath", "Gayathri", "Henry",
                "Indhu", "Jenifer", "kevin"};

        int age[] = {23, 25, 27,
                28, 29, 32,
                22, 26, 21,
                30, 31, 33,
                35, 36, 37,
                22, 24, 26};

        String manager[] = {"Joseph", "Joseph", "Manigandan",
                "Manigandan", "Fathima", "Fathima",
                "Bala", "Bala",  "Catherine"
                ,"Catherine", "Arivu", "Arivu",
                "Nandhini","Nandhini", "Dheva",
                "Dheva", "Eliza", "Eliza"};

        String designation[] = {"QUALITY_ANALYST", "TESTING_LEAD" , "ACCOUNTS_LEAD",
                "ACCOUNTANT", "FRONT_END_ENGINEER", "BACK_END_DEVELOPER",
                "HUMAN_RESOURCE_INTERN", "HUMAN_RESOURCE_ASSOCIATE", "JUNIOR_AUTOMATION_TESTER",
                "SENIOR_AUTOMATION_TESTER", "JUNIOR_FIRMWARE_DEVELOPER", "SENIOR_FIRMWARE_DEVELOPER",
                "PYTHON_SCRIPT_ENGINEER", "PYTHON_SCRIPT_EXECUTIVE", "JUNIOR_FIRMWARE_DEVELOPER",
                "FIRMWARE_DEVELOPMENT_LEAD", "WINDOWS_OS_DEVELOPER", "LINUX_OS_DEVELOPER" };


        String department[] = {"MANUAL_TESTING", "MANUAL_TESTING", "ACCOUNTS",
                "ACCOUNTS", "DEVELOPMENT", "DEVELOPMENT",
                "HUMAN_RESOURCE", "HUMAN_RESOURCE", "AUTOMATION_TESTING",
                "AUTOMATION_TESTING", "FRAMEWORK_DEVELOPMENT", "FRAMEWORK_DEVELOPMENT",
                "SCRIPTING", "SCRIPTING" , "FIRMWARE_DEVELOPMENT",
                "FIRMWARE_DEVELOPMENT", "OS_DEVELOPMENT", "OS_DEVELOPMENT"};


        for( int i = 0; i < names.length; i++){
            employeeList.add( new Employee(names[i], age[i], manager[i], designation[i], department[i]));
        }
    }



    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
