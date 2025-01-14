package servlet.recommandation;

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
import model.composant.Composant;
import model.recommandation.ComposantRecommande;
import model.recommandation.Mois;
import model.reparation.Reparation;
import model.reparation.Retour;

@WebServlet("/composant/recommandations/add")
public class AddRecommandationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idComposantStr = req.getParameter("composant");
        try {
            if( idComposantStr == null){
                throw new Exception("Aucune composant trouver pour inserer une recommandation");
            }else{
                Connection c = (Connection)req.getSession().getAttribute("connexion");
                List<Mois> mois = new Mois().getAll(c);

                req.setAttribute("composant", idComposantStr);
                req.setAttribute("mois", mois);
                req.setAttribute("pageUrl", "/WEB-INF/views/composant/addRecommandation.jsp");

                req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idComposantStr = req.getParameter("composant");
        try {
            if(idComposantStr == null){
                throw new Exception("Aucune composant trouver pour inserer une recommandation");
            }else{
                Connection c = (Connection)req.getSession().getAttribute("connexion");
                int idComposant = Integer.parseInt(idComposantStr);
                Composant comp = new Composant().getById(c, idComposant);
                int mois = Integer.parseInt(req.getParameter("mois"));
                int annee = Integer.parseInt(req.getParameter("annee"));
                String description = req.getParameter("desc");

                ComposantRecommande cr = new ComposantRecommande();
                cr.setComposant(comp);
                cr.setAnnee(annee);
                cr.setMois(new Mois().getById(c, mois));
                cr.setDescription(description);

                cr.insert(c);

                String success = "Recommandation inserer avec success";
                resp.sendRedirect(req.getContextPath()+"/composant/recommandations?success="+success);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }
    }
    
}
