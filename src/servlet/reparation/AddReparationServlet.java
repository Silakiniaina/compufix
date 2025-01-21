package servlet.reparation;

import java.sql.Connection;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ordinateur.Ordinateur;
import model.reparation.TypeReparation;
import model.technicien.Technicien;

@WebServlet("/reparations/add")
public class AddReparationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String ordinateurStr = req.getParameter("ordinateur");
        try {
            
            if(ordinateurStr == null){
                throw new Exception("Aucun ordinateur pour une reparation");
            }else{
                Connection conn = (Connection)req.getSession().getAttribute("connexion");
                int ordinateur = Integer.parseInt(ordinateurStr);
                Ordinateur o  = new Ordinateur().getById(conn, ordinateur);
                List<TypeReparation> types = new TypeReparation().getAll(conn);
                List<Technicien> techniciens = new Technicien().getAll(conn);

                req.setAttribute("ordinateur", o);
                req.setAttribute("types", types);
                req.setAttribute("techniciens", techniciens);

                req.setAttribute("pageUrl", "/WEB-INF/views/reparation/addReparation.jsp");
                req.setAttribute("jsFile", "addReparation.js");
                req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
            }


        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }

    
}