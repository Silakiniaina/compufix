package servlet.technicien;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.technicien.Genre;
import model.technicien.Technicien;

@WebServlet("/technicien/add")
public class AddTechnicienServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection) req.getSession().getAttribute("connection");

            List<Genre> genres = new Genre().getAll(c); // Assuming you have a getAll method in Genre

            if (mode != null) {
                int id = Integer.parseInt(req.getParameter("id"));
                Technicien t = new Technicien().getById(c, id); // Assuming you have a getById method in Technicien
                if (mode.equals("d")) {
                    t.delete(c); // Assuming you have a delete method in Technicien
                    resp.sendRedirect(req.getContextPath() + "/technicien/list");
                } else if (mode.equals("u")) {
                    req.setAttribute("updated", t);
                }
            }

            req.setAttribute("genres", genres);
            req.setAttribute("pageUrl", "/WEB-INF/views/technicien/addTechnicien.jsp");
            req.getRequestDispatcher("/WEB-INF/views/shared/layout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection) req.getSession().getAttribute("connection");

            Technicien technicien = new Technicien();
            technicien.setNomTechnicien(req.getParameter("nom"));
            int idGenre = Integer.parseInt(req.getParameter("genre"));
            technicien.setGenre(new Genre().getById(c, idGenre));

            if (mode != null && mode.equals("u")) {
                int id = Integer.parseInt(req.getParameter("id"));
                technicien.setIdTechnicien(id);
                technicien.update(c); // Assuming you have an update method in Technicien
            } else {
                technicien.insert(c);
            }

            String success = "Technicien ajouté avec succès";
            resp.sendRedirect(req.getContextPath() + "/technicien/list?success=" + success);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}