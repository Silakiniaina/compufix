package servlet.technicien;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.technicien.CommissionPeriodFilter;
import model.technicien.CommissionTechnicien;

@WebServlet("/techniciens/commission")
public class CommissionTechnicienServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String debutStr = req.getParameter("debut"); // Use getParameter to retrieve form values
        String finStr = req.getParameter("fin");

        try {
            Connection c = (Connection) req.getSession().getAttribute("connexion");

            CommissionPeriodFilter filter = null;

            // Check if filtering parameters are provided
            if ((debutStr != null && !debutStr.isEmpty()) || (finStr != null && !finStr.isEmpty())) {
                filter = new CommissionPeriodFilter();

                if (debutStr != null && !debutStr.isEmpty()) {
                    filter.setDebut(Date.valueOf(LocalDate.parse(debutStr))); // Parse and set start date
                }

                if (finStr != null && !finStr.isEmpty()) {
                    filter.setFin(Date.valueOf(LocalDate.parse(finStr))); // Parse and set end date
                }
            }

            // Fetch commissions with or without filters
            List<CommissionTechnicien> commissions = new CommissionTechnicien().getAllCommissions(c, filter);

            // Set attributes for the JSP
            req.setAttribute("commissions", commissions);
            req.setAttribute("pageUrl", "/WEB-INF/views/technicien/listeCommissions.jsp");

            // Forward to layout.jsp
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Directly forward the parameters to doGet
        doGet(req, resp);
    }
}
