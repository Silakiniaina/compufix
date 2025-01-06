package servlet.ordinateur;

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
import model.composant.cm.CarteMere;
import model.composant.disque.Disque;
import model.composant.processeur.Processeur;
import model.composant.ram.RAM;

@WebServlet("/ordinateur/add")
public class AddOrdinateurServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Composant> carteMeres = new CarteMere().getAll(c);
            List<Composant> rams = new RAM().getAll(c);
            List<Composant> disques = new Disque().getAll(c);
            List<Composant> processeurs = new Processeur().getAll(c);
            
            req.setAttribute("carteMeres", carteMeres);
            req.setAttribute("rams", rams);
            req.setAttribute("disques", disques);
            req.setAttribute("processeurs", processeurs);
            req.setAttribute("pageUrl", "/WEB-INF/views/ordinateur/addOrdinateur.jsp");

            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
    
}
