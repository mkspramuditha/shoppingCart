<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
        
        
	<section id="cart_items">
		<div class="container">
<!--			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="#">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
			</div>-->
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
                                            
                                            <c:choose>
                                                        <c:when test="${cart == null}">
                                                            <!--sdsds-->
                                                            <tr>
                                                                <td colspan="5"><p>No items added to the cart</p></td>
                                                            </tr>
                                                        
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach items="${cart}" var="row">
                                                                <tr>
                                                                        <td class="cart_product">
                                                                                <a href=""><img src=${row.item.image} alt=""></a>
                                                                        </td>
                                                                        <td class="cart_description">
                                                                                <h4><a href="">${row.item.name}</a></h4>
                                                                                <p>${row.item.name}</p>
                                                                        </td>
                                                                        <td class="cart_price">
                                                                                <p>Rs.${row.item.price}</p>
                                                                        </td>
                                                                        <td class="cart_quantity">
                                                                                <div class="cart_quantity_button">
                                                                                        <a class="cart_quantity_up" href=""> + </a>
                                                                                        <input class="cart_quantity_input" type="text" name="quantity" value=${row.quantity} autocomplete="off" size="2">
                                                                                        <a class="cart_quantity_down" href=""> - </a>
                                                                                </div>
                                                                        </td>
                                                                        <td class="cart_total">
                                                                                <p class="cart_total_price">$59</p>
                                                                        </td>
                                                                        <td class="cart_delete">
                                                                                <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
                                                                        </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
						

						
					</tbody>
				</table>
			</div>
		</div>
	</section> <!--/#cart_items-->

	<section id="do_action">
		<div class="container">
			<div class="heading">
				<h3>Purchase</h3>
				<!--<p>ant to use or would like to estimate your delivery cost.</p>-->
			</div>
			<div class="row">
				
				<div class="col-sm-6">
					<div class="total_area">
						<ul>
							<li>Cart Sub Total <span>$59</span></li>
							<li>Eco Tax <span>$2</span></li>
							<li>Shipping Cost <span>Free</span></li>
							<li>Total <span>$61</span></li>
						</ul>
							<a class="btn btn-default update" href="">Update</a>
							<a class="btn btn-default check_out" href="">Check Out</a>
					</div>
				</div>
			</div>
		</div>
	</section><!--/#do_action-->

	
	
	
    </body>
    
    <%@ include file="./script.jsp" %>
    
   
        
        
            
    
   

  

</html>

