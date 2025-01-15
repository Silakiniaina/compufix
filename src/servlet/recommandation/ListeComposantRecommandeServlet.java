package servlet.recommandation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.recommandation.ComposantRecommande;
import model.recommandation.RecommandationFilterArg;

@WebServlet("/composant/recommandations")
public class ListeComposantRecommandeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String moisStr = (String)req.getAttribute("mois");
        String anneeStr = (String)req.getAttribute("annee");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");
            List<ComposantRecommande> composants = null;

            int idMois = moisStr != null ? Integer.parseInt(moisStr) : 0;
            int annee = anneeStr != null ? Integer.parseInt(anneeStr) : 0;
            RecommandationFilterArg filter = new RecommandationFilterArg();
            filter.setMois(idMois);
            filter.setAnnee(annee);
            composants = new ComposantRecommande().getAllByMois(c, filter);
            req.setAttribute("composants", composants);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/listeRecommandation.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);;
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mois", req.getParameter("mois"));
        req.setAttribute("annee", req.getParameter("annee"));
        doGet(req, resp);
    }
}
