<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <title>TrocEnchère</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


</head>
<body class="container-fluid">
    <%@ include file="background.jsp"%>
    <header class="row">
        <nav class="navbar navbar-dark bg-dark rounded">
            <div class="container-fluid">
                <div class="col-md-center"><a href="${pageContext.request.contextPath}/deconnexion"><img class="rounded" src="<%=request.getContextPath()%>/images/logos.png" ></a></div>

                <div class="col-md-center text-white"><h1>${param.get("titre")}</h1></div>
                    <c:if test="${empty sessionScope.get('userConnected')}">
                        <a class="navbar-brand btn btn-dark" href="${pageContext.request.contextPath}/connexion">Se connecter</a>
                    </c:if>

                    <c:if test="${!empty sessionScope.get('userConnected')}">

                        <div class="col-md-center"><a class="navbar-brand btn btn-dark" href="${pageContext.request.contextPath}/vente">Nouvelle vente</a></div>
                        <div class="col-md-center">
                            <form method="post" class="my-0" action="${pageContext.request.contextPath}/profil">
                                <input type="hidden" name="userId" value=${userConnected.id}>
                                <button class="navbar-brand btn btn-dark">Mon Profil</button>
                            </form>
                        </div>
                        <div class="col-md-center">
                            <a class="navbar-brand btn btn-dark" href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
                        </div>
                    </c:if>
            </div>
        </nav>

        <%--Message Création Article OK--%>
        ${empty messageErreur ? "" : "<p class=\"text-decoration-underline text-danger\">"+=messageErreur+="</p>"}
        ${empty messageConfirm ? "" : "<p class=\"text-decoration-underline text-success\">"+=messageConfirm+="</p>"}
    </header>

