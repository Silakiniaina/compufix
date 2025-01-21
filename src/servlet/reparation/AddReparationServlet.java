package servlet.reparation;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.Composant;
import model.ordinateur.ComposantOrdinateur;
import model.ordinateur.Ordinateur;
import model.reparation.ComposantReparation;
import model.reparation.Reparation;
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
        PrintWriter out = resp.getWriter();
        String ordinateurStr = req.getParameter("ordinateur");
        String dateStr = req.getParameter("date");
        try {

            Connection c = (Connection)req.getSession().getAttribute("connexion");
            String[] composants = req.getParameterValues("composantReparation");
            Reparation r = new Reparation();
            if(composants != null && composants.length > 0){
                for(String st : composants){
                    String[] split = st.split(",");
                    ComposantReparation cr = new ComposantReparation();
                    ComposantOrdinateur co = new ComposantOrdinateur().getById(c, Integer.parseInt(split[2]));
                    TypeReparation tr = new TypeReparation().getById(c, Integer.parseInt(split[0]));
                    Technicien t = new Technicien().getById(c, Integer.parseInt(split[1]));


                    cr.setReparation(r);
                    cr.setComposantOrdinateur(co);
                    cr.setTypeReparation(tr);
                    cr.setTechnicien(t);

                    r.getComposants().add(cr);
                }
            }else{
                throw new Exception("Il faut au moins reparer un composant pour une reparation");
            }
            
            r.setOrdinateur(c, Integer.parseInt(ordinateurStr));
            r.setDateReparation(dateStr);
            r.insert(c);
            r.insererComposantReparations(c);
            
            String success = "Reparation ajouter avec succes";
            resp.sendRedirect(req.getContextPath()+"/reparations?success="+success);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        } 
    }

    
}