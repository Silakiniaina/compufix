package servlet.ordinateur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
import model.ordinateur.Ordinateur;

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
        PrintWriter out = resp.getWriter();
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        try {

            Connection c = (Connection)req.getSession().getAttribute("connexion");
            int idRam = Integer.parseInt(req.getParameter("ram"));
            int idProcesseur = Integer.parseInt(req.getParameter("processeur"));
            int idDisque = Integer.parseInt(req.getParameter("disque"));
            int idCarteMere = Integer.parseInt(req.getParameter("cm"));

            CarteMere cm = (CarteMere)new CarteMere().getById(c, idCarteMere);
            RAM ram = (RAM)new RAM().getById(c, idRam);
            Processeur cpu = (Processeur)new Processeur().getById(c, idProcesseur);
            Disque disque = (Disque)new Disque().getById(c, idDisque);

            Ordinateur o =  new Ordinateur();
            o.setNomOrdinateur(nom);
            o.setDescription(description);
            o.ajouterComposant(c, cm, 1);
            o.ajouterComposant(c, ram, 1);
            o.ajouterComposant(c, disque, 1);
            o.ajouterComposant(c, cpu, 1);

            o.insert(c);
            o.installerComposants(c);
            
            String success = "Composant ajouter avec succes";
            resp.sendRedirect(req.getContextPath()+"/ordinateur/add?success="+success);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        } 
    }
    
}
