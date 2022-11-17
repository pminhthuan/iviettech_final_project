<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 2022-11-11
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>View total</title>
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
<div class="row container-fluid px-4">
  <h1 class="mt-4">Report card</h1>
  <div class="col-xl-3 col-md-6 col-12">
    <div class="info-box bg-primary text-white">
<%--      <span class="info-box-icon push-bottom"><i class="material-icons edit-icon-you">style</i></span>--%>
      <div class="card-body">
        <span class="info-box-text">Total Week</span>
        <span class="info-box-number">
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="${allTotal.totalWeek}" currencySymbol="$"  type="currency"/>
                        <br>
                    </span>
        <span class="info-box-number">${allTotal.weekOrder} Order</span>
        <div class="card-footer progress">
          <div class="progress-bar width-${allTotal.percentWeek}"></div>
<%--  <div class="progress-bar width-5"></div>--%>
        </div>
        <span class="progress-description">
                                ${allTotal.percentWeek}% of this month
                            </span>
      </div>
    </div>
  </div>
  <div class="col-xl-3 col-md-6 col-12">
    <div class="info-box bg-primary text-white">
      <%--      <span class="info-box-icon push-bottom"><i class="material-icons edit-icon-you">style</i></span>--%>
      <div class="card-body">
        <span class="info-box-text">Total Month</span>
        <span class="info-box-number">
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="${allTotal.totalMonth}" currencySymbol="$"  type="currency"/>
                        <br>
                    </span>
        <span class="info-box-number">${allTotal.monthOrder} Order</span>
        <div class="card-footer progress">
          <div class="progress-bar width-${allTotal.percentMonth}"></div>
        </div>
        <span class="progress-description">
            ${allTotal.percentMonth}% of this year
        </span>
      </div>
    </div>
  </div>
  <div class="col-xl-3 col-md-6 col-12">
    <div class="info-box bg-primary text-white">
      <%--      <span class="info-box-icon push-bottom"><i class="material-icons edit-icon-you">style</i></span>--%>
      <div class="card-body">
        <span class="info-box-text">Total Year</span>
        <span class="info-box-number">
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="${allTotal.totalYear}" currencySymbol="$"  type="currency"/>
                        <br>
                    </span>
        <span class="info-box-number">${allTotal.yearOrder} Order</span>
        <div class="card-footer progress">
          <div class="progress-bar width-${allTotal.percentYear}"></div>
        </div>
        <span class="progress-description">
            ${allTotal.percentYear}%Total Earnings
        </span>
      </div>
    </div>
  </div>
  <div class="col-xl-3 col-md-6 col-12">
    <div class="info-box bg-primary text-white">
      <%--      <span class="info-box-icon push-bottom"><i class="material-icons edit-icon-you">style</i></span>--%>
      <div class="card-body">
        <span class="info-box-text">Total Earnings</span>
        <span class="info-box-number">
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="${allTotal.totalPrice}" currencySymbol="$"  type="currency"/>
                        <br>
                    </span>
        <span class="info-box-number">${allTotal.totalOrder} Order</span>
        <div class="card-footer progress">
          <div class="progress-bar width-100"></div>
        </div>
        <span class="progress-description">
            Total Earnings
        </span>
      </div>
    </div>
  </div>
</div>

</body>
</html>
