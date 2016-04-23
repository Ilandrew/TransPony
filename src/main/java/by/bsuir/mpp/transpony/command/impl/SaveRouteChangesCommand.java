package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.entity.user.User;
import by.bsuir.mpp.transpony.service.RouteService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.service.UserService;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;

public class SaveRouteChangesCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        Route route = (Route) request.getSession().getAttribute("route");
        if (route.getTotalLength() == null) {
            request.setAttribute("message", "Total length is not set.");
            return new EditRouteCommand().execute(request);
        } else {
            Integer index = (Integer) request.getSession().getAttribute("user_id");
            User routeCreator;
            try {
                routeCreator = UserService.getById(index);
                route.setOwner(routeCreator);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            try {
                RouteService.update(route);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("new_route", null);
            return PagePath.HOME_LOGISTIAN.getPage();
        }
    }
}
