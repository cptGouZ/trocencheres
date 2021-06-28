<%@ page import="bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="fragments/header.jsp"%>

<!-- PARTIE RECHERCHE-->
<div class="input-group mb-3">
    <div class="col-auto">
        <p>Liste des encheres</p>
        <form action="${pageContext.request.contextPath}/accueilS" method="post">
            <input type="text" name="textechoix"  class="form-control" id="idtext2" value="" placeholder="Le nom de l'article contient"/>
            <!-- Import fragment Categorie-->
            <jsp:include page="fragments/categorie.jsp">
                <jsp:param name="libellesCategories" value="${libellesCategories}"/>
            </jsp:include>
            <!--Voir les informations Ventes et Achats si le profil est connecté-->
            <c:if test="${!empty sessionScope.get('userConnected')}">
            <div class="row mb-3">
                <div class="col-auto">
                    <p>Achats</p>
                    <input type="checkbox" name="ach1" id="c1"/>
                    <label for="c1">encheres ouvertes</label><br />
                    <input type="checkbox" name="ach2" id="c2"/>
                    <label for="c2">mes encheres en cours</label><br />
                    <input type="checkbox" name="ach3" id="c3"/>
                    <label for="c3">mes encheres remportees</label><br />
                </div>
                <div class="col-auto">
                    <p>Mes ventes</p>
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
        <%--<div class="col-auto">
            <b>En cas de recherche par mot clé, merci de choisir d'abord la catégorie de l'objet recherché :)</b>
        </div>--%>
    </div>
</div>

<%@ include file="fragments/footer.jsp"%>

