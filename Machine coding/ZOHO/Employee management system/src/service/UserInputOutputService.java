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

    public static int isUserNeedToSearchMore(){
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


    public static void printMessageAndHorizontalLine( String message ){
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void printEmployees(List<Employee> employeeList ){

        if( employeeList == null || employeeList.size() == 0 ){
            printMessageAndHorizontalLine("❌---Sorry, there is no is no employee to display---❌");
        }else {
            System.out.printf("%-5s| %-20s| %-5s| %-27s| %-25s| %-20s%n",
                    "ID", "Name", "Age", "Designation",
                    "Department", "ReportingTo");
            System.out.println("-----------------------------------------------------------------------------------------------------------");

            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }

    public static void printEmployeeInReverseOrder( List<Employee> employeeTreeList ){
        if( employeeTreeList == null || employeeTreeList.size() == 0 ){
            printMessageAndHorizontalLine("❌---Sorry, there is no is no employee to display---❌");
        }else {
            System.out.printf("%-5s| %-20s| %-5s| %-27s| %-25s| %-20s%n",
                    "ID", "Name", "Age", "Designation",
                    "Department", "ReportingTo");
            System.out.println("-----------------------------------------------------------------------------------------------------------");

            for( int i = employeeTreeList.size()-1; i >= 0; i-- ){
                System.out.println(employeeTreeList.get(i));
            }
        }

    }

    public static int getEmployeeId(){
        printMessageAndHorizontalLine("Please enter the employee id");
        return scanner.nextInt();
    }

    public static String getManagerName(){
        printMessageAndHorizontalLine("Please enter the reporting manager name");
        return scanner.next();
    }
}
