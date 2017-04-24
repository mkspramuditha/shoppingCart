<%@page import="Entity.User"%>
<% User user = (User)session.getAttribute("user"); 
    
//        out.print(user.getRole().getMetacode());
        
        if(user != null){
            if(!"ROLE_USER".equals(user.getRole().getMetacode())){
                out.print("role dismatch");
            response.sendRedirect("login");
            }
        }
        else{
            response.sendRedirect("login");

        }
        
    %>