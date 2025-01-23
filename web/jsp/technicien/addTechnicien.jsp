<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.technicien.Technicien" %>
<%@ page import="model.technicien.Genre" %>
<%
    Technicien updated = (Technicien) request.getAttribute("updated");
    List<Genre> genres = (List<Genre>)request.getAttribute("genres");
%>

<div class="col-12">
    <h2 class="page-title">
        <%= updated != null ? "Mise a jour" : "Insertion" %> Technicien
    </h2>
    <div class="col-md-8 card shadow mb-4">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/technicien/add" method="POST">
                <input type="hidden" name="mode" value="<%= updated != null ? "u" : "" %>">
                <input type="hidden" name="id" value="<%= updated != null ? updated.getIdTechnicien() : "" %>">
                
                <div class="form-group">
                    <label for="nomTechnicien">Nom</label>
                    <input type="text" name="nom" class="form-control" id="nomTechnicien"
                           value="<%= updated != null ? updated.getNomTechnicien() : "" %>"
                           placeholder="Nom technicien" required>
                </div>
                
                <div class="form-group">
                    <label for="genre">Genre</label>
                    <select id="genre" name="genre" class="form-control" required>
                        <% for (Genre genre : genres) { %>
                            <option value="<%= genre.getIdGenre() %>">
                                <%= genre.getNomGenre() %>
                            </option>
                        <% } %>
                    </select>
                </div>
                
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </form>
        </div>
    </div>
</div>