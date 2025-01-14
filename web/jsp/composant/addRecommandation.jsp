<%@ page import="model.recommandation.Mois" %>
<%@ page import="java.util.List" %>

<%
    String composant = (String)request.getAttribute("composant");
    List<Mois>  mois = (List<Mois>)request.getAttribute("mois");
%>

<div class="col-12">
    <h2 class="page-title">Insertion Recommandation</h2>
    <div class="col-md-8 card shadow mb-4">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/composant/recommandations/add" method="POST" id="computerForm">
                <input type="hidden" name="composant" value="<%= composant != null ? composant : "" %>">
                <div class="form-group">
                    <label for="mois">Mois</label>
                    <select id="mois" name="mois" class="form-control">
                        <% for(Mois m : mois){ %>
                            <option value="<%= m.getIdMois() %>">
                                <%= m.getNomMois() %> 
                            </option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="annee">Annee</label>
                    <input type="number" name="annee" min="1" value="2025" class="form-control" id="annee" placeholder="Annee" required>
                </div>
                <div class="form-group mb-3">
                    <label for="description">Description</label>
                    <textarea class="form-control" name="desc" id="description" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </form>
        </div>
    </div>
</div>