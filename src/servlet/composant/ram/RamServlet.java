package servlet.composant.ram;

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
import model.composant.ram.RAM;
import model.composant.ram.TypeRam;

@WebServlet("/composant/ram/add")
public class RamServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            List<TypeRam> types = new TypeRam().getAll(c);

            if(mode != null){
                int id = Integer.parseInt(req.getParameter("id"));
                Composant r = new RAM().getById(c, id);
                if(mode.equals("d")){ 
                    r.delete(c);
                    resp.sendRedirect(req.getContextPath()+"/composant/ram/list");
                }else if(mode.equals("u")){
                    req.setAttribute("updated", r);
                }
            }
            
            req.setAttribute("types", types);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/ram/addRam.jsp");
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
            RAM r = new RAM();
            r.setNomComposant(req.getParameter("nom"));
            r.setCapacite(Double.parseDouble(req.getParameter("capacite")));
            r.setPrixUnitaire(Double.parseDouble(req.getParameter("pu")));
            r.setTypeRam(c, Integer.parseInt(req.getParameter("type")));
            r.setPortable(Boolean.parseBoolean(req.getParameter("categorie")));

            if( mode != null ){
                if( mode.equals("u")){
                    int id = Integer.parseInt(req.getParameter("id"));
                    r.setIdComposant(id);

                    r.update(c);
                }else{
                    r.insert(c);
                }
            }else{

            }
            resp.sendRedirect(req.getContextPath()+"/composant/ram/list");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
