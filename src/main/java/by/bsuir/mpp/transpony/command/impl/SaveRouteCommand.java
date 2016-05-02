package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.entity.user.User;
import by.bsuir.mpp.transpony.service.RouteService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.service.UserService;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;

public class SaveRouteCommand implements Command {
	private static final String NEW_ROUTE_ATTRIBUTE = "new_route";
	private static final String MESSAGE_ATTRIBUTE = "message";
	private static final String LENGTH_NOT_SET_MESSAGE = "Total length is not set.";

    @Override
    public String execute(HttpServletRequest request)
    {
        Route route = (Route) request.getSession().getAttribute(NEW_ROUTE_ATTRIBUTE);
        if (route.getTotalLength() == null) {
            request.setAttribute(MESSAGE_ATTRIBUTE, LENGTH_NOT_SET_MESSAGE);
            return new CreateRouteCommand().execute(request);
        } else {
            Integer index = (Integer) request.getSession().getAttribute(USER_ID_ATTRIBUTE);
            User routeCreator;
            try {
                routeCreator = UserService.getById(index);
                route.setOwner(routeCreator);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            try {
                RouteService.generateName(route);
                RouteService.add(route);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute(NEW_ROUTE_ATTRIBUTE, null);
            return PagePath.HOME_LOGISTIAN.getPage();
        }
    }
}
