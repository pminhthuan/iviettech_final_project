<%--
  Created by IntelliJ IDEA.
  User: Thuan
  Date: 10/17/22
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shopping</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="/resources/images/icons/logo_T_T_Black.png"/>
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
    <!--===============================================================================================-->
</head>

<jsp:include page="header.jsp"></jsp:include>

<!-- breadcrumb -->
<div class="container">
    <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
        <a href="/" class="stext-109 cl8 hov-cl1 trans-04">
            Home
            <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
        </a>

        <span class="stext-109 cl4">
                Shop
            </span>
    </div>
</div>

<!-- Product -->
<div class="bg0 m-t-23 p-b-140">
    <div class="container">
        <div class="flex-w flex-sb-m p-b-52">
            <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                <a href="/shop">
                    <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 ${activeLink}" data-filter="*">
                        All Products
                    </button>
                </a>
                <c:forEach items="${categories}" var="cate" >
                <div class="dropdown">
                    <a href="<c:url value="/shop/category/${cate.id}"/>" >
                        <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 ${tag == cate.id? "how-active1":""}" data-filter=".women">
                            ${cate.name}
                        </button>
                    </a>
                    <div class="dropdown-content">
                        <c:forEach items="${cate.categoryDetailEntityList}" var="cateDetail">
                        <a href="<c:url value="/shop/category/categorydetail/${cateDetail.id}"/>">
                            <button class="stext-106 cl6 hov1 bor3 trans-04 m-tb-5">
                                ${cateDetail.description}
                            </button>
                        </a>
                        </c:forEach>
                    </div>
                </div>
                </c:forEach>
