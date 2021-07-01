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
<div class="row justify-content-center">
    <div class="col">
        <form action="${pageContext.request.contextPath}/accueil" method="post">
            <div class="row justify-content-center">
                <div class="col-8">
                    <%-- Recherche par nom d'article --%>
                    <div class="row justify-content-center">
                        <div class="col">
                            <input type="text" name="textechoix"  class="form-control" id="idtext2" value="" placeholder="Le nom de l'article contient"/>
                        </div>
                    </div>
                    <!-- Import fragment Categorie-->
                    <div class="row justify-content-center">
                        <div class="col">
                            <jsp:include page="fragments/categorie.jsp">
                                <jsp:param name="listeCategories" value="${listeCategories}"/>
                            </jsp:include>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <!--Affichage du bouton de recherche-->
                    <button class="btn btn-primary mb-3" type="submit">Rechercher</button>
                </div>
            </div>
            <!--Voir les informations Ventes et Achats si le profil est connecté-->
            <c:if test="${!empty sessionScope.get('userConnected')}">
                <div class="row justify-content-center">
                    <div class="col-6 col-lg-3">
                        <input type="radio" name="achavent" id="acha" onclick='degriser("c1","c2","c3")'/>
                        <label for="acha">Achats</label><br />
                        <input type="checkbox" name="ach1" id="c1" disabled />
                        <label for="c1">Toutes les enchères ouvertes</label><br />
                        <input type="checkbox" name="ach2" id="c2" disabled />
                        <label for="c2">Enchères sur lesquelles j'ai misé</label><br />
                        <input type="checkbox" name="ach3" id="c3" disabled />
                        <label for="c3">Enchères gagnées</label><br />
                    </div>
                    <div class="col-6 col-lg-3">
                        <input type="radio" name="achavent" id="vent" onclick='degriser("c4","c5","c6")'/>
                        <label for="vent">Ventes</label><br />
                        <input type="checkbox" name="ven1" id="c4" disabled/>
                        <label for="c4">Mes articles en cours de vente</label><br />
                        <input type="checkbox" name="ven2" id="c5" disabled/>
                        <label for="c5">Mes articles en attente de vente</label><br />
                        <input type="checkbox" name="ven3" id="c6" disabled/>
                        <label for="c6">Mes ventes terminees</label><br />
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

