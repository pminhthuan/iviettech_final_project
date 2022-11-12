<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/21/22
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Product</title>
    <link rel="icon" type="image/png" href="/resources/images/icons/logo_T_T_Black.png"/>
    <link rel="stylesheet" type="text/css" href="/resources/admin/assets/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/dashboard.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/custom.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/styles.css">
    <script src="/resources/admin/js/all.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/js/scripts.js"></script>
    <script src="/resources/admin/js/Chart.min.js" crossorigin="anonymous"></script>
<%--    <script src="/resources/admin/assets/demo/chart-area-demo.js"></script>--%>
<%--    <script src="/resources/admin/assets/demo/chart-bar-demo.js"></script>--%>
    <script src="/resources/admin/js/simple-datatables@latest.js" crossorigin="anonymous"></script>
    <script src="/resources/admin/js/datatables-simple-demo.js"></script>
</head>
<body class="sb-nav-fixed">
<jsp:include page="ad_header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="ad_sidebar_menu.jsp"/>
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
                            <th>Category</th>
                            <th>Manufactor</th>
                            <th>Orginal</th>
                            <th>Actual</th>
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
                            <th>Category</th>
                            <th>Manufactor</th>
                            <th>Orginal</th>
                            <th>Actual</th>
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
                            <td>${p.categoryDetail.description}</td>
                            <td>${p.manufactor.name}</td>
                            <td>${p.original_price}</td>
                            <td>${p.actual_price}</td>
                            <td>${p.description}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.status == 0}">
<%--                                        <label style="color: red">${p.status}</label>--%>
                                        <a href="<c:url value="/admin/updateProductStatus/${p.id}"/>">
                                            <i class="fa fa-check" aria-hidden="true"></i></a>
                                    </c:when>
                                    <c:otherwise>
<%--                                        <label style="color: green">${p.status}</label>--%>
                                        <a href="<c:url value="/admin/updateProductStatus/${p.id}"/>">
                                            <i class="fa fa-times" aria-hidden="true" style="color: red"></i>
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="<c:url value="/admin/adProductImage/${p.id}"/>">
                                    <i class="fa fa-camera" aria-hidden="true" style="font-size: 24px;text-align: center"></i>
                                </a>
                            </td>
                            <td>
                                <a href="<c:url value="/admin/editProduct/${p.id}"/>">
                                    <i class="fa fa-pencil" aria-hidden="true" style="font-size:18px;color:greenyellow;text-align: center"></i>
                                </a>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <br>
            <div class="row col-md-12 container-fluid px-4">
                <div class="col-md-6">
                    <h4>Import Product data as a CSV file...</h4>
                    <c:if test="${mssImport != null}">
                        <div class="alert alert-danger">
                            <c:out value="${mssImport}"/>
                        </div>
                    </c:if>
                    <form:form action="/admin/importProduct" method="POST" enctype="multipart/form-data">
                        <div class="input-group">
                            <input name="file" type="file" class="form-control"/>
                            <button type="submit" class="btn btn-info">Import</button>&emsp;
                        </div>
                    </form:form>
                    <div>
                        <span class="input-group-btn">
                            <a href="/admin/exportProduct">
                                <button type="button" class="btn btn-success">Export Product</button>
                            </a>
                        </span> &emsp;
                        <span class="input-group-btn">
                            <a href="/admin/exportProductDetail">
                                <button type="button" class="btn btn-success">Export Product Detail</button>
                            </a>
                        </span>
                    </div>
                    <br>
                    <%--                    <span class="input-group-btn">--%>
                    <%--                        <button type="button" class="btn btn-success">Export to CSV</button>--%>
                    <%--                    </span>--%>
                    <%--                    <button type="button" class="btn btn-success">Export to CSV</button>--%>

                    <%--                    <a href="/export-csv">--%>
                    <%--                        <button type="button" class="btn btn-success">Export to CSV</button>--%>
                    <%--                    </a>--%>
                </div>
            </div>
<%--            <div>--%>

<%--                <a href="/admin/exportProduct">--%>
<%--                    <button type="button" class="btn btn-success">Export to CSV</button>--%>
<%--                </a>--%>

<%--            </div>--%>


<%--            <div>--%>
<%--                <button href="/admin/newProduct" class="accordion-button fa-solid fa-plus me-0" type="button">--%>
<%--                    &lt;%&ndash;                        <a href="/admin/newProduct" class="fa-solid fa-plus me-0">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        <i class="fa-solid fa-plus">New product</i>&ndash;%&gt;New product--%>
<%--                    &lt;%&ndash;                    </a>&ndash;%&gt;--%>
<%--                </button>--%>
<%--            </div>--%>
        </main>
        <jsp:include page="ad_footer.jsp"/>
    </div>
</div>

</body>
</html>
