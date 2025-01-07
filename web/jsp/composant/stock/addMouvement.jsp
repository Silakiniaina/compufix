<%@ page import="java.util.List" %>
<%@ page import="model.composant.Composant" %>
<%
    List<Composant> composants = (List<Composant>)request.getAttribute("composants");
%>
 
            <div class="col-12">
                <h2 class="page-title">
                    Ajouter Mouvement Stock
                </h2>
                <div class="col-md-8 card shadow mb-4">
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/composant/stock/mouvement/add"
                            method="POST">
                            <div class="form-group">
                                <label for="composant">Composant</label>
                                <select id="composant" name="composant" class="form-control">
                                    <% for(Composant composant : composants){ %>
                                        <option value="<%= composant.getIdComposant() %>">
                                            <%= composant.getNomComposant() %> 
                                        </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="date">Date</label>
                                <input type="date" name="date" class="form-control" min="0"
                                    id="date"
                                    placeholder="Date " required>
                            </div>
                            <div class="form-group">
                                <label for="quantite">Quantite</label>
                                <input type="number" name="quantite" class="form-control" min="0"
                                    id="quantite"
                                    value="0"
                                    placeholder="Quantite " required>
                            </div>
                            <div class="form-group">
                                <h5 class="mb-2">Categorie</h5>
                                <div class="custom-control custom-radio">
                                    <input type="radio" id="entree" name="est_entree"
                                        class="custom-control-input" value="true" check>
                                    <label class="custom-control-label" for="entree">Entree</label>
                                </div>
                                <div class="custom-control custom-radio">
                                    <input type="radio" id="sortie" name="est_entree"
                                        class="custom-control-input" value="false">
                                    <label class="custom-control-label" for="sortie">Sortie</label>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form>
                    </div>
                </div>
            </div> <!-- .col-22 -->
  