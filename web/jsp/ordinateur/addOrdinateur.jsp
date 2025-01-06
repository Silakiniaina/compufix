<%@ page import="model.composant.ram.RAM" %>
<%@ page import="model.composant.cm.CarteMere" %>
<%@ page import="model.composant.processeur.Processeur" %>
<%@ page import="model.composant.disque.Disque" %>
<%@ page import="java.util.List" %>
<%@ page import="model.composant.Composant" %>
<%
    List<Composant> carteMeres = (List<Composant>)request.getAttribute("carteMeres");
    List<Composant> rams = (List<Composant>)request.getAttribute("rams");
    List<Composant> processeurs = (List<Composant>)request.getAttribute("processeurs");
    List<Composant> disques = (List<Composant>)request.getAttribute("disques");
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <h2 class="page-title">
                    Insertion Ordinateur
                </h2>
                <div class="col-md-8 card shadow mb-4">
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/ordinateur/add"
                            method="POST">
                            <div class="form-group">
                                <label for="nomOrdinateur">Nom</label>
                                <input type="text" name="nom" class="form-control" id="nomOrdinateur"
                                    placeholder="Nom Ordinateur" required>
                            </div>
                            <div class="form-group">
                                <label for="carteMere">Carte Mere</label>
                                <select id="carteMere" name="cm" class="form-control">
                                    <% for(Composant carteMere : carteMeres){ 
                                        CarteMere cm = (CarteMere)carteMere;
                                    %>
                                        <option value="<%= cm.getIdComposant() %>">
                                            <%= cm.getNomComposant() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="ram">RAM</label>
                                <select id="ram" name="ram" class="form-control">
                                    <% for(Composant ram : rams){ 
                                        RAM r = (RAM)ram;
                                    %>
                                        <option value="<%= r.getIdComposant() %>">
                                            <%= r.getNomComposant() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="processeur">Processeur</label>
                                <select id="processeur" name="cpu" class="form-control">
                                    <% for(Composant processeur : processeurs){ 
                                        Processeur cpu = (Processeur)processeur;
                                    %>
                                        <option value="<%= cpu.getIdComposant() %>">
                                            <%= cpu.getNomComposant() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="disque">Disque</label>
                                <select id="disque" name="disque" class="form-control">
                                    <% for(Composant disque : disques){ 
                                        Disque d = (Disque)disque;
                                    %>
                                        <option value="<%= d.getIdComposant() %>">
                                            <%= d.getNomComposant() %>
                                        </option>
                                        <% } %>
                                </select>
                            </div>
                            <div class="form-group mb-3">
                                <label for="description">Description</label>
                                <textarea class="form-control" name="description" id="description" rows="3"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form>
                    </div>
                </div>
            </div> <!-- .col-22 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->
</main>