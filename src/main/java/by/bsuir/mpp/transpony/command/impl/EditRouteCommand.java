package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.RouteService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class EditRouteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request){

        List<CheckPoint> checkpoints = getAllCheckpoints();
        request.setAttribute("checkpoints", checkpoints);

        HttpSession session = request.getSession();
        if(request.getParameter("route_id") != null){
            int ind;
            ind = Integer.parseInt(request.getParameter("route_id"));
            try {
                Route route = RouteService.getForIndex(ind);
                route.setPoints(RouteService.getCheckPointForRoute(ind));
                session.setAttribute("route", route);
                session.getAttribute("route");
            } catch (ServiceException e) {
            e.printStackTrace();
            }

        }
        String funcParameter = request.getParameter("func");
        if (funcParameter != null) {
            Route route = (Route)session.getAttribute("route");
            List<CheckPoint> pointsInRoute = route.getPoints();
            if (funcParameter.equals("add_point_to_route")) {
                String pointName = request.getParameter("checkpoint_list");
                CheckPoint checkpoint = getCheckpointByName(pointName, checkpoints);
                pointsInRoute.add(checkpoint);
                request.setAttribute("message", "Point added to route.");
            } else if (funcParameter.equals("delete_point_from_route")) {
                String pointName = request.getParameter("delete_button");
                for (int i = 0; i < pointsInRoute.size(); i++) {
                    if (pointsInRoute.get(i).getName().equals(pointName)) {
                        pointsInRoute.remove(i);
                        break;
                    }
                }
                request.setAttribute("message", "Point deleted from route.");
            } else if (funcParameter.equals("add_total_length")) {
                String length = request.getParameter("length_value");
                BigDecimal lengthValue = new BigDecimal(length);
                route.setTotalLength(lengthValue);
                request.setAttribute("message", "Total length set.");
            }
            route.setPoints(pointsInRoute);
            session.setAttribute("route", route);
        }
        return PagePath.EDIT_ROUTE_PAGE.getPage();
    }
    private List<CheckPoint> getAllCheckpoints() {
        List<CheckPoint> checkpoints = new ArrayList<>();
        try {
            checkpoints = CheckpointService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return checkpoints;
    }

    private CheckPoint getCheckpointByName(String name, List<CheckPoint> checkpoints) {
        for (CheckPoint point : checkpoints) {
            if (point.getName().equals(name))
                return point;
        }
        return null;
    }
}
