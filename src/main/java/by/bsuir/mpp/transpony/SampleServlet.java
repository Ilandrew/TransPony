package by.bsuir.mpp.transpony;

import by.bsuir.mpp.transpony.dao.DaoExeption;
import by.bsuir.mpp.transpony.entity.Car;
import by.bsuir.mpp.transpony.entity.User;
import by.bsuir.mpp.transpony.util.DatabaseUtils;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "sample", urlPatterns = "/sample")
public class SampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Connection connection = null;
        Statement statement = null;
        Car car;
        List<Car> collection = new ArrayList<>();
       /* try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT  cr.id_car as id,\n" +
                    "        cr.license_plate as license_plate,\n" +
                    "        vc.name as vendor,\n" +
                    "        mc.name as model,\n" +
                    "        cr.fuel_consumption as fuel_consumption,\n" +
                    "        ct.name as car_type\n" +
                    "FROM CAR cr\n" +
                    "LEFT JOIN VENDOR_CAR vc ON cr.id_vendor_car = vc.id_vendor_car\n" +
                    "LEFT JOIN MODEL_CAR mc ON cr.id_model_car = mc.id_model_car\n" +
                    "LEFT JOIN CAR_TYPE ct ON cr.id_car_type = ct.id_car_type\n" +
                    "WHERE cr.id_car not in (SELECT tr.id_car FROM TRIP tr WHERE tr.id_trip_status = 2)");

            while (result.next()) {
                car = new Car();
                car.setId(result.getInt("id"));
                car.setLicensePlate(result.getString("license_plate"));
                car.setVendor(result.getString("vendor"));
                car.setModel(result.getString("model"));
                car.setType(result.getString("car_type"));
                car.setFuelConsumption(result.getBigDecimal("fuel_consumption"));
                collection.add(car);
            }

        } catch (SQLException | NamingException e) {
            //throw new DaoExeption();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }*/
        req.setAttribute("collection", collection);
        req.getRequestDispatcher("WEB-INF/carpage.jsp").forward(req, resp);
    }
    //WARNING! This class is for example. You should delete it after git pull.
}
