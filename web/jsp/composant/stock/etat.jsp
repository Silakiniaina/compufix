<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.composant.stock.ElementStock" %>
<%@ page import="model.composant.stock.Stock" %>

<% 
    List<ElementStock> stocks = (List<ElementStock>)request.getAttribute("stocks");
    Date dateFilter = (Date)request.getAttribute("date");
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <div class="row align-items-center mb-2">
                    <div class="col">
                        <h2 class="h3 page-title">Etat Stock</h2>
                    </div>
                    <div class="col-auto">
                        <form class="form-inline" action="<%= request.getContextPath() %>/composant/stock" method="POST">
                            <div class="form-group col-md-8">
                                <input type="date" name="date" class="form-control" id="dateFilter" placeholder="Date" value="<%= dateFilter  %>">
                            </div>
                            <div class="form-group col-md-2">
                                <button type="submit" class="btn btn-sm btn-outline-success">
                                    <span class="fe fe-upload-cloud fe-16"></span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- Bordered table -->
                <div class="col-md-12 my-4">
                    <div class="card shadow">
                        <div class="card-body">
                            <table class="table table-hover mb-0">
                                <thead>
                                    <tr>
                                        <th style="width: 120px;">ID</th>
                                        <th>Nom</th>
                                        <th>Total</th>
                                        <th>Utilise</th>
                                        <th>Restant</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(ElementStock stock : stocks){ %>
                                        <tr class="<%= stock.isBesoinApprovisionnement() ? "bg-danger" : "" %>">
                                            <td class="text-center">
                                                <%= stock.getComposant().getIdComposant() %>
                                            </td>
                                            <td>
                                                <%= stock.getComposant().getNomComposant() %>
                                            </td>
                                            <td>
                                                <%= stock.getTotal() %>
                                            </td>
                                            <td>
                                                <%= stock.getUtilise() %>
                                            </td>
                                            <td>
                                                <%= stock.getRestant() %>
                                            </td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div> <!-- .col-12 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->
</main>