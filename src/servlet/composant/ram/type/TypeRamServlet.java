package servlet.composant.ram.type;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.ram.TypeRam;

@WebServlet("/composant/ram/type/add")
public class TypeRamServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            if(mode != null){
                int id = Integer.parseInt(req.getParameter("id"));
                TypeRam t = new TypeRam().getById(c, id);
                if(mode.equals("d")){ 
                    t.delete(c);
                    resp.sendRedirect(req.getContextPath()+"/composant/ram/type/list");
                }else if(mode.equals("u")){
                    req.setAttribute("updated", t);
                }
            }
            req.setAttribute("pageUrl", "/WEB-INF/views/composant/ram/type/addType.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}