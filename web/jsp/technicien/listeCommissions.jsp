<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.technicien.CommissionTechnicien" %>

<% 
    List<CommissionTechnicien> commissions = (List<CommissionTechnicien>)request.getAttribute("commissions");
%>
 
<div class="col-12">
    <div class="row align-items-center mb-2">
        <div class="col-md-2">
            <h2 class="h3 page-title">Liste Commissions</h2>
        </div>
        <div class="col-auto d-flex" style="gap:10px;">
            <form class="form-inline" action="<%= request.getContextPath() %>/techniciens/commission" method="POST">
                <div class="row">
                    <div class="col-md-4 form-group mb-3">
                        <label for="debut">Debut</label>
                        <input type="date" name="debut" class="form-control" id="debut" placeholder="Nom Ordinateur">
                    </div>
                    <div class="col-md-4 form-group mb-3">
                        <label for="fin">Fin</label>
                        <input type="date" name="fin"  class="form-control" id="fin" placeholder="Nom Ordinateur">
                    </div>
                    <div class="col-md-4 form-group">
                        <button type="submit" class="btn btn-md btn-outline-success w-100">
                            Filtrer
                        </button>
                    </div>
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
                            <th>Technicien</th>
                            <th>Nombre Reparation</th>
                            <th>Total Reparation</th>
                            <th>Total Commission</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(CommissionTechnicien commission : commissions){ %>
                            <tr>
                                <td class="text-center">
                                    <%= commission.getTechnicien().getIdTechnicien() %>
                                </td>
                                <td>
                                    <%= commission.getTechnicien().getNomTechnicien() %>
                                </td>
                                <td>
                                    <%= commission.getNombreReparation() %>
                                </td>
                                <td>
                                    <%= commission.getTotalReparation() %>
                                </td>
                                <td>
                                    <%= commission.getTotalCommission() %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- .col-12 -->
