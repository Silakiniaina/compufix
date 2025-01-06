<%@ page import="java.util.List" %>
<%@ page import="model.composant.ram.RAM" %>
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
                                        <th>Capacite</th>
                                        <th>Categorie</th>
                                        <th>Prix Unitaire</th>
                                        <th style="width: 120px;">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% 
                                        for(Composant composant : composants){ 
                                            RAM r = (RAM)composant;
                                    %>
                                        <tr>
                                            <td class="text-center"><%= r.getIdRam() %></td>
                                            <td><%= r.getNomComposant() %></td>
                                            <td><%= r.getTypeRam().getNomTypeRam() %></td>
                                            <td><%= r.getCapacite() %></td>
                                            <td><%= r.isPortable() ? "Portable" : "Bureau" %></td>
                                            <td><%= r.getPrixUnitaire() %></td>
                                            <td class="text-center"> 
                                                <a href="<%= request.getContextPath() %>/composant/ram/add?mode=u&id=<%= r.getIdRam() %>" type="button" class="btn btn-sm btn-primary mx-1"> 
                                                    <span class="fe fe-book fe-16"></span>
                                                </a>
                                                <a href="<%= request.getContextPath() %>/composant/ram/add?mode=d&id=<%= r.getIdRam() %>" type="button" class="btn btn-sm btn-danger mx-1">
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
  