package by.bsuir.mpp.transpony.actions;

import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.Action;

import java.util.ArrayList;
import java.util.List;

public class ShowCheckPointAction extends ActionSupport{
    List<CheckPoint> collection = new ArrayList<>();
    public String execute() {

        try {
            collection = CheckpointService.getAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return Action.SUCCESS;
    }

    public List<CheckPoint> getCollection() {
        return collection;
    }

    public void setCollection(List<CheckPoint> collection) {
        this.collection = collection;
    }
}
