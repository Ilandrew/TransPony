package by.bsuir.mpp.transpony.command;

import by.bsuir.mpp.transpony.util.PagePath;

import java.util.HashMap;
import java.util.Map;

public class RoleHelper {
   private Map<Integer, String> role;
    public RoleHelper() {
        role = new HashMap<>();
        role.put(1, PagePath.HOME_LEADER.getPage());
        role.put(2, PagePath.HOME_ADMIN.getPage());
        role.put(3, PagePath.HOME_ACCOUNTANT.getPage());
        role.put(4, PagePath.HOME_LOGISTIAN.getPage());
        role.put(5, PagePath.HOME_DRIVER.getPage());
    }

    public String getRoleHomePage(int roleId){
        return role.get(roleId);
    }
}
