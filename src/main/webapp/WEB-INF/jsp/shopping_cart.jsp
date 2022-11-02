<%--
  Created by IntelliJ IDEA.
  User: Thuan
  Date: 10/19/22
  Time: 7:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shopping Cart</title>
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
    <link rel="stylesheet" type="text/css" href="/resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/css/util.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/custom.css">
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
				Shopping Cart
			</span>
    </div>
</div>


<!-- Shopping Cart -->
<div class="container">
    <div class="site-section">
        <div class="container">
            <c:choose>
                <c:when test="${sessionScope.shopping_cart.size() < 1 || sessionScope.shopping_cart == null}">
                    <div class="card-body cart">
                        <div class="col-sm-12 empty-cart-cls text-center">
                            <img src="/resources/images/icons/icon_empty_cart.png" width="130" height="130" class="img-fluid mb-4 mr-3">
                            <h3><strong>Your Cart is Empty</strong></h3>
<%--                            <h4>Add something to make me happy :)</h4>--%>
                            <a href="/shop" class="btn btn-primary cart-btn-transform m-3 h3" data-abc="true">Continue shopping</a>
                        </div>
                    </div>
                </c:when>
                <c:when test="${sessionScope.shopping_cart.size() >= 1}">
                    <div class="row mb-5">
                        <form class="col-md-12" method="post">
                            <div class="site-blocks-table tb-item">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th class="product-thumbnail">Image</th>
                                        <th class="product-name">Product</th>
                                        <th class="product-size">Size</th>
                                        <th class="product-color">Color</th>
                                        <th class="product-price">Price</th>
                                        <th class="product-quantity">Quantity</th>
                                        <th class="product-total">Total</th>
                                        <th class="product-remove">Remove</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${shopping_cart_list}" var="item">
                                        <tr>
                                            <td class="product-thumbnail">
                                                <img src="${item.imgSource}" alt="Image" class="img-fluid">
                                            </td>
                                            <td class="product-name">
                                                <h2 class="h6 text-black">${item.title}</h2>
                                                <span class="product-Id" hidden style="white-space:nowrap">${item.productId}</>
                                                <span class="product-detail-Id" hidden style="white-space:nowrap">${item.productDetailId}</>
                                            </td>
                                            <td class="form-size-color">${item.size}</td>
                                            <td class="form-size-size">${item.color}</td>
                                            <td class="form-price">$${item.price}</td>
                                            <td class="form-quantity">
                                                <div class="wrap-num-product flex-w m-l-auto m-r-0">
                                                    <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                        <i class="fs-16 zmdi zmdi-minus"></i>
                                                    </div>

                                                    <input class="mtext-104 cl3 txt-center num-product" type="number"
                                                           name="num-product1" value="${item.quantity}">

                                                    <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                        <i class="fs-16 zmdi zmdi-plus"></i>
                                                    </div>
                                                </div>

                                            </td>
                                            <td class="form-price total">$${item.totalPriceInNumber}</td>
                                            <td class="form-remove">
                                                <a href="/delete/${item.productDetailId}" class="btn btn-primary" style="font-size: 0.8rem;">X</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>

                    <div class="row">
                        <div class="col-md-6 btn-coupon">
                            <div class="row mb-5">
                                <div class="col-md-6 mb-3 mb-md-0">
                                    <button class="btn btn-primary btn-sm btn-block">Update Cart</button>
                                </div>
                                <div class="col-md-6">
                                    <a href="/shop"><button class="btn btn-outline-primary btn-sm btn-block">Continue Shopping</button></a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label class="text-black h5" for="coupon">Coupon</label>
                                    <p>Enter your coupon code if you have one.</p>
                                </div>
                                <div class="col-md-8 mb-3 mb-md-0">
                                    <input type="text" class="form-control py-3 input-coupon" id="coupon"
                                           placeholder="Coupon Code">
                                </div>
                                <div class="col-md-4">
                                    <button class="coupon btn btn-primary btn-sm">Apply Coupon</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 pl-5">
                            <div class="row justify-content-end">
                                <div class="col-md-7 cart-total">
                                    <div class="row">
                                        <div class="col-md-12 text-right border-bottom mb-5">
                                            <h5 class="text-black h5 text-uppercase">Cart Totals</h5>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <span class="text-black">Subtotal</span>
                                        </div>
                                        <div class="col-md-6 text-right">
                                            <strong id="sub_total_sum" class="text-black">$<c:out value="${total_price_in_cart}"/></strong>
                                        </div>
                                    </div>
                                    <div class="row mb-5">
                                        <div class="col-md-6">
                                            <span class="text-black">Total</span>
                                        </div>
                                        <div class="col-md-6 text-right">
                                            <strong id="total_sum" class="text-black">$<c:out value="${total_price_in_cart}"/></strong>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                                <%-- <button class="btn-checkout btn btn-primary btn-lg py-3 btn-block"--%>
                                                <%-- onclick="window.location='/checkout'">Proceed To Checkout</button>--%>
                                            <a href="" id="check_out" <c:if test="${sessionScope.shopping_cart == null}"><c:out value="disabled='disabled'"/></c:if>>
                                                <button id="btn_check_out" class="btn-checkout btn btn-primary btn-lg py-3 btn-block">Proceed To Checkout</button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
            </c:choose>

        </div>
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
<script src="/resources/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
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


    /* Handle when reducing the product quantity in cart */
    $(".btn-num-product-down").click(function() {
        let oldValue = $(this).parent().parent().find('input').val();
        if (oldValue == 1) {
            return;
        } else {
            // Don't allow decrementing below zero
            if (oldValue > 1) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }

        let row = $(this).parent().parent().parent();
        let currentBasePrice = row.find('.form-price').html();
        // get the number part, e.g. $52 -> 52
        currentBasePrice = currentBasePrice.substr(currentBasePrice.indexOf("$") + 1);
        // update price for each item in cart
        row.find('.form-price.total').text('$' + Number(currentBasePrice * newVal));

        let currentSubTotal = $("#sub_total_sum").html();
        currentSubTotal = currentSubTotal.substr(currentSubTotal.indexOf("$") + 1);
        // update total & sub-total price
        $("#sub_total_sum").text('$' + Number(Number(currentSubTotal) - Number(currentBasePrice)));
        $("#total_sum").text($("#sub_total_sum").html());
    });

    /* Handle when increasing the product quantity in cart */
    $(".btn-num-product-up").click(function() {
        let oldValue = $(this).parent().parent().find('input').val();
        if (oldValue == 5) {
            return;
        } else {
            var newVal = parseFloat(oldValue) + 1;
        }

        let row = $(this).parent().parent().parent();
        let currentBasePrice = row.find('.form-price').html();
        // get the number part, e.g. $52 -> 52
        currentBasePrice = currentBasePrice.substr(currentBasePrice.indexOf("$") + 1);
        row.find('.form-price.total').text('$' + Number(currentBasePrice * newVal));

        let currentSubTotal = $("#sub_total_sum").html();
        currentSubTotal = currentSubTotal.substr(currentSubTotal.indexOf("$") + 1);
        $("#sub_total_sum").text('$' + Number(Number(currentSubTotal) + Number(currentBasePrice)));
        $("#total_sum").text($("#sub_total_sum").html());
    });


    // before check out button is clicked, update the HREF attribute of the <a> tag
    // the HRED includes the query param ?data which contains all productID & latest quantity pairs
    $("#btn_check_out").click(function(){
        $('#check_out').each(function() {
            let newHref = '';
            $(".table.table-bordered tbody tr").each(function() {
                newHref = newHref + $(this).find(".product-detail-Id").text().trim() + '_' +  $(this).find("input").val() + '__';
            });;
            // remove the last 2 characters __
            newHref = newHref.slice(0,-2);
            $(this).attr('href', '/checkout?data=' + newHref);
        });
    });
</script>
<!--===============================================================================================-->
<script src="/resources/js/main.js"></script>
<!--===============================================================================================-->
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $('.btn').on('click', function() {
            var $this = $(this);
            $this.button('loading');
            setTimeout(function() {
              $this.button('reset');
            }, 8000);
        });
    });
</script>
<!--===============================================================================================-->
</body>
</html>