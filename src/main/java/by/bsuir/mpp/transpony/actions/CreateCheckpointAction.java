package by.bsuir.mpp.transpony.actions;

import by.bsuir.mpp.transpony.service.CheckpointService;
import by.bsuir.mpp.transpony.service.ServiceException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by Максим on 16.05.2016.
 */
public class CreateCheckpointAction extends ActionSupport {
	private List<String> types;

	public String execute() {
		try {
			types = CheckpointService.getAllTypes();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}
