package servlet.composant.stock;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.Composant;
import model.composant.ram.RAM;
import model.composant.ram.TypeRam;
import model.composant.stock.ElementMouvementStock;
import model.composant.stock.ElementStock;
import model.composant.stock.Stock;

@WebServlet("/composant/stock/mouvement/add")
public class MouvementStockServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Composant> composants = new Composant().getAll(c);

            req.setAttribute("composants", composants);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/stock/addMouvement.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);;
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            int idComposant = Integer.parseInt(req.getParameter("composant"));
            Date dt = Date.valueOf(req.getParameter("date"));
            double quantite = Double.parseDouble(req.getParameter("quantite"));
            boolean est_entree = Boolean.parseBoolean(req.getParameter("est_entree"));

            ElementMouvementStock element = new ElementMouvementStock();
            element.setIdComposant(idComposant);
            element.setDateMouvement(dt);
            element.setQuantite(quantite);
            
            new Stock().addMouvement(c, element, est_entree);

            resp.sendRedirect(req.getContextPath()+"/composant/stock/mouvement/add");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

}
