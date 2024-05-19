package service;

import model.Employee;

import java.util.List;
import java.util.Scanner;

public class UserInputOutputService {
    private static Scanner scanner = new Scanner( System.in);

    public static void closeScanner(){
        scanner.close();
    }

    public static int displayWelcomeMessage(){
        System.out.println();
        System.out.println("😃-----Welcome to employee management system-----😃");
        System.out.println("1. Display all Employee record");
        System.out.println("2. Search records");
        System.out.println("3. Print reporting tree of given employee");
        System.out.println("4. Employees reporting to the given manager");
        System.out.println("5. exit");

        return scanner.nextInt();
    }

    public static int getSearchFieldType( ){
        System.out.println("1. String");
        System.out.println("2. Integer");

        return scanner.nextInt();
    }

    public static int displayStringOptions(){
        System.out.println();
        System.out.println("Choose any string operations from below list");
        System.out.println("1. Equals");
        System.out.println("2. Not Equals");
        System.out.println("3. Contains");
        System.out.println("4. Not contains");
        System.out.println("5. Starts with");
        System.out.println("6. Ends with");

        return scanner.nextInt();
    }

    public static int displayIntegerOperations(){
        System.out.println();
        System.out.println("1. Lesser than");
        System.out.println("2. Greater than");
        System.out.println("3. Equals");
        System.out.println("4. Not equals");
        System.out.println("5. Between");

        return scanner.nextInt();
    }
    public static String  getStringToSearchFor(){
        System.out.println();
        System.out.println("Please enter the string value you want to search for");
        return scanner.next();
    }

    public static int getFieldToApplySearchConditions( List<String> allStringFields ){
        System.out.println();
        System.out.println("Please choose the field to apply search condition");
        for( int i = 0; i < allStringFields.size(); i++ ){
            System.out.println( i+1 +". " + allStringFields.get(i));
        }
        return scanner.nextInt()-1;
    }

    public static int isPlayerNeedToSearchMore(){
        System.out.println();
        System.out.println("Do you want to search more ?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        return scanner.nextInt();
    }

    public static int getIntegerToSearchFor(){
        System.out.println();
        System.out.println("Please enter the integer value you need to search for");

        return scanner.nextInt();
    }

    public static int getLowerBoundValue() {
        System.out.println();
        System.out.println("Please enter the lower bound value");

        return  scanner.nextInt();
    }

    public static int getUpperBoundValue() {
        System.out.println();
        System.out.println("Please enter the upper bound value");

        return  scanner.nextInt();
    }

    public static void displaySelectedEmployees( List<Employee> selectedEmployees ){
        if( selectedEmployees == null || selectedEmployees.size() == 0 ){
            printMessageAndHorizontalLine("❌---Sorry, there is no is no employee to display---❌");
        }else {
            System.out.println("✅---Printing selected employees---✅");
            for( Employee employee : selectedEmployees ) {
                System.out.println("Employee Id : " + employee.getEmployeeId());
                System.out.println("Employee name : " + employee.getName());
                System.out.println("Employee age :" + employee.getAge());
                System.out.println("Reporting manager : " + employee.getReportingTo());
                System.out.println("Designation : " + employee.getDesignation());
                System.out.println("Department : " + employee.getDepartment());
                System.out.println("-----------------------------------------------------------------------");
            }
        }
    }

    public static void printMessageAndHorizontalLine( String message ){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }
}
