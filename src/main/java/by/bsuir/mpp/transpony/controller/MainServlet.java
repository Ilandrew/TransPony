package by.bsuir.mpp.transpony.controller;

import by.bsuir.mpp.transpony.command.impl.GetCarCommand;
import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.impl.mysql.MySqlCarDao;
import by.bsuir.mpp.transpony.entity.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "sample", urlPatterns = "*.do")
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
        }

    }
}
