$(document).ready(function() {
    $("#update_form").validate({

        rules: {

            dateOfBirth: {
                required: true,
                regx: /^(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.](19|20)\d\d$/
            },

            firstName: {
                required: true
            },

            secondName: {
                required: true
            },

            address: {
                required: true
            }

        },

        messages: {

            dateOfBirth: {
                required: "This field is required",
                regx: "Wrong format"
            },

            firstName: {
                required: "This field is required"
            },

            secondName: {
                required: "This field is required"
            },

            address: {
                required: "This field is required"
            }

        }

    });

    $.validator.addMethod("regx", function (value, element, regexpr) {
        return regexpr.test(value);
    }, "Please enter a valid pasword.");

});