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
    <script type="text/javascript" src="/resources/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/resources/js/payment_validation.js"></script>
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
        <div class="col-md-12">
            <h3>New Payment</h3>
            <form id="payment_form" action="/savePayment" method="post">
                <label>Customer* </label>
                <select name="customer">
                    <#list customers as customer>
                        <option>[${customer.id}] ${customer.firstName} ${customer.secondName}</option>
                    </#list>
                </select><br/>
                <label>Biller* </label>
                <select name="biller">
                    <#list billers as biller>
                        <option>[${biller.id}] ${biller.companyName}</option>
                    </#list>
                </select><br/>
                <label>Account </label>
                <input type="text" name="account" /><br/>
                <label>Amount* </label>
                <input type="text" name="amount" /><br/>
                <input class="btn btn-primary" type="submit" value="Save" />
            </form>
        </div>
    </div>
</div>
</body>

</html>
