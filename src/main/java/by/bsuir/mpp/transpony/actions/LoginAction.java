package by.bsuir.mpp.transpony.actions;


import by.bsuir.mpp.transpony.command.RoleActionHelper;
import by.bsuir.mpp.transpony.service.CredentialService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.service.SessionService;
import by.bsuir.mpp.transpony.service.UserService;
import com.opensymphony.xwork2.ActionSupport;


/**
 * Created by vadim on 04.05.16.
 */
public class LoginAction extends ActionSupport {
    private String login;
    private String password;
    int userId;
    int roleID;

    public String execute() {
        RoleActionHelper roleHelper = new RoleActionHelper();
        try {
            userId = CredentialService.getId(login, password);
            if (userId > 0) {
                roleID = UserService.getById(userId).getUserPosition().getId();
                new SessionService().saveUserAuthentication(userId, roleHelper.getRoleHomePage(roleID));
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (roleID != 0){
        return roleHelper.getRoleHomePage(roleID);
        }else {
            return "badlogin";
        }
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
