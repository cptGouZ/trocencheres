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
                        <input type="radio" name="acha" id="a" checked/>
                        <label for="a">Achats</label><br />
                        <input type="checkbox" name="ach1" id="c1" />
                        <label for="c1">encheres ouvertes</label><br />
                        <input type="checkbox" name="ach2" id="c2" />
                        <label for="c2">mes encheres en cours</label><br />
                        <input type="checkbox" name="ach3" id="c3" />
                        <label for="c3">mes encheres remportees</label><br />
                    </div>
                    <div class="col-6 col-lg-3">
                        <input type="radio" name="acha" id="v"/>
                        <label for="v">Ventes</label><br />
                        <input type="checkbox" name="ven1" id="c4" />
                        <label for="c4">mes ventes en cours</label><br />
                        <input type="checkbox" name="ven2" id="c5" />
                        <label for="c5">ventes non debutees</label><br />
                        <input type="checkbox" name="ven3" id="c6" />
                        <label for="c6">ventes terminees</label><br />
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

