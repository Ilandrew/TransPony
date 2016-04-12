package by.bsuir.mpp.transpony.controller;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.command.CommandHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = "*.do")
public class MainServlet extends HttpServlet {

    private CommandHelper helper = new CommandHelper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        dispatch(req, resp);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String request = req.getParameter("button").toUpperCase();
        Command command = helper.getCommand(request);
        if (command != null) {
             req.getRequestDispatcher(command.execute(req)).forward(req, resp);
        } else {
            //тут будет дофига ошибка хз чего
            //req.getRequestDispatcher("WEB-INF/error.html").forward(req, resp);
        }
    }
}
