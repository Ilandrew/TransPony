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

/**
 * Created by vadim on 16.04.16.
 */
public class SaveCheckpointCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        CheckPoint checkPoint = new CheckPoint();
            String type = request.getParameter("types_list");
            String name = request.getParameter("name_text");
            String x = request.getParameter("x_text");
            String y = request.getParameter("y_text");
            if (name.equals("")){
                request.setAttribute("message", "Name of point is not set.");
                return new CreateCheckpointCommand().execute(request);
            } else {
                if (x.equals("")) {
                    request.setAttribute("message", "Point x is not set.");
                    return new CreateCheckpointCommand().execute(request);
                } else {

                    if (y.equals("")) {
                        request.setAttribute("message", "Point y is not set.");
                        return new CreateCheckpointCommand().execute(request);
                    } else
                    try {
                        checkPoint.setPointType(type);
                        checkPoint.setX(Float.parseFloat(x));
                        checkPoint.setY(Float.parseFloat(y));
                        checkPoint.setName(name);
                        CheckpointService.addNew(checkPoint);
                        request.getSession().setAttribute("new_checkpoint", null);
                        return PagePath.HOME_LOGISTIAN.getPage();
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                }
            }
        return PagePath.HOME_LOGISTIAN.getPage();
    }
}
