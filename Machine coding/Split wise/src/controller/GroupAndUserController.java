package controller;

import model.Group;
import model.User;
import repository.SplitWiseRepository;
import service.CommonUtilsService;
import service.UserInputOutputService;

public class GroupAndUserController {
    public static void handleNewGroupCreation(){
        User loggedInUser = AuthController.getLoggedInUser();

        // Group creation & chain activities :
        String groupName = UserInputOutputService.getGroupName();
        Group newGroup = new Group(loggedInUser.getName(), groupName );
        SplitWiseRepository.addGroupIntoGroupObjectsMap(newGroup);
        UserInputOutputService
                .printMessageAndOneLine("✅---New Group Created Successfully---✅");

        // Adding group into User :
        loggedInUser.getGroupsList().add( newGroup.getGroupName() );

        // Adding logged-in user into the created group :
        newGroup.getParticipantsList().add(loggedInUser.getName());

        // Adding users into created group
        addNewUsersIntoGroup(newGroup);
    }

    private static void addNewUsersIntoGroup(Group group){
        boolean isAdding = ( UserInputOutputService.needToAddNewUsers() == 1 ) ? true : false;

        while( isAdding ){
            String username = UserInputOutputService.getUserName();
            User currentUser = SplitWiseRepository.getUserFromUserObjectsMap(username);

            if(currentUser == null ){
                UserInputOutputService
                        .printMessageAndOneLine("‼️There is no user with this name, creating new User new User");
                currentUser = createNewUser(username);
            }

            // since this user is also a part of this group, adding the current group into his list :
            if( group != null
                    && !group.getParticipantsList()
                    .contains(currentUser.getName())){

                currentUser.getGroupsList().add(group.getGroupName());
            }

            group.getParticipantsList().add(username);
            isAdding = ( UserInputOutputService.needToAddNewUsers() == 1 ) ? true : false;
            //isAdding =  UserInputOutputService.needToAddNewUsers() == 1 // advanced version of above code:
        }
        UserInputOutputService.printMessageAndOneLine("✅---Successfully Added users into group : "
                + group.getGroupName()+"---✅");
    }

    public static User createNewUser(String username){
        if(username == null ){
            username = UserInputOutputService.getUserName();
        }
        String password = UserInputOutputService.getPassword("😉- Please enter password for your new Account");

        User newUser = new User(username);
        SplitWiseRepository.addNewUserIntoUserPasswordMap(username, password);
        SplitWiseRepository.addNewUserIntoUserObjectsMap(newUser);

        UserInputOutputService.printMessageAndOneLine("✅---New user Created successfully---✅");
        return newUser;
    }

    public static void selectGroupAndAddUsers(){
        Group selectedGroup = CommonUtilsService.getGroupName();
        addNewUsersIntoGroup(selectedGroup);
    }
}
