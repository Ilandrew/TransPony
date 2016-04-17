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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        dispatch(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        dispatch(request, response);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String commandName = HttpUtils.extractCommandFromQuery(request.getRequestURI(), ".do");
        Command command = helper.getCommand(commandName);
        if (command != null) {
            request.getRequestDispatcher(command.execute(request)).forward(request, response);
        }
    }
}
