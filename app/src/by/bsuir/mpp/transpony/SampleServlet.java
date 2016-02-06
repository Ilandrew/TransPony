package by.bsuir.mpp.transpony;

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

@WebServlet(name = "sample", urlPatterns = "/sample")
public class SampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery("select * from test.user where id=1");

            result.first();
            resp.getWriter().append(result.getString("login"));
        } catch (SQLException | NamingException e) {
            e.printStackTrace(resp.getWriter());
            resp.getWriter().flush();
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        resp.getWriter().append("<br>This is test servlet.").flush();
    }
    //WARNING! This class is for example. You should delete it after git pull.
}
