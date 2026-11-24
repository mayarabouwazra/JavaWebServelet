<%@ page import="isep.lab1.bean.Person" %>

<jsp:useBean id="person"
            class="isep.lab1.bean.Person"
            scope="request" />

<jsp:setProperty name="person"
                property="firstName"
                param="firstName" />

<jsp:setProperty name="person"
                property="lastName"
            param="lastName" />

<jsp:setProperty name="person"
            property="birthDay"
                param="birthDay" />

<jsp:setProperty name="person"
                property="sex"
                param="sex" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Person Information</title>
</head>
<body>

    <h1>Person Information</h1>

    <table border="1" cellpadding="10">
        <tr>
            <th>First Name</th>
            <td><jsp:getProperty name="person" property="firstName"/></td>
        </tr>

        <tr>
            <th>Last Name</th>
            <td><jsp:getProperty name="person" property="lastName"/></td>
        </tr>

        <tr>
            <th>Birth Day</th>
            <td><jsp:getProperty name="person" property="birthDay"/></td>
        </tr>

        <tr>
            <th>Sex</th>
            <td><jsp:getProperty name="person" property="sex"/></td>
        </tr>
    </table>

</body>
</html>
