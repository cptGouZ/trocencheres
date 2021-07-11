<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="fr">
<head>
    <title>TrocEnchère</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Zen+Loop&display=swap" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script>
        function degriser(a,b,c) {
            for (let i = 1; i <= 6; i++) {
                document.getElementById('c' + i).checked=false;
                document.getElementById('c' + i).disabled=true;
            }
            document.getElementById(a).disabled=false;
            document.getElementById(b).disabled=false;
            document.getElementById(c).disabled=false;}
    </script>
    <%@ include file="background.jsp"%>
</head>
<body class="container-fluid">
    <header class="row">
        <nav class="navbar navbar-dark bg-dark rounded">
            <div class="col">
                <a href="${pageContext.request.contextPath}/accueil">
                    <img class="rounded" alt="logo eni" src="${pageContext.request.contextPath}/images/logos.png"/>
                </a>
            </div>
            <div class="col text-white text-center">
                <h1>${param.get("titre")}</h1>
                ${sessionScope.get("pseudo")}
                ${sessionScope.get("creditdispo")} cr
            </div>
            <div class="col-right">
                <c:if test="${empty sessionScope.get('luid')}">
                    <a class="navbar-brand btn btn-dark" href="${pageContext.request.contextPath}/connexion">Se connecter</a>
                </c:if>

                <c:if test="${!empty sessionScope.get('luid')}">
                    <a class="btn btn-dark" href="${pageContext.request.contextPath}/vente">Nouvelle vente</a></div>
                    <a class="btn btn-dark" href="${pageContext.request.contextPath}/profil?uid=${sessionScope.get('luid')}">Mon Profil</a>
                    <a class="btn btn-dark" href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
                </c:if>
            </div>
        </nav>
        <%--Message Création Article OK--%>
        ${empty messageErreur ? "" : "<p class=\"alert alert-danger text-center\">"+=messageErreur+="</p>"}
        ${empty messageConfirm ? "" : "<p class=\"text-decoration-underline text-success\">"+=messageConfirm+="</p>"}
    </header>