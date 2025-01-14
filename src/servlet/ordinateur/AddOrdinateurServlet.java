package servlet.ordinateur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

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
import model.ordinateur.TypeOrdinateur;

@WebServlet("/ordinateur/add")
public class AddOrdinateurServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");
            
            List<Composant> composants = new Composant().getAll(c);
            List<TypeOrdinateur> types = new TypeOrdinateur().getAll(c);

            req.setAttribute("composants", composants);
            req.setAttribute("types", types);
            req.setAttribute("jsFile", "addOrdinateur.js");
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
            Ordinateur o =  new Ordinateur();
            o.setNomOrdinateur(nom);
            o.setDescription(description);
            o.setTypeOrdinateur(c, Integer.parseInt(req.getParameter("type")));

            String[] composantsId = req.getParameterValues("composant");
            if(composantsId != null && composantsId.length > 1){
                int id = 0;
                for(String st : composantsId){
                    id = Integer.parseInt(st);
                    Composant comp = new Composant().getById(c, id);
                    o.ajouterComposant(c, comp);
                }
            }else{
                throw new Exception("Nombre de composant insuffisant pour un ordinateur");
            }
            
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
