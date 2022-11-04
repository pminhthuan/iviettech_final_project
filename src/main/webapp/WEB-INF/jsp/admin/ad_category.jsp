<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/22/22
  Time: 2:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Category</title>
    <link rel="icon" type="image/png" href="/resources/images/icons/logo_T_T_Black.png"/>
    <link rel="stylesheet" type="text/css" href="/resources/admin/assets/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/dashboard.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/custom.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/styles.css">
    <script src="/resources/admin/js/all.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/js/scripts.js"></script>
    <script src="/resources/admin/js/Chart.min.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/assets/demo/chart-area-demo.js"></script>
    <script src="/resources/admin/assets/demo/chart-bar-demo.js"></script>
    <script src="/resources/admin/js/simple-datatables@latest.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/js/datatables-simple-demo.js"></script>
</head>
<body class="sb-nav-fixed">
<%@include file="ad_header.jsp"%>
<div id="layoutSidenav">
    <%@include file="ad_sidebar_menu.jsp"%>
    <div id="layoutSidenav_content">
        <main>
            <div class="row">
                <div class="form-group mb-3 col-xs-12 col-sm-8">
                    <div class="card mb-4">
                        <div class="card-header">
                            <a
                                    href="/admin/newCategoryDetail"
                                    class="btn btn-primary btn-block text-uppercase me-1">New category detail</a>
                            <i class="fas fa-table me-1"></i>
                            Category Detail
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Discription</th>
                                    <th>Category name</th>
                                    <th>Edit</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Discription</th>
                                    <th>Category name</th>
                                    <th>Edit</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${categoryDetailList}" var="cd">
                                    <tr>
                                        <td>${cd.id}</td>
                                        <td>${cd.name}</td>
                                        <td>${cd.description}</td>
                                        <td>${cd.category.name}</td>
                                        <td>
                                            <a href="<c:url value="/admin/editCategoryDetail/${cd.id}"/>"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="form-group mb-3 col-xs-12 col-sm-4">
                    <div class="card mb-4">
                        <div class="card-header">
                            <a
                                    href="/admin/newCategory"
                                    class="btn btn-primary btn-block text-uppercase me-1">Add new category</a>
                            <i class="fas fa-table me-1"></i>
                            Category Detail
                        </div>
                        <div class="card-body">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Edit</th>
                                </tr>
                                </thead>
<%--                                <tfoot>--%>
<%--                                <tr>--%>
<%--                                    <th>Id</th>--%>
<%--                                    <th>Name</th>--%>
<%--                                    <th>Edit</th>--%>
<%--                                </tr>--%>
<%--                                </tfoot>--%>
                                <tbody>
                                <c:forEach items="${categoryList}" var="c">
                                    <tr>
                                        <td>${c.id}</td>
                                        <td>${c.name}</td>
                                        <td>
                                            <a href="<c:url value="/admin/editCategory/${c.id}"/>"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="ad_footer.jsp"%>
    </div>
</div>

</body>
</html>
