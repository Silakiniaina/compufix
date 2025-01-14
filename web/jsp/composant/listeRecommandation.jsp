<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.utils.GeneralUtils" %>
<%@ page import="model.recommandation.Mois" %>
<%@ page import="model.recommandation.ComposantRecommande" %>

<% 
    List<Mois>  mois = (List<Mois>)request.getAttribute("mois");
    List<ComposantRecommande>  composants = (List<ComposantRecommande>)request.getAttribute("composants");
%>
 
<div class="col-12">
    <div class="row align-items-center mb-2">
        <div class="col">
            <h2 class="h3 page-title">Liste Recommandations composant</h2>
        </div>
        <div class="col-auto d-flex" style="gap:10px;">
            <form class="form-inline" action="<%= request.getContextPath() %>/composant/recommandations" method="POST">
                <div class="row">
                    <div class="col-md-4 form-group mb-3">
                        <label for="mois">Mois</label>
                        <select id="mois" name="mois" class="form-control w-100">
                            <option value="">All</option>
                            <% for(Mois m : mois){ %>
                                <option value="<%= m.getIdMois() %>">
                                    <%= m.getNomMois() %> 
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <%= GeneralUtils.generateSelectYearRecommandation() %>
                    <div class="col-md-4 form-group">
                        <button type="submit" class="btn btn-md btn-outline-success w-100">
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
                            <th>Description</th>
                            <th>Mois</th>
                            <th>Annee</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(ComposantRecommande composant : composants){ %>
                            <tr>
                                <td class="text-center">
                                    <%= composant.getIdComposantRecommande() %>
                                </td>
                                <td>
                                    <%= composant.getComposant().getNomComposant() %>
                                </td>
                                <td>
                                    <%= composant.getDescription() %>
                                </td>
                                <td>
                                    <%= composant.getMois().getNomMois() %>
                                </td>
                                <td>
                                    <%= composant.getAnnee() %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- .col-12 -->
