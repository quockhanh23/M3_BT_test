<%--
  Created by IntelliJ IDEA.
  User: Asus VivoBook
  Date: 12/8/2021
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>List</h2>
<c:forEach var="i" begin="0" end="${alo.size()-1}">
  <h2> ${alo.get(i).id} ${alo.get(i).name} ${alo.get(i).age} ${alo1.get(i).name}</h2>
</c:forEach>
</body>
</html>
