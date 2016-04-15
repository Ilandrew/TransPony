package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
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
            switch (CredentialService.getRole(id)) {
                case 1: {
                    request.getSession().setAttribute("user_id", id);
                    return "WEB-INF/logistian/index_logistian.jsp";
                }
                case 2: {
                    request.getSession().setAttribute("user_id", id);
                    return "WEB-INF/carpage.jsp";
                }
                case 3: {
                    request.getSession().setAttribute("user_id", id);
                    return "WEB-INF/carpage.jsp";
                }
                case 4: {
                    request.getSession().setAttribute("user_id", id);
                    return "WEB-INF/carpage.jsp";
                }
                case 5: {
                    request.getSession().setAttribute("user_id", id);
                    return "WEB-INF/carpage.jsp";
                }
            }
        }
        request.setAttribute("errorMessage", "Incorrect login/password");
        return "WEB-INF/login.jsp";
    }
}
