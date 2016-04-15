package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.command.RoleHelper;
import by.bsuir.mpp.transpony.service.CredentialService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.service.UserService;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int userId = 0;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            userId = CredentialService.getForCredential(login, password);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
		if (userId <= 0) {
			request.setAttribute("errorMessage", "Invalid login/password.");
			return PagePath.LOGIN_PAGE.getPage();
		}

		request.getSession().setAttribute("user_id", userId);
		Integer roleId = null;
		try {
			roleId = UserService.getForIndex(userId).getUserPosition().getId();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if (roleId == null) {
			request.setAttribute("errorMessage", "Login error has occurred. Try again another time.");
			return PagePath.LOGIN_PAGE.getPage();
		}
		RoleHelper roleHelper = new RoleHelper();
		return roleHelper.getRoleHomePage(roleId);
    }
}
