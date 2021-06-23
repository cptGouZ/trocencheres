<%@ page import="bo.Article" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="fragments/header.jsp"%>


<!-- PARTIE RECHERCHE-->
<%--Voir les informations Ventes et Achats si le profil est connecté--%>
<c:if test="${empty sessionScope.user}">
    <div class="input-group mb-3">
        <div class="col-auto">
            <p>Liste des encheres</p>
            <form action="http://localhost:8080/trocencheres_war_exploded/accueilS" method="post">
                <input type="text" name="texteart"  class="form-control" id="idtext" value="" placeholder="Le nom de l'article contient"/>
                <label for="categorie">Categorie : </label>
                <select name="categorie" id="categorie">
                    <option value="toutes">Toutes</option>
                    <option value="informatique">Informatique</option>
                    <option value="ameublement">Ameublement</option>
                    <option value="vetement">Vetement</option>
                    <option value="sport&loisirs">Sports&Loisirs</option>
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
<c:if test="${!empty sessionScope.user}">
    <div class="input-group mb-3">
        <div class="col-auto">
            <p>Liste des encheres</p>
            <form action="http://localhost:8080/trocencheres_war_exploded/accueilS" method="post">
                <input type="text" name="nomtext"  class="form-control" id="idtext2" value="" placeholder="Le nom de l'article contient"/>
                <label for="categorie">Categorie : </label>
                <select name="categorie" id="categorie2">
                    <option value="toutes">Toutes</option>
                    <option value="informatique">Informatique</option>
                    <option value="ameublement">Ameublement</option>
                    <option value="vetement">Vetement</option>
                    <option value="sport&loisirs">Sports&Loisirs</option>
                </select>
                <button class="btn btn-primary mb-3" type="submit">Rechercher</button>
            </form>
        </div>
    </div>
</c:if>


<!-- PARTIE RESULTATS-->
<div class="row mb-3">
    <div class="col-auto">
        <div class="row">
            <div class="col-auto">
                <%
                    out.print("<div>lien vers photo ordi</div>");
                %>
            </div>
            <div class="col-auto">
                <!--<div>Objet : ${a.getArticle()}</div>-->
                <%
                    List<Article> articleList2 = (List<Article>) request.getAttribute("listedesarticles");
                    //request.getAttribute("objet1");
                    for (Article item : articleList2) {
                        out.print("<div>Objet : " + item.getArticle() + "</div>");
                        request.getAttribute("montant");
                        out.print("<div>Prix : " + item.getPrixVente() + " points</div>");
                        request.getAttribute("debut");
                        out.print("<div>Fin de l'enchere : " + item.getDateFin() + "</div>");
                        request.getAttribute("dateDebut");
                        out.print("<div>Vendeur : lien vers le vendeur</div><br/>");
                    }
                %>
            </div>
                <div class="col-auto">
                    <div class="col-auto">
                    </div>
                </div>
            </div>
        </div>
</div>


<%@ include file="fragments/footer.jsp"%>

