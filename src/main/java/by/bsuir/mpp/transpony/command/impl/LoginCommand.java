package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.service.CredentialService;
import by.bsuir.mpp.transpony.service.ServiceException;

import javax.servlet.http.HttpServletRequest;


public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // just do it!
        int id = 0;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            id = CredentialService.getForCredential(login, password);
        }catch (ServiceException e){
            e.printStackTrace();
        }

        if (id > 0) {
            switch (CredentialService.getRole(id)) {
                case 1: {
                    request.getSession().setAttribute("User", id);
                    return "WEB-INF/logistian/index_logistian.jsp"; // адрес сайта 1 профиля
                }
                case 2: {
                    request.getSession().setAttribute("User", id);
                    return "WEB-INF/carpage.jsp"; // адрес сайта 2 профиля
                }
                case 3: {
                    request.getSession().setAttribute("User", id);
                    return "WEB-INF/carpage.jsp"; // адрес сайта 3 профиля
                }
                case 4: {
                    request.getSession().setAttribute("User", id);
                    return "WEB-INF/carpage.jsp"; // адрес сайта 4 профиля
                }
                case 5: {
                    request.getSession().setAttribute("User", id);
                    return "WEB-INF/carpage.jsp"; // адрес сайта 5 профиля
                }
            }
        }

            return "WEB-INF/login.jsp";
    }
}
