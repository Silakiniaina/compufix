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
            <li class="nav-item dropdown">
                <a href="#dashboard" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                    <i class="fe fe-home fe-16"></i>
                    <span class="ml-3 item-text">Dashboard</span><span class="sr-only">(current)</span>
                </a>
                <ul class="collapse list-unstyled pl-4 w-100" id="dashboard">
                    <li class="nav-item active">
                        <a class="nav-link pl-3" href="#"><span class="ml-1 item-text">Default</span></a>
                    </li>
                </ul>
            </li>
        </ul>
        <ul class="navbar-nav flex-fill w-100 mb-2">
            <li class="nav-item dropdown">
                <a href="#composant" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                    <i class="fe fe-paperclip fe-16"></i>
                    <span class="ml-3 item-text">Composant</span><span class="sr-only">(current)</span>
                </a>
                <ul class="collapse list-unstyled pl-4 w-100" id="composant">
                    <li class="nav-item active">
                        <ul class="navbar-nav flex-fill w-100 mb-2">
                            <li class="nav-item dropdown">
                                <a href="#ram" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                                    <i class="fe fe-codepen fe-16"></i>
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
                                    <i class="fe fe-codepen fe-16"></i>
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
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</aside>
