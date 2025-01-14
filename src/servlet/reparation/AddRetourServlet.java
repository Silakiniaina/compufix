package servlet.reparation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.reparation.Reparation;
import model.reparation.Retour;

@WebServlet("/reparation/retour/add")
public class AddRetourServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idReparationStr = req.getParameter("reparation");
        try {
            if(idReparationStr == null){
                throw new Exception("Aucune reparation trouver pour inserer une retour");
            }else{
                Connection c = (Connection)req.getSession().getAttribute("connexion");
                int idReparation = Integer.parseInt(idReparationStr);
                Reparation r = new Reparation().getById(c, idReparation);
                double prix = Double.parseDouble(req.getParameter("prix"));
                Date date = Date.valueOf(LocalDate.parse(req.getParameter("date")));

                Retour retour = new Retour();
                retour.setReparation(r);
                retour.setDateRetour(date);
                retour.setPrixTotal(prix);

                retour.insert(c);

                String success = "Retour inserer avec success";
                resp.sendRedirect(req.getContextPath()+"/reparation/retour?success="+success);
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idReparationStr = req.getParameter("reparation");
        try {
            if( idReparationStr == null){
                throw new Exception("Aucune reparation trouver pour inserer une retour");
            }else{

                req.setAttribute("reparation", idReparationStr);
                req.setAttribute("pageUrl", "/WEB-INF/views/reparation/addRetour.jsp");

                req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    
}