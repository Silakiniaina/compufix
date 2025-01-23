package servlet.technicien;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.technicien.Technicien;

@WebServlet("/technicien/list")
public class ListTechnicienServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get the database connection from the session
            Connection c = (Connection) req.getSession().getAttribute("connexion");

            // Retrieve the list of techniciens
            List<Technicien> techniciens = new Technicien().getAll(c); // Assuming you have a getAll method in Technicien

            // Set the list as a request attribute
            req.setAttribute("techniciens", techniciens);

            // Forward to the JSP view
            req.setAttribute("pageUrl", "/WEB-INF/views/technicien/listTechnicien.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
    }
}