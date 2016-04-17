package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.User;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.RouteService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.service.UserService;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;

public class SaveCheckpointCommand implements Command {
	private static final String MESSAGE_ATTRIBUTE = "message";
	private static final String NAME_NOT_SET_MESSAGE = "Name of point is not set.";
	private static final String X_VALUE_INVALID_MESSAGE = "Point x is not set.";
	private static final String Y_VALUE_INVALID_MESSAGE = "Point y is not set.";
	private static final String NAME_TEXT_PARAMETER = "name_text";
	private static final String X_TEXT_PARAMETER = "x_text";
	private static final String Y_TEXT_PARAMETER = "y_text";
	private static final String TYPE_LIST_PARAMETER = "types_list";

    @Override
    public String execute(HttpServletRequest request) {
		String name = request.getParameter(NAME_TEXT_PARAMETER);
		if (name.equals("")) {
			request.setAttribute(MESSAGE_ATTRIBUTE, NAME_NOT_SET_MESSAGE);
			return new CreateCheckpointCommand().execute(request);
		}
		String x = request.getParameter(X_TEXT_PARAMETER);
        if (x.equals("")) {
			request.setAttribute(MESSAGE_ATTRIBUTE, X_VALUE_INVALID_MESSAGE);
			return new CreateCheckpointCommand().execute(request);
		}
		String y = request.getParameter(Y_TEXT_PARAMETER);
        if (y.equals("")) {
			request.setAttribute(MESSAGE_ATTRIBUTE, Y_VALUE_INVALID_MESSAGE);
			return new CreateCheckpointCommand().execute(request);
		}
		CheckPoint checkPoint = new CheckPoint();
		String type = request.getParameter(TYPE_LIST_PARAMETER);
		checkPoint.setPointType(type);
		checkPoint.setX(Float.parseFloat(x));
		checkPoint.setY(Float.parseFloat(y));
		checkPoint.setName(name);
		try {
			CheckpointService.addNew(checkPoint);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return PagePath.HOME_LOGISTIAN.getPage();
    }
}
