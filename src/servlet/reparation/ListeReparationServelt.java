package servlet.reparation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.TypeComposant;
import model.reparation.Reparation;

@WebServlet("/reparations")
public class ListeReparationServelt extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String typeComposant = (String)req.getAttribute("type");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            List<TypeComposant> types = new TypeComposant().getAll(c);
            List<Reparation> reparations = new Reparation().getAll(c);

            if(typeComposant != null && !typeComposant.equals("")){
                int idTypeComposant = Integer.parseInt(typeComposant);

                reparations = new Reparation().getAllByTypeComposant(c, idTypeComposant);
            }

            req.setAttribute("types", types);
            req.setAttribute("reparations", reparations);
            req.setAttribute("pageUrl", "/WEB-INF/views/reparation/liste.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);;
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setAttribute("type", req.getParameter("type"));
       doGet(req, resp);
    }
    
}
