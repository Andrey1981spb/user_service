<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="user" class="ru.spb.dreamwhite.model.User" scope="session"/>
    <title>Тестовая страница: профиль</title>
</head>
<body>

<form method="post" action="user" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${user.id}">
    <dl>
        <dt>Введите тестовые данные: email:</dt>
        <dd><input type="text" name="email" size=50 value="${user.email}"></dd>
    </dl>

    <button type="submit">Сохранить</button>
</form>

</body>
</html>
