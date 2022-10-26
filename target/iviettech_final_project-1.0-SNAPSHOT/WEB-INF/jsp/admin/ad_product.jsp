<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/21/22
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
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
            <div class="card mb-4">
                <div class="card-header">
                    <a
                            href="/admin/newProduct"
                            class="btn btn-primary btn-block text-uppercase me-1">Add new product</a>

                    <i class="fas fa-table me-1"></i>
                    Product table

                </div>

                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Product name</th>
                            <th>Category detail</th>
                            <th>Manufactor</th>
                            <th>Orginal price</th>
                            <th>Actual price</th>
                            <th>Discription</th>
                            <th>Status</th>
                            <th>Image</th>
                            <th>Edit</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Product name</th>
                            <th>Category detail</th>
                            <th>Manufactor</th>
                            <th>Orginal price</th>
                            <th>Actual price</th>
                            <th>Discription</th>
                            <th>Status</th>
                            <th>Image</th>
                            <th>Edit</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${productList}" var="p">
                        <tr>
                            <td>${p.id}</td>
                            <td>
                                <a href="<c:url value="/admin/adProductDetail/${p.id}"/>">${p.name}</a>
                            </td>
                            <td>${p.categoryDetail.name}</td>
                            <td>${p.manufactor.name}</td>
                            <td>${p.original_price}</td>
                            <td>${p.actual_price}</td>
                            <td>${p.description}</td>
                            <td>${p.status}</td>
                            <td>
                                <a href="<c:url value="/admin/adProductImage/${p.id}"/>">Image</a>
                            </td>
                            <td>
                                <a href="<c:url value="/admin/editProduct/${p.id}"/>">Edit</a>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

<%--            <div>--%>
<%--                <button href="/admin/newProduct" class="accordion-button fa-solid fa-plus me-0" type="button">--%>
<%--                    &lt;%&ndash;                        <a href="/admin/newProduct" class="fa-solid fa-plus me-0">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        <i class="fa-solid fa-plus">New product</i>&ndash;%&gt;New product--%>
<%--                    &lt;%&ndash;                    </a>&ndash;%&gt;--%>
<%--                </button>--%>
<%--            </div>--%>
        </main>
        <%@include file="ad_footer.jsp"%>
    </div>
</div>

</body>
</html>