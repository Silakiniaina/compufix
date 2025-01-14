package servlet.composant.stock;

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
import model.composant.stock.ElementMouvementStock;
import model.composant.stock.MouvementStock;

@WebServlet("/composant/stock/mouvement/list")
public class ListMouvementStockServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String filterDateStr = req.getParameter("date");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Date dt = filterDateStr != null ? Date.valueOf(LocalDate.parse(filterDateStr)) : Date.valueOf(LocalDate.now());
            List<ElementMouvementStock> sorties  = new MouvementStock().getAllMouvement(c, false);
            List<ElementMouvementStock> entrees  = new MouvementStock().getAllMouvement(c, true);

            req.setAttribute("entrees", entrees);
            req.setAttribute("sorties", sorties);

            req.setAttribute("pageUrl", "/WEB-INF/views/composant/stock/mouvements.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
