package service;

import model.Employee;
import repository.EmployeeManagementRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public static List<Employee> showStringOperations( List<String> allStringFieldsList,
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

    public static List<Employee> showIntegerOperations( List<String> allIntegerFieldsList,
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

            if( valueOfField != null ){
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

                }else if( searchOperation.equals("ENDS_WITH") && valueOfField.endsWith(stringValueToSearchFor)){
                    newSearchResults.add(currentEmployee);
                }
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


    public static List<String> getAllStringFields( Field[] allFieldsInEmployeeClass ){
        List<String> allStringFieldsList = new ArrayList<>();

        for( Field currField : allFieldsInEmployeeClass ){
            if(currField.getType().getSimpleName().equals("String")){
                allStringFieldsList.add(currField.getName());
            }
        }
        return allStringFieldsList;
    }


    public static List<String> getAllIntegerFields( Field[] allFieldsInEmployeeClass ){
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

    public static Field[] getAllFieldsInEmployeeClass(){
        return Employee.class.getDeclaredFields();
    }
}
