<%@ include file="head.jsp" %>
<body class="vertical dark">
    <div class="wrapper">
        <%@ include file="navbar.jsp" %>
        <%@ include file="sidebar.jsp" %>
        <main role="main" class="main-content">
            <div class="container-fluid">
                <div class="row justify-content-center">
                    <%@ include file="error.jsp" %>
                    <% 
                        String pageurl = (String) request.getAttribute("pageUrl");
                        if (pageurl == null || pageurl.isEmpty()) {
                            pageurl = "index.jsp";
                        }
                    %>
                    <jsp:include page="<%= pageurl %>" />
                </div> <!-- .row -->
            </div> <!-- .container-fluid -->
        </main>
    </div>
    <%@ include file="scripts.jsp" %>
</body>
</html>
