<%
    String composant = (String)request.getAttribute("composant");
%>

<div class="col-12">
    <h2 class="page-title">Insertion Recommandation</h2>
    <div class="col-md-8 card shadow mb-4">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/composant/recommandations/add" method="POST" id="computerForm">
                <input type="hidden" name="composant" value="<%= composant != null ? composant : "" %>">
                <div class="form-group">
                    <label for="nomComposant">Date</label>
                    <input type="date" name="date" class="form-control" id="nomComposant" placeholder="Date Recommandation" required>
                </div>
                <div class="form-group mb-3">
                    <label for="description">Description</label>
                    <textarea class="form-control" name="desc" id="description" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </form>
        </div>
    </div>
</div>