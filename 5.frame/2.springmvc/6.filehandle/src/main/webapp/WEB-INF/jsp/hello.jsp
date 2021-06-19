<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}
文件上传 1
<form action="/upload" enctype="multipart/form-data" method="post">
    <input type="file" name="file"/>
    <input type="submit" value="upload">
</form>
文件上传 2
<form action="/upload2" enctype="multipart/form-data" method="post">
    <input type="file" name="file"/>
    <input type="submit" value="upload">
</form>
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
