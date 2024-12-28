<%@ page import="model.composant.disque.TypeDisque" %>
<%
    TypeDisque type = (TypeDisque)request.getAttribute("updated");
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <h2 class="page-title"><%= type != null ? "Update" : "Add" %> Type Disque</h2>
                <div class="col-md-6 card shadow mb-4">
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/composant/disque/type/add" method="POST">
                            <input type="hidden" name="mode" value="<%= type != null ? "u": "" %>">
                            <input type="hidden" name="id" value="<%= type != null ? type.getIdTypeDisque(): "" %>">
                            <div class="form-group">
                                <label for="nomType">Nom</label>
                                <input type="text" name="nom" class="form-control" id="nomType" value="<%= type != null ? type.getNomTypeDisque(): "" %>" placeholder="Nom type" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form>
                    </div>
                </div>
            </div> <!-- .col-22 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->
</main>