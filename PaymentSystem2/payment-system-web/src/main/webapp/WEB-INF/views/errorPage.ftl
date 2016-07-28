<html>

<head>
    <title>Payments List</title>
    <meta charset="utf-8">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/c3.min.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/d3.min.js"></script>
    <script src="/resources/js/c3.min.js"></script>
    <script src="/resources/js/updater.js"></script>
    <script src="/resources/js/project.js"></script>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1>Mega Inc</h1>
        </div>
        <div class="col-md-6 menu">
            <div class="btn-group btn-group-justified menu">
                <a href="/" class="btn
                <#if payments ??>
                    btn-info
                <#else>
                    btn-default
                </#if>
                ">Payments</a>
                <a href="/customers" class="btn
                <#if payments ??>
                    btn-default
                <#else>
                    <#if customers ??>
                        btn-info
                    <#else>
                        btn-default
                    </#if>
                </#if>
                ">Customers</a>
                <a href="/billers" class="btn
                <#if payments ??>
                    btn-default
                <#else>
                    <#if billers ??>
                        btn-info
                    <#else>
                        btn-default
                    </#if>
                </#if>
                ">Billers</a>
            </div>
        </div>
    </div>
    <div class="row">
       <h4 class="error">${error}</h4>
    </div>
</div>
<script type="application/javascript">

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

</script>
</body>

</html>
