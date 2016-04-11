package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.User;
import by.bsuir.mpp.transpony.servise.ServiceException;
import by.bsuir.mpp.transpony.servise.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class ShowUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<User> collection = new ArrayList<>();
        try {
            collection = UserService.getAll();
        }catch (ServiceException e){
            e.printStackTrace();
        }
        request.setAttribute("collection", collection);
        return "WEB-INF/userpage.jsp";
    }
}
