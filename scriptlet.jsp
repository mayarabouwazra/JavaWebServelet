<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Scriptlet</title>
</head>
<body>

    <h1>Server Information</h1>

    <%
        Date currentTime = new Date();
        String host = request.getRemoteHost();
    %>

    <p>Current time: <%= currentTime %></p>

    <p>Remote host: <%= host %></p>

</body>
</html>