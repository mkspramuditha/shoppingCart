<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="./loginCheck.jsp" %>
    <%@include file="./header.jsp" %>

</head>
<body>

<div class="wrapper">
    <div class="sidebar" data-color="purple" data-image="assets/img/sidebar-5.jpg">

    <!--

        Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
        Tip 2: you can also add an image using data-image tag

    -->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a href="http://www.creative-tim.com" class="simple-text">
                    Creative Tim
                </a>
            </div>

            <ul class="nav">
                <li>
                    <a href="dashboard">
                        <i class="pe-7s-graph"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li class="active">
                    <a href="addItem">
                        <i class ="pe-7s-graph"></i>
                        <p>Add Item</p>
                    </a>
                </li>

            </ul>
    	</div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Edit Item</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-dashboard"></i>
                            </a>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li>
                           <a href="">
                               Account
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    
                     <div class="col-md-12">
                        <div class="card">
                            <div class="content">
                                <form method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <input type="text" hidden value=${id} name="id"/>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Item Name</label>
                                                <input type="text" required="required" class="form-control" placeholder="Item Name" name="name" value=${name} >
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Item Code</label>
                                                <input type="text" required="required" class="form-control" placeholder="Item Code" name="itemCode" value=${itemCode}>
                                            </div>
                                        </div>
                                        
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Price</label>
                                                <input type="number" required="required" class="form-control" placeholder="Price" name="price" value=${price}>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Quantity</label>
                                                <input type="number" required="required" class="form-control" placeholder="Quantity" name="quantity" value=${quantity}>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Category</label>
                                                <select required="required" name="category" class="form-control">
                                                    <c:choose>
                                                        <c:when test="${categories == null}">

                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach items="${categories}" var="category">
                                                                <c:choose>
                                                                    <c:when test="${currentCategory == category.id}">
                                                                        <option value="${category.id}" selected>${category.name}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${category.id}">${category.name}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Image</label>
                                                <input type="file" name="image" required="required" />
                                            </div>
                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-info btn-fill pull-right">Save Item</button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                
                
              
            </div>
        </div>


        

    </div>
</div>


</body>

<%@include file= "script.jsp" %>

<script>
    $(document).ready(function() {
        $('#example').DataTable( {
            "dom": '<"top"i>rt<"bottom"lp><"clear">'
        } );
} );
    
</script>

    <!--   Core JS Files   -->
    
</html>