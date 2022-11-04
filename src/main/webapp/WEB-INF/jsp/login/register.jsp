<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 11/02/22
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
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
<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">Create Account</h3></div>
                            <div class="card-body">
                                <form:form action="${action}" role="form" method="post" modelAttribute="user" class="registration-form">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <form:input path="firstName" type="text" class="form-control" placeholder="Enter your first name" id="inputFirstName"/>
<%--                                                <input class="form-control" id="inputFirstName" type="text" placeholder="Enter your first name" />--%>
                                                <label for="inputFirstName">First name</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <form:input path="lastName" type="text" class="form-control" placeholder="Enter your last name" id="inputLastName"/>
<%--                                                <input class="form-control" id="inputLastName" type="text" placeholder="Enter your last name" />--%>
                                                <label for="inputLastName">Last name</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:input path="email" class="form-control" id="inputEmail" type="email" placeholder="name@example.com" />
<%--                                        <input class="form-control" id="inputEmail" type="email" placeholder="name@example.com" />--%>
                                        <label for="inputEmail">Email address</label>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <form:input path="password" class="form-control" id="inputPassword" type="password" placeholder="Create a password" />
                                                <label for="inputPassword">Password</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputPasswordConfirm" type="password" placeholder="Confirm password" />
                                                <label for="inputPasswordConfirm">Confirm Password</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <form:select path="gender" class="form-control" id="inputGender" cssStyle="align-items: center">
                                                    <form:option value="0" label="---Gender---" for="inputGender"/>
                                                    <form:option value="1" label="Male" />
                                                    <form:option value="2" label="Female" />
                                                    <form:option value="3" label="Other" />
                                                    <%--                                    <form:options items="${categoryDetailList}" />--%>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <form:input path="age" type="number" class="form-control" placeholder="Age" id="inputAge"/>
                                                    <%--                                                <input class="form-control" id="inputLastName" type="text" placeholder="Enter your last name" />--%>
                                                <label for="inputAge">Age</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:input path="phoneNumber" class="form-control" id="inputPhone" type="text" placeholder="0905******" />
                                            <%--                                        <input class="form-control" id="inputEmail" type="email" placeholder="name@example.com" />--%>
                                        <label for="inputPhone">Phone number</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <form:input path="address" class="form-control" id="inputAddress" type="text" placeholder="Ho Chi Minh City" />
                                            <%--                                        <input class="form-control" id="inputEmail" type="email" placeholder="name@example.com" />--%>
                                        <label for="inputAddress">Address</label>
                                    </div>

                                    <div class="mt-4 mb-0">
                                        <div class="d-grid"> <button type="submit" class="btn btn-primary btn-block">Create Account</button></div>
<%--                                        <div class="d-grid"><a class="btn btn-primary btn-block" href="/login">Create Account</a></div>--%>
                                    </div>
                                </form:form>
                            </div>
                            <div class="card-footer text-center py-3">
                                <div class="small"><a href="/login">Have an account? Go to login</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutAuthentication_footer">
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2022</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
</body>
</html>
