<%@ page import="java.util.List" %>
<%@ page import="model.composant.Composant" %>
<%@ page import="model.ordinateur.TypeOrdinateur" %>

<%
    List<Composant> composants = (List<Composant>) request.getAttribute("composants");
    List<TypeOrdinateur> types = (List<TypeOrdinateur>) request.getAttribute("types");
%>

<div class="col-12">
    <h2 class="page-title">Insertion Ordinateur</h2>
    <div class="col-md-8 card shadow mb-4">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/ordinateur/add" method="POST" id="computerForm">
                <div class="form-group">
                    <label for="nomOrdinateur">Nom</label>
                    <input type="text" name="nom" class="form-control" id="nomOrdinateur" placeholder="Nom Ordinateur" required>
                </div>
                <div class="form-group">
                    <label for="typeordi">Type Ordinateur</label>
                    <select id="typeordi" name="type" class="form-control">
                        <% for(TypeOrdinateur type : types){ %>
                            <option value="<%= type.getIdTypeOrdinateur() %>">
                                <%= type.getNomTypeOrdinateur() %>
                            </option>
                            <% } %>
                    </select>
                </div>
                <div class="form-group mb-3">
                    <label for="description">Description</label>
                    <textarea class="form-control" name="description" id="description" rows="3"></textarea>
                </div>
                <div class="form-group mb-3">
                    <label for="componentSelect">Composants</label>
                    <select class="form-control" id="componentSelect">
                        <option value="">Selectionner un composant</option>
                        <% if (composants != null) {
                            for (Composant composant : composants) { %>
                                <option value="<%= composant.getIdComposant() %>" 
                                        data-name="<%= composant.getNomComposant() %>">
                                    <%= composant.getNomComposant() %>
                                </option>
                        <% }
                        } %>
                    </select>
                </div>

                <!-- Zone des composants sélectionnés -->
                <div id="selectedComponents" class="mb-3"></div>

                <!-- Champs cachés pour envoyer les composants sélectionnés -->
                <div id="hiddenComponentInputs"></div>

                <button type="submit" class="btn btn-primary">Ajouter</button>
            </form>
        </div>
    </div>
</div>

<style>
.component-tag {
    display: inline-flex;
    align-items: center;
    background-color: #2d3748;
    color: #fff;
    padding: 0.5rem 1rem;
    border-radius: 0.375rem;
    margin: 0.25rem;
    animation: fadeIn 0.3s ease-in;
}

.component-tag button {
    background: none;
    border: none;
    color: #fff;
    margin-left: 0.5rem;
    cursor: pointer;
    padding: 0 0.25rem;
}

.component-tag button:hover {
    color: #fc8181;
}

@keyframes fadeIn {
    from { opacity: 0; transform: translateY(-10px); }
    to { opacity: 1; transform: translateY(0); }
}
</style>