package controller;

import model.Employee;
import repository.EmployeeManagementRepository;
import service.UserInputOutputService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    public static void handleEmployeeSearchRequest(){
        Field[] allFieldsInEmployeeClass = getAllFieldsInEmployeeClass();
        List<Employee> searchResults = null;
        List<String> allStringFieldsList = null;
        List<String> allIntegerFieldsList = null;
        boolean isPlayerSearching;

        do{
            int choice = UserInputOutputService.getSearchFieldType();

            if( choice == 1 ){
                if( allStringFieldsList == null ){ // lazy initialization :
                    allStringFieldsList = getAllStringFields(allFieldsInEmployeeClass);
                }
                searchResults = showStringOperations(allStringFieldsList, searchResults);

            }else{
                if( allIntegerFieldsList == null ){ // lazy initialization :
                    allIntegerFieldsList =  getAllIntegerFields(allFieldsInEmployeeClass);
                }
                searchResults = showIntegerOperations(allIntegerFieldsList, searchResults);
            }

            int isSearchingFurther = UserInputOutputService.isPlayerNeedToSearchMore();
            isPlayerSearching = isSearchingFurther == 2 ? false : true;

        }while(isPlayerSearching);

        UserInputOutputService.displaySelectedEmployees(searchResults);
    }


    private static List<Employee> showStringOperations(  List<String> allStringFieldsList,
                                                         List<Employee> searchResults ){
        if( searchResults ==  null ){
            searchResults = EmployeeManagementRepository.getAllEmployees();
        }

        String fieldToApplySearchCondition = getSelectedFieldFromList(allStringFieldsList);
        int operation = UserInputOutputService.displayStringOptions();
        String stringValueToSearchFor = UserInputOutputService.getStringToSearchFor();


        if( operation == 1 ){
            return filterUsingStringOperations(stringValueToSearchFor,
                    fieldToApplySearchCondition, searchResults, "EQUALS" );

        }else if( operation == 2 ){
            return filterUsingStringOperations(stringValueToSearchFor,
                    fieldToApplySearchCondition, searchResults, "NOT_EQUALS");

        }else if( operation == 3 ){
            return filterUsingStringOperations(stringValueToSearchFor,
                    fieldToApplySearchCondition, searchResults, "CONTAINS");

        }else if( operation == 4 ){
            return filterUsingStringOperations(stringValueToSearchFor,
                    fieldToApplySearchCondition, searchResults, "NOT_CONTAINS");

        }else if( operation == 5 ){
            return  filterUsingStringOperations(stringValueToSearchFor,
                    fieldToApplySearchCondition, searchResults, "STARTS_WITH");

        }else if ( operation == 6 ){
            return  filterUsingStringOperations(stringValueToSearchFor,
                    fieldToApplySearchCondition, searchResults, "ENDS_WITH");

        }

        System.out.println();
        System.out.println("Invalid input");
        return searchResults;
    }

    private static List<Employee> showIntegerOperations( List<String> allIntegerFieldsList,
                                                          List<Employee> searchResults ){
        if( searchResults ==  null ){// This happens only if it's the first iteration :
            searchResults = EmployeeManagementRepository.getAllEmployees();
        }

        String fieldToApplySearchCondition = getSelectedFieldFromList(allIntegerFieldsList);
        int operation = UserInputOutputService.displayIntegerOperations();


        if( operation == 1 ){
            return filterUsingIntegerOperations(fieldToApplySearchCondition,
                    searchResults, "LESSER_THAN");

        }else if( operation == 2 ){
            return filterUsingIntegerOperations(fieldToApplySearchCondition,
                    searchResults, "GREATER_THAN");

        }else if( operation == 3 ){
            return filterUsingIntegerOperations(fieldToApplySearchCondition,
                    searchResults, "EQUALS");

        }else if( operation == 4 ){
            return filterUsingIntegerOperations(fieldToApplySearchCondition,
                    searchResults, "NOT_EQUALS");

        }else if ( operation == 5 ){
            return filterUsingIntegerOperations(fieldToApplySearchCondition,
                    searchResults, "BETWEEN");

        }else{
            System.out.println();
            System.out.println("Invalid input");
            return searchResults;
        }

    }



    private static List<Employee> filterUsingStringOperations( String stringValueToSearchFor,
                                                               String fieldToApplySearchCondition,
                                                               List<Employee> searchResults,
                                                               String searchOperation){
        List<Employee> newSearchResults = new ArrayList<>();

        for( Employee currentEmployee : searchResults ){

            String valueOfField = EmployeeManagementRepository
                    .getStringValueFromEmployee(currentEmployee, fieldToApplySearchCondition);

            if( searchOperation.equals("EQUALS") && valueOfField.equals(stringValueToSearchFor)){
                newSearchResults.add(currentEmployee);

            }else if( searchOperation.equals("NOT_EQUALS") && !valueOfField.equals(stringValueToSearchFor) ){
                newSearchResults.add(currentEmployee);

            }else if( searchOperation.equals("CONTAINS") && valueOfField.contains(stringValueToSearchFor)){
                newSearchResults.add(currentEmployee);

            }else if( searchOperation.equals("NOT_CONTAINS") && !valueOfField.contains(stringValueToSearchFor)){
                newSearchResults.add(currentEmployee);

            }else if (searchOperation.equals("STARTS_WITH") && valueOfField.startsWith(stringValueToSearchFor)){
                newSearchResults.add(currentEmployee);

            }else if( searchResults.equals("ENDS_WITH") && valueOfField.endsWith(stringValueToSearchFor)){
                newSearchResults.add(currentEmployee);
            }
        }
        return newSearchResults;
    }


    private  static List<Employee> filterUsingIntegerOperations( String fieldToApplySearchCondition,
                                                                List<Employee> searchResults,
                                                                String searchOperation){

        List<Employee> newSearchResults = new ArrayList<>();
        int integerValueToSearchFor = ( !searchOperation.equals("BETWEEN") )
                ? UserInputOutputService.getIntegerToSearchFor() : -1;
        int lowerBound = ( searchOperation.equals("BETWEEN"))
                ? UserInputOutputService.getLowerBoundValue() : -1;
        int upperBound = ( searchOperation.equals("BETWEEN") )
                ? UserInputOutputService.getUpperBoundValue() : -1;


        for( Employee currEmployee : searchResults ){
            int valueOfField = EmployeeManagementRepository
                        .getIntegerValueFromEmployee(currEmployee, fieldToApplySearchCondition);

            if( searchOperation.equals("LESSER_THAN") && valueOfField < integerValueToSearchFor){
                newSearchResults.add(currEmployee);

            }else if( searchOperation.equals("GREATER_THAN") && valueOfField > integerValueToSearchFor){
                newSearchResults.add(currEmployee);

            }else if( searchOperation.equals("EQUALS") && valueOfField == integerValueToSearchFor ){
                newSearchResults.add(currEmployee);

            }else if( searchOperation.equals("NOT_EQUALS") && valueOfField != integerValueToSearchFor ){
                newSearchResults.add( currEmployee );

            }else if( searchOperation.equals("BETWEEN") ){

                if( valueOfField >= lowerBound && valueOfField <= upperBound ){
                    newSearchResults.add(currEmployee);
                }
            }
        }
        return newSearchResults;
    }


    private static List<String> getAllStringFields( Field[] allFieldsInEmployeeClass ){
        List<String> allStringFieldsList = new ArrayList<>();

        for( Field currField : allFieldsInEmployeeClass ){
            if(currField.getType().getSimpleName().equals("String")){
                allStringFieldsList.add(currField.getName());
            }
        }
        return allStringFieldsList;
    }


    private static List<String> getAllIntegerFields( Field[] allFieldsInEmployeeClass ){
        List<String> allIntegerFieldsList = new ArrayList<>();

        for( Field currField : allFieldsInEmployeeClass ){
            if( currField.getType().getSimpleName().equals("int") ){
                allIntegerFieldsList.add( currField.getName() );
            }
        }
        return allIntegerFieldsList;
    }

    private static String getSelectedFieldFromList(List<String> allStringFieldsList){

        int idx = UserInputOutputService
                .getFieldToApplySearchConditions(allStringFieldsList);
        String fieldToApplySearchCondition = allStringFieldsList.get(idx);;
        return fieldToApplySearchCondition;
    }

    private static Field[] getAllFieldsInEmployeeClass(){
        return Employee.class.getDeclaredFields();
    }
}
