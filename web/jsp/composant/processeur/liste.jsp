<%@ page import="java.util.List" %>
<%@ page import="model.composant.processeur.Processeur" %>
<%@ page import="model.composant.Composant" %>
<%
    List<Composant> composants = (List<Composant>)request.getAttribute("composants");
%>
 
            <div class="col-12">
                <!-- Bordered table -->
                <div class="col-md-12 my-4">
                    <div class="card shadow">
                        <div class="card-body">
                            <table class="table table-hover mb-0">
                                <thead>
                                    <tr>
                                        <th style="width: 120px;">ID</th>
                                        <th>Nom</th>
                                        <th>Type</th>
                                        <th>Generation</th>
                                        <th>Nombre de coeur</th>
                                        <th>Capacite</th>
                                        <th>Prix Unitaire</th>
                                        <th style="width: 120px;">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% 
                                        for(Composant composant : composants){ 
                                            Processeur r = (Processeur)composant;
                                    %>
                                        <tr>
                                            <td class="text-center"><%= r.getIdProcesseur() %></td>
                                            <td><%= r.getNomComposant() %></td>
                                            <td><%= r.getTypeProcesseur().getNomTypeProcesseur() %></td>
                                            <td><%= r.getGeneration() %></td>
                                            <td><%= r.getNombreCoeur() %></td>
                                            <td><%= r.getCapacite() %></td>
                                            <td><%= r.getPrixUnitaire() %></td>
                                            <td class="text-center"> 
                                                <a href="<%= request.getContextPath() %>/composant/processeur/add?mode=u&id=<%= r.getIdProcesseur() %>" type="button" class="btn btn-sm btn-primary mx-1"> 
                                                    <span class="fe fe-book fe-16"></span>
                                                </a>
                                                <a href="<%= request.getContextPath() %>/composant/processeur/add?mode=d&id=<%= r.getIdProcesseur() %>" type="button" class="btn btn-sm btn-danger mx-1">
                                                    <span class="fe fe-trash-2 fe-16"></span>
                                                </a>
                                            </td>
                                        </tr>
                                    <% 
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div> <!-- .col-12 -->
  