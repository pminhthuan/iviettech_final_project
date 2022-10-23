<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/23/22
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Mamufactor</title>
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
                    <i class="fas fa-table me-1"></i>
                    Order
                </div>
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Require date</th>
                            <th>Shipping date</th>
                            <th>Total</th>
                            <th>Status</th>
                            <th>Note</th>
                            <th>Edit</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Require date</th>
                            <th>Shipping date</th>
                            <th>Total</th>
                            <th>Status</th>
                            <th>Note</th>
                            <th>Edit</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${orderList}" var="o">
                            <tr>
                                <td>${o.id}</td>
                                <td>
                                    <a href="<c:url value="/admin/adOrderDetail/${o.id}"/>">${o.fullName}</a>
                                </td>
                                <td>${o.phoneNumber}</td>
                                <td>${o.email}</td>
                                <td>${o.address}</td>
                                <td>${o.requireDate}</td>
                                <td>${o.shippingDate}</td>
                                <td>${o.totalAmount}</td>
                                <td>${o.status}</td>
                                <td>${o.note}</td>
                                <td>
                                    <a href="<c:url value="#${o.id}"/>">Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <%@include file="ad_footer.jsp"%>
    </div>
</div>
</body>
</html>
