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
    <script src="/resources/js/filterScript.js"></script>
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
            <div class="col-md-3">
                <select id="customer_filter" onchange="f()">
                    <option selected>All Customers</option>
                    <#list customers as customer>
                        <option>[${customer.id}] ${customer.firstName} ${customer.secondName}</option>
                    </#list>
                </select>
            </div>
            <div class="col-md-3">
                <select id="biller_filter" onchange="f()">
                    <option selected>All Billers</option>
                    <#list billers as biller>
                        <option>[${biller.id}] ${biller.companyName}</option>
                    </#list>
                </select>
            </div>
            <div class="col-md-6">
                <button class="new-payment-btn" data-toggle="modal" data-target="#myModal">
                    Download payments from *.xml
                </button>
                <a class="new-payment-btn" href="/savePayment"><button>New Payment</button></a>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Download payments from *.xml</h4>
                            </div>
                            <div class="modal-body">
                                <form method="POST" action="/uploadFile" enctype="multipart/form-data">
                                    <input type="file" name="file"><br />

                                    <input class="btn btn-info" type="submit" value="Upload">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="res" class="table table-striped table-hover ">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Customer</th>
                        <th>Biller</th>
                        <th>Account</th>
                        <th>Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list payments as payment>
                    <tr>
                        <td>
                            <#if payment.paymentDate ??>
                                ${payment.paymentDate}
                            </#if>
                        </td>
                        <td>${payment.customer.firstName} ${payment.customer.secondName}</td>
                        <td>${payment.biller.companyName}</td>
                        <td>
                            <#if payment.account ??>
                                ${payment.account}
                            </#if>
                        </td>
                        <td>${payment.amount}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>

</html>
