package by.bsuir.mpp.transpony.actions;

import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by vadim on 16.05.16.
 */
public class DeletePointAction extends ActionSupport{
    CheckPoint checkPoint = new CheckPoint();

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public String execute(){
        try {
            if (checkPoint != null){
                CheckpointService.delete(checkPoint);
                return Action.SUCCESS;
            } else {
                return "baddelete";
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            return "baddelete";
        }
    }
}
