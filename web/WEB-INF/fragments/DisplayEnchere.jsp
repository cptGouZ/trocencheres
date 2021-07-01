<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- PARTIE RESULTATS-->
<div class="col-12">
    <div class="row">
        <c:forEach var="item" items="${listedesarticles}">
            <div class="col-12 col-lg-5 col-xl-4 my-3">
                <div class="row align-items-center">
                    <div class="col-3">
                        <img alt="photo ordinateur" width="70" height="50" src="https://images-na.ssl-images-amazon.com/images/I/61pBhllH4PL._AC_SL1200_.jpg">
                    </div>
                    <div class="col-9">
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
                </div>
            </div>
        </c:forEach>
    </div>
</div>
