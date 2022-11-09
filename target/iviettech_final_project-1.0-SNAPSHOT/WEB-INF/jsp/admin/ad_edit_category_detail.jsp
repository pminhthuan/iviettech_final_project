<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/24/22
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Category detail</title>
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
            <div class="container" style="margin-top: 60px;">
                <%--                <h2>${msg}</h2>--%>
                <div class="col-md-4">
                    <form:form action="${action}" method="post" modelAttribute="categoryDetail">
                        <fieldset class="scheduler-border">
                            <legend class="scheduler-border"><c:out value="${msg}"/></legend>
                            <c:if test="${type.equals('updateCategoryDetail')}">
                                <div class="form-group">
                                    <label class="control-label">ID</label>
                                    <form:input path="id" type="text" class="form-control" id="id" placeholder="ID" readonly="true"/>
<%--                                    <form:hidden path="id" />--%>
                                    <form:hidden path="category.id"/>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="control-label">Category detail name (*)</label>
                                <form:input path="name" type="text" class="form-control" placeholder="Name" required="true"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Category name (*)</label>
                                <form:select path="category.id" class="form-control">
                                    <form:option value="0" label="--- Select ---" />
                                    <form:options items="${cateList}" />
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Description (*)</label>
                                <form:input path="description" type="text" class="form-control" placeholder="Description" required="true"/>
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
