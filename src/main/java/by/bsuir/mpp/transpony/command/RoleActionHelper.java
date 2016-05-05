package by.bsuir.mpp.transpony.command;

import by.bsuir.mpp.transpony.util.PagePath;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vadim on 04.05.16.
 */
public class RoleActionHelper {

    private Map<Integer, String> role;
    public RoleActionHelper() {
        role = new HashMap<>();
        role.put(1, "leader");
        role.put(2, "admin");
        role.put(3, "accountant");
        role.put(4, "logistian");
        role.put(5, "driver");
    }

    public String getRoleHomePage(int roleId){
        return role.get(roleId);
    }
}
