package servlet.technicien;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.composant.TypeComposant;
import model.reparation.Reparation;
import model.technicien.CommissionPeriodFilter;
import model.technicien.CommissionTechnicien;

@WebServlet("/techniciens/commission")
public class CommissionTechnicienServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String debutStr = (String)req.getAttribute("debut");
        String finStr = (String)req.getAttribute("fin");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            CommissionPeriodFilter filter = null;

            if(debutStr != null || finStr != null){
                filter = new CommissionPeriodFilter();
                if(debutStr != null){
                    filter.setDebut(Date.valueOf(LocalDate.parse(debutStr)));
                }
                if(finStr != null){
                    filter.setDebut(Date.valueOf(LocalDate.parse(finStr)));
                }
            }

            List<CommissionTechnicien> commissions = new CommissionTechnicien().getAllCommissions(c, filter);

            req.setAttribute("commissions", commissions);
            req.setAttribute("pageUrl", "/WEB-INF/views/technicien/listeCommissions.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);;
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("debut", req.getParameter("debut"));
        req.setAttribute("fin", req.getParameter("fin"));
        doGet(req, resp);
    }
    
}
