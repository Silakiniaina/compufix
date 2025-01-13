package servlet.composant;

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
import model.composant.TypeComposant;

@WebServlet("/composant/add")
public class AddComposantServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            List<TypeComposant> types = new TypeComposant().getAll(c);

            if(mode != null){
                int id = Integer.parseInt(req.getParameter("id"));
                Composant r = new Composant().getById(c, id);
                if(mode.equals("d")){ 
                    r.delete(c);
                    resp.sendRedirect(req.getContextPath()+"/composant/list");
                }else if(mode.equals("u")){
                    req.setAttribute("updated", r);
                }
            }
            
            req.setAttribute("types", types);
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/addComposant.jsp");
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

            Composant comp = new Composant();
            comp.setNomComposant(req.getParameter("nom"));
            comp.setCapacite(Double.parseDouble(req.getParameter("capacite")));
            comp.setPrixUnitaire(Double.parseDouble(req.getParameter("pu")));
            comp.setTypeComposant(c, Integer.parseInt(req.getParameter("type")));

            if( mode != null ){
                if( mode.equals("u")){
                    int id = Integer.parseInt(req.getParameter("id"));
                    comp.setIdComposant(id);
                    comp.update(c);
                }else{
                    comp.insert(c);
                }
            }else{

            }

            String success = "Composant ajouter avec succes";
            resp.sendRedirect(req.getContextPath()+"/composant/list?success="+success);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
