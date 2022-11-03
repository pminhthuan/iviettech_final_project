<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/23/22
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
            <div class="container" style="margin-top: 60px;">
<%--                <h2>${msg}</h2>--%>
                <div class="col-md-4">
                    <form:form action="${action}" method="post" modelAttribute="product">
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border"><c:out value="${msg}"/></legend>
                            <c:if test="${type.equals('updateProduct')}">
                                <div class="form-group">
                                    <label class="control-label">ID</label>
                                    <form:input path="id" type="text" class="form-control" id="id" placeholder="ID" readonly="true"/>
<%--                                    <form:hidden path="id" />--%>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="control-label">Product name (*)</label>
                                <form:input path="name" type="text" class="form-control" placeholder="Name" required="true"/>
                            </div>
<%--                            <div class="row">--%>
<%--                                <div class="form-group mb-3 col-xs-12 col-sm-6">--%>
<%--                                    <label class="control-label">Category (*)</label>--%>
<%--                                    <form:select path="categoryDetail.category.name" class="form-control">--%>
<%--                                        <form:option value="0" label="--- Select ---" />--%>
<%--                                        <form:options items="${catelList}" />--%>
<%--                                    </form:select>--%>
<%--                                </div>--%>
<%--                                <div class="form-group mb-3 col-xs-12 col-sm-6">--%>
<%--                                    <label class="control-label">Category Detail (*)</label>--%>
<%--                                    <form:select path="categoryDetail.name" class="form-control">--%>
<%--                                        <form:option value="0" label="--- Select ---" />--%>
<%--                                        <form:options items="${categoryDetailList}" />--%>
<%--                                    </form:select>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <div class="form-group">
                                <label class="control-label">Category Detail (*)</label>
                                <form:select path="categoryDetail.id" class="form-control">
                                    <form:option value="0" label="--- Select ---" />
                                    <form:options items="${categoryDetailList}" />
                                </form:select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">Manufactor (*)</label>
                                <form:select path="manufactor.id" class="form-control">
                                    <form:option value="0" label="--- Select ---" />
                                    <form:options items="${manufactorList}" />
                                </form:select>
                            </div>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Original price (*)</label>
                                    <form:input path="original_price" type="number" step="0.1" class="form-control" placeholder="Original price" required="true" min="0.1"/>
                                </div>
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Actual price (*)</label>
                                    <form:input path="actual_price" type="number" step="0.1" class="form-control" placeholder="Actual price" required="true" min="0.1"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Discription</label>
                                <form:input path="description" type="text" class="form-control" placeholder="Discription"/>
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
