package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.entity.user.User;
import by.bsuir.mpp.transpony.service.ServiceException;
import by.bsuir.mpp.transpony.service.UserService;
import by.bsuir.mpp.transpony.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class ShowUserCommand implements Command {
    private static final String COLLECTION_ATTRIBUTE = "collection";

    @Override
    public String execute(HttpServletRequest request) {
        List<User> collection = new ArrayList<>();
        try {
            collection = UserService.getAll();
        }catch (ServiceException e){
            e.printStackTrace();
        }
        request.setAttribute(COLLECTION_ATTRIBUTE, collection);
        return PagePath.SHOW_USERS_PAGE.getPage();
    }
}
