package servlet.composant;

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
import model.composant.HistoriqueFilter;
import model.composant.HistoriquePrixComposant;

@WebServlet("/composant/historique-prix")
public class HistoriquePrixComposantServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String composantStr = req.getParameter("composant");
        String debutStr = req.getParameter("debut");
        String finStr = req.getParameter("fin");
        try {
            Connection c = (Connection) req.getSession().getAttribute("connexion");
            HistoriqueFilter filter = null;
            if ((debutStr != null && !debutStr.isEmpty()) || (finStr != null && !finStr.isEmpty()) || (composantStr != null && !composantStr.isEmpty())) {
                filter = new HistoriqueFilter();

                if (debutStr != null && !debutStr.isEmpty()) {
                    filter.setDateDebut(Date.valueOf(LocalDate.parse(debutStr))); 
                }

                if (finStr != null && !finStr.isEmpty()) {
                    filter.setDateFin(Date.valueOf(LocalDate.parse(finStr))); 
                }

                if(composantStr != null && !composantStr.isEmpty()){
                    int id = Integer.parseInt(composantStr);
                    Composant compo = new Composant().getById(c, id);
                    filter.setComposant(compo);
                }
            }
            List<HistoriquePrixComposant> historiques = new HistoriquePrixComposant().getAllByFilter(c, filter);
            List<Composant> composants = new Composant().getAll(c);
            req.setAttribute("historiques", historiques);
            req.setAttribute("composants", composants);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/historiquePrix.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    

}
