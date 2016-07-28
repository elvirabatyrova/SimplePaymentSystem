$(document).ready(function() {
    $("#payment_form").validate({

        rules: {

            account: {
                regx: /^([0-9]{8,18})?$/
            },

            amount: {
                required: true,
                regx: /^[0-9]{0,7}([.][0-9]{0,2})?$/
            }

        },

        messages: {

            account: {
                regx: "It is a number from 8 to 18"
            },

            amount: {
                required: "This field is required",
                regx: "Wrong format. Rigth example: 54.66"
            }

        }

    });


    $.validator.addMethod("regx", function (value, element, regexpr) {
        return regexpr.test(value);
    }, "Please enter a valid pasword.");

});