<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta  charset="UTF-8"/>
    <title>爬虫首页</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row row-centered">
        <div class="well col-md-6 col-centered">
            <h2>欢迎使用爬虫小工具</h2>
            <form action="/xpath/getURL"  method="get"  role="form">
                <div class="input-group input-group-md">
                    <label class="sr-only" for="name">网页地址</label>
                    <input type="text" class="form-control" id="url" placeholder="请输入页面地址" name="url">
                </div>
                <br>
                <div class="input-group input-group-md">
                    <label class="sr-only" for="name">id</label>
                    <input type="text" class="form-control" id="name" placeholder="请输入爬取要素" name="id">
                </div>
                <br/>
                <button type="submit" class="btn btn-success">开始爬取</button>
            </form>
            <form class="form-inline" role="form" action="/xpath/getXpathList"  method="get">
                <button type="submit" class="btn btn-info">结果查询</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
