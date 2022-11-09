<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 11/04/22
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
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
<body>
<jsp:include page="ad_header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="ad_sidebar_menu.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table me-1"></i>
                    Account
                </div>
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>Age</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Role</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>Age</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Role</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${userList}" var="a">
                            <tr>
                                <td>
                                    <a href="/admin/editAccount/${a.id}">${a.firstName} ${a.lastName}</a>
                                </td>
                                <td>${a.email}</td>
<%--                                <td>--%>
<%--                                    <a href="<c:url value="/admin/editManufactor/${m.id}"/>"><i class="fa fa-pencil" aria-hidden="true"></i> ${m.name}</a>--%>
<%--                                </td>--%>
                                <td>${a.gender}</td>
                                <td>${a.age}</td>
                                <td>${a.phoneNumber}</td>
                                <td>${a.addressDetail}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${a.role.id == 1}">
                                            <%--                                        <label style="color: red">${p.status}</label>--%>
                                            <a href="<c:url value="/admin/updateRoleAccount/${a.id}"/>">
                                                <i class="fa fa-check" aria-hidden="true"></i></a>
                                        </c:when>
                                        <c:otherwise>
                                            <%--                                        <label style="color: green">${p.status}</label>--%>
                                            <a href="<c:url value="/admin/updateRoleAccount/${a.id}"/>">
                                                <i class="fa fa-times" aria-hidden="true" style="color: red"></i>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <jsp:include page="ad_footer.jsp"/>
    </div>
</div>
</body>
</html>
