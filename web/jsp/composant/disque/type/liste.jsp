<%@ page import="java.util.List" %>
<%@ page import="model.composant.disque.TypeDisque" %>
<%
    List<TypeDisque> types = (List<TypeDisque>)request.getAttribute("listes");
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
                                        for(TypeDisque type : types){ 
                                    %>
                                        <tr>
                                            <td class="text-center"><%= type.getIdTypeDisque() %></td>
                                            <td><%= type.getNomTypeDisque() %></td>
                                            <td class="text-center"> 
                                                <a href="<%= request.getContextPath() %>/composant/disque/type/add?mode=u&id=<%= type.getIdTypeDisque() %>" type="button" class="btn btn-sm btn-primary mx-1"> 
                                                    <span class="fe fe-book fe-16"></span>
                                                </a>
                                                <a href="<%= request.getContextPath() %>/composant/disque/type/add?mode=d&id=<%= type.getIdTypeDisque() %>" type="button" class="btn btn-sm btn-danger mx-1">
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
  