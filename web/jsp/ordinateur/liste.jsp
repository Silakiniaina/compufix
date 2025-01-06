<%@ page import="java.util.List" %>
<%@ page import="model.ordinateur.Ordinateur" %>

<%
    List<Ordinateur> ordinateurs = (List<Ordinateur>)request.getAttribute("ordinateurs");
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
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
                                        <th>Description</th>
                                        <th>Prix</th>
                                        <th style="width: 120px;">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% 
                                    for(Ordinateur ordinateur : ordinateurs){ 
                                %>
                                    <tr>
                                        <td class="text-center"><%= ordinateur.getIdOrdinateur() %></td>
                                        <td><%= ordinateur.getNomOrdinateur() %></td>
                                        <td><%= ordinateur.getDescription() %></td>
                                        <td>0</td>
                                        <td class="text-center"> 
                                            <a href="<%= request.getContextPath() %>/ordinateur/add?mode=u&id=<%= ordinateur.getIdOrdinateur() %>" type="button" class="btn btn-sm btn-primary mx-1"> 
                                                <span class="fe fe-tool fe-16"></span>
                                            </a>
                                            <%-- <a href="<%= request.getContextPath() %>/ordinateur/add?mode=d&id=<%= ordinateur.getIdOrdinateur() %>" type="button" class="btn btn-sm btn-danger mx-1">
                                                <span class="fe fe-trash-2 fe-16"></span>
                                            </a> --%>
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