package service;

import controller.AuthController;
import model.Group;
import repository.SplitWiseRepository;

import java.util.List;

public class CommonUtilsService {
    public static Group getGroupName(){
        List<String> groups = AuthController.getLoggedInUser().getGroupsList();
        int idx = UserInputOutputService.displayListAndGetChoice(groups,
                "😉- Please select group name from the list");
        String groupName = groups.get(idx);
        return SplitWiseRepository.getGroupFromGroupObjectMap(groupName);
    }
}
