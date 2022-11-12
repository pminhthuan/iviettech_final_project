<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 11/04/22
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Edit account</title>
    <link rel="stylesheet" type="text/css" href="/resources/admin/assets/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/dashboard.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/custom.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/styles.css">
    <script src="/resources/admin/js/all.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <%--    <script src="/resources/admin/js/scripts.js"></script>--%>
    <%--    <script src="/resources/admin/js/Chart.min.js" crossorigin="anonymous"></script>--%>
    <script src="/resources/admin/assets/demo/chart-area-demo.js"></script>
    <script src="/resources/admin/assets/demo/chart-bar-demo.js"></script>
    <script src="/resources/admin/js/simple-datatables@latest.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/js/datatables-simple-demo.js"></script>
</head>
<body class="sb-nav-fixed">
<jsp:include page="ad_header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="ad_sidebar_menu.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <div class="container" style="margin-top: 60px;">
                <%--                <h2>${msg}</h2>--%>
                <div class="col-md-4">
                    <form:form action="${action}" method="post" modelAttribute="account">
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border"><c:out value="${msg}"/></legend>
                            <c:if test="${type.equals('updateAccount')}">
                                <div class="form-group">
                                    <label class="control-label">ID</label>
                                    <form:input path="id" type="text" class="form-control" id="id" placeholder="ID" readonly="true"/>
<%--                                    <form:hidden path="email" />--%>
                                    <form:hidden path="password" />
                                    <form:hidden path="province.id" />
                                    <form:hidden path="district.id" />
                                    <form:hidden path="ward.id" />
                                </div>
                            </c:if>

                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">First name (*)</label>
                                    <form:input path="firstName" type="text" class="form-control" placeholder="First name" required="true"/>
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Last name (*)</label>
                                    <form:input path="lastName" type="text" class="form-control" placeholder="Last name" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Email (*)</label>
                                <form:input path="email" type="text" class="form-control" placeholder="Email" required="true"/>
                            </div>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Age (*)</label>
                                    <form:input path="age" type="number" step="1" class="form-control" placeholder="Age" required="true"/>
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Actual price (*)</label>
                                    <form:select path="gender" class="form-control">
                                        <form:option value="0" label="---Gender---"/>
                                        <form:option value="1" label="Male" />
                                        <form:option value="2" label="Female" />
                                        <form:option value="3" label="Other" />
                                        <%--                                    <form:options items="${categoryDetailList}" />--%>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Phone (*)</label>
                                <form:input path="phoneNumber" type="text" class="form-control" placeholder="Number phone" required="true"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Address (*)</label>
                                <form:input path="addressDetail" type="text" class="form-control" placeholder="Address" required="true"/>
                            </div>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Role (*)</label>
                                    <form:select path="role.id" class="form-control">
                                        <form:option value="1" label="Customer"/>
                                        <form:option value="2" label="Admin" />
                                    </form:select>
                                </div>
                            </div>
<%--                            <c:if test="${type.equals('newAccount')}">--%>
<%--                                <div class="form-group">--%>
<%--                                    <label class="control-label">ID</label>--%>
<%--                                    <form:select path="password" type="password" class="form-control" readonly="true"/>--%>
<%--                                    <form:option value="E412D0EDAE7E4C37EAF763643BA006B27AFEF2E747DB3E65B1C5355AB1B3564C" label="Default password: 12345"/>--%>
<%--                                </div>--%>
<%--                            </c:if>--%>
                            <br>
                            <button type="submit" class="btn btn-info">Save</button>
                        </fieldset>
                    </form:form>
                </div>
            </div>

        </main>
        <jsp:include page="ad_footer.jsp"/>
    </div>
</div>

</body>
</html>
