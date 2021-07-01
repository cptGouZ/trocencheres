<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- PARTIE RESULTATS-->
<div class="col-12">
    <div class="row">
        <c:forEach var="item" items="${listedesarticles}">
            <div class="col-12 col-md-6 col-lg-4 col-xl-3">
                <div>Objet : ${item.article}</div>
                <div>Prix initial : ${item.prixInitiale} points</div>
                <div>Prix en cours : ${item.prixVente} points</div>
                <div>Debut des encheres : ${item.dateDebut}</div>
                <div>Fin de l'enchere : ${item.dateFin}</div>
                <div>Vendeur : ${item.utilisateur.pseudo}</div>

                <%--Pouvoir afficher un article et ses enchères--%>
                <c:if test="${!empty sessionScope.get('userConnected')}">
                    <form method="post" action="${pageContext.request.contextPath}/afficherenchere">
                        <input type="hidden" name="idArticle" value="${item.id}">
                        <button class="btn btn-success mb-3">Afficher/Encherir</button>
                    </form>
                </c:if>
                <%--Lien vers profil vendeur si le profil est connecté--%>
                <c:if test="${!empty sessionScope.get('userConnected')}">
                    <form method="post" action="${pageContext.request.contextPath}/profil">
                        <input type="hidden" name="userId" value="${item.utilisateur.id}"/>
                        <button>InfosVendeur</button>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
