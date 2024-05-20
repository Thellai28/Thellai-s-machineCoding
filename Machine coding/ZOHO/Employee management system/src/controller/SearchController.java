package controller;

import model.Employee;
import service.SearchService;
import service.UserInputOutputService;

import java.lang.reflect.Field;
import java.util.List;

public class SearchController {
    public static void handleEmployeeSearchRequest(){
        Field[] allFieldsInEmployeeClass = SearchService.getAllFieldsInEmployeeClass();
        List<Employee> searchResults = null;
        List<String> allStringFieldsList = null;
        List<String> allIntegerFieldsList = null;
        boolean isUserSearching;

        do{
            int choice = UserInputOutputService.getSearchFieldType();

            if( choice == 1 ){ // String
                if( allStringFieldsList == null ){ // lazy initialization :
                    allStringFieldsList = SearchService.getAllStringFields(allFieldsInEmployeeClass);
                }
                searchResults = SearchService.showStringOperations(allStringFieldsList, searchResults);

            }else{
                if( allIntegerFieldsList == null ){ // lazy initialization :
                    allIntegerFieldsList =  SearchService.getAllIntegerFields(allFieldsInEmployeeClass);
                }
                searchResults = SearchService.showIntegerOperations(allIntegerFieldsList, searchResults);
            }

            int isSearchingFurther = UserInputOutputService.isUserNeedToSearchMore();
            isUserSearching = isSearchingFurther == 2 ? false : true;

        }while(isUserSearching);

        UserInputOutputService.printEmployees(searchResults);
    }
}
