<%
    Ordinateur ordinateur = (Ordinateur)request.getAttribute("ordinateur");
    List<Technicien> techniciens = (List<Technicien>)request.getAttribute("techniciens");
    List<TypeReparation> typeReparations = (List<TypeReparation>)request.getAttribute("typeReparations");
%>

<div class="col-12">
    <h2 class="page-title">Nouvelle Réparation</h2>
    <div class="col-md-10 card shadow mb-4">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/reparation/add" method="POST" id="reparationForm">
                <input type="hidden" name="ordinateur" value="<%= ordinateur.getIdOrdinateur() %>">
                
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="dateReparation">Date de réparation</label>
                        <input type="date" name="dateReparation" class="form-control" id="dateReparation" required>
                    </div>
                    
                    <div class="form-group col-md-6">
                        <label>Ordinateur</label>
                        <input type="text" class="form-control" value="<%= ordinateur.getNom() %>" disabled>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label>Type de réparation</label>
                        <select id="typeReparation" class="form-control" required>
                            <option value="">Sélectionner un type</option>
                            <% for(TypeReparation type : typeReparations) { %>
                                <option value="<%= type.getIdTypeReparation() %>"><%= type.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label>Technicien</label>
                        <select id="technicien" class="form-control" required>
                            <option value="">Sélectionner un technicien</option>
                            <% for(Technicien tech : techniciens) { %>
                                <option value="<%= tech.getIdTechnicien() %>"><%= tech.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    
                    <div class="form-group col-md-4">
                        <label>Composant</label>
                        <select id="composant" class="form-control" required>
                            <option value="">Sélectionner un composant</option>
                            <% for(ComposantOrdinateur comp : ordinateur.getComposants()) { %>
                                <option value="<%= comp.getIdComposantOrdinateur() %>">
                                    <%= comp.getComposant().getNom() %>
                                </option>
                            <% } %>
                        </select>
                    </div>
                </div>

                <button type="button" class="btn btn-secondary mb-3" id="addComposant">
                    Ajouter un composant
                </button>

                <!-- Hidden inputs to store component data -->
                <div id="hiddenInputs"></div>

                <!-- Liste des composants ajoutés -->
                <div class="composants-preview" id="composantsPreview" style="display: none;">
                    <div class="card border-info">
                        <div class="card-header bg-info text-white">
                            Composants à réparer
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

                <div class="form-group mt-4">
                    <button type="submit" class="btn btn-primary">Enregistrer la réparation</button>
                </div>
            </form>
        </div>
    </div>
</div>
<style>
.composants-preview {
    position: fixed;
    bottom: 20px;
    right: 20px;
    width: 350px;
    z-index: 1000;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

.composants-preview .card-body {
    max-height: 300px;
    overflow-y: auto;
}
</style>