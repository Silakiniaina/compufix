<%@ page import="model.composant.ram.TypeRam" %>
<%@ page import="model.composant.ram.RAM" %>
<%@ page import="java.util.List" %>
<%@ page import="model.composant.Composant" %>
<%
    List<TypeRam> types = (List<TypeRam>)request.getAttribute("types");
    Composant updated = (Composant)request.getAttribute("updated");
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <h2 class="page-title">
                    <%= updated !=null ? "Mise a jour" : "Insertion" %> RAM
                </h2>
                <div class="col-md-8 card shadow mb-4">
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/composant/ram/add"
                            method="POST">
                            <input type="hidden" name="mode" value="<%= updated != null ? "u": "" %>">
                            <input type="hidden" name="id" value="<%= updated != null ? ((RAM)updated).getIdRam(): "" %>">
                            <input type="hidden" name="idComposant" value="<%= updated != null ? updated.getIdComposant(): "" %>">
                            <div class="form-group">
                                <label for="nomComposant">Nom</label>
                                <input type="text" name="nom" class="form-control" id="nomComposant"
                                    value="<%= updated != null ? updated.getNomComposant(): "" %>"
                                    placeholder="Nom type" required>
                            </div>
                            <div class="form-group">
                                <label for="capacite">Capacite [ Mo ]</label>
                                <input type="nomber" name="capacite" class="form-control" min="128"
                                    id="capacite"
                                    value="<%= updated != null ? updated.getCapacite(): 128 %>"
                                    placeholder="Capacite " required>
                            </div>
                            <div class="form-group">
                                <label for="typeram">Type RAM</label>
                                <select id="typeram" name="type" class="form-control">
                                    <% for(TypeRam type : types){ %>
                                        <option value="<%= type.getIdTypeRam() %>">
                                            <%= type.getNomTypeRam() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <h5 class="mb-2">Categorie</h5>
                                <div class="custom-control custom-radio">
                                    <input type="radio" id="portable" name="categorie"
                                        class="custom-control-input" value="true" check>
                                    <label class="custom-control-label" for="portable">Portable</label>
                                </div>
                                <div class="custom-control custom-radio">
                                    <input type="radio" id="bureau" name="categorie"
                                        class="custom-control-input" value="false">
                                    <label class="custom-control-label" for="bureau">Bureau</label>
                                </div>
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
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->
</main>