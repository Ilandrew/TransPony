package by.bsuir.mpp.transpony.controller;

import by.bsuir.mpp.transpony.command.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = "*.do")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        processRequest(req,resp);
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestType requestType = RequestType.valueOf(request.getParameter("button").toUpperCase());
        switch (requestType){
            case GET_CAR:{
                GetCarCommand getCarCommand = new GetCarCommand();
                request.getRequestDispatcher(getCarCommand.execute(request)).forward(request, response);
            }
            case SHOW_USER:{
                ShowUserCommand showUserCommand = new ShowUserCommand();
                request.getRequestDispatcher(showUserCommand.execute(request)).forward(request, response);
            }
            case LOGIN:{
                LoginCommand loginCommand = new LoginCommand();
                request.getRequestDispatcher(loginCommand.execute(request)).forward(request, response);
            }
            case LOGIN_STR:{
                LoginStrCommand loginStrCommand = new LoginStrCommand();
                request.getRequestDispatcher(loginStrCommand.execute(request)).forward(request, response);
            }
            case CREATE_ROUTE:{
                CreateRouteCommand createRouteCommand = new CreateRouteCommand();
                request.getRequestDispatcher(createRouteCommand.execute(request)).forward(request, response);
            }
            case SHOW_ROUTES:{
                ShowRoutesCommand showRoutesCommand = new ShowRoutesCommand();
                request.getRequestDispatcher(showRoutesCommand.execute(request)).forward(request, response);
            }
        }
    }
}
