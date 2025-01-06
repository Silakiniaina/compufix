<%@ page import="java.util.List" %>
<%@ page import="model.composant.processeur.TypeProcesseur" %>
<%
    List<TypeProcesseur> types = (List<TypeProcesseur>)request.getAttribute("listes");
%>
 
            <div class="col-12">
                <!-- Bordered table -->
                <div class="col-md-8 my-4">
                    <div class="card shadow">
                        <div class="card-body">
                            <table class="table table-hover mb-0">
                                <thead>
                                    <tr>
                                        <th style="width: 120px;">ID</th>
                                        <th>Nom</th>
                                        <th style="width: 120px;">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% 
                                        for(TypeProcesseur type : types){ 
                                    %>
                                        <tr>
                                            <td class="text-center"><%= type.getIdTypeProcesseur() %></td>
                                            <td><%= type.getNomTypeProcesseur() %></td>
                                            <td class="text-center"> 
                                                <a href="<%= request.getContextPath() %>/composant/processeur/type/add?mode=u&id=<%= type.getIdTypeProcesseur() %>" type="button" class="btn btn-sm btn-primary mx-1"> 
                                                    <span class="fe fe-book fe-16"></span>
                                                </a>
                                                <a href="<%= request.getContextPath() %>/composant/processeur/type/add?mode=d&id=<%= type.getIdTypeProcesseur() %>" type="button" class="btn btn-sm btn-danger mx-1">
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
  