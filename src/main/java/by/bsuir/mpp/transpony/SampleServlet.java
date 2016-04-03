package by.bsuir.mpp.transpony;

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
        User user = null;
        List<User> collection = new ArrayList<>();
        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("select id_employee, first_name, second_name, middle_name, initials from EMPLOYEE");

            while (result.next()) {
                user = new User();
                user.setId(result.getInt("id_employee"));
                user.setFirstName(result.getString("id_employee"));
                user.setSecondName(result.getString("second_name"));
                user.setMiddleName(result.getString("middle_name"));
                user.setInitials(result.getString("initials"));
                collection.add(user);
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace(resp.getWriter());
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        req.setAttribute("collection", collection);
        req.getRequestDispatcher("WEB-INF/new.jsp").forward(req, resp);
    }
    //WARNING! This class is for example. You should delete it after git pull.
}
