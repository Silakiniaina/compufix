<aside class="sidebar-left border-right bg-white shadow" id="leftSidebar" data-simplebar>
    <a href="#" class="btn collapseSidebar toggle-btn d-lg-none text-muted ml-2 mt-3" data-toggle="toggle">
        <i class="fe fe-x"><span class="sr-only"></span></i>
    </a>
    <nav class="vertnav navbar navbar-light">
        <div class="w-100 mb-3 d-flex">
            <a class="navbar-brand mx-auto mt-1 flex-fill text-center" href="index">
                <img src="<%= request.getContextPath() %>/assets/images/logo.svg" alt="Logo" class="navbar-brand-img brand-sm" style="width: 30%">
            </a>            
        </div>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item w-100">
                <a class="nav-link" href="#">
                  <i class="fe fe-home fe-16"></i>
                  <span class="ml-3 item-text">Dashboard</span>
                </a>
            </li>
        </ul>
        <p class="text-muted nav-heading mt-4 mb-1">
            <span>Composants</span>
        </p>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/composant/list">
                  <i class="fe fe-package fe-16"></i>
                  <span class="ml-3 item-text">Liste</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/composant/add">
                  <i class="fe fe-plus fe-16"></i>
                  <span class="ml-3 item-text">Ajouter</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/composant/recommandations">
                  <i class="fe fe-cpu fe-16"></i>
                  <span class="ml-3 item-text">Recommandation</span>
                  <span class="badge badge-pill badge-primary">New</span>
                </a>
            </li>
        </ul>
        <%-- <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item dropdown">
                <a href="#ram" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                    <i class="fe fe-package fe-16"></i>
                    <span class="ml-3 item-text">RAM</span><span class="sr-only">(current)</span>
                </a>
                <ul class="collapse list-unstyled pl-4 w-100" id="ram">
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/ram/type/list"><span class="ml-1 item-text">Liste type</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/ram/list"><span class="ml-1 item-text">Liste</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/ram/type/add"><span class="ml-1 item-text">Ajouter type</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/ram/add"><span class="ml-1 item-text">Ajouter</span></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a href="#processeur" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                    <i class="fe fe-cpu fe-16"></i>
                    <span class="ml-3 item-text">Processeur</span><span class="sr-only">(current)</span>
                </a>
                <ul class="collapse list-unstyled pl-4 w-100" id="processeur">
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/processeur/type/list"><span class="ml-1 item-text">Liste type</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/processeur/list"><span class="ml-1 item-text">Liste</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/processeur/type/add"><span class="ml-1 item-text">Ajouter type </span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/processeur/add"><span class="ml-1 item-text">Ajouter</span></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a href="#disque" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                    <i class="fe fe-hard-drive fe-16"></i>
                    <span class="ml-3 item-text">Disque</span><span class="sr-only">(current)</span>
                </a>
                <ul class="collapse list-unstyled pl-4 w-100" id="disque">
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/disque/type/list"><span class="ml-1 item-text">Liste type</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/disque/list"><span class="ml-1 item-text">Liste</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/disque/type/add"><span class="ml-1 item-text">Ajouter type</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/disque/add"><span class="ml-1 item-text">Ajouter</span></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item dropdown">
                <a href="#cm" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                    <i class="fe fe-codesandbox fe-16"></i>
                    <span class="ml-3 item-text">Carte Mere</span><span class="sr-only">(current)</span>
                </a>
                <ul class="collapse list-unstyled pl-4 w-100" id="cm">
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/cm/list"><span class="ml-1 item-text">Liste</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/cm/add"><span class="ml-1 item-text">Ajouter</span></a>
                    </li>
                </ul>
            </li>
        </ul> --%>
        <p class="text-muted nav-heading mt-4 mb-1">
            <span>Ordinateur</span>
        </p>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/ordinateur/list">
                  <i class="fe fe-monitor fe-16"></i>
                  <span class="ml-3 item-text">Liste</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/ordinateur/add">
                  <i class="fe fe-plus fe-16"></i>
                  <span class="ml-3 item-text">Ajouter</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="#">
                  <i class="fe fe-sliders fe-16"></i>
                  <span class="ml-3 item-text">Etats ordinateurs</span>
                  <span class="badge badge-pill badge-primary">New</span>
                </a>
            </li>
        </ul>
        <p class="text-muted nav-heading mt-4 mb-1">
            <span>Reparation</span>
        </p>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/reparations">
                  <i class="fe fe-trending-up fe-16"></i>
                  <span class="ml-3 item-text">Liste</span>
                  <span class="badge badge-pill badge-primary">New</span>
                </a>
            </li>
        </ul>
        <p class="text-muted nav-heading mt-4 mb-1">
            <span>Technicien</span>
        </p>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/technicien/add">
                  <i class="fe fe-plus fe-16"></i>
                  <span class="ml-3 item-text">Add</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/technicien/list">
                  <i class="fe fe-list fe-16"></i>
                  <span class="ml-3 item-text">List</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/techniciens/commission">
                  <i class="fe fe-trending-up fe-16"></i>
                  <span class="ml-3 item-text">Commissions</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/techniciens/commission-genre">
                  <i class="fe fe-list fe-16"></i>
                  <span class="ml-3 item-text">Commissions Genre</span>
                </a>
            </li>
        </ul>
        <p class="text-muted nav-heading mt-4 mb-1">
            <span>Retour</span>
        </p>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/reparation/retour">
                  <i class="fe fe-trending-up fe-16"></i>
                  <span class="ml-3 item-text">Liste</span>
                </a>
            </li>
        </ul>
        <p class="text-muted nav-heading mt-4 mb-1">
            <span>Stock</span>
        </p>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item dropdown">
                <a href="#stock" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                    <i class="fe fe-activity fe-16"></i>
                    <span class="ml-3 item-text">Mouvement Stock</span><span class="sr-only">(current)</span>
                </a>
                <ul class="collapse list-unstyled pl-4 w-100" id="stock">
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/stock/mouvement/list"><span class="ml-1 item-text">Mouvement Stock</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/stock/mouvement/add"><span class="ml-1 item-text">Ajouter Mouvement</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="<%= request.getContextPath() %>/composant/statistique"><span class="ml-1 item-text">Statistique</span></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/composant/stock">
                  <i class="fe fe-trending-up fe-16"></i>
                  <span class="ml-3 item-text">Etat stocks</span>
                  <span class="badge badge-pill badge-primary">New</span>
                </a>
            </li>
        </ul>
    </nav>
</aside>
