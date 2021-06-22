<%@ page import="bo.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="fragments/header.jsp"%>


<!-- PARTIE RECHERCHE-->
<div class="input-group mb-3">
    <div class="col-auto">
        <p>Liste des encheres</p>
        <form action="http://localhost:8080/trocencheres_war_exploded/accueil" method="post">
            <input type="text" name="nomtext"  class="form-control" id="idtext" value="" placeholder="Le nom de l'article contient"/>
            <label for="categorie">Categorie : </label>
            <select name="categorie" id="categorie">
                <option value="toutes">Toutes</option>
                <option value="informatique">Informatique</option>
                <option value="ameublement">Ameublement</option>
                <option value="vetement">Vetement</option>
                <option value="sport&loisirs">Sports&Loisirs</option>
            </select>
        </form>
        <div class="row mb-3">
            <div class="col-auto">
                <p>Achats</p>
                <input type="checkbox" name="achats" id="c1"/>
                <label for="c1">encheres ouvertes</label><br />
                <input type="checkbox" name="achats" id="c2"/>
                <label for="c2">mes encheres en cours</label><br />
                <input type="checkbox" name="achats" id="c3"/>
                <label for="c3">mes encheres remportees</label><br />
                <p>Mes ventes</p>
                <input type="checkbox" name="chk" id="c4"/>
                <label for="c4">mes ventes en cours</label><br />
                <input type="checkbox" name="chk" id="c5"/>
                <label for="c5">ventes non debutees</label><br />
                <input type="checkbox" name="chk" id="c6"/>
                <label for="c6">ventes terminees</label><br />
            </div>
            <div class="col-auto">
            </div>
        </div>
    </div>
    <div class="col-auto">
        <button class="btn btn-primary mb-3" type="submit">Rechercher</button>
    </div>
</div>

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
                <div>Objet : ${a.getArticle()}</div>
                <%
                    Article a = new Article();
                    request.getAttribute("article");
                    out.print("<div>Objet : " + a.getArticle() + "</div>");
                    request.getAttribute("montant");
                    out.print("<div>Prix : " + a.getPrixVente() + "</div>");
                    request.getAttribute("debut");
                    out.print("<div>Fin de l'enchere : " + a.getDateFin() + "</div>");
                    request.getAttribute("dateDebut");
                    out.print("<div>Vendeur : lien vers le vendeur</div>");
                %>
            </div>
                <div class="col-auto">
                    <div class="col-auto">
                    </div>
                </div>
            </div>
        </div>
<%--    <div class="col"> BOUCLE POUR REPETER LE FRAGMENT--%>
<%--        <div class="row">--%>
<%--            <div class="col">--%>
<%--                <div class="col">--%>
<%--                    <div class="col">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
</div>




<%@ include file="fragments/footer.jsp"%>