<%--                <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".men">--%>
<%--                    Men--%>
<%--                </button>--%>

            </div>

            <div class="flex-w flex-c-m m-tb-10">
                <div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
                    <i class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
                    <i class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
                    Filter
                </div>

                <div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
                    <i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
                    <i class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
                    Search
                </div>
            </div>

            <!-- Search product -->
            <div class="dis-none panel-search w-full p-t-10 p-b-15">
                <div class="bor8 dis-flex p-l-15">
                    <form action="/shop/search" method="get" style="height: 4rem;">
                        <button class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04" style="float: left">
                            <i class="zmdi zmdi-search"></i>
                        </button>
                        <input class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" value="${searchInput}" name="searchInput" placeholder="Search">
                    </form>
                </div>
            </div>

            <!-- Filter -->
            <div class="dis-none panel-filter w-full p-t-10">
                <div class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm">
                    <div class="filter-col1 p-r-15 p-b-27">
                        <div class="mtext-102 cl2 p-b-15">
                            Sort By
                        </div>

                        <ul>
                            <li class="p-b-6">
                                <a href="/shop" class="filter-link stext-106 trans-04 filter-link-active">
                                    Default
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="/shop/filter/bestseller" class="filter-link stext-106 trans-04">
                                    Popularity
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="/shop/filter/rating" class="filter-link stext-106 trans-04">
                                    High rating
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="#" class="filter-link stext-106 trans-04 ">
                                    Newness
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="/shop/filter/asc" class="filter-link stext-106 trans-04">
                                    Price: Low to High
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="/shop/filter/desc" class="filter-link stext-106 trans-04">
                                    Price: High to Low
                                </a>
                            </li>
                        </ul>
                    </div>

                    <div class="filter-col2 p-r-15 p-b-27">
                        <div class="mtext-102 cl2 p-b-15">
                            Price
                        </div>

                        <ul>
                            <li class="p-b-6">
                                <a href="/shop" class="filter-link stext-106 trans-04 filter-link-active">
                                    All
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="<c:url value="/shop/filter/${0}/${50}"/>" class="filter-link stext-106 trans-04">
                                    $0.00 - $50.00
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="<c:url value="/shop/filter/${50}/${100}"/>" class="filter-link stext-106 trans-04">
                                    $50.00 - $100.00
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="<c:url value="/shop/filter/${100}/${150}"/>" class="filter-link stext-106 trans-04">
                                    $100.00 - $150.00
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="<c:url value="/shop/filter/${150}/${200}"/>" class="filter-link stext-106 trans-04">
                                    $150.00 - $200.00
                                </a>
                            </li>

                            <li class="p-b-6">
                                <a href="<c:url value="/shop/filter/${200}/${100000}"/>" class="filter-link stext-106 trans-04">
                                    $200.00+
                                </a>
                            </li>
                        </ul>
                    </div>

                    <div class="filter-col3 p-r-15 p-b-27">
                        <div class="mtext-102 cl2 p-b-15">
                            Color
                        </div>

                        <ul>
                            <li class="p-b-6">
									<span class="fs-15 lh-12 m-r-6" style="color: #222;">
										<i class="zmdi zmdi-circle"></i>
									</span>

                                <a href="<c:url value="/shop/filter/Black"/>" class="filter-link stext-106 trans-04">
                                    Black
                                </a>
                            </li>

                            <li class="p-b-6">
									<span class="fs-15 lh-12 m-r-6" style="color: #4272d7;">
										<i class="zmdi zmdi-circle"></i>
									</span>

                                <a href="<c:url value="/shop/filter/Blue"/>" class="filter-link stext-106 trans-04 filter-link-active">
                                    Blue
                                </a>
                            </li>

                            <li class="p-b-6">
									<span class="fs-15 lh-12 m-r-6" style="color: #b3b3b3;">
										<i class="zmdi zmdi-circle"></i>
									</span>

                                <a href="<c:url value="/shop/filter/Grey"/>" class="filter-link stext-106 trans-04">
                                    Grey
                                </a>
                            </li>

                            <li class="p-b-6">
									<span class="fs-15 lh-12 m-r-6" style="color: yellow;">
										<i class="zmdi zmdi-circle"></i>
									</span>

                                <a href="<c:url value="/shop/filter/Yellow"/>" class="filter-link stext-106 trans-04">
                                    Yellow
                                </a>
                            </li>

                            <li class="p-b-6">
									<span class="fs-15 lh-12 m-r-6" style="color: #fa4251;">
										<i class="zmdi zmdi-circle"></i>
									</span>

                                <a href="<c:url value="/shop/filter/Red"/>" class="filter-link stext-106 trans-04">
                                    Red
                                </a>
                            </li>

                            <li class="p-b-6">
									<span class="fs-15 lh-12 m-r-6" style="color: #aaa;">
										<i class="zmdi zmdi-circle-o"></i>
									</span>

                                <a href="<c:url value="/shop/filter/White"/>" class="filter-link stext-106 trans-04">
                                    White
                                </a>
                            </li>
                        </ul>
                    </div>

                    <div class="filter-col4 p-b-27">
                        <div class="mtext-102 cl2 p-b-15">
                            Tags
                        </div>

                        <div class="flex-w p-t-4 m-r--5">
                            <a href="/shop/search?searchInput=Cotton" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
                                Cotton
                            </a>

                            <a href="/shop/search?searchInput=Short Sleeve" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
                                Short Sleeve
                            </a>

                            <a href="/shop/search?searchInput=Wide Form" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
                                Wide Form
                            </a>

                            <a href="/shop/search?searchInput=Sport" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">
                                Sport
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <c:choose>
            <c:when test="${productListP.hasContent()}">
                <div class="row">
                    <!-- single product -->
                    <c:forEach items="${productListP.content}" var="p">
                        <div class="col-lg-3 col-md-4 col-sm-6">
                            <!-- Block2 -->
                            <div class="block2">
                                <div class="block2-pic hov-img0">
                                    <a href="<c:url value="/shop/view/${p.product.id}"/>">
                                        <img src="${p.imageUrl}" alt="${p.imageAlt}">
                                    </a>

                                    <!-- <a href="#" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">
                                        Quick View -->
                                    </a>
                                </div>

                                <div class="block2-txt flex-w flex-t p-t-10">
                                    <div class="block2-txt-child1 flex-col-l ">
                                        <a href="/shop/view/${p.product.id}" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                                ${p.product.name}
                                        </a>

                                        <span class="stext-105 cl3 price">
									$${p.product.actual_price}
								</span>
                                    </div>

                                    <div class="block2-txt-child2 flex-r p-t-3">
                                        <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                                            <img class="icon-heart1 dis-block trans-04" src="/resources/images/icons/icon-heart-01.png" alt="ICON">
                                            <img class="icon-heart2 dis-block trans-04 ab-t-l" src="/resources/images/icons/icon-heart-02.png" alt="ICON">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
                <c:otherwise>
                    <div class="alert alert-warning" role="alert" style="text-align: center">
                        ${msg != null?msg:"Product is updating..."}
                    </div>
                </c:otherwise>
            </c:choose>

        <!-- Load more -->
        <c:if test="${productListP.hasContent()}">
            <div class="flex-c-m flex-w w-full p-t-45">
                    <%--            <a href="#" class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04">--%>
                    <%--                Load More--%>
                    <%--            </a>--%>
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${productListP.number == 0?"disabled":""}">
                            <a class="page-link " href="${url}p=${productListP.number - 1}" tabindex="-1">Previous</a>
                        </li>
                        <c:forEach begin="0" end="${productListP.totalPages - 1}" var="i">
                            <li class="page-item ${tagPages == i? "active":""}"><a class="page-link" href="${url}p=${i}">${i+1}</a></li>
                        </c:forEach>
                        <li class="page-item ${productListP.number == productListP.totalPages - 1?"disabled":""}">
                            <a class="page-link" href="${url}p=${productListP.number + 1}">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </c:if>
    </div>
</div>


<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>


<!-- Back to top -->
<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
</div>

<!-- Modal1 -->
<div class="wrap-modal1 js-modal1 p-t-60 p-b-20">
    <div class="overlay-modal1 js-hide-modal1"></div>

    <div class="container">
        <div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
            <button class="how-pos3 hov3 trans-04 js-hide-modal1">
                <img src="/resources/images/icons/icon-close.png" alt="CLOSE">
            </button>

