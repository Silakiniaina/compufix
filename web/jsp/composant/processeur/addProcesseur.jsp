<%@ page import="model.composant.processeur.TypeProcesseur" %>
<%@ page import="model.composant.processeur.Processeur" %>
<%@ page import="java.util.List" %>
<%@ page import="model.composant.Composant" %>
<%
    List<TypeProcesseur> types = (List<TypeProcesseur>)request.getAttribute("types");
    Composant updated = (Composant)request.getAttribute("updated");
%>
 
            <div class="col-12">
                <h2 class="page-title">
                    <%= updated !=null ? "Mise a jour" : "Insertion" %> Processeur
                </h2>
                <div class="col-md-8 card shadow mb-4">
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/composant/processeur/add"
                            method="POST">
                            <input type="hidden" name="mode" value="<%= updated != null ? "u": "" %>">
                            <input type="hidden" name="id" value="<%= updated != null ? ((Processeur)updated).getIdProcesseur(): "" %>">
                            <input type="hidden" name="idComposant" value="<%= updated != null ? updated.getIdComposant(): "" %>">
                            <div class="form-group">
                                <label for="nomComposant">Nom</label>
                                <input type="text" name="nom" class="form-control" id="nomComposant"
                                    value="<%= updated != null ? updated.getNomComposant(): "" %>"
                                    placeholder="Nom type" required>
                            </div>
                            <div class="form-group">
                                <label for="capacite">Capacite [ Hz ]</label>
                                <input type="nomber" name="capacite" class="form-control" min="0"
                                    id="capacite"
                                    value="<%= updated != null ? updated.getCapacite(): 0 %>"
                                    placeholder="Capacite " required>
                            </div>
                            <div class="form-group">
                                <label for="TypeProcesseur">Type Processeur</label>
                                <select id="TypeProcesseur" name="type" class="form-control">
                                    <% for(TypeProcesseur type : types){ %>
                                        <option value="<%= type.getIdTypeProcesseur() %>">
                                            <%= type.getNomTypeProcesseur() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="generation">Generation</label>
                                <input type="nomber" name="generation" class="form-control" min="0"
                                    id="generation"
                                    value="<%= updated != null ? ((Processeur)updated).getGeneration(): 0 %>"
                                    placeholder="Generation " required>
                            </div>
                            <div class="form-group">
                                <label for="core">Nombre de coeur</label>
                                <input type="nomber" name="core" class="form-control" min="2"
                                    id="core"
                                    value="<%= updated != null ? ((Processeur)updated).getNombreCoeur(): 2 %>"
                                    placeholder="Capacite " required>
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
  