<%@ include file="head.jsp" %>
<body class="vertical dark">
    <div class="wrapper">
        <%@ include file="navbar.jsp" %>
        <%@ include file="sidebar.jsp" %>
        <% 
            String pageurl = (String) request.getAttribute("pageUrl");
            if (pageurl == null || pageurl.isEmpty()) {
                pageurl = "index.jsp";
            }
        %>
        <jsp:include page="<%= pageurl %>" />
    </div>
    <%@ include file="scripts.jsp" %>
</body>
</html>
