<%@ page import="bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--Données pous JSTL et Charset-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="titre" value="Accueil"/>
</jsp:include>

<!-- PARTIE RECHERCHE-->
<div class="row">
    <div class="col-12 col-md-6 col-lg-3">
        <form action="${pageContext.request.contextPath}/accueilS" method="post">
            <input type="text" name="textechoix"  class="form-control" id="idtext2" value="" placeholder="Le nom de l'article contient"/>
            <!-- Import fragment Categorie-->
            <jsp:include page="fragments/categorie.jsp">
                <jsp:param name="libellesCategories" value="${libellesCategories}"/>
            </jsp:include>
            <!--Voir les informations Ventes et Achats si le profil est connecté-->
            <c:if test="${!empty sessionScope.get('userConnected')}">
            <div class="row mb-3">
                <div class="border border-dark">
                    <input type="radio" name="acha" id="a" checked/>
                    <label for="a">Achats</label><br />
                    <input type="checkbox" name="ach1" id="c1"/>
                    <label for="c1">encheres ouvertes</label><br />
                    <input type="checkbox" name="ach2" id="c2"/>
                    <label for="c2">mes encheres en cours</label><br />
                    <input type="checkbox" name="ach3" id="c3"/>
                    <label for="c3">mes encheres remportees</label><br />
                </div>
                <div class="border border-dark">
                    <input type="radio" name="acha" id="v"/>
                    <label for="v">Ventes</label><br />
                    <input type="checkbox" name="ven1" id="c4"/>
                    <label for="c4">mes ventes en cours</label><br />
                    <input type="checkbox" name="ven2" id="c5"/>
                    <label for="c5">ventes non debutees</label><br />
                    <input type="checkbox" name="ven3" id="c6"/>
                    <label for="c6">ventes terminees</label><br />
                </div>
            </div>
            </c:if>
            <!--Affichage du bouton de recherche-->
            <button class="btn btn-primary mb-3" type="submit">Rechercher</button>
        </form>
    </div>
</div>


<!-- PARTIE RESULTATS-->
<div class="row mb-3">
    <div class="col-auto">
        <div class="row">
            <div class="col-auto">
                <%
                    List<Article> articleList2 = (List<Article>) request.getAttribute("listedesarticles");
                    for (Article item : articleList2) {
                        out.print("<div class=\"col-auto\">");
                        out.print("<div>Objet : " + item.getArticle() + "</div>");
                        out.print("<div>Prix : " + item.getPrixVente() + " points</div>");
                        out.print("<div>Fin de l'enchere : " + item.getDateFin() + "</div>");
                        out.print("<div>Vendeur : " + item.getUtilisateur().getPseudo() + "</div><br>");
                %>
                <%--Pouvoir afficher un article et ses enchères--%>
                <c:if test="${!empty sessionScope.get('userConnected')}">
                    <div class="col-xs-12 col-md-6">
                        <form method="post" action="${pageContext.request.contextPath}/afficherenchere">
                            <input type="hidden" name="idArticle" value="${item.id}">
                            <button class="btn btn-success mb-3">Afficher</button>
                        </form>
                    </div>
                </c:if>
                <%--Pouvoir encherir sur un article--%>
                <c:if test="${!empty sessionScope.get('userConnected')}">
                    <div class="col-xs-12 col-md-6">
                        <form method="post" action="${pageContext.request.contextPath}/encherir">
                            <input type="hidden" name="idArticle" value="${item.id}">
                            <button class="btn btn-success mb-3">Encherir</button>
                        </form>
                    </div>
                </c:if>
                <%--Lien vers profil vendeur si le profil est connecté--%>
                <c:if test="${!empty sessionScope.get('userConnected')}">
                    <div class="col-auto">
                        <a href="${pageContext.request.contextPath}/profil?userId=<%=item.getUtilisateur().getId()%>">InfosVendeur</a>
                    </div>
                </c:if>
        </div>
            <%
                }
            %>
    </div>
</div>

<%@ include file="fragments/footer.jsp"%>

