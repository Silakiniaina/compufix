<%@ page import="model.composant.TypeComposant" %>
<%@ page import="java.util.List" %>
<%@ page import="model.composant.Composant" %>
<%
    List<TypeComposant> types = (List<TypeComposant>)request.getAttribute("types");
    Composant updated = (Composant)request.getAttribute("updated");
%>
 
            <div class="col-12">
                <h2 class="page-title">
                    <%= updated !=null ? "Mise a jour" : "Insertion" %> Composant
                </h2>
                <div class="col-md-8 card shadow mb-4">
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/composant/add"
                            method="POST">
                            <input type="hidden" name="mode" value="<%= updated != null ? "u": "" %>">
                            <input type="hidden" name="id" value="<%= updated != null ? updated.getIdComposant(): "" %>">
                            <div class="form-group">
                                <label for="nomComposant">Nom</label>
                                <input type="text" name="nom" class="form-control" id="nomComposant"
                                    value="<%= updated != null ? updated.getNomComposant(): "" %>"
                                    placeholder="Nom type" required>
                            </div>
                            <div class="form-group">
                                <label for="capacite">Capacite </label>
                                <input type="nomber" name="capacite" class="form-control" min="1"
                                    id="capacite"
                                    value="<%= updated != null ? updated.getCapacite(): 1 %>"
                                    placeholder="Capacite " required>
                            </div>
                            <div class="form-group">
                                <label for="type_compoant">Type Composant</label>
                                <select id="type_compoant" name="type" class="form-control">
                                    <% for(TypeComposant type : types){ %>
                                        <option value="<%= type.getIdTypeComposant() %>">
                                            <%= type.getNomTypeComposant() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pu">Prix Unitaire</label>
                                <input type="nomber" name="pu" class="form-control" min="0"
                                    id="pu"
                                    value="<%= updated != null ? updated.getPrixUnitaire(): 0 %>"
                                    placeholder="PU " required>
                            </div>
                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form>
                    </div>
                </div>
            </div> <!-- .col-22 -->
  