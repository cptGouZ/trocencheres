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

    <script>
        function decocher(a,b,c) {
            document.getElementById(a).checked=false;
            document.getElementById(b).checked=false;
            document.getElementById(c).checked=false;
        }
    </script>

</head>
<body class="container">
    <%@ include file="background.jsp"%>
    <header class="row">
        <div class="col"><a href="${pageContext.request.contextPath}/deconnexion"><img src="<%=request.getContextPath()%>/images/logos.png" ></a></div>
        <div class="col"><h1>${param.get("titre")}</h1></div>
        <c:if test="${empty sessionScope.get('userConnected')}">
            <div class="col"><a href="${pageContext.request.contextPath}/connexion">Se connecter</a></div>
        </c:if>
        <c:if test="${!empty sessionScope.get('userConnected')}">
            <div class="col"><a href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a></div>
            <div class="col">
                <form method="post" action="${pageContext.request.contextPath}/profil">
                    <input type="hidden" name="userId" value=${userConnected.id}>
                    <button>Mon Profil</button>
                </form>
            </div>
            <div class="col"><a href="${pageContext.request.contextPath}/vente">Nouvelle vente</a></div>
        </c:if>

        <%--Message Création Article OK--%>
        ${empty messageErreur ? "" : "<p class=\"text-decoration-underline text-danger\">"+=messageErreur+="</p>"}
        ${empty messageConfirm ? "" : "<p class=\"text-decoration-underline text-success\">"+=messageConfirm+="</p>"}
    </header>

