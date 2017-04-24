

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <title>Login or SignUp</title>
          <%@ include file="./head.jsp" %>
    </head>
    <body>
        <%! String tab="login"; %>  
        <%@include file="./navbar.jsp" %>
        
        <section id="form"><!--form-->
		<div class="container">
			<div class="row">
                            <%
                                if (request.getAttribute("error") == null) {
                                    out.println("empty");
                                } else {
                                    out.println("<b>"+request. getAttribute("error")+"</b>!");
                                }
                            %>
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
                                                <form action="login" method="post">
                                                    <input name="email" type="email" placeholder="Email" />
							<input name="password" type="password" placeholder="Password" />
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<button type="submit" class="btn btn-default">Login</button>
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>New User Signup!</h2>
						<form action="#">
							<input type="text" placeholder="Name"/>
							<input type="email" placeholder="Email Address"/>
							<input type="password" placeholder="Password"/>
							<button type="submit" class="btn btn-default">Signup</button>
						</form>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
	
    </body>
              <%@ include file="./script.jsp" %>

  

</html>
