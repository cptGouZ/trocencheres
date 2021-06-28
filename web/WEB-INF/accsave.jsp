<%@ page import="bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="bo.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="fragments/header.jsp"%>


<!-- PARTIE RECHERCHE-->
<%--Voir les informations Ventes et Achats si le profil est connecté--%>
<c:if test="${!empty sessionScope.get('userConnected')}">
    <div class="input-group mb-3">
        <div class="col-auto">
            <p>Liste des encheres</p>
            <form action="http://localhost:8080/trocencheres_war_exploded/accueilS" method="post">
                <input type="text" name="textechoix"  class="form-control" id="idtext" value="" placeholder="Le nom de l'article contient"/>
                <label for="categorie">Categorie : </label>
                <select name="categorie" id="categorie">
                    <option value="toutes">toutes</option>
                    <!--On boucle pour inclure la liste des categories-->
                    <c:forEach items="${libellesCategories}" var="libelleCategorie">
                        <option value="${libelleCategorie}">${libelleCategorie}</option>
                    </c:forEach>
                </select>
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
                <div>
                    <button class="btn btn-primary mb-3" type="submit">Rechercher</button>
                </div>
            </form>
        </div>
    </div>
</c:if>

<%--Ne pas voir les informations Ventes et Achats si le profil n'est pas connecté--%>
<c:if test="${empty sessionScope.get('userConnected')}">
    <div class="input-group mb-3">
        <div class="col-auto">
            <p>Liste des encheres</p>
            <form action="http://localhost:8080/trocencheres_war_exploded/accueilS" method="post">
                <input type="text" name="textechoix"  class="form-control" id="idtext2" value="" placeholder="Le nom de l'article contient"/>
                <!-- Import fragment Categorie-->
                <jsp:include page="fragments/categorie.jsp">
                    <jsp:param name="libellesCategories" value="${libellesCategories}"/>
                </jsp:include>

                <button class="btn btn-primary mb-3" type="submit">Rechercher</button>
            </form>
        </div>
    </div>
</c:if>


<!-- PARTIE RESULTATS-->
<%--Pas de lien vers profil vendeur si le profil est déconnecté--%>
<c:if test="${empty sessionScope.get('userConnected')}">
    <div class="row mb-3">
        <div class="col-auto">
            <div class="row">
                <div class="col-auto">
                    <%
                        List<Article> articleList2 = (List<Article>) request.getAttribute("listedesarticles");
                        for (Article item : articleList2) {
                            out.print("<div>Objet : " + item.getArticle() + "</div>");
                            out.print("<div>Prix : " + item.getPrixVente() + " points</div>");
                            out.print("<div>Fin de l'enchere : " + item.getDateFin() + "</div>");
                            out.print("<div>Vendeur : " + item.getUtilisateur().getPseudo() + "</div></br>");
                        }
                    %>
                </div>
                <b><c:if test="${empty sessionScope.get('userConnected')}">
                    <div class="col-auto">
                                <%
                   out.print("En cas de recherche par mot clé, merci de choisir d'abord la catégorie de l'objet recherché :)");
                %>
                </b></div>
            </c:if>
        </div>
    </div>
    </div>
</c:if>

<%--Lien vers profil vendeur si le profil est connecté--%>
<c:if test="${!empty sessionScope.get('userConnected')}">
    <div class="row mb-3">
        <div class="col-auto">
            <div class="row">
                <div class="col-auto">
                    <%
                        List<Article> articleList3 = (List<Article>) request.getAttribute("listedesarticles");
                        for (Article item : articleList3) {
                            out.print("<div>Objet : " + item.getArticle() + "</div>");
                            out.print("<div>Prix : " + item.getPrixVente() + " points</div>");
                            out.print("<div>Fin de l'enchere : " + item.getDateFin() + "</div>");
                            out.print("<div>Vendeur : " + item.getUtilisateur().getPseudo() + "</div>");
                    %>
                    <div class="col"><a href="${pageContext.request.contextPath}/profil?userId=<%=item.getUtilisateur().getId()%>">InfosVendeur</a></div></br>
                    <% }
                    %>
                </div>
                <b><c:if test="${!empty sessionScope.get('userConnected')}">
                    <div class="col-auto">
                                <%
                        out.print("En cas de recherche par mot clé, merci de choisir d'abord la catégorie de l'objet recherché :)");
                    %>
                </b></div>
            </c:if>
        </div>
    </div>
    </div>
</c:if>

<%@ include file="fragments/footer.jsp"%>

