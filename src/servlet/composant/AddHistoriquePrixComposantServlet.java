package servlet.composant;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.HistoriquePrixComposant;
import model.composant.Composant;

@WebServlet("/composant/update-prix")
public class AddHistoriquePrixComposantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection c = (Connection) req.getSession().getAttribute("connection");

            int idComposant = Integer.parseInt(req.getParameter("composant"));
            Composant composant = new Composant().getById(c, idComposant);

            req.setAttribute("composant", composant);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/addHistoriquePrix.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection) req.getSession().getAttribute("connection");

            int idComposant = Integer.parseInt(req.getParameter("idComposant"));
            double nouveauPrix = Double.parseDouble(req.getParameter("nouveauPrix"));
            String dateModificationStr = req.getParameter("dateModification");

            HistoriquePrixComposant historique = new HistoriquePrixComposant();
            if(dateModificationStr != null && !dateModificationStr.isEmpty()){
                historique.setDateModification(Date.valueOf(LocalDate.parse(dateModificationStr)));
            }
            
            Composant composant = new Composant().getById(c, idComposant);
            historique.setAncienPrix(composant.getPrixUnitaire());
            historique.setNouveauPrix(nouveauPrix);
            composant.setIdComposant(idComposant);
            historique.setComposant(composant);
            
            historique.insert(c);
            String success = "Historique de prix ajout&eacute; avec succès!";
            resp.sendRedirect(req.getContextPath() + "/composant/historique-prix?idComposant=" + idComposant + "&success=" + success);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}