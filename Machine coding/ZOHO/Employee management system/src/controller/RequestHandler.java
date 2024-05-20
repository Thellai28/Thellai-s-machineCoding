package controller;

import service.UserInputOutputService;

public class RequestHandler {
    public static void handleRequest( int request ){
        switch ( request ){
            case 1 : {
                DisplayEmployeeController.printAllEmployeeAsTable();
                break;
            } case 2 : {
                SearchController.handleEmployeeSearchRequest();
                break;
            } case 3 : {
                ReportingTreeController.handleReportingTreeRequest();
                break;
            } case 4 : {
                ReportingManagerController.getAllEmployeesUnderSpecificManager();
                break;
            } case 5 : {
                UserInputOutputService
                        .printMessageAndHorizontalLine("⚠️---Shutting down employee management system---⚠️");
                Main.shutDownEmployeeManagementSystem();
                break;
            } default:{
                UserInputOutputService.printMessageAndHorizontalLine("❌---Invalid input---❌");
            }
        }
    }
}
