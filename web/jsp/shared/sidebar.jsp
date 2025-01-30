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
                  <i class="fe fe-star fe-16"></i>
                  <span class="ml-3 item-text">Recommandations</span>
                  <span class="badge badge-pill badge-primary">New</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/composant/historique-prix">
                  <i class="fe fe-book-open fe-16"></i>
                  <span class="ml-3 item-text">Historique Prix</span>
                  <span class="badge badge-pill badge-primary">New</span>
                </a>
            </li>
        </ul>
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
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/reparations">
                  <i class="fe fe-tool fe-16"></i>
                  <span class="ml-3 item-text">Reparations</span>
                  <span class="badge badge-pill badge-primary">New</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/reparation/retour">
                  <i class="fe fe-truck fe-16"></i>
                  <span class="ml-3 item-text">Retours</span>
                </a>
            </li>
        </ul>
        <p class="text-muted nav-heading mt-4 mb-1">
            <span>Technicien</span>
        </p>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/technicien/list">
                  <i class="fe fe-list fe-16"></i>
                  <span class="ml-3 item-text">Liste</span>
                </a>
            </li>

            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/technicien/add">
                  <i class="fe fe-plus fe-16"></i>
                  <span class="ml-3 item-text">Ajouter</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/techniciens/commission">
                  <i class="fe fe-briefcase fe-16"></i>
                  <span class="ml-3 item-text">Commissions</span>
                </a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="<%= request.getContextPath() %>/techniciens/commission-genre">
                  <i class="fe fe-users fe-16"></i>
                  <span class="ml-3 item-text">Commissions Genre</span>
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
