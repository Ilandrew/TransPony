package by.bsuir.mpp.transpony.command.impl;


import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.service.RouteService;
import by.bsuir.mpp.transpony.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowRoutesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request)
    {
        List<Route> collection = new ArrayList<>();
        try {
            collection = RouteService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("collection", collection);
        return "WEB-INF/logistian/show_routes.jsp";
    }
}
