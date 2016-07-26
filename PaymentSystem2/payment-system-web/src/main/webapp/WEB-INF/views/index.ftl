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
            <div class="col-md-12">
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">Brand</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                                <li><a href="#">Link</a></li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">One more separated link</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                                <button type="submit" class="btn btn-default">Submit</button>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="#">Link</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <h1>Payments</h1>
                <table class="table table-striped table-hover ">
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
