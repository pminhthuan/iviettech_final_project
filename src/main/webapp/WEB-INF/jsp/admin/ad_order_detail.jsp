<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/23/22
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
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
                    Order detail
                </div>
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Product name</th>
                            <th>Quantity</th>
                            <th>Amount</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Product name</th>
                            <th>Quantity</th>
                            <th>Amount</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${orderDetailList}" var="od">
                            <tr>
                                <td>${od.id}</td>
                                <td>${od.product.name}</td>
                                <td>${od.quantity}</td>
                                <td>${od.amount}</td>
                                <td>
                                    <a href="<c:url value="#/${m.id}"/>">Edit</a>
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
