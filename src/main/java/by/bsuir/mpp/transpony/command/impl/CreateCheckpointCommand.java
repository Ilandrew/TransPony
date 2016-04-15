package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CreateCheckpointCommand implements Command
{

    @Override
    public String execute(HttpServletRequest request)
    {
        CheckpointService checkpointService = new CheckpointService();
        List<String> type = null;
            try {
                type = checkpointService.getAllType();
            }catch (ServiceException e){
                e.printStackTrace();
            }
            request.setAttribute("type", type);

        return PagePath.CREATE_CHECKPOINT_PAGE.getPage();
    }

}
