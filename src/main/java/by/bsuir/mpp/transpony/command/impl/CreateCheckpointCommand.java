package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateCheckpointCommand implements Command {
    private static final String TYPES_ATTRIBUTE = "types";

    @Override
    public String execute(HttpServletRequest request) {
        CheckpointService checkpointService = new CheckpointService();
        List<String> types = null;
            try {
                types = checkpointService.getAllTypes();
            }catch (ServiceException e){
                e.printStackTrace();
            }
            request.setAttribute(TYPES_ATTRIBUTE, types);

        return PagePath.CREATE_CHECKPOINT_PAGE.getPage();
    }

}
