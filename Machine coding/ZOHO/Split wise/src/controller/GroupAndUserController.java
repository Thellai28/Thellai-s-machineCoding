package controller;

import model.Group;
import model.User;
import repository.SplitWiseRepository;
import service.CommonUtilsService;
import service.UserInputOutputService;

public class GroupAndUserController {
    public static void handleNewGroupCreation(){
        String groupName = UserInputOutputService.getGroupName();
        Group newGroup = new Group(AuthController.getLoggedInUserName(), groupName );
        SplitWiseRepository.addGroupIntoGroupObjectsMap(newGroup);
        UserInputOutputService
                .printMessageAndOneLine("✅---New Group Created Successfully---✅");

        int needToAddNewUsers = UserInputOutputService.needToAddNewUsers();

        if( needToAddNewUsers == 1 ){
            addNewUsersIntoGroup(newGroup);
        }
    }

    private static void addNewUsersIntoGroup(Group group){
        int userCount = UserInputOutputService.getUsersCount();
        while( userCount-- > 0 ){
            String username = UserInputOutputService.getUserName();
            User currentUser = SplitWiseRepository.getUserFromUserObjectsMap(username);

            if(currentUser == null ){
                createNewUser(username);
            }
            group.getParticipantsList().add(username);
        }
        UserInputOutputService.printMessageAndOneLine("✅---Successfully Added users into group : "
                + group.getGroupName()+"---✅");
    }

    private static void createNewUser(String username){
        if(username == null ){
            username = UserInputOutputService.getUserName();
        }
        String password = UserInputOutputService.getPassword();

        SplitWiseRepository.addNewUserIntoUserPasswordMap(username, password);
        SplitWiseRepository.addNewUserIntoUserObjectsMap(new User(username));

        UserInputOutputService.printMessageAndOneLine("✅---New user Created successfully---✅");
    }

    public static void selectGroupAndAddUsers(){
        Group selectedGroup = CommonUtilsService.getGroupName();
        addNewUsersIntoGroup(selectedGroup);
    }

}
