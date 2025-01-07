<%@ page import="model.composant.disque.TypeDisque" %>
<%@ page import="model.composant.ram.TypeRam" %>
<%@ page import="model.composant.processeur.TypeProcesseur" %>
<%@ page import="model.composant.cm.CarteMere" %>
<%@ page import="java.util.List" %>
<%@ page import="model.composant.Composant" %>
<%
    List<TypeDisque> types_disque = (List<TypeDisque>)request.getAttribute("types_disque");
    List<TypeProcesseur> types_processeur = (List<TypeProcesseur>)request.getAttribute("types_processeur");
    List<TypeRam> types_ram = (List<TypeRam>)request.getAttribute("types_ram");
    Composant updated = (Composant)request.getAttribute("updated");
%>
 
            <div class="col-12">
                <h2 class="page-title">
                    <%= updated !=null ? "Mise a jour" : "Insertion" %> Carte Mere
                </h2>
                <div class="col-md-8 card shadow mb-4">
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/composant/cm/add"
                            method="POST">
                            <input type="hidden" name="mode" value="<%= updated != null ? "u": "" %>">
                            <input type="hidden" name="id" value="<%= updated != null ? ((CarteMere)updated).getIdCarteMere(): "" %>">
                            <input type="hidden" name="idComposant" value="<%= updated != null ? updated.getIdComposant(): "" %>">
                            <div class="form-group">
                                <label for="nomComposant">Nom</label>
                                <input type="text" name="nom" class="form-control" id="nomComposant"
                                    value="<%= updated != null ? updated.getNomComposant(): "" %>"
                                    placeholder="Nom type" required>
                            </div>
                            <div class="form-group">
                                <label for="typeRam">Type RAM</label>
                                <select id="typeRam" name="type_ram" class="form-control">
                                    <% for(TypeRam type : types_ram){ %>
                                        <option value="<%= type.getIdTypeRam() %>">
                                            <%= type.getNomTypeRam() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="slot_ram">Slot RAM</label>
                                <input type="number" name="slot_ram" class="form-control" min="1"
                                    id="slot_ram"
                                    value="<%= updated != null ? ((CarteMere)updated).getNombreSlotRam(): 1 %>"
                                    placeholder="Slot RAM " required>
                            </div>
                            <div class="form-group">
                                <label for="typeDisque">Type Disque</label>
                                <select id="typeDisque" name="type_disque" class="form-control">
                                    <% for(TypeDisque type : types_disque){ %>
                                        <option value="<%= type.getIdTypeDisque() %>">
                                            <%= type.getNomTypeDisque() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="slot_disque">Slot Disque</label>
                                <input type="number" name="slot_disque" class="form-control" min="1"
                                    id="slot_disque"
                                    value="<%= updated != null ? ((CarteMere)updated).getNombreSlotDisque(): 1 %>"
                                    placeholder="Slot Disque " required>
                            </div>
                            <div class="form-group">
                                <label for="typeProcesseur">Type Processeur</label>
                                <select id="typeProcesseur" name="type_cpu" class="form-control">
                                    <% for(TypeProcesseur type : types_processeur){ %>
                                        <option value="<%= type.getIdTypeProcesseur() %>">
                                            <%= type.getNomTypeProcesseur() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pu">Prix Unitaire</label>
                                <input type="number" name="pu" class="form-control" min="0"
                                    id="pu"
                                    value="<%= updated != null ? updated.getPrixUnitaire(): 0 %>"
                                    placeholder="PU " required>
                            </div>
                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form>
                    </div>
                </div>
            </div> <!-- .col-22 -->