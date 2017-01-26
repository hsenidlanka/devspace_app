var $rateYo;
$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "feedbacks/purchased-items",
        success: function (response) {
            $("#feedback-content-div").html(response);
        }
    });

    $(function () {
        $rateYo = $("#rateYo").rateYo({
            starWidth: "30px",
            fullStar: true
        });
        $("#rateYo").rateYo("option", "rating", 1);
    });

    $(document.body).on('click', '.btn-viewfeedback', function () {
        var itemName = $(this).closest('div').find('span').text();

        $("#feedback-comment").text("");
        $("#rateYo").rateYo("option", "rating", 3);

        $("#lbl-feedbackSuccess").hide();
        $("#lbl-feedbackFailed").hide();
        $("#modal-addFeedback").modal('show');
        $.ajax({
            type: "GET",
            url: "feedbacks/get",
            data: {"itemName": itemName},
            dataType: "json",
            success: function (response) {
                $.each(response, function (idx, obj) {
                    $("#feedback-comment").text(obj['comment']);
                    if (obj['comment'].length > 0) {
                        $("#btn-add-feedback-ok").text("Update");
                    } else {
                        $("#btn-add-feedback-ok").text("Add");
                    }
                    $("#rateYo").rateYo("option", "rating", obj['stars']);
                });
            }
        });

        $("#btn-add-feedback-ok").off('click');
        $("#btn-add-feedback-ok").click(function () {
            var rating = $rateYo.rateYo("rating");
            if ($("#btn-add-feedback-ok").text() === "Add") {
                $.ajax({
                    type: "POST",
                    url: "feedbacks/add",
                    data: {
                        "comment": $("#feedback-comment").text(),
                        "numberOfStars": rating,
                        "itemName": itemName
                    },
                    success: function (statusCode) {
                        if (statusCode == 201) {
                            $("#lbl-feedbackSuccess").show();
                            $("#lbl-feedbackFailed").hide();
                        } else {
                            $("#lbl-feedbackFailed").show();
                            $("#lbl-feedbackSuccess").hide();
                        }
                    }
                });
            } else {
                alert("noo");
            }
        });
    });
});