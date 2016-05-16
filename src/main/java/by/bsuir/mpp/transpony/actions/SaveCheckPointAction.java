package by.bsuir.mpp.transpony.actions;

import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.service.CheckpointService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by vadim on 16.05.16.
 */
public class SaveCheckPointAction extends ActionSupport{
    CheckPoint checkPoint = new CheckPoint();
    public String execute() throws Exception {
        if (checkPoint != null){
            CheckpointService.addNew(checkPoint);
            return Action.SUCCESS;
        }else
        return "badpoint";
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public void setName(String name) {
        checkPoint.setName(name);
    }
    public void setPointType(String type){
        checkPoint.setPointType("Растаможка");
    }
    public void setX(float x){
        checkPoint.setX(x);
    }
    public void setY(float y){
        checkPoint.setY(y);
    }
}
