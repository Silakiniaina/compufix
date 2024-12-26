<%@ page import="model.composant.ram.TypeRam" %>
<%
    TypeRam type = (TypeRam)request.getAttribute("updated");
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <div class="col-md-4 card shadow mb-4">
                    <div class="card-body">
                        <form>
                            <input type="hidden" name="mode" value="<%= type != null ? "u": "" %>">
                            <div class="form-group">
                                <label for="nomType">Nom</label>
                                <input type="text" name="nom" class="form-control" id="nomType" value="<%= type != null ? type.getNomTypeRam(): "" %>" placeholder="Nom type" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Ajouter</button>
                        </form>
                    </div>
                </div>
            </div> <!-- .col-12 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->
</main>