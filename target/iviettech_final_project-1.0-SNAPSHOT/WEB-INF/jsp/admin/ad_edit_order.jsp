<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/24/22
  Time: 8:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Order</title>
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
            <div class="container" style="margin-top: 60px;">
                <%--                <h2>${msg}</h2>--%>
                <div class="col-md-4">
                    <form:form action="${action}" method="post" modelAttribute="order">
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border"><c:out value="${msg}"/></legend>
                            <c:if test="${type.equals('updateOrder')}">
                                <div class="form-group">
                                    <label class="control-label">ID</label>
                                    <form:input path="id" type="text" class="form-control" id="id" placeholder="ID" readonly="true"/>
<%--                                    <form:hidden path="id" />--%>
                                        <%--                                    <form:hidden path="product.id" />--%>
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">First name (*)</label>
                                    <form:input path="firstName" type="text" class="form-control" placeholder="First name" required="true" readonly="true"/>
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Last name (*)</label>
                                    <form:input path="lastName" type="text" class="form-control" placeholder="Last name" required="true" readonly="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Phone (*)</label>
                                <form:input path="phoneNumber" type="text" class="form-control" placeholder="0123456789" required="true" readonly="true"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Email (*)</label>
                                <form:input path="email" type="text" class="form-control" placeholder="abc@email.com" required="true" readonly="true"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Address (*)</label>
                                <form:input path="addressDetail" type="text" class="form-control" placeholder="Address" required="true" readonly="true"/>
                            </div>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Require Date (*)</label>
                                    <form:input path="requireDate" type="date" class="form-control" name="requireDate" required="true" readonly="true"/>
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                        <label class="control-label">Shipping Date (*)</label>
                                        <form:input path="shippingDate" type="date" class="form-control" name="shippingDate"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Total Amount (*)</label>
                                    <form:input path="totalAmount" type="text" class="form-control" name="totalAmount" required="true" readonly="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Note</label>
                                <form:input path="notesOfCustomer" type="text" class="form-control" placeholder="Note"/>
                            </div>

                            <br>
                            <button type="submit" class="btn btn-info">Save</button>
                        </fieldset>
                    </form:form>
                </div>
            </div>

        </main>
        <%@include file="ad_footer.jsp"%>
    </div>
</div>
</body>
</html>
