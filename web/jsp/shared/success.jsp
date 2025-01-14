<%
   if (request.getParameter("success") != null) {
       String success = (String) request.getParameter("success");
%>
       <div class="col-md-12">
           <div class="card border-success mb-3">
               <div class="card-header bg-success text-white">
                   <h5 class="card-title mb-0">Success</h5>
               </div>
               <div class="card-body text-success">
                   <p class="card-text"><%= success %></p>
               </div>
           </div>
       </div>
<%
   }
%>
