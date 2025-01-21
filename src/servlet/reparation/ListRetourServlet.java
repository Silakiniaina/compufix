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
import model.ordinateur.TypeOrdinateur;
import model.reparation.Retour;
import model.reparation.RetourFilter;
import model.reparation.TypeReparation;

@WebServlet("/reparation/retour")
public class ListRetourServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String typeOrdinateurStr = (String)req.getAttribute("typeOrdinateur");
        String typeReparationStr = (String)req.getAttribute("typeReparation");
        String typeComposantStr = (String)req.getAttribute("typeComposant");
        String dateRetourStr = (String)req.getAttribute("date");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connection");

            List<TypeComposant> typeComposants = new TypeComposant().getAll(c);
            List<TypeOrdinateur> typeOrdinateurs = new TypeOrdinateur().getAll(c);
            List<TypeReparation> typeReparations = new TypeReparation().getAll(c);
            
            RetourFilter filter = new RetourFilter(c, typeOrdinateurStr, typeReparationStr, typeComposantStr,dateRetourStr);
            List<Retour> retours = new Retour().getAllWithFilter(c, filter);
            req.setAttribute("typeComposants", typeComposants);
            req.setAttribute("typeReparations", typeReparations);
            req.setAttribute("typeOrdinateurs", typeOrdinateurs);
            req.setAttribute("retours", retours);
            req.setAttribute("pageUrl", "/WEB-INF/views/reparation/listeRetour.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);;
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typeOrdinateur", req.getParameter("typeOrdinateur"));
        req.setAttribute("typeReparation", req.getParameter("typeReparation"));
        req.setAttribute("typeComposant", req.getParameter("typeComposant"));
        req.setAttribute("date", req.getParameter("date"));
        doGet(req, resp);
    }
    
}
