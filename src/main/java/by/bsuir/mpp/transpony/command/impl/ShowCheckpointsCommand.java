package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowCheckpointsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<CheckPoint> collection = new ArrayList<>();
        try {
            collection = CheckpointService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("collection", collection);
        return PagePath.SHOW_CHECKPOINTS_PAGE.getPage();
    }
}
