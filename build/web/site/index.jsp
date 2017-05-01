<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <title>EShopper | Home</title>
          <%@ include file="./head.jsp" %>
    </head>
    <body>
        <%! String tab="login"; %>  
        <%@include file="./navbar.jsp" %>
        
        <fmt:parseNumber var="i" type="number" value="${lPrice}" />
        <fmt:parseNumber var="j" type="number" value="${hPrice}" />

	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>Category</h2>
						<div class="panel-group category-products" id="accordian">
							
							
						<c:choose>
                                                        <c:when test="${categories == null}">
                                                            
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach items="${categories}" var="category">
                                                                <div class="panel panel-default">
                                                                        <div class="panel-heading">
                                                                                <h4 class="panel-title " ><a onclick='filter(<c:if test="${empty name}">null</c:if><c:if test="${not empty name}">${name}</c:if>    ,   ${category.id}   ,    <c:if test="${empty i}">null</c:if><c:if test="${not empty i}">${i}</c:if>   ,   <c:if test="${empty j}">null</c:if><c:if test="${not empty j}">${j}</c:if>);' href="#">${category.name}</a></h4>
                                                                        </div>
                                                                </div>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
							
						</div>
					
					
						
						<div class="price-range"><!--price-range-->
							<h2>Price Range</h2>
							<div class="well text-center">
                                                            <c:choose>
                                                                <c:when test="${lPrice == null || hPrice == null}">
                                                                    
								 <input type="text" class="span2" value="" data-slider-min=${lowRange} data-slider-max=${highRange} data-slider-step="5" data-slider-value="[200,350]" id="sl2" ><br />
                                                                    
                                                                </c:when>
                                                                <c:otherwise>
                                                                    

                                                                    <input type="text" class="span2" value="" data-slider-min=${lowRange} data-slider-max=${highRange} data-slider-step="5" data-slider-value="[<c:out value="${i}"/>,<c:out value="${j}" />]" id="sl2" ><br />

                                                                </c:otherwise>
                                                            </c:choose>
								 <b class="pull-left">${lowRange}</b> <b class="pull-right">${highRange}</b>
                                                                 
							</div>
                                                        <button onclick='sliderFilter(<c:if test="${empty name}">null</c:if><c:if test="${not empty name}">"${name}"</c:if>,<c:if test="${empty category}">null</c:if><c:if test="${not empty category}">${category}</c:if> );' class="btn btn-success">Filter</button>
                                                </div>
						
						<div class="shipping text-center"><!--shipping-->
							<img src="images/home/shipping.jpg" alt="" />
						</div><!--/shipping-->
					
					</div>
				</div>
				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Features Items</h2>
                                                    <c:choose>
                                                        <c:when test="${items == null}">
                                                            
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach items="${items}" var="item">
                                                                <div class="col-sm-4">
                                                                        <div class="product-image-wrapper">
                                                                                <div class="single-products">
                                                                                        <div class="productinfo text-center">
                                                                                                <img src="uploads/${item.image}" alt="" />
                                                                                                <h2>Rs.${item.price}</h2>
                                                                                                <p>${item.name}</p>
                                                                                                <a href="#" onClick="addToCart(${item.id},1);" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                                                                        </div>
                                                                                        <div class="product-overlay">
                                                                                                <div class="overlay-content">
                                                                                                        <h2>Rs.${item.price}</h2>
                                                                                                        <p>${item.name}</p>
                                                                                                        <a href="#" onClick="addToCart(${item.id},1);" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                                                                                </div>
                                                                                        </div>
                                                                                </div>
<!--                                                                                <div class="choose">
                                                                                        <ul class="nav nav-pills nav-justified">
                                                                                                <li><a href="#"><i class="fa fa-plus-square"></i>Add to wishlist</a></li>
                                                                                                <li><a href="#"><i class="fa fa-plus-square"></i>Add to compare</a></li>
                                                                                        </ul>
                                                                                </div>-->
                                                                        </div>
                                                                </div>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
						
						
					</div><!--features_items-->
				
                                        <div id="pagination">
                                            
                                        </div>
				</div>
			</div>
		</div>
	</section>
	
	
    </body>
    
    <%@ include file="./script.jsp" %>
    
    <script>
            
            jQuery(function($) {
                // After one second, remove the "refreshed/loaded" notice.
                setTimeout(function() { $("#refreshed").remove(); }, 1000);
                // The paginator is created in exactly the same manner as the basic example.
                var items = $(".product-image-wrapper");
                var numItems = items.length;
                var perPage =6;
                items.slice(perPage).hide();
                $("#pagination").pagination({
                    items: numItems,
                    itemsOnPage: perPage,
                    cssStyle: "light-theme",
                    onPageClick: function(pageNumber) {
                        var showFrom = perPage * (pageNumber - 1);
                        var showTo = showFrom + perPage;
                        items.hide()
                             .slice(showFrom, showTo).show();
                    }
                });
                // Now we'll create a function to check the URL fragment
                // and change page according to the number it finds there.
                function checkFragment() {
                    // If there's no hash, treat it like page 1.
                    var hash = window.location.hash || "#page-1";
                    // We'll use regular expressions to check the hash string as follows:
                    // ^            Be strictly at the beginning of the string (i.e. fail "hi!#page-3").
                    // #page-       Exactly match the text "#page-".
                    // (            Start a matching group so we can access what's inside (i.e. the page number) later.
                    //      \d      Match any digit (i.e. 0123456789).
                    //      +       Match the previous literal (i.e. digit in this case) one or more times.
                    // )            End the matching group.
                    // $            Be strictly at the end of the string (i.e. fail "#page-3hi!").
                    hash = hash.match(/^#page-(\d+)$/);
                    if(hash) {
                        // The `selectPage` function is one of many described in the documentation:
                        // https://flaviusmatis.github.io/simplePagination.js/
                        // We've captured the page number in a regex group: `(\d+)`.
                        $("#pagination").pagination("selectPage", parseInt(hash[1]));
                    }
                };
                // We'll call this function whenever back/forward is pressed...
                $(window).bind("popstate", checkFragment);
                // (Thanks to [Mike O'Connor](https://stackoverflow.com/users/3568095/mike-oconnor)
                // for highlighting the need for this!)
                
                // ... and we'll also call it when the page has loaded
                // (which is right now).
                checkFragment();
            });
        </script>
        
        <script>
            
            function sliderFilter(name, category){
                var lPrice = $('#sl2').data('slider').getValue()[0];
                var hPrice = $('#sl2').data('slider').getValue()[1];
                filter(name,category,lPrice,hPrice);
            }
            
            function filterName(category,lPrice,hPrice){
                var name = '"'+$("#search").val()+'"';
                filter(name,category,lPrice,hPrice);
            }
            
            function filter(name = null,category = null,lPrice = null,hPrice= null){
                var link = '/shoppingCart/?';
                if(name !== null){
                    link+= 'name="'+name+'"&';
                }
                if(category !== null){
                    link+= 'category='+category+'&';
                }
                if(lPrice !== null && hPrice !== null){
                    link+= 'lPrice='+lPrice+'&hPrice='+hPrice+'&';
                }
                window.location.href = link;
            

            }
            
            function addToCart(itemId,quantity){
                var saveData = $.ajax({
                        type: 'POST',
                        url: "addCartItem",
                        data: {item:itemId,quantity:quantity},
                        dataType: "text",
                        success: function(resultData) { alert("Item Added to the cart !") },
                        error:function(){alert("error")}
                });
            }
            
         
            
        </script>
        
        
            
    
   

  

</html>
