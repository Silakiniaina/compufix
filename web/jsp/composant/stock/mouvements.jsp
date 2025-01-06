<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.composant.stock.ElementMouvementStock" %>
<%@ page import="model.composant.stock.Stock" %>

<% 
    List<ElementMouvementStock> sorties = (List<ElementMouvementStock>)request.getAttribute("sorties");
    List<ElementMouvementStock> entrees = (List<ElementMouvementStock>)request.getAttribute("entrees");
    String dateFilter = (String)request.getAttribute("date");
%>
 
            <div class="col-12">
                <div class="row align-items-center mb-2">
                    <div class="col">
                        <h2 class="h3 page-title">Historique Mouvement</h2>
                    </div>
                    <div class="col-auto">
                        <form class="form-inline" action="<%= request.getContextPath() %>/composant/stock" method="POST">
                            <div class="form-group col-md-8">
                                <input type="date" name="date" class="form-control" id="dateFilter" placeholder="Date" value="<%= dateFilter != null ? LocalDate.parse(dateFilter) : LocalDate.now() %>">
                            </div>
                            <div class="form-group col-md-2">
                                <button type="submit" class="btn btn-sm btn-outline-success">
                                    <span class="fe fe-upload-cloud fe-16"></span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <!-- Bordered table -->
                    <div class="col-md-6 my-4">
                        <div class="card shadow">
                            <h4 class="h4 page-title">Entree en Stock</h4>
                            <div class="card-body">
                                <table class="table table-hover mb-0">
                                    <thead>
                                        <tr>
                                            <th style="width: 120px;">ID</th>
                                            <th>Nom</th>
                                            <th>Date</th>
                                            <th>Quantite</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for(ElementMouvementStock entree : entrees){ %>
                                            <tr>
                                                <td class="text-center">
                                                    <%= entree.getComposant().getIdComposant() %>
                                                </td>
                                                <td>
                                                    <%= entree.getComposant().getNomComposant() %>
                                                </td>
                                                <td>
                                                    <%= entree.getDateMouvement() %>
                                                </td>
                                                <td>
                                                    <%= entree.getQuantite() %>
                                                </td>
                                            </tr>
                                            <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 my-4">
                        <div class="card shadow">
                            <h4 class="h4 page-title">Sortie en Stock</h4>
                            <div class="card-body">
                                <table class="table table-hover mb-0">
                                    <thead>
                                        <tr>
                                            <th style="width: 120px;">ID</th>
                                            <th>Nom</th>
                                            <th>Date</th>
                                            <th>Quantite</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for(ElementMouvementStock sortie : sorties){ %>
                                            <tr>
                                                <td class="text-center">
                                                    <%= sortie.getComposant().getIdComposant() %>
                                                </td>
                                                <td>
                                                    <%= sortie.getComposant().getNomComposant() %>
                                                </td>
                                                <td>
                                                    <%= sortie.getDateMouvement() %>
                                                </td>
                                                <td>
                                                    <%= sortie.getQuantite() %>
                                                </td>
                                            </tr>
                                            <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- .col-12 -->
  