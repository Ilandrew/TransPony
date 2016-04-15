package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.command.RoleHelper;
import by.bsuir.mpp.transpony.service.CredentialService;
import by.bsuir.mpp.transpony.service.ServiceException;

import javax.servlet.http.HttpServletRequest;


public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int id = 0;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            id = CredentialService.getForCredential(login, password);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (id > 0) {
        request.getSession().setAttribute("user_id", id);
            RoleHelper roleHelper = new RoleHelper();
            return roleHelper.getRole(id);
        }
        request.setAttribute("errorMessage", "Incorrect login/password");
        return "WEB-INF/login.jsp";
    }
}
