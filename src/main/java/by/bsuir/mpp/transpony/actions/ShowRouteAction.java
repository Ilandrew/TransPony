package by.bsuir.mpp.transpony.actions;

import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.RouteService;
import by.bsuir.mpp.transpony.service.ServiceException;
import com.opensymphony.xwork2.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 05.05.16.
 */
public class ShowRouteAction {
    List<Route> routeList = new ArrayList<>();

    public String execute() {


        try {
            routeList = RouteService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }
}
