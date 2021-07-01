<%@ page import="bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--Données pous JSTL et Charset-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Accueil"/>
    <jsp:param name="messageErreur" value="${messageErreur}"/>
    <jsp:param name="messageConfirm" value="${messageConfirm}"/>
</jsp:include>

<!-- PARTIE RECHERCHE-->
<div class="row justify-content-center my-4">
    <div class="col-12 col-md-8 col-lg-6">
        <form action="${pageContext.request.contextPath}/accueil" method="post">
            <div class="row align-items-center mx-1">
                <div class="col px-0">
                    <%--Choix article et catégorie--%>
                    <input type="text" name="textechoix"  class="form-control" id="idtext2" value="" placeholder="Le nom de l'article contient"/>
                    <jsp:include page="fragments/categorie.jsp">
                        <jsp:param name="listeCategories" value="${listeCategories}"/>
                    </jsp:include>
                </div>
                <!--Affichage du bouton de recherche-->
                <button class="col-12 col-md-3 mx-md-2 btn btn-primary" type="submit">Rechercher</button>
            </div>
            <!--Voir les informations Ventes et Achats si le profil est connecté-->
            <c:if test="${!empty sessionScope.get('userConnected')}">
                <div class="row justify-content-center">
                    <div class="col mx-1">
                        <input type="radio" name="acha" id="a" checked/>
                        <label for="a">Achats</label>
                        <br />
                        <input type="checkbox" name="ach1" id="c1" />
                        <label for="c1">Toutes les enchères ouvertes</label>
                        <br />
                        <input type="checkbox" name="ach2" id="c2" />
                        <label for="c2">Enchères sur lesquelles j'ai misé</label>
                        <br />
                        <input type="checkbox" name="ach3" id="c3" />
                        <label for="c3">Enchères gagnées</label>
                    </div>
                    <div class="col mx-1">
                        <input type="radio" name="acha" id="v"/>
                        <label for="v">Ventes</label>
                        <br />
                        <input type="checkbox" name="ven1" id="c4" />
                        <label for="c4">Mes articles en cours de vente</label>
                        <br />
                        <input type="checkbox" name="ven2" id="c5" />
                        <label for="c5">Mes articles en attente de vente</label>
                        <br />
                        <input type="checkbox" name="ven3" id="c6"/>
                        <label for="c6">Mes ventes terminees</label>
                    </div>
                </div>
            </c:if>
        </form>
    </div>
</div>
<jsp:include page="fragments/DisplayEnchere.jsp">
    <jsp:param name="listedesarticles" value="${listedesarticles}"/>
</jsp:include>

<%@ include file="fragments/footer.jsp"%>

