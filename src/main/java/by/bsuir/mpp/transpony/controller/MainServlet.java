package by.bsuir.mpp.transpony.controller;

import by.bsuir.mpp.transpony.command.Command;
import by.bsuir.mpp.transpony.command.CommandHelper;
import by.bsuir.mpp.transpony.util.HttpUtils;

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

        String request = HttpUtils.extractCommandFromQuery(req.getRequestURI(), ".do");
        Command command = helper.getCommand(request);
        if (command != null) {
             req.getRequestDispatcher(command.execute(req)).forward(req, resp);
        } else {
            //req.getRequestDispatcher("WEB-INF/error.html").forward(req, resp);
        }
    }
}
