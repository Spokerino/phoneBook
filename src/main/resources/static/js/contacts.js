// Begin Something modal population and submit functions
var url = "/contacts/";
var editModalTarget = url + "loadContact/";
var names = ['idContact', 'firstName', 'lastName', 'patronymic',
    'phoneMobile', 'phoneHome', 'address', 'email'];


// Build the url for the Ajax request for Something.
function showEditModal(index) {
    var editUrl = editModalTarget + index;
    loadEntity(editUrl);
}

// Ajax request for Something to populate the modal form.
function loadEntity(url) {
    $.getJSON(url, {}, function (data) {
        populateModal(data, names);
    });
}

// Assign the data values to the modal form.
function populateModal(data) {
    $('#id-update').val(data.idContact);
    $('#first-name-update').val(data.firstName);
    $('#last-name-update').val(data.lastName);
    $('#patronymic-update').val(data.patronymic);
    $('#mobile-update').val(data.phoneMobile);
    $('#home-phone-update').val(data.phoneHome);
    $('#address-update').val(data.address);
    $('#email-update').val(data.email);
    validateForm('#contact-form-edit');
}

function validateForm(id) {
    $(id).validate({
        rules: {
            firstName: {
                required: true,
                minlength: 4,
                maxlength: 30
            },
            lastName: {
                required: true,
                minlength: 4,
                maxlength: 30
            },
            patronymic: {
                required: true,
                minlength: 4,
                maxlength: 30
            },
            phoneMobile: {
                required: true,
                minlength: 10,
                maxlength: 15
            },
            phoneHome: {
                required: false,
                maxlength: 15
            },
            email: {
                required: false,
                maxlength: 25
            }
        },
        messages: {
            firstName: {
                required: "Please enter a first name",
                minlength: "Your first name must consist of at least 4 characters",
                maxlength: "Your first name must consist of maximum 30 characters"
            },
            lastName: {
                required: "Please provide a last name",
                minlength: "Your last name must be at least 4 characters long",
                maxlength: "Your last name must consist of maximum 30 characters"
            },
            patronymic: {
                required: "Please provide a patronymic",
                minlength: "Your patronymic must be at least 4 characters long",
                maxlength: "Your patronymic must consist maximum 30 characters"
            },
            phoneMobile: {
                required: "Please provide a number of your mobile phone",
                minlength: "Your mobile phone's number must be at least 10 characters long",
                maxlength: "Your mobile phone's number must consist of maximum 15 characters"
            },
            phoneHome: {
                maxlength: "Your mobile phone's number must consist of maximum 15 characters"
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