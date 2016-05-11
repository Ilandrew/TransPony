package by.bsuir.mpp.transpony.actions;


import by.bsuir.mpp.transpony.command.RoleActionHelper;
import by.bsuir.mpp.transpony.entity.user.User;
import by.bsuir.mpp.transpony.entity.user.UserCredentials;
import by.bsuir.mpp.transpony.service.CredentialService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.service.SessionService;
import by.bsuir.mpp.transpony.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginAction extends ActionSupport {
    UserCredentials userCredentials = new UserCredentials();
    int userId;
    int roleID;

    public String execute() {
        RoleActionHelper roleHelper = new RoleActionHelper();
        try {
            userId = CredentialService.getId(userCredentials.getLogin(), userCredentials.getPassword());
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

    public void setLogin(String login) {
        userCredentials.setLogin(login);
    }

    public void setPassword(String password) {
        userCredentials.setPassword(password);
    }

}

