<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.composant.TypeComposant" %>
<%@ page import="model.reparation.TypeReparation" %>
<%@ page import="model.ordinateur.TypeOrdinateur" %>
<%@ page import="model.reparation.Reparation" %>
<%@ page import="model.reparation.Retour" %>

<% 
    List<TypeComposant>  typeComposants = (List<TypeComposant>)request.getAttribute("typeComposants");
    List<TypeReparation>  typeReparations = (List<TypeReparation>)request.getAttribute("typeReparations");
    List<TypeOrdinateur>  typeOrdinateurs = (List<TypeOrdinateur>)request.getAttribute("typeOrdinateurs");
    List<Retour>  retours = (List<Retour>)request.getAttribute("retours");
    String date = (String)request.getAttribute("date");
%>
 
<div class="col-12">
    <div class="row align-items-center mb-2">
        <div class="col-md-2">
            <h2 class="h3 page-title">Liste Retours</h2>
        </div>
        <div class="col-auto d-flex" style="gap:10px;">
            <form class="form-inline" action="<%= request.getContextPath() %>/reparation/retour" method="POST">
                <div class="row">
                    <div class="col-md-2 form-group mb-3">
                        <label for="typeOrdinateur">Type Ordinateur</label>
                        <select id="typeOrdinateur" name="typeOrdinateur" class="form-control w-100">
                            <option value="">All</option>
                            <% for(TypeOrdinateur type : typeOrdinateurs){ %>
                                <option value="<%= type.getIdTypeOrdinateur() %>">
                                    <%= type.getNomTypeOrdinateur() %> 
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-2 form-group mb-3">
                        <label for="typeReparation">Type Reparation</label>
                        <select id="typeReparation" name="typeReparation" class="form-control w-100">
                            <option value="">All</option>
                            <% for(TypeReparation type : typeReparations){ %>
                                <option value="<%= type.getIdTypeReparation() %>">
                                    <%= type.getNomTypeReparation() %> 
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-2 form-group mb-3">
                        <label for="typeComposant">Type Composant</label>
                        <select id="typeComposant" name="typeComposant" class="form-control w-100">
                            <option value="">All</option>
                            <% for(TypeComposant type : typeComposants){ %>
                                <option value="<%= type.getIdTypeComposant() %>">
                                    <%= type.getNomTypeComposant() %> 
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-2 form-group mb-3">
                        <label for="dateRetour">Date</label>
                        <input type="date" name="date" class="form-control" id="dateRetour" placeholder="Nom Ordinateur">
                    </div>
                    <div class="col-md-2 form-group">
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
                            <th>Date</th>
                            <th>Ordinateur</th>
                            <th>Client</th>
                            <th>Type Ordinateur</th>
                            <th>Problemes</th>
                            <th>Prix Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Retour retour : retours){ %>
                            <tr>
                                <td class="text-center">
                                    <%= retour.getIdRetour() %>
                                </td>
                                <td>
                                    <%= retour.getDateRetour() %>
                                </td>
                                <td>
                                    <%= retour.getReparation().getOrdinateur().getClient().getNomClient() %>
                                </td>
                                <td>
                                    <%= retour.getReparation().getOrdinateur().getNomOrdinateur() %>
                                </td>
                                <td>
                                    <%= retour.getReparation().getOrdinateur().getTypeOrdinateur().getNomTypeOrdinateur() %>
                                </td>
                                <td>
                                    <% for(TypeComposant t : retour.getReparation().getTypeComposants()){ %>
                                    <%= t.getNomTypeComposant() %> ,   
                                    <% } %>
                                </td>
                                <td>
                                    <%= retour.getPrixTotal() %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- .col-12 -->
