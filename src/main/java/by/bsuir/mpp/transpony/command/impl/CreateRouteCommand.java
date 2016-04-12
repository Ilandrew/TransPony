package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import javax.servlet.http.HttpServletRequest;

public class CreateRouteCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        return "WEB-INF/logistian/create_route.jsp";
    }
}
