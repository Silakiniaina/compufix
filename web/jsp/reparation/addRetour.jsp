<%
    String reparation = (String)request.getAttribute("reparation");
%>

<div class="col-12">
    <h2 class="page-title">Insertion Retour</h2>
    <div class="col-md-8 card shadow mb-4">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/reparation/retour/add" method="POST" id="computerForm">
                <input type="hidden" name="reparation" value="<%= reparation != null ? reparation : "" %>">
                <div class="form-group">
                    <label for="dateRetour">Date</label>
                    <input type="date" name="date" class="form-control" id="dateRetour" placeholder="Nom Ordinateur" required>
                </div>
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </form>
        </div>
    </div>
</div>