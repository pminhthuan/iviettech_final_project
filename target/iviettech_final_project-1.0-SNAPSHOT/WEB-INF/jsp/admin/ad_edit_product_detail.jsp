<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/23/22
  Time: 8:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Product detail</title>
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
                    <form:form action="${action}" method="post" modelAttribute="productDetail">
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border"><c:out value="${msg}"/></legend>
                            <c:if test="${type.equals('updateProductDetail')}">
                                <div class="form-group">
                                    <label class="control-label">ID</label>
                                    <form:input path="id" type="text" class="form-control" id="id" placeholder="ID" disabled="true"/>
                                    <form:hidden path="id" />
                                    <form:hidden path="product.id" />
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="control-label">Product name (*)</label>
                                <form:select path="product.id" class="form-control">
                                    <form:option value="0" label="--- Select ---" />
                                    <form:options items="${productList}" />
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Size (*)</label>
                                <form:select path="size" class="form-control">
                                    <form:option value="0" label="--- Select ---" />
                                    <form:option value="Free size" label="Free size" />
                                    <form:option value="S" label="S" />
                                    <form:option value="M" label="M" />
                                    <form:option value="L" label="L" />
                                    <form:option value="XL" label="XL" />
                                    <form:option value="XXL" label="XXL" />
<%--                                    <form:options items="${categoryDetailList}" />--%>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Color (*)</label>
                                <form:select path="color" class="form-control">
                                    <form:option value="0" label="--- Select ---" />
                                    <form:option value="Black" label="Black" />
                                    <form:option value="White" label="White" />
                                    <form:option value="Red" label="Red" />
                                    <form:option value="Yellow" label="Yellow" />
                                    <form:option value="Organe" label="Organe" />
                                    <form:option value="Dark Grey" label="Dark Grey" />
                                    <%--                                    <form:options items="${categoryDetailList}" />--%>
                                </form:select>
                            </div>
                            <div class="row">
                                <div class="form-group mb-3 col-xs-12 col-sm-6">
                                    <label class="control-label">Quantity (*)</label>
                                    <form:input path="quantity" type="number" step="1" class="form-control" placeholder="Original price" required="true" min="1"/>
                                </div>
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
