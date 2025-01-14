<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.composant.TypeComposant" %>
<%@ page import="model.reparation.Reparation" %>

<% 
    List<TypeComposant>  types = (List<TypeComposant>)request.getAttribute("types");
    List<Reparation>  reparations = (List<Reparation>)request.getAttribute("reparations");
%>
 
            <div class="col-12">
                <div class="row align-items-center mb-2">
                    <div class="col">
                        <h2 class="h3 page-title">Liste Reparations</h2>
                    </div>
                    <div class="col-auto">
                        <form class="form-inline row" action="<%= request.getContextPath() %>/reparations" method="POST">
                            <div class="form-group col-md-8 mb-3">
                            <label for="type">Type Composant</label>
                                <select id="type" name="type" class="form-control w-100">
                                    <option value="">All</option>
                                    <% for(TypeComposant type : types){ %>
                                        <option value="<%= type.getIdTypeComposant() %>">
                                            <%= type.getNomTypeComposant() %> 
                                        </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="form-group col-md-4">
                                <button type="submit" class="btn btn-md btn-outline-success w-100">
                                    Filter
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
                                        <th>Date</th>
                                        <th>Ordinateur</th>
                                        <th>Problemes</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(Reparation reparation : reparations){ %>
                                        <tr>
                                            <td class="text-center">
                                                <%= reparation.getIdReparation() %>
                                            </td>
                                            <td>
                                                <%= reparation.getDateReparation() %>
                                            </td>
                                            <td>
                                                <%= reparation.getOrdinateur().getNomOrdinateur() %>
                                            </td>
                                            <td>
                                                <% for(TypeComposant t : reparation.getTypeComposants()){ %>
                                                <%= t.getNomTypeComposant() %> ,   
                                                <% } %>
                                            </td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div> <!-- .col-12 -->
  