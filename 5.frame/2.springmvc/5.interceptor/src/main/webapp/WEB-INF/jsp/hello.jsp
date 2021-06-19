<%--
  Created by IntelliJ IDEA.
  User: railgun
  Date: 2021/6/16
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}
</body>
<script type="text/javascript">
    let user = {id: '89', name: '超电磁炮'};
    console.log(user);
    console.log('---------- 分割线 ----------');
    let json = JSON.stringify(user);
    console.log(json);
    console.log('---------- 分割线 ----------');
    let demo = JSON.parse(json);
    console.log(demo);
    console.log('---------- 分割线 ----------');
</script>
</html>
