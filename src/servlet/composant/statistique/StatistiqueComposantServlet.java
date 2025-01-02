package servlet.composant.statistique;

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
import model.composant.statistique.StatistiqueComposant;

@WebServlet("/composant/statistique")
public class StatistiqueComposantServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String dateDebutStr = req.getParameter("debut");
        String dateFinStr = req.getParameter("fin");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Date debut = dateDebutStr != null ? Date.valueOf(LocalDate.parse(dateDebutStr)) : Date.valueOf(LocalDate.now().minusMonths(1));
            Date fin = dateFinStr != null ? Date.valueOf(LocalDate.parse(dateFinStr)) : Date.valueOf(LocalDate.now());
            List<StatistiqueComposant> statistiques  = new StatistiqueComposant().getStatistique(c, debut, fin);

            req.setAttribute("statistiques", statistiques);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/statistique/mouvement.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
