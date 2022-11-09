jQuery(document).ready(function($) {
    $("#submitCommentForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        submitComment();
    });

    // $('#username, #content').change(function(){
    //     if($("#username").val().length  && $("#content").val().length){
    //         $("#register").prop('disabled', false);
    //     } else {
    //         $("#register").prop('d   isabled', true);
    //     }
    // });
});

function submitComment() {
    var comment = $("#username").val() + "," + $("#content").val() + "," + $("#rating").val();

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/comment/submit",
        data : comment,
        timeout : 100000,
        success : function(data) {
            console.log(data);
            display(data);
        }
    });
}

function display(data) {
    var comment = data.split('||');
    // comment[2]: rating, 1 -> 1 star, 2 = 2 star and so on
    var newComment = "<div class='form-group'>"
        + "<label>" +comment[0] + "</label>"
        + "<input type='text' class='form-control' placeholder='" + comment[1] + "' readonly>"
        + "</div>";
    $('#commentList').append(newComment);
}