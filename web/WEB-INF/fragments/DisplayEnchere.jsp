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
            <div class="col-8 col-lg-5 col-xl-4 m-3 border border-2 rounded rounded-5 border-secondary">
                <div class="row align-items-center">
                    <div class="col-3">
                        <img alt="photo ordinateur" width="70" height="50" src="https://images-na.ssl-images-amazon.com/images/I/61pBhllH4PL._AC_SL1200_.jpg">
                    </div>
                    <div class="col-9">
                        <div class="container-fluid">
                            <div>Objet : ${item.article}</div>
                            <div>Prix initial : ${item.prixInitiale} points</div>
                            <div>Prix en cours : ${item.prixVente} points</div>
                            <div>Debut enchere : <fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "short" value="${ dateDebut }"/></div>
                            <div>Fin enchere : <fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "short" value="${ dateFin }"/></div>
                            <div>Vendeur : ${item.utilisateur.pseudo}</div>

                            <%--Pouvoir afficher un article et ses enchères--%>
                            <c:if test="${!empty sessionScope.get('userConnected')}">
                                <form method="post" action="${pageContext.request.contextPath}/afficherenchere">
                                    <input type="hidden" name="idArticle" value="${item.id}">
                                    <button class="btn btn-dark">Afficher/Encherir</button>
                                </form>
                            </c:if>
                            <%--Lien vers profil vendeur si le profil est connecté--%>
                            <c:if test="${!empty sessionScope.get('userConnected')}">
                                <form method="post" action="${pageContext.request.contextPath}/profil">
                                    <input type="hidden" name="userId" value="${item.utilisateur.id}"/>
                                    <button class="btn btn-dark">InfosVendeur</button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
