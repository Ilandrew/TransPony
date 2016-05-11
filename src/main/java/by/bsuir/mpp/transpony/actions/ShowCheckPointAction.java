package by.bsuir.mpp.transpony.actions;

import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.Action;

import java.util.List;

public class ShowCheckPointAction extends ActionSupport{
    private List<CheckPoint> pointLists;
    public String execute() {

        try {
            pointLists = CheckpointService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public List<CheckPoint> getPointLists() {
        return pointLists;
    }

    public void setPointLists(List<CheckPoint> pointLists) {
        this.pointLists = pointLists;
    }
}
