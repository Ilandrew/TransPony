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
    private static final String CHECKPOINTS_ATTRIBUTE = "checkpoints";
    private static final String ROUTE_ATTRIBUTE = "route";
	private static final String MESSAGE_ATTRIBUTE = "message";
	private static final String POINT_ADDED_MESSAGE = "Point added to route.";
	private static final String POINT_DELETED_MESSAGE = "Point deleted from route.";
	private static final String TOTAL_LENGTH_SET_MESSAGE = "Total length set.";
	private static final String ROUTE_ID_PARAMETER = "route_id";
	private static final String FUNC_PARAMETER = "func";
	private static final String ADD_POINT_PARAMETER_VALUE = "add_point_to_route";
	private static final String DELETE_POINT_PARAMETER_VALUE = "delete_point_from_route";
	private static final String CHANGE_LENGTH_PARAMETER_VALUE = "change_total_length";
	private static final String CHECKPOINT_LIST = "checkpoint_list";
	private static final String DELETE_BUTTON = "delete_button";
	private static final String LENGTH_TEXT = "length_value";

    @Override
    public String execute(HttpServletRequest request) {
        List<CheckPoint> checkpoints = getAllCheckpoints();
        request.setAttribute(CHECKPOINTS_ATTRIBUTE, checkpoints);

        HttpSession session = request.getSession();
        if(request.getParameter(ROUTE_ID_PARAMETER) != null){
            int id;
            id = Integer.parseInt(request.getParameter(ROUTE_ID_PARAMETER));
            try {
                Route route = RouteService.getById(id);
                route.setPoints(RouteService.getByRouteId(id));
                session.setAttribute(ROUTE_ATTRIBUTE, route);
            } catch (ServiceException e) {
            	e.printStackTrace();
            }
        }

        String funcParameter = request.getParameter(FUNC_PARAMETER);
        if (funcParameter != null) {
            Route route = (Route)session.getAttribute(ROUTE_ATTRIBUTE);
            List<CheckPoint> pointsInRoute = route.getPoints();
			switch (funcParameter) {
				case ADD_POINT_PARAMETER_VALUE: {
					String pointName = request.getParameter(CHECKPOINT_LIST);
					CheckPoint checkpoint = getCheckpointByName(pointName, checkpoints);
					pointsInRoute.add(checkpoint);
					request.setAttribute(MESSAGE_ATTRIBUTE, POINT_ADDED_MESSAGE);
					break;
				}
				case DELETE_POINT_PARAMETER_VALUE: {
					String pointName = request.getParameter(DELETE_BUTTON);
					for (int i = 0; i < pointsInRoute.size(); i++) {
						if (pointsInRoute.get(i).getName().equals(pointName)) {
							pointsInRoute.remove(i);
							break;
						}
					}
					request.setAttribute(MESSAGE_ATTRIBUTE, POINT_DELETED_MESSAGE);
					break;
				}
				case CHANGE_LENGTH_PARAMETER_VALUE:
					String length = request.getParameter(LENGTH_TEXT);
					BigDecimal lengthValue = new BigDecimal(length);
					route.setTotalLength(lengthValue);
					request.setAttribute(MESSAGE_ATTRIBUTE, TOTAL_LENGTH_SET_MESSAGE);
					break;
			}
            route.setPoints(pointsInRoute);
            session.setAttribute(ROUTE_ATTRIBUTE, route);
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
