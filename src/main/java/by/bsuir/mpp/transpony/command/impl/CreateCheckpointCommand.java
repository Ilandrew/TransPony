package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;

public class CreateCheckpointCommand implements Command
{

    @Override
    public String execute(HttpServletRequest request)
    {
        return PagePath.CREATE_CHECKPOINT_PAGE.getPage();
    }

}
