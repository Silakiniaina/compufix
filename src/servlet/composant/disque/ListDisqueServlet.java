package servlet.composant.disque;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.Composant;
import model.composant.disque.Disque;

@WebServlet("/composant/disque/list")
public class ListDisqueServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Composant> composants = new Disque().getAll(c);

            req.setAttribute("composants", composants);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/disque/liste.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
