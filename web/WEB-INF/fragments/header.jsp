<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <title>Troc Enchères</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body class="container">
    <header class="row">
        <div class="col">ENI-Enchères</div>
        <div class="col">${title}</div>
        <c:if test="${empty sessionScope.get('userConnected')}">
            <div class="col"><a href="${pageContext.request.contextPath}/connexion">Se connecter</a></div>
        </c:if>
        <c:if test="${!empty sessionScope.get('userConnected')}">
            <div class="col"><a href="${pageContext.request.contextPath}/deconnexion?seDeconnecter=true">Déconnexion</a></div>
            <div class="col"><a href="${pageContext.request.contextPath}/profil?userId=${userConnected.id}">Mon Profil</a></div>
            <div class="col">Enchère</div>
            <div class="col"><a href="${pageContext.request.contextPath}/vente">Nouvelle vente</a></div>
        </c:if>
    </header>

