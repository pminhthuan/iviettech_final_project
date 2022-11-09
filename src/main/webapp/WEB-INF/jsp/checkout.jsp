<%--
  Created by IntelliJ IDEA.
  User: Thuan
  Date: 10/21/22
  Time: 11:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Checkout</title>
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
  <link rel="stylesheet" type="text/css" href="/resources/css/custom.css">
  <!--===============================================================================================-->
  <link href="/resources/vendor/bootstrap/css/bootstrap.min.02.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="/resources/css/form-validation.css" rel="stylesheet">
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
                Checkout
    </span>
  </div>
</div>


<!-- Checkout -->
<div class="container">
  <div class="row checkout-form">
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
        <div class="col-md-4 order-md-2 mb-4">
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-muted">Your cart</span>
                <span class="badge badge-secondary badge-pill"><c:out value="${sessionScope.shopping_cart.size()}"/> items</span>
          </h4>
          <ul class="list-group mb-3">
            <c:forEach items="${sessionScope.shopping_cart}" var="item">
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                <div>
                  <h6 class="my-0">${item.title}</h6>
                  <small class="text-muted">${item.quantity} x ${item.price}, ${item.color}, ${item.size}</small>
                </div>
                <span class="text-muted">$${item.totalPriceInNumber}</span>
              </li>
            </c:forEach>
            <li class="list-group-item d-flex justify-content-between bg-light">
              <div class="text-success">
                <h6 class="my-0">Promo code</h6>
                  <%--            <small>EXAMPLECODE</small>--%>
              </div>
              <span class="text-success">-$0</span>
            </li>
            <li class="list-group-item d-flex justify-content-between">
              <span>Total (USD)</span>
              <strong><span id="total_price_cart">$<c:out value="${sessionScope.total_price_in_cart}"/></span></strong>
            </li>
          </ul>

          <form class="card p-2">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="Promo code">
              <div class="input-group-append">
                <button type="submit" class="btn btn-secondary">Redeem</button>
              </div>
            </div>
          </form>
        </div>
        <div class="col-md-8 order-md-1">
          <h4 class="mb-3">Billing address</h4>
          <form:form action="checkout" method="post" modelAttribute="order" class="needs-validation" novalidate="true">
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="firstName">First name</label>
                <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="" value="${sessionScope.user.firstName}" required="true"/>
                <div class="invalid-feedback">
                  Valid first name is required.
                </div>
              </div>
              <div class="col-md-6 mb-3">
                <label for="lastName">Last name</label>
                <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="" value="${sessionScope.user.lastName}" required="true"/>
                <div class="invalid-feedback">
                  Valid last name is required.
                </div>
              </div>
            </div>

            <div class="mb-3">
              <label for="phoneNumber">Phone number</label>
              <div class="input-group">
                <form:input path="phoneNumber"  type="text" class="form-control" id="phoneNumber" value="${sessionScope.user.phoneNumber}" placeholder="0905545462" required="true"/>
                <div class="invalid-feedback" style="width: 100%;">
                  Your phone number is required.
                </div>
              </div>
            </div>

            <div class="mb-3">
              <label for="email">Email <span class="text-muted">(Optional)</span></label>
              <form:input path="email" type="email" class="form-control" id="email" value="${sessionScope.user.email}" placeholder="example@gmail.com"/>
              <div class="invalid-feedback">
                Please enter a valid email address for shipping updates.
              </div>
            </div>

            <div class="row">
              <div class="col-md-4 mb-3">
                <label for="province">Province</label>
                <form:select path="province.id" class="custom-select d-block w-100" id="province" value="${sessionScope.user.province.id}" required="true">
                  <form:option value='-1'>Select an option</form:option>
                  <form:options items="${province}"/>
                </form:select>
                <div class="invalid-feedback">
                  Please select a valid province.
                </div>
              </div>
              <div class="col-md-4 mb-3">
                <label for="district">District</label>
                <form:select path="district.id" class="custom-select d-block w-100" id="district" value="${sessionScope.user.district.id}" required="true">
                </form:select>
                <div class="invalid-feedback">
                  Please provide a valid district.
                </div>
              </div>
              <div class="col-md-4 mb-3">
                <label for="ward">Ward</label>
                <form:select path="ward.id" class="custom-select d-block w-100" id="ward" value="${sessionScope.user.ward.id}" required="true">
                </form:select>
                <div class="invalid-feedback">
                  Please provide a valid ward.
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="address">Address detail</label>
              <form:input path="addressDetail" type="text" class="form-control" id="address" value="${sessionScope.user.addressDetail}" placeholder="234 Hang Ma St or Phuong Nam Village" required="true"/>
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>
            <hr class="mb-4">
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="same-address">
              <label class="custom-control-label" for="same-address">Shipping address is the same as my
                billing
                address</label>
            </div>
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="save-info">
              <label class="custom-control-label" for="save-info">Save this information for next time</label>
            </div>
            <hr class="mb-4">

            <h4 class="mb-3">Payment</h4>

            <div class="d-block my-3">
              <div class="custom-control custom-radio">
                <form:radiobutton path="paymentMethod" value="COD" id="cod" title="paymentMethod" cssClass="custom-control-input" checked="true"
                                  required="true"></form:radiobutton>
                  <%--            <form:input path="paymentMethod" id="cod" value="COD" name="paymentMethod" type="radio" class="custom-control-input" checked="true"--%>
                  <%--                   required="true"/>--%>
                <label class="custom-control-label" for="cod">COD</label>
              </div>
              <div class="custom-control custom-radio">
                <form:radiobutton path="paymentMethod" value="Credit card" id="credit" title="paymentMethod" cssClass="custom-control-input"
                                  required="true"></form:radiobutton>
                  <%--            <form:input path="paymentMethod" id="credit" value="Credit card" name="paymentMethod" type="radio" class="custom-control-input" --%>
                  <%--                        required="true"/>--%>
                <label class="custom-control-label" for="credit">Credit card</label>
              </div>
              <div class="custom-control custom-radio">
                <form:radiobutton path="paymentMethod" value="Paypal" id="paypal" title="paymentMethod" cssClass="custom-control-input"
                                  required="true"></form:radiobutton>
                  <%--            <form:input path="paymentMethod" id="paypal" value="Paypal" name="paymentMethod" type="radio" class="custom-control-input" --%>
                  <%--                        required="true"/>--%>
                <label class="custom-control-label" for="paypal">PayPal</label>
              </div>
            </div>


            <div class="m-1" id="paypal-success" style="display: none";>
              <div class="alert alert-success alert-dismissible fade show">
                <strong>Success!</strong>
                <span> Your order has been purchased successfully. Thank you for your payment!</span> <br>
                <span id="paypal-transaction-id"> Transaction ID: </span>
              </div>
            </div>
            <!-- Replace with your own sandbox Business account app client ID -->
            <script src="https://www.paypal.com/sdk/js?client-id=ATi1AbbuU-e1J1_UP4KvtvtboUG1adhcX9-19g40-MWOsAv5FSJkNAuWo4y5_hdSymXhDOfuoDJN6_T0&currency=USD" data-namespace="paypal_sdk"></script>
            <!-- Set up a container element for the button -->
            <div id="paypal-button-container"></div>
            <script>
              paypal_sdk.Buttons({
                // Sets up the transaction when a payment button is clicked
                createOrder: (data, actions) => {
                  let total_price = $("#total_price_cart").html();
                  total_price = Number(total_price.substr(total_price.indexOf("$") + 1));
                  return actions.order.create({
                    purchase_units: [{
                      amount: {
                        value: total_price
                      }
                    }]
                  });
                },
                // Finalize the transaction after payer approval
                onApprove: (data, actions) => {
                  return actions.order.capture().then(function(orderData) {
                    // Successful capture! For dev/demo purposes:
                    console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));
                    const transaction = orderData.purchase_units[0].payments.captures[0];
                    //alert(`Transaction ${transaction.status}: ${transaction.id}\n\nSee console for all available details`);
                    $("#paypal-button-container").hide();
                    $("#paypal-success").show();
                    $("#paypal-transaction-id").text(transaction.id);

                  });
                }
              }).render('#paypal-button-container');
            </script>


            <%--        <div class="row">--%>
            <%--          <div class="col-md-6 mb-3">--%>
            <%--            <label for="cc-name">Name on card</label>--%>
            <%--            <input type="text" class="form-control" id="cc-name" placeholder="" required>--%>
            <%--            <small class="text-muted">Full name as displayed on card</small>--%>
            <%--            <div class="invalid-feedback">--%>
            <%--              Name on card is required--%>
            <%--            </div>--%>
            <%--          </div>--%>
            <%--          <div class="col-md-6 mb-3">--%>
            <%--            <label for="cc-number">Credit card number</label>--%>
            <%--            <input type="text" class="form-control" id="cc-number" placeholder="" required>--%>
            <%--            <div class="invalid-feedback">--%>
            <%--              Credit card number is required--%>
            <%--            </div>--%>
            <%--          </div>--%>
            <%--        </div>--%>
            <%--        <div class="row">--%>
            <%--          <div class="col-md-3 mb-3">--%>
            <%--            <label for="cc-expiration">Expiration</label>--%>
            <%--            <input type="text" class="form-control" id="cc-expiration" placeholder="" required>--%>
            <%--            <div class="invalid-feedback">--%>
            <%--              Expiration date required--%>
            <%--            </div>--%>
            <%--          </div>--%>
            <%--          <div class="col-md-3 mb-3">--%>
            <%--            <label for="cc-cvv">CVV</label>--%>
            <%--            <input type="text" class="form-control" id="cc-cvv" placeholder="" required>--%>
            <%--            <div class="invalid-feedback">--%>
            <%--              Security code required--%>
            <%--            </div>--%>
            <%--          </div>--%>
            <%--        </div>--%>
            <hr class="mb-4">
            <form:hidden path="totalAmount" value="${sessionScope.total_price_in_cart}"></form:hidden>
            <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
          </form:form>
        </div>
      </c:when>
      </c:choose>

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
  $(".js-select2").each(function () {
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
  $('.js-pscroll').each(function () {
    $(this).css('position', 'relative');
    $(this).css('overflow', 'hidden');
    var ps = new PerfectScrollbar(this, {
      wheelSpeed: 1,
      scrollingThreshold: 1000,
      wheelPropagation: false,
    });

    $(window).on('resize', function () {
      ps.update();
    })
  });
</script>
<!--===============================================================================================-->
<script src="/resources/js/main.js"></script>
<!--===============================================================================================-->
<script src="https://code.jquery.com/jquery-3.5.1.slim.js" integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM=" crossorigin="anonymous"></script>
<%--<script>window.jQuery || document.write('<script src="../assets/js//resources/vendor/jquery.slim.min.js"><\/script>')</script>--%>
<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.02.js"></script>
<script src="/resources/js/form-validation.js"></script>
<!--===============================================================================================-->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $("#province").change(function() {
      var provinceId = $(this).val();
      var s = '<option value=' + -1 + '>Select an option</option>';
      if (provinceId > 0) {
        $.ajax({
          url : 'getDistricts',
          data : { "provinceId" : provinceId },
          success : function(result) {
            var result = JSON.parse(result);
            for (var i = 0; i < result.length; i++) {
              s += '<option value="' + result[i][0] + '">'+ result[i][1]+ '</option>';
            }
            $('#district').html(s);
          }
        });
      }
      //reset data
      $('#district').html(s);
      $('#ward').html(s);

    });

    $("#district").change(function() {
      var districtId = $(this).val();
      var s = '<option value=' + -1 + '>Select an option</option>';
      if (districtId > 0) {
        $.ajax({
          url : 'getWards',
          data : {"districtId" : districtId},
          success : function(result) {
            var result = JSON.parse(result);
            for (var i = 0; i < result.length; i++) {
              s += '<option value="' + result[i][0] + '">'+ result[i][1]+ '</option>';
            }
            $('#ward').html(s);
          }
        });
      }
      //reset data
      $('#ward').html(s);
    });
  });
</script>
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
      }, 10000);
    });
  });
</script>
<!--===============================================================================================-->
</body>

</html>