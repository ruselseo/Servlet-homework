<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  tagdir=""%>--%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="name">Name:
        <input type="text" name="name" id="name">
    </label><br>
    <label for="emailId">Email:
        <input type="text" name="email" id="emailId">
    </label><br>
    <label>
        <input type="radio" name="gender" value="MALE">
    </label> MALE
    <label>
        <input type="radio" name="gender" value="FEMALE">
    </label> FEMALE
    <br>
    <label for="group">Группа:</label>
    <select id="group" name="group">
        <option value="1">101</option>
        <option value="2">102</option>
        <option value="3">103</option>
        <option value="4">201</option>
        <option value="5">202</option>
        <option value="6">203</option>
        <option value="7">301</option>
        <option value="8">302</option>
        <option value="9">303</option>
    </select>
    <br>
    <label for="class">Предмет:</label>
    <select id="class" name="class">
        <option value="1">MATH</option>
        <option value="2">SOCIOLOGY</option>
        <option value="3">ENGLISH</option>
        <option value="4">GYM</option>
    </select>
    <br>
    <button type="submit">Send</button>

</form>
</body>
</html>