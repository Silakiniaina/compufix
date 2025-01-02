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
import model.composant.stock.ElementStock;
import model.composant.stock.Stock;

@WebServlet("/composant/stock")
public class StockServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String filterDateStr = req.getParameter("date");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Date dt = filterDateStr != null ? Date.valueOf(LocalDate.parse(filterDateStr)) : Date.valueOf(LocalDate.now());
            List<ElementStock> stocks  = new Stock().getEtatStock(c, dt);

            req.setAttribute("stocks", stocks);
            req.setAttribute("date", dt);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/stock/etat.jsp");
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
