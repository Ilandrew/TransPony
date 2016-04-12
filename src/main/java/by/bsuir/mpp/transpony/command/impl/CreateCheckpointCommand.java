package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;

import javax.servlet.http.HttpServletRequest;

public class CreateCheckpointCommand implements Command
{

    @Override
    public String execute(HttpServletRequest request)
    {
        return "WEB-INF/logistian/create_checkpoint";
    }
}
