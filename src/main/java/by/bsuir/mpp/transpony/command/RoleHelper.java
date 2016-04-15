package by.bsuir.mpp.transpony.command;

import java.util.HashMap;
import java.util.Map;

public class RoleHelper {
   private Map<Integer, String> role;
    public RoleHelper() {
        role = new HashMap();
        role.put(1,"WEB-INF/logistian/index_logistian.jsp");
        role.put(2, "WEB-INF/logistian/index_admin.jsp");
        role.put(3, "WEB-INF/logistian/index_ruler.jsp");
        role.put(4, "WEB-INF/logistian/index_driver.jsp");
        role.put(5, "WEB-INF/logistian/index_exped.jsp");
    }
    public String getRole(int id){
        return role.get(id);
    }
}
