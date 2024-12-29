package servlet.composant.cm;

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
import model.composant.disque.TypeDisque;
import model.composant.processeur.TypeProcesseur;
import model.composant.ram.TypeRam;

@WebServlet("/composant/cm/add")
public class CarteMereServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            List<TypeDisque> types_disque = new TypeDisque().getAll(c);
            List<TypeProcesseur> types_processeur = new TypeProcesseur().getAll(c);
            List<TypeRam> types_ram = new TypeRam().getAll(c);

            if(mode != null){
                int id = Integer.parseInt(req.getParameter("id"));
                Composant r = new CarteMere().getById(c, id);
                if(mode.equals("d")){ 
                    r.delete(c);
                    resp.sendRedirect(req.getContextPath()+"/composant/cm/list");
                }else if(mode.equals("u")){
                    req.setAttribute("updated", r);
                }
            }
            
            req.setAttribute("types_disque", types_disque);
            req.setAttribute("types_processeur", types_processeur);
            req.setAttribute("types_ram", types_ram);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/cm/addCarteMere.jsp");
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
            CarteMere r = new CarteMere();
            r.setNomComposant(req.getParameter("nom"));
            r.setCapacite(0);
            r.setPrixUnitaire(Double.parseDouble(req.getParameter("pu")));
            r.setTypeDisque(c, Integer.parseInt(req.getParameter("type_disque")));
            r.setTypeRam(c, Integer.parseInt(req.getParameter("type_ram")));
            r.setTypeProcesseur(c, Integer.parseInt(req.getParameter("type_cpu")));
            r.setNombreSlotRam(Integer.parseInt(req.getParameter("slot_ram")));
            r.setNombreSlotDisque(Integer.parseInt(req.getParameter("slot_disque")));

            if( mode != null ){
                if( mode.equals("u")){
                    int id = Integer.parseInt(req.getParameter("id"));
                    int idComposant = Integer.parseInt(req.getParameter("idComposant"));
                    r.setIdCarteMere(id);
                    r.setIdComposant(idComposant);

                    r.update(c);
                }else{
                    r.insert(c);
                }
            }else{

            }
            resp.sendRedirect(req.getContextPath()+"/composant/cm/list");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
