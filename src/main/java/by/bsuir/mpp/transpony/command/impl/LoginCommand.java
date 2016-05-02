package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.command.RoleHelper;
import by.bsuir.mpp.transpony.entity.document.DocumentFormat;
import by.bsuir.mpp.transpony.service.*;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
	private static final String LOGIN_PARAMETER = "login";
	private static final String PASSWORD_PARAMETER = "password";
	private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
	private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid login/password.";
	private static final String LOGIN_ERROR_MESSAGE = "Login error has occurred. Try again another time.";

    @Override
    public String execute(HttpServletRequest request) {
		int userId = 0;
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        try {
            userId = CredentialService.getId(login, password);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
		if (userId <= 0) {
			request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, INVALID_CREDENTIALS_MESSAGE);
			return PagePath.LOGIN_PAGE.getPage();
		}

		request.getSession().setAttribute(USER_ID_ATTRIBUTE, userId);
		Integer roleId = null;
		try {
			roleId = UserService.getById(userId).getUserPosition().getId();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if (roleId == null) {
			request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, LOGIN_ERROR_MESSAGE);
			return PagePath.LOGIN_PAGE.getPage();
		}
		RoleHelper roleHelper = new RoleHelper();
		return roleHelper.getRoleHomePage(roleId);
    }
}
