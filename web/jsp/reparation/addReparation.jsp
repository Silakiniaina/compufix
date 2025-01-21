<%@page import="java.util.List" %>
<%@page import="model.ordinateur.Ordinateur" %>
<%@page import="model.technicien.Technicien" %>
<%@page import="model.reparation.TypeReparation" %>
<%@page import="model.ordinateur.ComposantOrdinateur" %>

<%
    Ordinateur ordinateur = (Ordinateur)request.getAttribute("ordinateur");
    List<Technicien> techniciens = (List<Technicien>)request.getAttribute("techniciens");
    List<TypeReparation> types = (List<TypeReparation>)request.getAttribute("types");
%>

<div class="col-12">
    <h2 class="page-title">Insertion Reparation</h2>
    
    <div class="col-md-10 card shadow mb-4">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/reparation/add" method="POST" id="reparationForm">
                <input type="hidden" name="ordinateur" value="<%= ordinateur.getIdOrdinateur() %>">
                
                <div class="form-group">
                    <label>Ordinateur</label>
                    <input type="text" class="form-control" value="<%= ordinateur.getNomOrdinateur() %>" disabled>
                </div>
                
                <div class="form-group">
                    <label for="dateReparation">Date de reparation</label>
                    <input type="date" name="dateReparation" class="form-control" id="dateReparation" required>
                </div>
                

                <div class="card mt-4 mb-4">
                    <div class="card-header bg-light">
                        <h5 class="mb-0">Details de la reparation</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label>Type de reparation</label>
                                <select id="typeReparation" class="form-control" required>
                                    <option value="">Selectionner le type de reparation</option>
                                    <% for(TypeReparation type : types) { %>
                                        <option value="<%= type.getIdTypeReparation() %>"><%= type.getNomTypeReparation() %></option>
                                    <% } %>
                                </select>
                            </div>
                            
                            <div class="form-group col-md-4">
                                <label>Technicien</label>
                                <select id="technicien" class="form-control" required>
                                    <option value="">Selectionner un technicien</option>
                                    <% for(Technicien tech : techniciens) { %>
                                        <option value="<%= tech.getIdTechnicien() %>"><%= tech.getNomTechnicien() %></option>
                                    <% } %>
                                </select>
                            </div>
                            
                            <div class="form-group col-md-4">
                                <label>Composant</label>
                                <select id="composant" class="form-control" required>
                                    <option value="">Selectionner un composant</option>
                                    <% for(ComposantOrdinateur comp : ordinateur.getComposants()) { %>
                                        <option value="<%= comp.getIdComposantOrdinateur() %>">
                                            <%= comp.getComposant().getNomComposant() %>
                                        </option>
                                    <% } %>
                                </select>
                            </div>
                        </div>

                        <button type="button" class="btn btn-secondary" id="addComposant">
                            Ajouter un composant
                        </button>

                        <div class="composants-preview" id="composantsPreview" style="display: none;">
                            <div class="card border-info">
                                <div class="card-header bg-info text-white">
                                    Composants à reparer
                                    <button type="button" class="close text-white" id="closePreview">
                                        <span>&times;</span>
                                    </button>
                                </div>
                                <div class="card-body p-0">
                                    <ul class="list-group list-group-flush" id="composantsList">
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Hidden inputs to store component data -->
                <div id="hiddenInputs"></div>

                <!-- Components Preview -->
                <div class="composants-preview" id="composantsPreview" style="display: none;">
                    <div class="card border-info">
                        <div class="card-header bg-info text-white">
                            Composants à reparer
                            <button type="button" class="close text-white" id="closePreview">
                                <span>&times;</span>
                            </button>
                        </div>
                        <div class="card-body p-0">
                            <ul class="list-group list-group-flush" id="composantsList">
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Enregistrer la reparation</button>
                </div>
            </form>
        </div>
    </div>
</div>

<style>
.composants-preview {
    margin-top: 1rem; /* Add space between button and preview */
    width: 100%; /* Take full width of parent */
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

.composants-preview .card-body {
    max-height: 300px;
    overflow-y: auto;
}
</style>