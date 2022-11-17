<%--
  Created by IntelliJ IDEA.
  User: phamv
  Date: 10/23/22
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Product image</title>
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
    <link rel="stylesheet" type="text/css" href="/resources/vendor/lightbox2/css/lightbox.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/slick/slick.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/MagnificPopup/magnific-popup.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/css/util.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body class="sb-nav-fixed">
<jsp:include page="ad_header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="ad_sidebar_menu.jsp"/>
    <div id="layoutSidenav_content">
        <main>

                    <div class="tab01">
                    <!--tab panes-->
                        <div class="tab-content p-t-50">
                            <!-- - -->
                            <div class="tab-pane fade show active" id="best-seller" role="tabpanel">
                                <!-- Slide2 -->
                                <div class="wrap-slick2">

                                    <div class="slick2">
                                        <c:forEach items="${productImageList}" var="pi">
                                            <div class="item-slick2 p-l-15 p-r-15 p-t-15 p-b-15">
                                                <!-- Block2 -->
                                                <div class="block2">
                                                    <div class="block2-pic hov-img0">
<%--                                                        <a href="<c:url value="/viewImage/${pi.id}"/>">--%>
                                                        <a>
                                                            <img src="${pi.imageUrl}" alt="${pi.imageAlt}">
                                                        </a>
                                                    </div>

                                                    <div class="block2-txt flex-w flex-t p-t-14">
                                                        <div class="block2-txt-child1 flex-col-l ">
<%--                                                            <a href="<c:url value="/admin/viewImage/${pi.id}"/>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">--%>
<%--                                                                    ${p.product.name}--%>
<%--                                                            </a>--%>
                                                            <a class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">${p.product.name}</a>

                                                            <span class="stext-105 cl3">
                                                                         <a href="<c:url value="/admin/deleteImage/${pi.id}/${pi.product.id}"/>">
                                                                             <i class="fa fa-trash" aria-hidden="true" style="font-size:36px;"></i>
                                                                             Delete
                                                                         </a>
                                                                    </span>
                                                        </div>

    <%--                                                    <div class="block2-txt-child2 flex-r p-t-3">--%>
    <%--                                                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">--%>
    <%--                                                            <img class="icon-heart1 dis-block trans-04" src="/resources/images/icons/icon-heart-01.png" alt="ICON">--%>
    <%--                                                            <img class="icon-heart2 dis-block trans-04 ab-t-l" src="/resources/images/icons/icon-heart-02.png" alt="ICON">--%>
    <%--                                                        </a>--%>
    <%--                                                    </div>--%>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
<%--                </div>--%>
<%--            </div>--%>

        </main>
        <div class="custom-file mt-3 mb-3">
            <h2>Upload a file...</h2>
            <form:form action="uploadFile" method="POST" enctype="multipart/form-data">
                <div class="input-group">
                    <input name="file" type="file" class="form-control"/>
                    <button type="submit" class="btn btn-info">Upload</button>
                </div>
<%--                <button type="submit" class="btn btn-info">Upload</button>--%>
            </form:form>
        </div>
        <br>
        <jsp:include page="ad_footer.jsp"/>
    </div>
</div>
<!--===============================================================================================-->
<script src="/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="/resources/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="/resources/vendor/bootstrap/js/popper.js"></script>
<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="/resources/vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function(){
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="/resources/vendor/daterangepicker/moment.min.js"></script>
<script src="/resources/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="/resources/vendor/slick/slick.min.js"></script>
<script src="/resources/js/slick-custom.js"></script>
<!--===============================================================================================-->
<script src="/resources/vendor/parallax100/parallax100.js"></script>
<script>
    $('.parallax100').parallax100();
</script>
<!--===============================================================================================-->
<script src="/resources/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<script>
    $('.gallery-lb').each(function() { // the containers for all your galleries
        $(this).magnificPopup({
            delegate: 'a', // the selector for gallery item
            type: 'image',
            gallery: {
                enabled:true
            },
            mainClass: 'mfp-fade'
        });
    });
</script>
<!--===============================================================================================-->
<script src="/resources/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
<script src="/resources/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $('.js-addwish-b2').on('click', function(e){
        e.preventDefault();
    });

    $('.js-addwish-b2').each(function(){
        var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
        $(this).on('click', function(){
            swal(nameProduct, "is added to wishlist !", "success");

            $(this).addClass('js-addedwish-b2');
            $(this).off('click');
        });
    });

    $('.js-addwish-detail').each(function(){
        var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

        $(this).on('click', function(){
            swal(nameProduct, "is added to wishlist !", "success");

            $(this).addClass('js-addedwish-detail');
            $(this).off('click');
        });
    });

    /*---------------------------------------------*/

    $('.js-addcart-detail').each(function(){
        var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
        $(this).on('click', function(){
            swal(nameProduct, "is added to cart !", "success");
        });
    });
</script>
<!--===============================================================================================-->
<script src="/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        $(this).css('position','relative');
        $(this).css('overflow','hidden');
        var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function(){
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="/resources/js/main.js"></script>
</body>
</html>
