<%@ page import="java.util.List" %>
<%@ page import="model.composant.cm.CarteMere" %>
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
                                        <th>Type RAM</th>
                                        <th>Slot RAM</th>
                                        <th>Type Disque</th>
                                        <th>Slot Disque</th>
                                        <th>Type Processeur</th>
                                        <th>Prix Unitaire</th>
                                        <th style="width: 120px;">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% 
                                        for(Composant composant : composants){ 
                                            CarteMere r = (CarteMere)composant;
                                    %>
                                        <tr>
                                            <td class="text-center"><%= r.getIdCarteMere() %></td>
                                            <td><%= r.getNomComposant() %></td>
                                            <td><%= r.getTypeRam().getNomTypeRam() %></td>
                                            <td><%= r.getNombreSlotRam() %></td>
                                            <td><%= r.getTypeDisque().getNomTypeDisque() %></td>
                                            <td><%= r.getNombreSlotDisque() %></td>
                                            <td><%= r.getTypeProcesseur().getNomTypeProcesseur() %></td>
                                            <td><%= r.getPrixUnitaire() %></td>
                                            <td class="text-center"> 
                                                <a href="<%= request.getContextPath() %>/composant/cm/add?mode=u&id=<%= r.getIdCarteMere() %>" type="button" class="btn btn-sm btn-primary mx-1"> 
                                                    <span class="fe fe-book fe-16"></span>
                                                </a>
                                                <a href="<%= request.getContextPath() %>/composant/cm/add?mode=d&id=<%= r.getIdCarteMere() %>" type="button" class="btn btn-sm btn-danger mx-1">
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
  