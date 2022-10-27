jQuery(document).ready(function($) {
    $('#add2cart_btn').click(function(e) {
        e.preventDefault();
        if($("#size :selected").text() == 'Select an option' || $("#color :selected").text() == 'Select an option' ){
            Swal.fire(
                'Size and Color?',
                'Please, select an option!',
                'question'
            );
        } else {
            const product = $("#product_id").text()
                + ",," + $("#product_image").text()
                + ",," + $("#product_title").text()
                + ",," + $("#product_price").text()
                + ",," + $("#product_size").text()
                + ",," + $("#product_color").text()
                + ",," + $("#quantity_change_input").val();


            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/add2cart",
                data : product,
                timeout : 100000,
                success : function(data) {
                    location.reload();
                    if (data == "0") {
                        // do nothing, not update the cart badge / number
                        swal($("#product_title").text(), "is added to cart !", "success");
                    } else if (data == "1") {
                        // there is a new item has been added to cart, therefore need update the cart badge by 1
                        //$("#cart_item_quantity").text(Number($("#cart_item_quantity").html()) + 1);
                        $("#cart_number").attr("data-notify", Number($("#cart_number").attr("data-notify")) + 1);
                        swal($("#product_title").text(), "is added to cart !", "success");
                    } else {
                        // alert("Oops, there is something wrong - please try again later!")
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Something went wrong - please try again later!'
                        })
                    }
                }
            });
        }


    });
});