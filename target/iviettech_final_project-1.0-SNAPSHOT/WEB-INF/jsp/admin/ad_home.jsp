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
  <%@include file="ad_header.jsp"%>
  <div id="layoutSidenav">
      <%@include file="ad_sidebar_menu.jsp"%>
      <div id="layoutSidenav_content">
          <main>
              <%@include file="ad_dashboard.jsp"%>
<%--              <div class="container-fluid px-4">--%>
<%--                  <h1 class="mt-4">Dashboard</h1>--%>
<%--                  <ol class="breadcrumb mb-4">--%>
<%--                      <li class="breadcrumb-item active">Dashboard</li>--%>
<%--                  </ol>--%>
<%--                  <div class="row">--%>
<%--                      <div class="col-xl-3 col-md-6">--%>
<%--                          <div class="card bg-primary text-white mb-4">--%>
<%--                              <div class="card-body">Primary Card</div>--%>
<%--                              <div class="card-footer d-flex align-items-center justify-content-between">--%>

<%--                                  <a class="small text-white stretched-link" href="#">View Details</a>--%>
<%--                                  <a class="small text-white stretched-link" >${totalDay}</a>--%>
<%--                                  <div class="small text-white"><i class="fas fa-angle-right"></i></div>--%>
<%--                              </div>--%>
<%--                          </div>--%>
<%--                      </div>--%>
<%--                      <div class="col-xl-3 col-md-6">--%>
<%--                          <div class="card bg-warning text-white mb-4">--%>
<%--                              <div class="card-body">Warning Card</div>--%>
<%--                              <div class="card-footer d-flex align-items-center justify-content-between">--%>
<%--                                  <a class="small text-white stretched-link" href="#">View Details</a>--%>
<%--                                  <div class="small text-white"><i class="fas fa-angle-right"></i></div>--%>
<%--                              </div>--%>
<%--                          </div>--%>
<%--                      </div>--%>
<%--                      <div class="col-xl-3 col-md-6">--%>
<%--                          <div class="card bg-success text-white mb-4">--%>
<%--                              <div class="card-body">Success Card</div>--%>
<%--                              <div class="card-footer d-flex align-items-center justify-content-between">--%>
<%--                                  <a class="small text-white stretched-link" href="#">View Details</a>--%>
<%--                                  <div class="small text-white"><i class="fas fa-angle-right"></i></div>--%>
<%--                              </div>--%>
<%--                          </div>--%>
<%--                      </div>--%>
<%--                      <div class="col-xl-3 col-md-6">--%>
<%--                          <div class="card bg-danger text-white mb-4">--%>
<%--                              <div class="card-body">Danger Card</div>--%>
<%--                              <div class="card-footer d-flex align-items-center justify-content-between">--%>
<%--                                  <a class="small text-white stretched-link" href="#">View Details</a>--%>
<%--                                  <div class="small text-white"><i class="fas fa-angle-right"></i></div>--%>
<%--                              </div>--%>
<%--                          </div>--%>
<%--                      </div>--%>
<%--                  </div>--%>
<%--              </div>--%>
          </main>
          <%@include file="ad_footer.jsp"%>
      </div>
  </div>

  </body>
</html>
