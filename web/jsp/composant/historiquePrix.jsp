<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.composant.HistoriquePrixComposant" %>
<%@ page import="model.composant.Composant" %>

<% 
    List<HistoriquePrixComposant> historiques = (List<HistoriquePrixComposant>) request.getAttribute("historiques");
    List<Composant> composants = (List<Composant>) request.getAttribute("composants");
%>

<div class="col-12">
    <div class="row align-items-center mb-2">
        <div class="col-md-2">
            <h2 class="h3 page-title">Liste Historique Prix</h2>
        </div>
        <div class="col-md-10">
            <form class="form-inline" action="<%= request.getContextPath() %>/composant/historique-prix" method="POST">
                <div class="row">
                    <div class="form-group col-md-4">
                        <label>Composant</label>
                        <select id="composant" class="form-control" name="composant">
                            <option value="">All</option>
                            <% for (Composant composant : composants) { %>
                                <option value="<%= composant.getIdComposant() %>">
                                    <%= composant.getNomComposant() %>
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="debut">Debut</label>
                        <input type="date" name="debut" class="form-control" id="debut">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="fin">Fin</label>
                        <input type="date" name="fin" class="form-control" id="fin">
                    </div>
                    <div class="form-group col-md-2">
                        <button type="submit" class="btn btn-md btn-outline-success w-100 mt-4">
                            Filtrer
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Bordered table -->
    <div class="col-md-12 my-4">
        <div class="card shadow">
            <div class="card-body">
                <table class="table table-hover mb-0">
                    <thead>
                        <tr>
                            <th style="width: 120px;">ID</th>
                            <th>Composant</th>
                            <th>Date modification</th>
                            <th>Ancien prix</th>
                            <th>Nouveau prix</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (HistoriquePrixComposant historique : historiques) { %>
                            <tr>
                                <td class="text-center"><%= historique.getIdHistorique() %></td>
                                <td><%= historique.getComposant().getNomComposant() %></td>
                                <td><%= historique.getDateModification() %></td>
                                <td><%= historique.getAncienPrix() %></td>
                                <td><%= historique.getNouveauPrix() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- .col-12 -->