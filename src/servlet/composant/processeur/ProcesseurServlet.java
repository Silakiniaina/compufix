package servlet.composant.processeur;

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
import model.composant.processeur.Processeur;
import model.composant.processeur.TypeProcesseur;

@WebServlet("/composant/processeur/add")
public class ProcesseurServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            List<TypeProcesseur> types = new TypeProcesseur().getAll(c);

            if(mode != null){
                int id = Integer.parseInt(req.getParameter("id"));
                Composant r = new Processeur().getById(c, id);
                if(mode.equals("d")){ 
                    r.delete(c);
                    resp.sendRedirect(req.getContextPath()+"/composant/processeur/list");
                }else if(mode.equals("u")){
                    req.setAttribute("updated", r);
                }
            }
            
            req.setAttribute("types", types);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/processeur/addProcesseur.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);;
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");
            Processeur r = new Processeur();
            r.setNomComposant(req.getParameter("nom"));
            r.setCapacite(Double.parseDouble(req.getParameter("capacite")));
            r.setPrixUnitaire(Double.parseDouble(req.getParameter("pu")));
            r.setTypeProcesseur(c, Integer.parseInt(req.getParameter("type")));
            r.setGeneration(Integer.parseInt(req.getParameter("generation")));
            r.setNombreCoeur(Integer.parseInt(req.getParameter("core")));


            if( mode != null ){
                if( mode.equals("u")){
                    int id = Integer.parseInt(req.getParameter("id"));
                    int idComposant = Integer.parseInt(req.getParameter("idComposant"));
                    r.setIdProcesseur(id);
                    r.setIdComposant(idComposant);

                    r.update(c);
                }else{
                    r.insert(c);
                }
            }else{

            }
            resp.sendRedirect(req.getContextPath()+"/composant/processeur/list");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
