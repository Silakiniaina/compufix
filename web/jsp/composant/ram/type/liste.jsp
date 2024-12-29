<%@ page import="java.util.List" %>
<%@ page import="model.composant.ram.TypeRam" %>
<%
    List<TypeRam> types = (List<TypeRam>)request.getAttribute("listes");
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
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
                                        for(TypeRam type : types){ 
                                    %>
                                        <tr>
                                            <td class="text-center"><%= type.getIdTypeRam() %></td>
                                            <td><%= type.getNomTypeRam() %></td>
                                            <td class="text-center"> 
                                                <a href="<%= request.getContextPath() %>/composant/ram/type/add?mode=u&id=<%= type.getIdTypeRam() %>" type="button" class="btn btn-sm btn-primary mx-1"> 
                                                    <span class="fe fe-book fe-16"></span>
                                                </a>
                                                <a href="<%= request.getContextPath() %>/composant/ram/type/add?mode=d&id=<%= type.getIdTypeRam() %>" type="button" class="btn btn-sm btn-danger mx-1">
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
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->
</main>