<%--            <div class="row">--%>
<%--                <div class="col-md-6 col-lg-7 p-b-30">--%>
<%--                    <div class="p-l-25 p-r-30 p-lr-0-lg">--%>
<%--                        <div class="wrap-slick3 flex-sb flex-w">--%>
<%--                            <div class="wrap-slick3-dots"></div>--%>
<%--                            <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>--%>

<%--                            <div class="slick3 gallery-lb">--%>
<%--                                <c:forEach items="${productImageEntityList}" var="i">--%>
<%--                                    <div class="item-slick3" data-thumb="${i.imageUrl}">--%>
<%--                                        <div class="wrap-pic-w pos-relative">--%>
<%--                                            <img src="${i.imageUrl}" alt="${i.imageAlt}">--%>

<%--                                            <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="${i.imageUrl}">--%>
<%--                                                <i class="fa fa-expand"></i>--%>
<%--                                            </a>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="col-md-6 col-lg-5 p-b-30">--%>
<%--                    <div class="p-r-50 p-t-5 p-lr-0-lg">--%>
<%--                        <h4 class="mtext-105 cl2 js-name-detail p-b-14">--%>
<%--                            <c:out value="${productEntity.get().name}"/>--%>
<%--                        </h4>--%>

<%--                        <span class="mtext-106 cl2">--%>
<%--							$<c:out value="${productEntity.get().actual_price}"/>--%>
<%--						</span>--%>

<%--                        <p class="stext-102 cl3 p-t-23">--%>
<%--                            <c:out value="${productEntity.get().description}"/>--%>
<%--                        </p>--%>

<%--                        <!--  -->--%>
<%--                        <div class="p-t-33">--%>
<%--                            <div class="flex-w flex-r-m p-b-10">--%>
<%--                                <div class="size-203 flex-c-m respon6">--%>
<%--                                    Size--%>
<%--                                </div>--%>

<%--                                <div class="size-204 respon6-next">--%>
<%--                                    <div class="rs1-select2 bor8 bg0">--%>
<%--                                        <select id="size" class="js-select2" name="size" required>--%>
<%--                                            <option>Select an option</option>--%>
<%--                                            <c:forEach items="${productSizeList}" var="size">--%>
<%--                                                <option value="${size}">${size}</option>--%>
<%--                                            </c:forEach>--%>
<%--                                        </select>--%>
<%--                                        <div class="dropDownSelect2"></div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <div class="flex-w flex-r-m p-b-10">--%>
<%--                                <div class="size-203 flex-c-m respon6">--%>
<%--                                    Color--%>
<%--                                </div>--%>

<%--                                <div class="size-204 respon6-next">--%>
<%--                                    <div class="rs1-select2 bor8 bg0">--%>
<%--                                        <select id="color" class="js-select2" name="color">--%>
<%--                                            <option>Select an option</option>--%>
<%--                                            <c:forEach items="${productColorList}" var="color">--%>
<%--                                                <option value="${color}">${color}</option>--%>
<%--                                            </c:forEach>--%>
<%--                                        </select>--%>
<%--                                        <div class="dropDownSelect2"></div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <div class="flex-w flex-r-m p-b-10">--%>
<%--                                <div class="size-204 flex-w flex-m respon6-next">--%>
<%--                                    <div class="wrap-num-product flex-w m-r-20 m-tb-10">--%>
<%--                                        <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">--%>
<%--                                            <i class="fs-16 zmdi zmdi-minus"></i>--%>
<%--                                        </div>--%>

<%--                                        <input class="mtext-104 cl3 txt-center num-product" min="1" type="number" name="num-product" value="1" id="quantity_change_input">--%>

<%--                                        <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">--%>
<%--                                            <i class="fs-16 zmdi zmdi-plus"></i>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>

<%--                                    &lt;%&ndash;                                <button id="add2cart_btn" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    Add to cart&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                </button>&ndash;%&gt;--%>
<%--                                    <a href="#" id="add2cart_btn" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">ADD TO CARD</a>--%>
<%--                                    <span hidden id="product_id"><c:out value="${productEntity.get().id}"/></span>--%>
<%--                                    <span hidden id="product_image"><c:out value="${productEntity.get().mainProductImageURL}"/></span>--%>
<%--                                    <span hidden id="product_title"><c:out value="${productEntity.get().name}"/></span>--%>
<%--                                    <span hidden id="product_price"><c:out value="${productEntity.get().actual_price}"/></span>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <!--  -->--%>
<%--                        <div class="flex-w flex-m p-l-100 p-t-40 respon7">--%>
<%--                            <div class="flex-m bor9 p-r-10 m-r-11">--%>
<%--                                <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">--%>
<%--                                    <i class="zmdi zmdi-favorite"></i>--%>
<%--                                </a>--%>
<%--                            </div>--%>

<%--                            <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">--%>
<%--                                <i class="fa fa-facebook"></i>--%>
<%--                            </a>--%>

<%--                            <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">--%>
<%--                                <i class="fa fa-twitter"></i>--%>
<%--                            </a>--%>

<%--                            <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">--%>
<%--                                <i class="fa fa-google-plus"></i>--%>
<%--                            </a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
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
    $('.js-addwish-b2, .js-addwish-detail').on('click', function(e){
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
