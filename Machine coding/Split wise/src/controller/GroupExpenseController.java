package controller;

import model.Group;
import service.CommonUtilsService;
import service.UserInputOutputService;

public class GroupExpenseController {

    public static void showGroupExpense(){
        Group selectedGroup = CommonUtilsService.getGroupName();
        UserInputOutputService.printExpenses(selectedGroup.getExpenseList());
    }
}
