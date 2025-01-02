<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.composant.statistique.StatistiqueComposant" %>

<% 
    List<StatistiqueComposant> statistiques = (List<StatistiqueComposant>)request.getAttribute("statistiques");
    Date debut = (Date)request.getAttribute("debut");
    Date fin = (Date)request.getAttribute("fin");
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <div class="row align-items-center mb-2">
                    <div class="col">
                        <h2 class="h3 page-title">Statistique Composant</h2>
                    </div>
                    <div class="col-auto">
                        <form class="form-inline" action="<%= request.getContextPath() %>/composant/statistique" method="POST">
                            <div class="form-group col-md-5">
                                <input type="date" name="debut" class="form-control" id="debut" placeholder="Date" value="<%= debut  %>">
                            </div>
                            <div class="form-group col-md-5">
                                <input type="date" name="fin" class="form-control" id="debut" placeholder="Date" value="<%= fin  %>">
                            </div>
                            <div class="form-group col-md-2">
                                <button type="submit" class="btn btn-sm btn-outline-success">
                                    <span class="fe fe-upload-cloud fe-16"></span>
                                </button>
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
                                        <th>Nom</th>
                                        <th>Nombre Entree</th>
                                        <th>Total entree</th>
                                        <th>Nombre Sortie</th>
                                        <th>Total Sortie</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(StatistiqueComposant statistique : statistiques){ %>
                                        <tr>
                                            <td class="text-center">
                                                <%= statistique.getComposant().getIdComposant() %>
                                            </td>
                                            <td>
                                                <%= statistique.getComposant().getNomComposant() %>
                                            </td>
                                            <td>
                                                <%= statistique.getNombreEntree() %>
                                            </td>
                                            <td>
                                                <%= statistique.getTotalEntree() %>
                                            </td>
                                            <td>
                                                <%= statistique.getNombreSortie() %>
                                            </td>
                                            <td>
                                                <%= statistique.getTotalSortie() %>
                                            </td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div> <!-- .col-12 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->
</main>