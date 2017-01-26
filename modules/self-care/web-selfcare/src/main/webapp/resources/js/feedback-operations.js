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
            starWidth: "20px",
            fullStar: true
        });
        $("#rateYo").rateYo("option", "rating", 1);
    });

    $(document.body).on('click', '.btn-viewfeedback', function () {
        var itemName = $(this).closest('div').find('span').text();

        $("#rateYo").rateYo("option", "rating", 3);
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
                        "comment": $("#feedback-comment").val(),
                        "numberOfStars": rating,
                        "itemName": itemName
                    },
                    success: function (statusCode) {
                        if (statusCode == 201) {
                            $.notify("Feedback added successfully.", {
                                align: "center",
                                verticalAlign: "top",
                                delay: 3000,
                                animationType: "fade",
                                color: "#fff",
                                background: "#00B300"
                            });
                            $("#modal-addFeedback").modal('hide');
                        } else {
                            $.notify("Error adding the feedback.", {
                                align: "center",
                                verticalAlign: "top",
                                delay: 3000,
                                animationType: "fade",
                                color: "#fff",
                                background: "#D44950"
                            });
                        }
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: "feedbacks/update",
                    data: {
                        "comment": $("#feedback-comment").val(),
                        "numberOfStars": rating,
                        "itemName": itemName
                    },
                    success: function (statusCode) {
                        if (statusCode == 201) {
                            $.notify("Feedback updated successfully.", {
                                align: "center",
                                verticalAlign: "top",
                                delay: 3000,
                                animationType: "fade",
                                color: "#fff",
                                background: "#00B300"
                            });
                            $("#modal-addFeedback").modal('hide');
                        } else {
                            $.notify("Error updating the feedback.", {
                                align: "center",
                                verticalAlign: "top",
                                delay: 3000,
                                animationType: "fade",
                                color: "#fff",
                                background: "#D44950"
                            });
                        }
                    }
                });
            }
        });
    });
});