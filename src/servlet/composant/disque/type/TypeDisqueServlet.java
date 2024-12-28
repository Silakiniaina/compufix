package servlet.composant.disque.type;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.disque.TypeDisque;

@WebServlet("/composant/disque/type/add")
public class TypeDisqueServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            if(mode != null){
                int id = Integer.parseInt(req.getParameter("id"));
                TypeDisque t = new TypeDisque().getById(c, id);
                if(mode.equals("d")){ 
                    t.delete(c);
                    resp.sendRedirect(req.getContextPath()+"/composant/disque/type/list");
                }else if(mode.equals("u")){
                    req.setAttribute("updated", t);
                }
            }
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/disque/type/addType.jsp");
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
            TypeDisque t = new TypeDisque();
            t.setNomTypeDisque(req.getParameter("nom"));

            if( mode != null ){
                if( mode.equals("u")){
                    int id = Integer.parseInt(req.getParameter("id"));
                    t.setIdTypeDisque(id);

                    t.update(c);
                }else{
                    t.insert(c);
                }
            }else{

            }
            resp.sendRedirect(req.getContextPath()+"/composant/disque/type/list");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    

}
