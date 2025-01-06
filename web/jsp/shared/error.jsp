<% 
   if(request.getAttribute("error") != null){
       String error = (String)request.getAttribute("error");
%>
       <div class="col-md-12">
           <div class="card border-danger mb-3">
               <div class="card-header bg-danger text-white">
                   <h5 class="card-title mb-0">Error</h5>
               </div>
               <div class="card-body text-danger">
                   <p class="card-text"><%= error %></p>
               </div>
           </div>
       </div>
<% 
   }
%>