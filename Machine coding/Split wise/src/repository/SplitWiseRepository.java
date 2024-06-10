package repository;

import model.Group;
import model.SplitWise;
import model.User;

public class SplitWiseRepository {
    private static SplitWise splitWise;

    public static void initializeSplitWiseRepository(){
        splitWise = new SplitWise();
    }

    public static String getPasswordFromUserPasswordMap( String username ){
        return splitWise.getUserPasswordMap().get(username);
    }

    public static User getUserFromUserObjectsMap( String username ){
        return splitWise.getUserObjectsMap().get(username);
    }

    public static void addGroupIntoGroupObjectsMap( Group group ){
        splitWise.getGroupObjectsMap().put( group.getGroupName(), group );
    }

    public static void addNewUserIntoUserObjectsMap( User user ){
        splitWise.getUserObjectsMap().put(user.getName(), user);
    }

    public static void addNewUserIntoUserPasswordMap( String username, String password ){
        splitWise.getUserPasswordMap().put( username, password );
    }

    public static Group getGroupFromGroupObjectMap( String groupName ){
        return splitWise.getGroupObjectsMap().get(groupName);
    }
}
