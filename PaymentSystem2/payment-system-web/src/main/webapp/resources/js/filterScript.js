$(document).ready(function() {
    f = function(request, response) {

        $.ajax({
            url: "/payment_filter",
            data: {"q1": $("#customer_filter").val(), "q2": $("#biller_filter").val()},
            dataType: "json",
            success: function(resp) {
                console.debug(resp);
                if (resp.results.length > 0) {
                    $("#res").text("");
                    $("#res").append("<thead><tr><th>Date</th><th>Customer</th><th>Biller</th>" +
                        "<th>Account</th><th>Amount</th></tr></thead><tbody>")
                    for (var i = 0; i < resp.results.length; i++) {
                        $("#res").append("<tr><td>" + resp.results[i].paymentDate +
                            "</td><td> " + resp.results[i].customer.firstName + " " +
                            resp.results[i].customer.secondName + "</td><td>" +
                            resp.results[i].biller.companyName + "</td><td>" + resp.results[i].account +
                            "</td><td>" + resp.results[i].amount + "</td></tr>");

                    }
                    $("#res").append("</tbody>");
                } else {
                    $("#res").text("No results.")
                }
            }
        })

    }

});