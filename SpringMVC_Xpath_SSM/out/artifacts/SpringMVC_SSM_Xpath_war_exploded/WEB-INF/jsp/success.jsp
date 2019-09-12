<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta  charset="UTF-8"/>
    <title>爬虫结果页面</title>
    <!-- 引入bootstrap样式 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入bootstrap-table样式 -->
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">

    <!-- jquery -->
    <script src="https://cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <!-- bootstrap-table.min.js -->
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <!-- 引入中文语言包 -->
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
    <style>
        .table thead tr th {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<table id="table" data-toggle="table" data-url="data1.json">
    <thead>
    <tr>
        <th data-field="type">类型</th>
        <th data-field="name">名称</th>
        <th data-field="xpath">Xpath</th>
    </tr>
    <c:forEach items="${list}" var="list">
        <tr>
            <th data-field="id">${list.type}</th>
            <th data-field="name">${list.name}</th>
            <th data-field="price">${list.xpath}</th>
        </tr>
    </c:forEach>
    </thead>

</table>

<table id="mytable"></table>
</body>
</html>
