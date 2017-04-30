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
                <li class="active">
                    <a href="dashboard">
                        <i class="pe-7s-graph"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                
                <li>
                    <a href="addItem">
                        <i class="pe-7s-graph"></i>
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
                    <a class="navbar-brand" href="#">Dashboard</a>
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
<!--                            <div class="header">
                                <h4 class="title">Edit Profile</h4>
                            </div>-->
                            <div class="content">
                                <form>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input type="text" class="form-control" placeholder="Item Name" name="name" value="">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Category</label>
                                                <select name="category" class="form-control">
                                                    <option value="0">All</option>
                                                    <c:choose>
                                                        <c:when test="${categories == null}">

                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach items="${categories}" var="category">
                                                                <option value="${category.id}">${category.name}</option>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Item Code</label>
                                                <input name="itemCode" type="text" class="form-control" placeholder="Item Code">
                                            </div>
                                        </div>
                                    </div>

                                <button type="submit" class="btn btn-info btn-fill pull-right">Search</button>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                
                
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            
                            <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Type</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Edit</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${items == null}">
                                        
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${items}" var="item">
                                            <tr>
                                                <td>${item.name}</td>
                                                <td>${item.category.name}</td>
                                                <td>${item.price}</td>
                                                <td>${item.quantity}</td>
                                                <td><a class="btn btn-success" href="editItem?id=${item.id}">Edit</a></td>
                                                <td><a class="btn btn-danger">Delete</a></td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>

                        </div>
                    </div>



                </div>
            </div>
        </div>


        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>

                    </ul>
                </nav>
                <p class="copyright pull-right">
                    &copy; <script>document.write(new Date().getFullYear())</script> <a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web
                </p>
            </div>
        </footer>

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