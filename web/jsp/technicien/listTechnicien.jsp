<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.technicien.Technicien" %>
<%
    List<Technicien> techniciens = (List<Technicien>) request.getAttribute("techniciens");
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
                            <th>Genre</th>
                            <th style="width: 120px;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            for (Technicien technicien : techniciens) { 
                        %>
                            <tr>
                                <td class="text-center"><%= technicien.getIdTechnicien() %></td>
                                <td><%= technicien.getNomTechnicien() %></td>
                                <td><%= technicien.getGenre().getNomGenre() %></td>
                                <td class="text-center"> 
                                    <a href="<%= request.getContextPath() %>/technicien/add?mode=u&id=<%= technicien.getIdTechnicien() %>" type="button" class="btn btn-sm btn-primary mx-1"> 
                                        <span class="fe fe-edit fe-16"></span>
                                    </a>
                                    <a href="<%= request.getContextPath() %>/technicien/add?mode=d&id=<%= technicien.getIdTechnicien() %>" type="button" class="btn btn-sm btn-danger mx-1">
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