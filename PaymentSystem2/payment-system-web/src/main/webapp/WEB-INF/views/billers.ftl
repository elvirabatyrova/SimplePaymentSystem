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
        <div class="col-md-12">
            <table class="table table-striped table-hover ">
                <thead>
                <tr>
                    <th>Company Name</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#list billers as biller>
                <tr>
                    <td>${biller.companyName}</td>
                    <td>
                        <form class="update-delete-btn" action="/deleteBiller?id=${biller.id}" method="post">
                            <input type="submit" value="Delete" />
                        </form>
                        <a class="update-btn" href="/saveBiller?id=${biller.id}"><button type="button" class="update-delete-btn">Change
                        </button></a>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
            <a href="/saveBiller"><button type="button" class="btn btn-info">New Biller</button></a>
        </div>
    </div>
</div>
</body>

</html>
