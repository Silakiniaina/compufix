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
import model.technicien.CommissionGenreFilter;
import model.technicien.CommissionTechnicienGenre;

@WebServlet("/techniciens/commission-genre")
public class CommissionTechnicienGenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String debutStr = req.getParameter("debut"); // Use getParameter to retrieve form values
        String finStr = req.getParameter("fin");
        String genreStr = req.getParameter("genre");

        try {
            Connection c = (Connection) req.getSession().getAttribute("connexion");

            CommissionGenreFilter filter = null;

            // Check if filtering parameters are provided
            if ((debutStr != null && !debutStr.isEmpty()) || (finStr != null && !finStr.isEmpty()) || genreStr != null && !genreStr.trim().equals("") ) {
                filter = new CommissionGenreFilter();

                if (debutStr != null && !debutStr.isEmpty()) {
                    filter.setDebut(Date.valueOf(LocalDate.parse(debutStr))); // Parse and set start date
                }

                if (finStr != null && !finStr.isEmpty()) {
                    filter.setFin(Date.valueOf(LocalDate.parse(finStr))); // Parse and set end date
                }

                if(genreStr != null && !genreStr.trim().equals("")){
                    filter.setGenre(c,Integer.parseInt(genreStr));
                }
            }


            // Fetch commissions with or without filters
            List<CommissionTechnicienGenre> commissions = new CommissionTechnicienGenre().getAllCommissionsGenre(c, filter);

            // Set attributes for the JSP
            req.setAttribute("commissions", commissions);
            req.setAttribute("pageUrl", "/WEB-INF/views/technicien/listeCommissionsGenre.jsp");

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
