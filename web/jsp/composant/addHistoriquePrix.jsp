<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.composant.Composant" %>
<%
    // Retrieve the Composant object from the request
    Composant composant = (Composant) request.getAttribute("composant");
%>
    <div class="col-12">
        <h2 class="page-title">Insertion Historique de Prix</h2>
        <div class="col-md-8 card shadow mb-4">
            <div class="card-body">
                <form action="<%= request.getContextPath() %>/composant/update-prix" method="POST" id="historiqueForm">
                    <input type="hidden" name="idComposant" value="<%= composant != null ? composant.getIdComposant() : "" %>">
                    <!-- Nouveau Prix -->
                    <div class="form-group">
                        <label for="nouveauPrix">Nouveau Prix</label>
                        <input type="number" step="0.01" name="nouveauPrix" class="form-control" id="nouveauPrix" placeholder="Nouveau Prix" required>
                    </div>

                    <!-- Date de Modification -->
                    <div class="form-group">
                        <label for="dateModification">Date de Modification</label>
                        <input type="date" name="dateModification" class="form-control" id="dateModification" required>
                    </div>

                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </form>
            </div>
        </div>
    </div>