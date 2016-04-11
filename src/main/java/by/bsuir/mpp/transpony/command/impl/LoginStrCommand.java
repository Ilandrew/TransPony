package by.bsuir.mpp.transpony.command.impl;

import by.bsuir.mpp.transpony.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LoginStrCommand implements Command {
        // этот класс создан для тэста логинизации ибо перенаправление чёт не фурычит потом удалить!
    @Override
    public String execute(HttpServletRequest request) {
        return "WEB-INF/login.jsp";
    }
}
