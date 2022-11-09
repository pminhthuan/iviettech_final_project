<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/21/22
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
      <title>Admin home</title>
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
  <jsp:include page="ad_header.jsp"/>
  <div id="layoutSidenav">
      <jsp:include page="ad_sidebar_menu.jsp"/>
      <div id="layoutSidenav_content">
          <main>
              <jsp:include page="ad_dashboard.jsp"/>
<%--              <div class="container-fluid px-4">--%>
<%--                  <h1 class="mt-4">Dashboard</h1>--%>
<%--                  <div class="row">--%>
<%--                      <div class="col-xl-6">--%>
<%--                          <div class="card mb-4">--%>
<%--                              <div class="card-header">--%>
<%--                                  <i class="fas fa-chart-bar me-1"></i>--%>
<%--                                  Order quantity per months--%>
<%--                              </div>--%>
<%--                              <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>--%>
<%--                          </div>--%>
<%--                      </div>--%>
<%--                      <div class="col-xl-6">--%>
<%--                          <div class="card mb-4">--%>
<%--                              <div class="card-header">--%>
<%--                                  <i class="fas fa-chart-area me-1"></i>--%>
<%--                                  Order prices per months--%>
<%--                              </div>--%>
<%--                              <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>--%>
<%--                          </div>--%>
<%--                      </div>--%>
<%--                  </div>--%>
<%--                  <div class="card mb-4">--%>
<%--                      <div class="card-header">--%>
<%--                          <i class="fas fa-table me-1"></i>--%>
<%--                          Last 5 pending orders--%>
<%--                      </div>--%>
<%--                      <div class="card-body">--%>
<%--                          <table id="datatablesSimple">--%>
<%--                              <thead>--%>
<%--                              <tr>--%>
<%--                                  <th>Id</th>--%>
<%--                                  <th>Name</th>--%>
<%--                                  <th>Phone</th>--%>
<%--                                  <th>Email</th>--%>
<%--                                  <th>Address</th>--%>
<%--                                  <th>Total</th>--%>
<%--                              </tr>--%>
<%--                              </thead>--%>
<%--                              <tfoot>--%>
<%--                              <tr>--%>
<%--                                  <th>Id</th>--%>
<%--                                  <th>Name</th>--%>
<%--                                  <th>Phone</th>--%>
<%--                                  <th>Email</th>--%>
<%--                                  <th>Address</th>--%>
<%--                                  <th>Total</th>--%>
<%--                              </tr>--%>
<%--                              </tfoot>--%>
<%--                              <tbody>--%>
<%--                              <c:forEach items="${order_data_list}" var="order">--%>
<%--                                  <tr>--%>
<%--                                      <td>${order.id}</td>--%>
<%--                                      <td>--%>
<%--                                          <a href="<c:url value="/admin/adOrderDetail/${order.id}"/>">${order.firstName} ${order.lastName}</a>--%>
<%--                                      </td>--%>
<%--                                      <td>${order.phoneNumber}</td>--%>
<%--                                      <td>${order.email}</td>--%>
<%--                                      <td>${order.addressDetail}</td>--%>
<%--                                      <td>--%>
<%--                                              ${order.totalAmount}--%>
<%--                                      </td>--%>
<%--                                  </tr>--%>
<%--                              </c:forEach>--%>

<%--                              </tbody>--%>
<%--                          </table>--%>
<%--                      </div>--%>
<%--                  </div>--%>
<%--              </div>--%>
          </main>
          <jsp:include page="ad_footer.jsp"/>
      </div>
  </div>

  </body>
</html>
