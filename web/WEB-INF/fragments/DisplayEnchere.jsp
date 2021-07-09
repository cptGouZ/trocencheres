<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- PARTIE RESULTATS-->
<div class="col-12">
    <div class="row justify-content-center">
        <c:forEach var="item" items="${listedesarticles}">
            <fmt:parseDate value="${ item.dateDebut }" pattern="yyyy-MM-dd'T'HH:mm" var="dateDebut" type="both" />
            <fmt:parseDate value="${ item.dateFin }" pattern="yyyy-MM-dd'T'HH:mm" var="dateFin" type="both" />

<%--            <fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value = "${item.dateDebut}" />--%>
            <div class="col-11 col-sm-9 col-md-7 col-lg-6 col-xl-4 m-3 border border-2 rounded rounded-5 border-secondary">
                <div class="row align-items-center">
                    <div class="col-2">
                        <img alt="photo de ${item.article}" width="70" height="50" src="https://images-na.ssl-images-amazon.com/images/I/61pBhllH4PL._AC_SL1200_.jpg">
                    </div>
                    <div class="col-10">
                        <div class="container-fluid">
                            <div>Objet : ${item.article}</div>
                            <div>Prix initial : ${item.prixInitiale} points</div>
                            <div>Prix en cours : ${item.prixVente} points</div>
                            <div>Debut enchere : <fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "short" value="${ dateDebut }"/></div>
                            <div>Fin enchere : <fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "short" value="${ dateFin }"/></div>
                            <div>Vendeur : ${item.utilisateur.pseudo}</div>

                            <%--Pouvoir afficher un article et ses enchères--%>
                            <div class="row">
                                <c:if test="${!empty sessionScope.get('luid')}">
                                    <form class="col" method="post" action="${pageContext.request.contextPath}/afficherenchere">
                                        <input type="hidden" name="idArticle" value="${item.id}">
                                        <button class="btn btn-dark">Afficher/Encherir</button>
                                    </form>
                                    <a class="btn btn-dark" href="${pageContext.request.contextPath}/profil?uid=${item.utilisateur.id}">InfosVendeur</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
