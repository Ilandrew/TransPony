package by.bsuir.mpp.transpony.command.impl;


import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.service.RouteService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowRoutesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request)
    {
        List<Route> routes = new ArrayList<>();
        try {
            routes = RouteService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("routes", routes);
        return PagePath.SHOW_ROUTES_PAGE.getPage();
    }
}
