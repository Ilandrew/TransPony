package by.bsuir.mpp.transpony.actions;

import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 04.05.16.
 */
public class RedirectAction extends ActionSupport {

    public String execute() {
    return "create";
    }

    public String createRoute(){
        return "create";
    }

    public String showRoute(){
        return "show";
    }

    public String createCP(){
        return "createcp";
    }
}
