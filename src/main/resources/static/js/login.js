$(document).ready(function() {
    $('#login-registration').submit(function (event) {
        var login = $('#login').val();
        var password = $('#password').val();
        var fio = $('#fio').val();
        var json = {"login": login, "password": password, "fio": fio};

        $.ajax({
            url: $('#login-registration').attr("action"),
            data: JSON.stringify(json),
            dataType: 'json',
            type: "POST",
            success: function (data) {
//                      console.log("SUCCESS: ", data);
                window.location.replace("/login?registered");
            },
            error: function (data) {
//                      console.log("ERROR: ", data);
                if (data.responseText === 'exist') {
                    $('#error-message').css("display", "block");
                }
            },

            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            }
        });
        event.preventDefault();
    });

    window.history.pushState("", "", "/login");
    validateForm('#registration-form');
});

function validateForm(id) {
    $(id).validate({
        rules: {
            login: {
                required: true,
                minlength: 3,
                maxlength: 12
            },
            password: {
                required: true,
                minlength: 5,
                maxlength: 15
            },
            fio: {
                required: true,
                minlength: 5,
                maxlength: 35
            }
        },
        messages: {
            login: {
                required: "Please enter a login",
                minlength: "Your login must consist of at least 3 characters",
                maxlength: "Your login must consist of maximum 12 characters"
            },
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 5 characters long",
                maxlength: "Your password must consist of maximum 15 characters"
            },
            fio: {
                required: "Please provide a fio",
                minlength: "Your fio must be at least 5 characters long",
                maxlength: "Your full name must consist of maximum 35 characters"
            }
        },
        errorElement: "em",
        errorPlacement: function (error, element) {
            // Add the `help-block` class to the error element
            error.addClass("help-block");

            // Add `has-feedback` class to the parent div.form-group
            // in order to add icons to inputs
            element.parents(".err").addClass("has-feedback");

            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.parent("label"));
            } else {
                error.insertAfter(element);
            }

            // Add the span element, if doesn't exists, and apply the icon classes to it.
            if (!element.next("span")[0]) {
                $('<span class="glyphicon glyphicon-remove form-control-feedback"></span>').insertAfter(element);
            }
        },
        success: function (label, element) {
            // Add the span element, if doesn't exists, and apply the icon classes to it.
            if (!$(element).next("span")[0]) {
                $('<span class="glyphicon glyphicon-ok form-control-feedback"></span>').insertAfter($(element));
            }
        },
        highlight: function (element, errorClass, validClass) {
            $(element).parents(".err").addClass("has-error").removeClass("has-success");
            $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).parents(".err").addClass("has-success").removeClass("has-error");
            $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
        }
    });
}

