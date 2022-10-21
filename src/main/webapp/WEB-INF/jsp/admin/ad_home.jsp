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
          </main>
          <%@include file="ad_footer.jsp"%>
      </div>
  </div>

  </body>
</html>
