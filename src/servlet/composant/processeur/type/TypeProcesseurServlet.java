package servlet.composant.processeur.type;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.processeur.TypeProcesseur;

@WebServlet("/composant/processeur/type/add")
public class TypeProcesseurServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            if(mode != null){
                int id = Integer.parseInt(req.getParameter("id"));
                TypeProcesseur t = new TypeProcesseur().getById(c, id);
                if(mode.equals("d")){ 
                    t.delete(c);
                    resp.sendRedirect(req.getContextPath()+"/composant/processeur/type/list");
                }else if(mode.equals("u")){
                    req.setAttribute("updated", t);
                }
            }
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/processeur/type/addType.jsp");
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
            TypeProcesseur t = new TypeProcesseur();
            t.setNomTypeProcesseur(req.getParameter("nom"));

            if( mode != null ){
                if( mode.equals("u")){
                    int id = Integer.parseInt(req.getParameter("id"));
                    t.setIdTypeProcesseur(id);

                    t.update(c);
                }else{
                    t.insert(c);
                }
            }else{

            }
            resp.sendRedirect(req.getContextPath()+"/composant/processeur/type/list");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    

}
