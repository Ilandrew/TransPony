package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.Route;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreateRouteCommand implements Command {
	private static final String CHECKPOINTS_ATTRIBUTE = "checkpoints";
	private static final String NEW_ROUTE_ATTRIBUTE = "new_route";
	private static final String MESSAGE_ATTRIBUTE = "message";
	private static final String POINT_ADDED_MESSAGE = "Point added to route.";
	private static final String POINT_DELETED_MESSAGE = "Point deleted from route.";
	private static final String TOTAL_LENGTH_SET_MESSAGE = "Total length set.";
	private static final String FUNC_PARAMETER = "func";
	private static final String ADD_POINT_PARAMETER_VALUE = "add_point_to_route";
	private static final String DELETE_POINT_PARAMETER_VALUE = "delete_point_from_route";
	private static final String ADD_LENGTH_PARAMETER_VALUE = "add_total_length";
	private static final String CHECKPOINT_LIST = "checkpoint_list";
	private static final String DELETE_BUTTON = "delete_button";
	private static final String LENGTH_TEXT = "length_value";

    @Override
    public String execute(HttpServletRequest request) {
        List<CheckPoint> checkpoints = getAllCheckpoints();
        request.setAttribute(CHECKPOINTS_ATTRIBUTE, checkpoints);

        String funcParameter = request.getParameter(FUNC_PARAMETER);
        if (funcParameter != null) {
            HttpSession session = request.getSession();
            Route route = (Route) session.getAttribute(NEW_ROUTE_ATTRIBUTE);
            if (route == null) {
                route = new Route();
                route.setPoints(new ArrayList<>());
            }

            List<CheckPoint> pointsInRoute = route.getPoints();
            if (funcParameter.equals(ADD_POINT_PARAMETER_VALUE)) {
                String pointName = request.getParameter(CHECKPOINT_LIST);
                CheckPoint checkpoint = getCheckpointByName(pointName, checkpoints);
                pointsInRoute.add(checkpoint);
                request.setAttribute(MESSAGE_ATTRIBUTE, POINT_ADDED_MESSAGE);
            } else if (funcParameter.equals(DELETE_POINT_PARAMETER_VALUE)) {
                String pointName = request.getParameter(DELETE_BUTTON);
                for (int i = 0; i < pointsInRoute.size(); i++) {
                    if (pointsInRoute.get(i).getName().equals(pointName)) {
                        pointsInRoute.remove(i);
                        break;
                    }
                }
                request.setAttribute(MESSAGE_ATTRIBUTE, POINT_DELETED_MESSAGE);
            } else if (funcParameter.equals(ADD_LENGTH_PARAMETER_VALUE)) {
				String length = request.getParameter(LENGTH_TEXT);
				BigDecimal lengthValue = new BigDecimal(length);
				route.setTotalLength(lengthValue);
				request.setAttribute(MESSAGE_ATTRIBUTE, TOTAL_LENGTH_SET_MESSAGE);
			}
            route.setPoints(pointsInRoute);
            session.setAttribute(NEW_ROUTE_ATTRIBUTE, route);
        }

        return PagePath.CREATE_ROUTE_PAGE.getPage();
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
