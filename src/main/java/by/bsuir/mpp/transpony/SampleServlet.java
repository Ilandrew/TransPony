package by.bsuir.mpp.transpony;

import by.bsuir.mpp.transpony.dao.DaoException;
import by.bsuir.mpp.transpony.dao.impl.mysql.MySqlCar;
import by.bsuir.mpp.transpony.entity.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "sample", urlPatterns = "/sample")
public class SampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<Car> collection = new ArrayList<>();

        try {
            collection = MySqlCar.getInstance().getFreeCars();
        } catch (DaoException daoException) {
            daoException.printStackTrace();
        }

        req.setAttribute("collection", collection);
        req.getRequestDispatcher("WEB-INF/carpage.jsp").forward(req, resp);
    }
    //WARNING! This class is for example. You should delete it after git pull.
}
