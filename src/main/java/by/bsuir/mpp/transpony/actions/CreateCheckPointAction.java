package by.bsuir.mpp.transpony.actions;

import by.bsuir.mpp.transpony.entity.Type;
import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 05.05.16.
 */
public class CreateCheckPointAction extends ActionSupport{

    List<Type> types = new ArrayList<>();

    public String execute() {
        try {
            types = CheckpointService.getAllTypes();
        }catch (ServiceException e){
            e.printStackTrace();
        }

        return Action.SUCCESS;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
